package modelo;
import excepciones.RolNoDisponibleException;
import java.util.ArrayList;
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
    public void setEntrenador(Entrenador entrenador) { this.entrenador = entrenador; }
    public void setPresupuesto(Double presupuesto) { this.presupuesto = presupuesto; }

	// Métodos de gestión del equipo

	// Método que valida titulares con mismo rol, busca un hueco en el array de titulares y lo llena con el jugador, si algo falla lanza la excepcion
	    public void ficharTitular(Jugador jugador) throws RolNoDisponibleException {
        // VALIDACIÓN 1: Verificar que el rol no esté ocupado
        for (int i = 0; i < titulares.length; i++) {
            // Si hay un jugador en esta posición Y tiene el mismo rol
            if (titulares[i] != null && titulares[i].getRol() == jugador.getRol()) {
                // Lanza la excepción personalizada del módulo excepciones
                throw new RolNoDisponibleException( "El rol " + jugador.getRol() + " ya está ocupado por " + titulares[i].getNickname() + ". No puede haber dos titulares con el mismo rol."
                );
            }
        }

        // VALIDACIÓN 2: Buscar una posición libre para colocar al jugador
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

	// Fichar a un jugador pero para la lista de suplentes, aqui no hay restriccion de rol, pueden repetirse cuantas veces quieran y de momento no existe una norma que debas tener suplente para cada rol, un equipo puede tener solo suplenes para un rol, y quedarse sin suplentes para el resto de posiciones

    public void ficharSuplente(Jugador jugador) {
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
        // VALIDACIÓN 1: Índice válido
        if (indiceTitular < 0 || indiceTitular >= titulares.length) {
            throw new IllegalArgumentException("Índice inválido. Debe estar entre 0 y " + (titulares.length - 1));
        }

        // VALIDACIÓN 2: Que haya un titular en esa posición
        Jugador titular = titulares[indiceTitular];
        if (titular == null) {
            throw new IllegalArgumentException("No hay ningún titular en la posición " + indiceTitular);
        }

        // VALIDACIÓN 3: Mismo rol
        if (titular.getRol() != suplente.getRol()) {
			// He formateado el mensaje en bloque, creo que se ve mejor así
            throw new RolNoDisponibleException("""
                                               El suplente debe tener el mismo rol que el titular saliente.
                                               Titular: """ + titular.getRol() + " | Suplente: " + suplente.getRol());
        }

        // VALIDACIÓN 4: El suplente debe pertenecer al equipo
        if (!suplentes.contains(suplente)) {
            throw new IllegalArgumentException("El suplente " + suplente.getNickname() + " no es suplente de este equipo");
        }

        // REALIZAR LA SUSTITUCIÓN
        // 1. El actual titular pasa a banco de suplentes
        titulares[indiceTitular] = suplente;

        // 2. Quitar al suplente de la lista de suplentes
        suplentes.remove(suplente);

        // 3. Añadir al ex-titular a la lista de suplentes
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

	// Método que actuaría como setter conjunto para victorias, derrotas, puntosAFavor y puntosEnContra
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

    // Añade un jugador a la lista dinámica de suplentes
    public void añadirSuplente(Jugador jugador) { this.suplentes.add(jugador); }

    // Elimina un jugador de la lista dinámica de suplentes
    public void eliminarSuplente(Jugador jugador) {
        this.suplentes.remove(jugador);
    }

    // Método que nos permite calcular el coste total de la plantilla
    public Double calcularCostePlantilla() {
        Double total = 0.0; // Inicializo la variable a 0
		// Si existe un entrenador, al total le sumo su coste llamando al metodo calcularCosteMensual de la clase Entrenador
        if (entrenador != null) {
            total += entrenador.calcularCosteMensual();
        }
		// Recorro el array de titulares con un for each y si el jugador no es null, al total le sumo su coste llamando al metodo calcularCosteMensual de la clase Jugador
        for (Jugador j : titulares) {
            if (j != null) total += j.calcularCosteMensual();
        }
		// Lo mismo que antes pero con el array de suplentes
        for (Jugador j : suplentes) {
            total += j.calcularCosteMensual();
        }
        return total;
    }
}
