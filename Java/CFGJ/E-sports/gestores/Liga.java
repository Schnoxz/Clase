package gestores;
/* Esta es la clase principal donde se gestiona la liga e-sports

Se encarga de gestionar:

	- Personas, tanto jugadores como entrenadores
	- Equipos
	- Partidas y fechas de calendario
	- Incidencias y sanciones
	- Estadisticas de equipos y jugadores
	- Clasificaciones y premios

Esta clase implementa las estructuras obligatorias para el proyecto:

	- HashSet, usado en el control de duplicados
	- Queue para gestionar partidos pendientes
	- Stack para tener un log o historial de acciones
	- Matrices bidimensionales para el calendario
	- ArrayList para listas dinámicas
*/
import excepciones.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import modelo.*;

public class Liga {
	// Atributos de clase para la gestión
	private String nombreLiga;
	private String temporada;

	// Listas dinámicas principales
	private ArrayList<PersonaLiga> personas;      // Incluye a todas las personas de la liga
    private ArrayList<Equipo> equipos;            // Todos los equipos
    private ArrayList<Partido> partidosJugados;   // Para el historial de partidos disputados
    private ArrayList<IncidenciaLog> incidencias; // Recoge el registro de incidencias

	// Listas dinámicas que son obligatorias de usar
	private HashSet<String> idsPersonasRegistradas;  // Guarda los IDs de personas registradas y asi evitar duplicados
    private HashSet<String> nombresEquipos; 		// Guarda los nombres de equipos y evita duplicados
    private HashSet<String> idsPartidos; 		    // Guarda los IDs de partidos y evita duplicados
    private Queue<Partido> colaPartidosPendientes;  // Se guardan partidos pendientes de jugar y se juegan en orden de cola
    private Stack<String> pilaHistorialAcciones;	// Guarda el historial de acciones realizadas y se muestran en orden de pila, el último es el primero en mostrarse
    private Partido[][] calendario;					// Filas: jornadas en la temporada, columnas: partidos de la jornada

    // Configuración del calendario
    private int numeroJornadas;
    private int partidosPorJornada;

	// Clase constructor de la clase Liga
	public Liga(String nombreLiga, String temporada, int numeroJornadas) {
        this.nombreLiga = nombreLiga;
        this.temporada = temporada;
        this.numeroJornadas = numeroJornadas;

        // Inicializar listas dinámicas
        this.personas = new ArrayList<>();
        this.equipos = new ArrayList<>();
        this.partidosJugados = new ArrayList<>();
        this.incidencias = new ArrayList<>();

        // Inicializar HashSets para control de duplicados
        this.idsPersonasRegistradas = new HashSet<>();
        this.nombresEquipos = new HashSet<>();
        this.idsPartidos = new HashSet<>();

        // Inicializar Queue para partidos pendientes (FIFO)
        this.colaPartidosPendientes = new LinkedList<>();

        // Inicializar Stack para historial (LIFO)
        this.pilaHistorialAcciones = new Stack<>();

        // Inicializar matriz de calendario
        // Calculamos partidos por jornada (asumimos 4 equipos mínimo = 2 partidos por jornada)
        this.partidosPorJornada = 5; // Valor por defecto, ajustable
        this.calendario = new Partido[numeroJornadas][partidosPorJornada];

        // Registrar creación de la liga en el historial
        registrarAccion("Creación de la liga: " + nombreLiga + " - Temporada " + temporada);
    }

	// GETTERS
    public String getNombreLiga() { return nombreLiga; }
    public String getTemporada() { return temporada; }
    public ArrayList<PersonaLiga> getPersonas() { return personas; }
    public ArrayList<Equipo> getEquipos() { return equipos; }
    public ArrayList<Partido> getPartidosJugados() { return partidosJugados; }
    public Queue<Partido> getColaPartidosPendientes() { return colaPartidosPendientes; }

	// Aquí van los métodos para gestionar personas dentro de la liga

