package modelo;

import excepciones.JugadorSancionadoException;
import excepciones.PresupuestoExcedidoException;
import excepciones.RolNoDisponibleException; // Añadido: Import de la nueva excepción
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Un equipo gestiona fichajes de jugadores, valida sus roles. Calcula estadisticas como winrate y puntos, y gestiona las convocatorias de partidos
   Se Compone de:
   - 1 Entrenador
   - 5 Jugadores titulares
   - Un número no definido de Jugadores suplentes
*/
public class Equipo {
    private String nombre;
    private String ciudad;
    private Entrenador entrenador;
    private Jugador[] titulares = new Jugador[5];
    private List<Jugador> suplentes = new ArrayList<>();
    private Double presupuesto;
    private Integer victorias = 0;
    private Integer derrotas = 0;
    private Integer puntosAFavor = 0;
    private Integer puntosEnContra = 0;

    public Equipo(String nombre, String ciudad, Double presupuesto) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.presupuesto = presupuesto;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getCiudad() { return ciudad; }
    public Entrenador getEntrenador() { return entrenador; }
    public Jugador[] getTitulares() { return titulares; }
    public List<Jugador> getSuplentes() { return suplentes; }
    public Double getPresupuesto() { return presupuesto; }
    public Integer getVictorias() { return victorias; }
    public Integer getDerrotas() { return derrotas; }
    public Integer getPuntosAFavor() { return puntosAFavor; }
    public Integer getPuntosEnContra() { return puntosEnContra; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    // Validacion de presupuesto para fichar a un entrenador antes de ficharlo, si el presupuesto no es suficiente, se lanza una excepción y se muestra un mensaje de error
    public void setEntrenador(Entrenador entrenador) {
        try {
            validarPresupuesto(entrenador);
            this.entrenador = entrenador;
        } catch (PresupuestoExcedidoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setPresupuesto(Double presupuesto) { this.presupuesto = presupuesto; }
    public void setVictorias(Integer victorias) { this.victorias = victorias; }
    public void setDerrotas(Integer derrotas) { this.derrotas = derrotas; }
    public void setPuntosAFavor(Integer puntosAFavor) { this.puntosAFavor = puntosAFavor; }
    public void setPuntosEnContra(Integer puntosEnContra) { this.puntosEnContra = puntosEnContra; }


    // MÉTODOS DE GESTIÓN

    // Método privado auxiliar que valida si el equipo puede permitirse un nuevo fichaje. Suma el coste actual de la plantilla al coste de la nueva persona y lo compara con el presupuesto.
    private void validarPresupuesto(PersonaLiga persona) throws PresupuestoExcedidoException {
        Double costeActual = calcularCostePlantilla();
        Double nuevoCoste = persona.calcularCosteMensual();

        if ((costeActual + nuevoCoste) > presupuesto) {
            throw new PresupuestoExcedidoException( "¡OPERACIÓN DENEGADA! El presupuesto de " + nombre + " es de " + presupuesto + "€ " + "y el coste total tras el fichaje de " + persona.getNickname() + " sería de " + (costeActual + nuevoCoste) + "€.");
        }
    }

    // Método que valida titulares con mismo rol, busca un hueco en el array de titulares y lo llena con el jugador, si algo falla lanza la excepcion
    // Modificado: Añadido throws PresupuestoExcedidoException y la validación
    public void ficharTitular(Jugador jugador) throws RolNoDisponibleException, PresupuestoExcedidoException {
        // Valida el presupuesto antes de fichar al titular, si no se cumple lanza la excepción
        validarPresupuesto(jugador);

        // Verificar que el rol no esté ocupado
        for (int i = 0; i < titulares.length; i++) {
            // Si hay un jugador en esta posición Y tiene el mismo rol
            if (titulares[i] != null && titulares[i].getRol() == jugador.getRol()) {
                // Lanza la excepción personalizada del módulo excepciones
                throw new RolNoDisponibleException( "El rol " + jugador.getRol() + " ya está ocupado por " + titulares[i].getNickname() + ". No puede haber dos titulares con el mismo rol."
                );
            }
        }

        // Valida que haya espacios libres en el array de titulares, y busca una posición libre para colocar al jugador
        for (int i = 0; i < titulares.length; i++) {
            if (titulares[i] == null) {  // Posición libre encontrada
                titulares[i] = jugador;
                System.out.println(jugador.getNickname() + " fichado como titular [" +  jugador.getRol() + "] en " + nombre);
                return;  // Salir del método, ya está fichado
            }
        }

        // Si llegamos aquí, no hay espacios libres
        System.out.println("-- ERROR -- No hay espacios disponibles para titulares.");
        System.out.println("   Considera ficharlo como suplente o sustituir a un titular.");
    }

    // Fichar a un jugador pero para la lista de suplentes
    // Modificado: Añadido throws PresupuestoExcedidoException y la validación
    public void ficharSuplente(Jugador jugador) throws PresupuestoExcedidoException {
        // Valida el presupuesto antes de fichar al suplente, si no se cumple lanza la excepción
        validarPresupuesto(jugador);

        // Se añade a la lista, en este caso dinámica, porque es la de suplentes
        suplentes.add(jugador);
        System.out.println(jugador.getNickname() + " fichado como suplente [" +  jugador.getRol() + "] en " + nombre);
    }

    /* Sustituye a un jugador titular por uno suplente, se debe validar:
        -- El índice de posicion debe ser el correcto
        -- Debe existir un jugador previo en esa posiciñion
        -- El suplente debe ser del mismo Rol
        -- El suplente debe estar previamente registrado en la lista de suplentes
    */
    public void sustituirJugador(int indiceTitular, Jugador suplente) throws RolNoDisponibleException {
        // Valida que el indice sea válido
        if (indiceTitular < 0 || indiceTitular >= titulares.length) {
            throw new IllegalArgumentException("Índice inválido. Debe estar entre 0 y " + (titulares.length - 1));
        }

        // Valida que haya un titular en esa posición
        Jugador titular = titulares[indiceTitular];
        if (titular == null) {
            throw new IllegalArgumentException("No hay ningún titular en la posición " + indiceTitular);
        }

        // Valida que el suplente tenga el mismo rol que el titulares
        if (titular.getRol() != suplente.getRol()) {
            // He formateado el mensaje en bloque, creo que se ve mejor así
            throw new RolNoDisponibleException("""
                                               El suplente debe tener el mismo rol que el titular saliente.
                                               Titular: """ + titular.getRol() + " | Suplente: " + suplente.getRol());
        }

        // Valida que el suplente pertenezca a la lista de suplentes del equipo, si no es así lanza una excepción
        if (!suplentes.contains(suplente)) {
            throw new IllegalArgumentException("El suplente " + suplente.getNickname() + " no es suplente de este equipo");
        }

        // REALIZAR LA SUSTITUCIÓN
        // El actual titular pasa a banco de suplentes
        titulares[indiceTitular] = suplente;

        // Quita al suplente de la lista de suplentes
        suplentes.remove(suplente);

        // Añade al ex-titular a la lista de suplentes
        suplentes.add(titular);

        System.out.println("Sustitución realizada:");
        System.out.println("   SALE: " + titular.getNickname() + " (ahora suplente)");
        System.out.println("   ENTRA: " + suplente.getNickname() + " (ahora titular)");
    }

    // Método que elimina a un jugador de la plantilla, sea titular o suplente
    public void eliminarJugador(Jugador jugador) {
        // Busca en la lista de titulares
        for (int i = 0; i < titulares.length; i++) {
            if (titulares[i] != null && titulares[i].equals(jugador)) {
                titulares[i] = null;  // Libera la posición y se confirma con un mensaje
                System.out.println(jugador.getNickname() + " eliminado de titulares");
                return;
            }
        }

        // Buscar en suplentes
        if (suplentes.remove(jugador)) {  // remove devuelve true si lo encontró y eliminó
            System.out.println(jugador.getNickname() + " eliminado de suplentes");
        } else {
            System.out.println(jugador.getNickname() + " no pertenece a " + nombre);
        }
    }
    // Añade un jugador a la lista dinámica de suplentes
    public void añadirSuplente(Jugador jugador) { this.suplentes.add(jugador); }

    // Elimina un jugador de la lista dinámica de suplentes
    public void eliminarSuplente(Jugador jugador) {
        this.suplentes.remove(jugador);
    }

    // Método que nos permite calcular el coste total de la plantilla (entrenador + titulares + suplentes)
    public Double calcularCostePlantilla() {
        Double total = 0.0; // Inicializo la variable a 0
        // Si existe un entrenador, al total le sumo su coste llamando al metodo calcularCosteMensual de la clase Entrenador
        if (entrenador != null) {
            total += entrenador.calcularCosteMensual();
        }
        // Recorro el array de titulares con un for each y si el jugador no es null, al total le sumo su coste llamando al metodo calcularCosteMensual de la clase Jugador
        for (Jugador t : titulares) {
            if (t != null) total += t.calcularCosteMensual();
        }
        // Lo mismo que antes pero con el array de suplentes y puedes no tener suplentes
        for (Jugador s : suplentes) {
            total += s.calcularCosteMensual();
        }
        return total;
    }

    // Método que valida una convocatoria para disputar un partido, son 5 titulares, ninguno de ellos puede estar sancionado y no debe haber 2 roles iguales
     public boolean convocatoriaValida() throws JugadorSancionadoException {
        int contadorTitulares = 0;
        // Uso un HashSet para verificar que no se repitan roles, ya que un HashSet no permite elementos duplicados, si intento añadir un rol que ya existe, el método add devolverá 'false', lo que me indicará que hay un rol repetido en la convocatoria.
        java.util.HashSet<Rol> rolesConvocados = new java.util.HashSet<>();

        // Cuenta los jugadores y verifica que existan 5 titulares
        for (Jugador t : titulares) {
            if (t != null) {
                // Verifica si está sancionado
                if (t.getIsSancionado()) {
                    throw new JugadorSancionadoException("El jugador " + t.getNickname() + " está sancionado y no puede jugar");
                }
                // Intentamos añadir el rol al HashSet, si devuelve 'false', significa que el rol ya existía previamente en la lista.
                if (!rolesConvocados.add(t.getRol())) {
                    System.out.println("Convocatoria inválida: Rol duplicado detectado (" + t.getRol() + ").");
                    return false;
                }
                contadorTitulares++;
            }
        }
        // Debe haber exactamente 5 titulares
        if (contadorTitulares != 5) {
            System.out.println("Convocatoria inválida: solo hay " + contadorTitulares + " titulares (se necesitan 5)");
            return false;
        }
        return true;  // Todas las validaciones pasadas
    }

    // METODOS QUE ACTUALIZAN ESTADISTICAS

    // Método que registra victorias al contador, suma puntos a favor y añade victorias a entrenador
    public void registrarVictoria(int puntos) {
        this.victorias++;
        this.puntosAFavor += puntos;

    // Incrementa victorias del entrenador
        if (entrenador != null) {
            entrenador.incrementarVictorias();
        }
    }

    // Método que registra una derrotas
    public void registrarDerrota(int puntosRivales) {
        this.derrotas++;
        this.puntosEnContra += puntosRivales;
    }

    // Método que actuaría como setter conjunto para victorias, derrotas, puntosAFavor y puntos En Contra
    // Actualiza las estadísticas del equipo tras acabar un partido, lo he juntado todo en uno con este metodo y asi no se escapa nada que se deba calcular desde fuera
    public void registrarResultadoPartido(boolean victoria, Integer puntosAFavor, Integer puntosEnContra) {
        // Si el resultado es victoria, se suma 1 a victorias, si no se suma 1 a derrotas
        if (victoria) {
            this.victorias++;
        } else {
            this.derrotas++;
        }
        // Se suman los puntos al atributo correspondiente
        this.puntosAFavor += puntosAFavor;
        this.puntosEnContra += puntosEnContra;
    }

    // toString que representa el equipo, muestra su nombre, ciudad, entrenador, titulares y suplentes y su estadística
    @Override
    public String toString() {
        return "Equipo {" + "nombre='" + nombre + '\'' + ", ciudad='" + ciudad + '\'' + ", entrenador=" + entrenador + ", titulares=" + Arrays.toString(titulares) +
        ", suplentes=" + suplentes + ", victorias=" + victorias + ", derrotas=" + derrotas + ", puntosAFavor=" + puntosAFavor + ", puntosEnContra=" + puntosEnContra + '}';
    }

	// Método que muestra únicamente la plantilla del equipo, con su entrenador, titulares y suplentes, sin mostrar las estadísticas
	public void mostrarPlantilla() {
		System.out.println("EQUIPO: " + nombre + " (" + ciudad + ")");
		System.out.println("ENTRENADOR: " + (entrenador != null ? entrenador.getNickname() : "Sin entrenador"));
		System.out.println("TITULARES:");
		for (Jugador t : titulares) {
			if (t != null) {
				System.out.println("  - " + t.getNickname() + " [" + t.getRol() + "]");
			}
		}
		System.out.println("SUPLENTES:");
		for (Jugador s : suplentes) {
			System.out.println("  - " + s.getNickname() + " [" + s.getRol() + "]");
		}
	}
}
