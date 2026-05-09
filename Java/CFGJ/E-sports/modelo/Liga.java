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
	- ArralyList para listas dinámicas
*/
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

	// Métodos para gestionar personas dentro de la liga

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

}