	// Dar de alta a una persona, sea jugador o entrenador, este método cumple la verificación de ID duplicado con el uso de HashSet
	public void altaPersona(PersonaLiga persona) throws PersonaDuplicadaException {
		if (idsPersonasRegistradas.contains(persona.getIdentificador())) {
			throw new PersonaDuplicadaException("La persona con ID " + persona.getIdentificador() + " ya se encuentra registrada en la liga.");
		}
	    // Si no salta la excepción, se registra a la lista y al HashSet
        personas.add(persona);
        idsPersonasRegistradas.add(persona.getIdentificador());

        // Se registra además en el historial que era una pila
        String tipoPersona = (persona instanceof Jugador) ? "Jugador" : "Entrenador"; // Determina el tipo de persona si es Jugador o Entrenador
        registrarAccion("Alta de " + tipoPersona + ": " + persona.getNickname() + " (ID: " + persona.getIdentificador() + ")"); // Llamada al método para registrar la acción y se le pasa el nombre y el ID de la persona
		// Se verifica el registro con un mensaje por pantalla
        System.out.println("Persona registrada: " + persona.getNickname());
    }

	// Listar a las personas que ya están registradas dentro de la liga
	public void listarPersonas() {
		// Se verifica si la lista de personas esta vacia y se enviaa un mensaje
		if (personas.isEmpty()){
			System.out.println("No hay personas registradas en la liga.");
			return;
		}
		// Mensaje que muestra el total de personas
		System.out.println("\n=== PERSONAS REGISTRADAS EN LA LIGA ===");
        System.out.println("Total: " + personas.size() + " personas\n");

		// Diferencio el tipo de persona entre entrenador y jugador con el instanceof y cuento el total de cada uno
		System.out.println("--- JUGADORES ---");
        int countJugadores = 0;
        for (PersonaLiga p : personas) {
			// Al usar instanceof se verifica si la persona es de la clase Jugador pero no se accede a sus atributos por eso uso un cast, ya que quiero trabajar con ellos
            if (p instanceof Jugador) {
                Jugador j = (Jugador) p; // Cast para acceder a los atributos de la clase Jugador y usar los métodos
                System.out.println("  • " + j.getNickname() + " [" + j.getRol() + "] - " + "Rendimiento: " + String.format("%.1f", j.calcularRendimiento()) + (j.getIsSancionado() ? " [SANCIONADO]" : ""));
                countJugadores++;
            }
        }
        if (countJugadores == 0) {
            System.out.println("  (No hay jugadores)");
        }

        System.out.println("\n--- ENTRENADORES ---");
        int countEntrenadores = 0;
        for (PersonaLiga p : personas) {
            if (p instanceof Entrenador) {
                Entrenador e = (Entrenador) p; // Cast
                System.out.println("  • " + e.getNickname() + " - " + "Especialidad: " + e.getEspecialidad() + " (" + e.getVictoriasTotales() + " victorias)");
                countEntrenadores++;
            }
        }
        if (countEntrenadores == 0) {
            System.out.println("  (No hay entrenadores)");
        }
    }

	 public PersonaLiga buscarPersonaPorId(String id) {
        for (PersonaLiga p : personas) {
            if (p.getIdentificador().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public boolean eliminarPersona(String id) {
        PersonaLiga persona = buscarPersonaPorId(id);
        if (persona == null) {
            System.out.println("No se encontró ninguna persona con ID: " + id);
            return false;
        }

        // Verificar si está asignada a algún equipo
        for (Equipo eq : equipos) {
            // Verificar si es el entrenador
            if (eq.getEntrenador() != null && eq.getEntrenador().equals(persona)) {
                System.out.println("No se puede eliminar: es entrenador de " + eq.getNombre());
                return false;
            }

            // Verificar si es jugador titular
            for (Jugador t : eq.getTitulares()) {
                if (t != null && t.equals(persona)) {
                    System.out.println("No se puede eliminar: es titular de " + eq.getNombre());
                    return false;
                }
            }

            // Verificar si es suplente (solo si sabemos que la persona es un Jugador)
            if (persona instanceof Jugador) {
                Jugador posibleSuplente = (Jugador) persona;
                if (eq.getSuplentes().contains(posibleSuplente)) {
                    System.out.println("No se puede eliminar: es suplente de " + eq.getNombre());
                    return false;
                }
            }
		}

       	// Eliminar de las estructuras
        personas.remove(persona);
        idsPersonasRegistradas.remove(id);

        registrarAccion("Eliminación de persona: " + persona.getNickname() + " (ID: " + id + ")");
        System.out.println("Persona eliminada: " + persona.getNickname());
        return true;
    }


    // GESTIÓN DE EQUIPOS =
    public void crearEquipo(Equipo equipo) throws EquipoDuplicadoException {
        // Control de duplicados con HashSet
        if (nombresEquipos.contains(equipo.getNombre())) {
            throw new EquipoDuplicadoException(
                "Ya existe un equipo con el nombre: " + equipo.getNombre()
            );
        }

        equipos.add(equipo);
        nombresEquipos.add(equipo.getNombre());

        registrarAccion("Creación de equipo: " + equipo.getNombre() + " (" + equipo.getCiudad() + ")");

        System.out.println("Equipo creado: " + equipo.getNombre());
    }

	// Método para buscar un equipo por nombre
    public Equipo buscarEquipoPorNombre(String nombre) {
        for (Equipo e : equipos) {
            if (e.getNombre().equalsIgnoreCase(nombre)) { // Comparación sin distinguir mayúsculas/minúsculas
                return e;
            }
        }
        return null;
    }

	// Método para listar todos los equipos
    public void listarEquipos() {
        if (equipos.isEmpty()) {
    		System.out.println("No hay equipos registrados en la liga.");
            return;
        }

        System.out.println("\n=== EQUIPOS DE LA LIGA ===");
        System.out.println("Total: " + equipos.size() + " equipos\n");

        for (Equipo eq : equipos) {
            System.out.println("• " + eq.getNombre() + " (" + eq.getCiudad() + ")");
            System.out.println("   Entrenador: " + (eq.getEntrenador() != null ? eq.getEntrenador().getNickname() : "Sin asignar"));
            System.out.println("   Estadísticas: " + eq.getVictorias() + "V - " + eq.getDerrotas() + "D");
            System.out.println("   Presupuesto: " + eq.getPresupuesto() + "€");
            System.out.println("   Coste plantilla: " + String.format("%.2f", eq.calcularCostePlantilla()) + "€/mes");
            System.out.println();
        }
    }

    // GESTIÓN DE CALENDARIO Y PARTIDOS

	// Método para generar el calendario de partidos
    public void generarCalendario() {
        if (equipos.size() < 2) {
            System.out.println("Se necesitan al menos 2 equipos para generar el calendario.");
            return;
        }

        System.out.println("\n=== GENERANDO CALENDARIO ===");

        int partidosProgramados = 0;
        int jornadaActual = 0;
        int partidoEnJornada = 0;

        // Genera enfrentamientos: cada equipo juega contra todos los demás
        for (int i = 0; i < equipos.size(); i++) {
            for (int j = i + 1; j < equipos.size(); j++) {
                try {
                    // Crea un ID único para el partido
                    String idPartido = "P" + (partidosProgramados + 1);

                    // Crea el partido
                    Partido partido = new Partido(idPartido, jornadaActual + 1, equipos.get(i), equipos.get(j)); // Las jornadas empiezan en 1

                    // Añade el partido a la matriz de calendario
                    if (jornadaActual < numeroJornadas && partidoEnJornada < partidosPorJornada) {
        				calendario[jornadaActual][partidoEnJornada] = partido;
                    }

                    // Se guarda el partido en la cola
                    colaPartidosPendientes.offer(partido);
                    idsPartidos.add(idPartido);

                    partidosProgramados++;
                    partidoEnJornada++;

                    // Si llenamos una jornada, pasamos a la siguiente
                    if (partidoEnJornada >= partidosPorJornada) {
                        partidoEnJornada = 0;
                        jornadaActual++;
                    }

                } catch (PartidoInvalidoException e) { // Lanza PartidoInvalidoException si falla
                    System.out.println("Error al crear partido: " + e.getMessage());
                }
            }
        }

        registrarAccion("Generación de calendario: " + partidosProgramados + " partidos programados en " + (jornadaActual + 1) + " jornadas");

        System.out.println("Calendario generado: " + partidosProgramados + " partidos");
        System.out.println("Distribuidos en " + (jornadaActual + 1) + " jornadas");
    }

	// Método para mostrar el calendario completo
    public void mostrarCalendarioCompleto() {
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║       CALENDARIO DE LA TEMPORADA          ║");
        System.out.println("╚════════════════════════════════════════════╝\n");

		// Recorre el calendario y muestra los partidos por jornada
        for (int j = 0; j < numeroJornadas; j++) {
            System.out.println("--- JORNADA " + (j + 1) + " ---");

            boolean hayPartidos = false;
            for (int p = 0; p < partidosPorJornada; p++) {
                if (calendario[j][p] != null) {
                    Partido partido = calendario[j][p];
                    System.out.print("  " + partido.getId() + ": ");
                    System.out.print(partido.getLocal().getNombre() + " vs " + partido.getVisitante().getNombre());

                    if (partido.isJugado()) {
                        System.out.println(" → " + partido.getPuntosLocal() + "-" + partido.getPuntosVisitante());
                    } else {
                        System.out.println(" [PENDIENTE]");
                    }
                    hayPartidos = true;
                }
            }

            if (!hayPartidos) {
                System.out.println("  (Sin partidos programados)");
            }
            System.out.println();
        }
    }

	// Método para consultar el calendario de una jornada
    public void consultarJornada(int numJornada) {
        if (numJornada < 1 || numJornada > numeroJornadas) {
            System.out.println("Número de jornada inválido. Debe estar entre 1 y " + numeroJornadas);
            return;
        }

        int indiceJornada = numJornada - 1; // Para que se muestre un enumerado real, le resto 1, si no nos mostraria siempre una jornada adicional según el indice de la que realmente es

        System.out.println("\n=== JORNADA " + numJornada + " ===\n");
		// Recorre el calendario y muestra los partidos de la jornada especificada, si no se encuentra ningún partido programado para esa jornada se muestra un mensaje indicando que no hay partidos programados para esa jornada, si se encuentra un partido programado se muestra su información
        boolean hayPartidos = false;
        for (int p = 0; p < partidosPorJornada; p++) {
            if (calendario[indiceJornada][p] != null) {
				calendario[indiceJornada][p].mostrarInfo();
                hayPartidos = true;
            }
        }

        if (!hayPartidos) {
            System.out.println("No hay partidos programados para esta jornada.");
        }
    }

    // GESTIÓN DE COLA DE PARTIDOS

	// Muestra el siguiente partido pendiente en la cola
    public void mostrarSiguientePartido() {
        if (colaPartidosPendientes.isEmpty()) {
            System.out.println("No hay partidos pendientes en la cola.");
            return;
        }
		// Muestra el partido pendiente siguiente de la cola
        Partido siguiente = colaPartidosPendientes.peek();
        System.out.println("\n🎮 SIGUIENTE PARTIDO A DISPUTAR:");
        siguiente.mostrarInfo();
    }

	// Disputa el siguiente partido pendiente
	// Método que consulta el primer partido de la cola sin borrarlo
    public Partido disputarSiguientePartido() {
        if (colaPartidosPendientes.isEmpty()) {
            return null;
        }
        return colaPartidosPendientes.peek();
    }

    // Método que ejecuta la lógica de registrar el partido en el sistema
    public void registrarDisputaPartido(Partido partido, int puntosLocal, int puntosVisitante, Jugador mvp) throws JugadorSancionadoException, PartidoInvalidoException {

        // Verificación de convocatorias (Lanza JugadorSancionadoException si falla)
        if (!partido.getLocal().convocatoriaValida()) {
            throw new PartidoInvalidoException("La convocatoria del equipo local (" + partido.getLocal().getNombre() + ") no es válida.");
        }
        if (!partido.getVisitante().convocatoriaValida()) {
            throw new PartidoInvalidoException("La convocatoria del equipo visitante (" + partido.getVisitante().getNombre() + ") no es válida.");
        }

        // Registra el resultado en el objeto partido (Lanza PartidoInvalidoException si ya estaba jugado)
        partido.registrarResultado(puntosLocal, puntosVisitante, mvp);

        // Actualiza las estructuras que hemos definido para la gestión de partidos, como la cola de partidos pendientes y el historial de partidos jugados
        colaPartidosPendientes.poll(); // Extrae y elimina definitivamente el partido de la cola
        partidosJugados.add(partido);  // Lo añade al historial de jugados

        // Registra la acción en el historial
        registrarAccion("Partido disputado: " + partido.getId() + " | Resultado: " + puntosLocal + "-" + puntosVisitante);
    }

	// Muestra la cola de partidos pendientes
    public void mostrarPartidosPendientes() {
        if (colaPartidosPendientes.isEmpty()) {
            System.out.println("No hay partidos pendientes.");
            return;
        }

        System.out.println("\n=== PARTIDOS PENDIENTES ===");
        System.out.println("Total en cola: " + colaPartidosPendientes.size() + "\n");

        int contador = 1;
        for (Partido p : colaPartidosPendientes) {
            System.out.println(contador + ". " + p.toString());
            contador++;
        }
    }

	// Vacia la cola de partidos pendientes
    public void vaciarColaPartidos() {
        int cantidadEliminada = colaPartidosPendientes.size();
        colaPartidosPendientes.clear(); // Vacía la cola de partidos pendientes
		// Registra la acción de vaciar la cola en el historial y muestra un mensaje indicando que la cola ha sido vaciada y cuántos partidos se eliminaron
        registrarAccion("Cola de partidos vaciada: " + cantidadEliminada + " partidos eliminados");
        System.out.println("Cola vaciada. Se eliminaron " + cantidadEliminada + " partidos.");
    }

    // GESTIÓN DE INCIDENCIAS

	// Registra una nueva incidencia
    public void registrarIncidencia(IncidenciaLog incidencia) {
        incidencias.add(incidencia);
        registrarAccion("Incidencia registrada: " + incidencia.getTipo());
        System.out.println("Incidencia registrada");
    }

	// Listar todas las incidencias
    public void listarIncidencias() {
        if (incidencias.isEmpty()) {
            System.out.println("No hay incidencias registradas.");
            return;
        }
		// Muestra el total de incidencias registradas y luego recorre la lista de incidencias mostrando su información, si no se encuentra ninguna incidencia se muestra un mensaje indicando que no hay incidencias registradas
        System.out.println("\n=== INCIDENCIAS REGISTRADAS ===");
        System.out.println("Total: " + incidencias.size() + "\n");

        for (int i = 0; i < incidencias.size(); i++) {
            System.out.println((i+1) + ". " + incidencias.get(i).toString());
        }
    }

	// Busca incidencias relacionadas con un jugador en particular
    public void buscarIncidenciasPorJugador(Jugador jugador) {
        System.out.println("\n=== INCIDENCIAS DE " + jugador.getNickname() + " ===\n");
		// Recorre la lista de incidencias y muestra solo las que estén relacionadas con el jugador especificado, si no se encuentra ninguna incidencia relacionada se muestra un mensaje indicando que no se encontraron incidencias para ese jugador
        boolean encontradas = false;
        for (IncidenciaLog inc : incidencias) {
            if (inc.getJugador() != null && inc.getJugador().equals(jugador)) {
                System.out.println("• " + inc.toString());
                encontradas = true;
            }
        }

        if (!encontradas) {
            System.out.println("No se encontraron incidencias para este jugador.");
        }
    }

	// Busca incidencias relacionadas con un equipo en particular
    public void buscarIncidenciasPorEquipo(Equipo equipo) {
        System.out.println("\n=== INCIDENCIAS DE " + equipo.getNombre() + " ===\n");
		// Recorre la lista de incidencias y muestra solo las que estén relacionadas con el equipo especificado, si no se encuentra ninguna incidencia relacionada se muestra un mensaje indicando que no se encontraron incidencias para ese equipo
        boolean encontradas = false;
        for (IncidenciaLog inc : incidencias) {
            if (inc.getEquipo() != null && inc.getEquipo().equals(equipo)) {
                System.out.println("• " + inc.toString());
                encontradas = true;
            }
        }

        if (!encontradas) {
            System.out.println("No se encontraron incidencias para este equipo.");
        }
    }

    // HISTORIAL DE ACCIONES

	// Registra una nueva acción en el historial
    public void registrarAccion(String accion) {
        String timestamp = java.time.LocalDateTime.now().format(
            java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
        pilaHistorialAcciones.push("[" + timestamp + "] " + accion); // Se añade la acción a la pila con un formato de timestamp para tener un registro temporal de las acciones realizadas
    }

	// Muestra la última accion registrada
    public void mostrarUltimaAccion() {
        if (pilaHistorialAcciones.isEmpty()) {
            System.out.println("No hay acciones registradas en el historial.");
            return;
        }

        System.out.println("\nÚLTIMA ACCIÓN:");
        System.out.println(pilaHistorialAcciones.peek());
    }

	// Muestra el historial de acciones
    public void mostrarHistorial() {
        if (pilaHistorialAcciones.isEmpty()) {
            System.out.println("No hay acciones en el historial.");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║         HISTORIAL DE ACCIONES             ║");
        System.out.println("╚════════════════════════════════════════════╝\n");
        System.out.println("Total de acciones: " + pilaHistorialAcciones.size() + "\n");

        // Crea una copia temporal de la pila para mostrarla sin modificar el orden original, ya que al usar pop se eliminarían las acciones del historial, con esta copia podemos mostrar el historial completo sin perder los datos ademñas no uso .clone porque java no puede comprobar la pila original, tendría que hacer pop en la pila original para mostrar el historial pero eso eliminaría las acciones del historial, con esta copia temporal puedo mostrar el historial completo sin perder los datos originales
        Stack<String> copiaTemp = new Stack<>();
		copiaTemp.addAll(pilaHistorialAcciones);

        int contador = 1;
        while (!copiaTemp.isEmpty()) {
            System.out.println(contador + ". " + copiaTemp.pop());
            contador++;
        }
    }

	// Deshace la última accion registrada funcionando con la pila
    public void deshacerUltimaAccion() {
        if (pilaHistorialAcciones.isEmpty()) {
            System.out.println("No hay acciones para deshacer.");
            return;
        }

        String accionDeshecha = pilaHistorialAcciones.pop();
        System.out.println("Acción deshecha del historial:");
        System.out.println("  " + accionDeshecha);
    }

    // CLASIFICACIÓN Y ESTADÍSTICAS

	// Muestra la clasificación de equipos
    public void mostrarClasificacion() {
        if (equipos.isEmpty()) {
            System.out.println("No hay equipos para clasificar.");
            return;
        }

        // Crear una copia de la lista para ordenarla sin modificar la original
        ArrayList<Equipo> clasificacion = new ArrayList<>(equipos);

        // Ordenar usando Comparator con criterios múltiples
        clasificacion.sort((e1, e2) -> {
            // Victorias (descendente)
            int compVictorias = Integer.compare(e2.getVictorias(), e1.getVictorias());
            if (compVictorias != 0) return compVictorias;

            // Diferencia de puntos (descendente)
            int diff1 = e1.getPuntosAFavor() - e1.getPuntosEnContra();
            int diff2 = e2.getPuntosAFavor() - e2.getPuntosEnContra();
            int compDiferencia = Integer.compare(diff2, diff1);
            if (compDiferencia != 0) return compDiferencia;

            // Nombre en orden alfabético (ascendente)
            return e1.getNombre().compareTo(e2.getNombre());
        });

        // Mostrar la clasificación
        System.out.println("\n╔═══════════════════════════════════════════════════════════════════╗");
        System.out.println("║                      CLASIFICACIÓN GENERAL                        ║");
        System.out.println("╠═══════════════════════════════════════════════════════════════════╣");
        System.out.println("║ Pos │ Equipo                  │  PJ │  V │  D │  PF │  PC │ Diff ║");
        System.out.println("╠═════╪═════════════════════════╪═════╪════╪════╪═════╪═════╪══════╣");
		// Recorre la lista de equipos ordenada y muestra su posición, nombre, partidos jugados, victorias, derrotas, puntos a favor, puntos en contra y diferencia de puntos
        for (int i = 0; i < clasificacion.size(); i++) {
            Equipo e = clasificacion.get(i);
            int totalPartidos = e.getVictorias() + e.getDerrotas();
            int diferencia = e.getPuntosAFavor() - e.getPuntosEnContra();
			// Para que el formato de la tabla se mantenga, se limita el nombre del equipo a 23 caracteres y si es mayor se muestra solo los primeros 23 caracteres, el formato de lo demás se mantiene 3d para números enteros y 4d con signo para la diferencia, además de mostrar el encabezado de la tabla con los nombres de cada columna
            System.out.printf("║ %3d │ %-23s │ %3d │ %2d │ %2d │ %3d │ %3d │ %+4d ║%n", (i + 1), e.getNombre().length() > 23 ? e.getNombre().substring(0, 23) : e.getNombre(), totalPartidos, e.getVictorias(), e.getDerrotas(), e.getPuntosAFavor(), e.getPuntosEnContra(), diferencia);
        }

        System.out.println("╚═════╧═════════════════════════╧═════╧════╧════╧═════╧═════╧══════╝");
        System.out.println("\nLeyenda: PJ=Partidos Jugados, V=Victorias, D=Derrotas,");
        System.out.println("         PF=Puntos a Favor, PC=Puntos en Contra, Diff=Diferencia");
    }

	// Muestra las estadísticas de los jugadores
    public void mostrarEstadisticasJugadores() {
        System.out.println("\n=== ESTADÍSTICAS DE JUGADORES ===\n");

        boolean hayJugadores = false;
        for (PersonaLiga p : personas) {
            if (p instanceof Jugador) {
                Jugador j = (Jugador) p;
                System.out.println("• " + j.getNickname() + " [" + j.getRol() + "]");
                System.out.println("   Partidas jugadas: " + j.getPartidasJugadas());
                System.out.println("   MVPs obtenidos: " + j.getMvpTotales());
                System.out.println("   Rendimiento: " + String.format("%.2f", j.calcularRendimiento()));
                System.out.println("   Nivel mecánico: " + j.getNivelMecanico());
                System.out.println("   Nivel estratégico: " + j.getNivelEstrategico());
                System.out.println("   Estado: " + (j.getIsSancionado() ? "SANCIONADO" : "Activo"));
                System.out.println("   Coste mensual: " + String.format("%.2f", j.calcularCosteMensual()) + "€");
                System.out.println();
                hayJugadores = true;
            }
        }

        if (!hayJugadores) {
            System.out.println("No hay jugadores registrados.");
        }
    }
}


