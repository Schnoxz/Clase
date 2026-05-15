package ui;

import excepciones.*;
import gestores.Liga;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelo.*;

/* Funcionalidades que se implementan en el Main:
	- Gestion de personas (jugadores y entrenadores)
	- Gestion de equipos (creacion, asignacion de Entrenador, fichajes titulares y suplentes, sustituciones)
	- Mercado de fichajes (asignar Entrenador, fichar titular, fichar suplente, hacer sustitución)
	- Calendario y disputa de partidos (generar calendario, ver calendario, disputar partido con registro de resultado y MVP)
	- Clasificaciones y estadísticas (mostrar tabla de clasificación, mostrar estadísticas globales de jugadores, mostrar estadísticas por personaje)
	- Tribunal de sanciones e incidencias (abrir expediente, listar expedientes)
	- Sistema de entrenamiento (entrenar, ver rendimiento, ver historial de entrenamientos)
	- Auditoria con stack de operaciones (mostrar historial de operaciones realizadas en el sistema)
	- Enciclopedia de dioses (listar dioses disponibles, mostrar detalles de cada dios, estadísticas globales por dios)
 */

// Clase principal, contiene la interfaz de usuario y una inicialización masiva de datos reales de la SPL 2021
public class Main {
    private static final Scanner teclado = new Scanner(System.in);
    private static Liga liga;
    private static List<PersonajeJuego> poolDioses = new ArrayList<>();

    public static void main(String[] args) {
        // Inicializa la liga con 14 jornadas (ida y vuelta para 8 equipos)
        liga = new Liga("Smite Pro League 2021", "Temporada 8", 14);
        inicializarSistemaCompleto();

        int opcion = 0;
        do {
            mostrarMenuPrincipal();
            try {
                System.out.print("➤ Ingrese un comando numérico: ");
                opcion = Integer.parseInt(teclado.nextLine());

                switch (opcion) {
                    case 1 -> menuGestionPersonas();
                    case 2 -> menuGestionEquipos();
                    case 3 -> menuFichajes();
                    case 4 -> menuEntrenamiento();
                    case 5 -> menuCalendarioYPartidos();
                    case 6 -> menuClasificacionYEstadisticas();
                    case 7 -> menuIncidencias();
                    case 8 -> menuAuditoria();
                    case 9 -> visorPoolDioses();
                    case 10 -> System.out.println("Cerrando Menu 1GSY. ¡GG WP!");
                    default -> System.out.println("Error: Comando no reconocido por el sistema.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error de formato: Por favor, utilice valores numéricos.");
            } catch (Exception e) {
                System.out.println("Error Crítico del Sistema: " + e.getMessage());
            }
        } while (opcion != 10);
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println(" ⚡ MENU - SMITE PRO LEAGUE 2021 ⚡ ");
        System.out.println("=".repeat(60)); // Imprime el caracter como separación y con un repeat le digo cuantas veces repetirlo
        System.out.println("  1. 👥 GESTIÓN DE ROSTERS (Jugadores/Entrenadores)");
        System.out.println("  2. 🛡️  GESTIÓN DE EQUIPOS");
        System.out.println("  3. ✍️  MERCADO DE FICHAJES (Traspasos y Suplentes)");
        System.out.println("  4. 🏋️  SISTEMA DE ENTRENAMIENTO Y RENDIMIENTO");
        System.out.println("  5. 📅 CALENDARIO Y DISPUTA DE JORNADAS");
        System.out.println("  6. 🏆 CLASIFICACIONES Y ESTADÍSTICAS");
        System.out.println("  7. ⚠️  TRIBUNAL DE SANCIONES E INCIDENCIAS");
        System.out.println("  8. 📜 AUDITORÍA DE SISTEMA");
        System.out.println("  9. 🏛️  ENCICLOPEDIA DE DIOSES");
        System.out.println("  10. 🚪 APAGAR SISTEMA");
        System.out.println("-".repeat(60));
    }

    // METODOS DE MENUS SECUNDARIOS

    // Método para gestionar personas (jugadores y entrenadores). Permite dar de alta, listar y buscar por ID. Implementa manejo de excepciones para datos duplicados o formato incorrecto.
    private static void menuGestionPersonas() {
        System.out.println("\n--- GESTIÓN DE ROSTERS ---");
        System.out.println("[1] Dar de Alta | [2] Listado General | [3] Buscar por ID | [4] Modificar Datos | [5] Eliminar | [6] Volver");
        int op = Integer.parseInt(teclado.nextLine());

        try {
            switch (op) {
                case 1 -> altaPersona();
                case 2 -> liga.listarPersonas();
                case 3 -> buscarPersona();
                case 4 -> modificarPersona();
                case 5 -> eliminarPersona();
                case 6 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } catch (PersonaDuplicadaException e) {
            System.out.println("ALERTA: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void menuGestionEquipos() {
        System.out.println("\n--- GESTIÓN DE EQUIPOS ---");
        System.out.println("[1] Nuevo Equipo | [2] Ver Todos los Equipos | [3] Ver Plantilla Detallada | [4] Volver");
        int op = Integer.parseInt(teclado.nextLine());
        try {
            switch (op) {
                case 1 -> crearEquipo();
                case 2 -> liga.listarEquipos();
                case 3 -> verPlantillaEquipo();
                case 4 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } catch (EquipoDuplicadoException e) {
            System.out.println("ALERTA: " + e.getMessage());
        }
    }

    private static void menuFichajes() {
        System.out.println("\n--- MERCADO DE FICHAJES ---");
        System.out.println("[1] Asignar Entrenador | [2] Fichar Titular | [3] Fichar Suplente | [4] Hacer Sustitución | [5] Volver");
        int op = Integer.parseInt(teclado.nextLine());
        if (op == 5) return;

        try {
            System.out.print("Nombre exacto del Equipo: ");
            Equipo eq = liga.buscarEquipoPorNombre(teclado.nextLine());
            System.out.print("ID del Profesional a operar: ");
            PersonaLiga per = liga.buscarPersonaPorId(teclado.nextLine());

            if (eq == null || per == null) {
                System.out.println("Equipo o Persona no encontrados en la base de datos.");
                return;
            }

            switch (op) {
                case 1 -> {
                    if (per instanceof Entrenador e) {
                        eq.setEntrenador(e);
                        System.out.println("Entrenador asignado exitosamente.");
                    } else System.out.println("Error: Este ID pertenece a un jugador, no a un Entrenador.");
                }
                case 2 -> eq.ficharTitular((Jugador) per);
                case 3 -> eq.ficharSuplente((Jugador) per);
                case 4 -> {
                    System.out.println("Índices Titulares: 0=TOP, 1=JUNGLE, 2=MID, 3=ADC, 4=SUPPORT");
                    System.out.print("Índice del titular a sacar: ");
                    int idx = Integer.parseInt(teclado.nextLine());
                    eq.sustituirJugador(idx, (Jugador) per);
                }
                default -> System.out.println("Opción inválida.");
            }
        } catch (RolNoDisponibleException | IllegalArgumentException e) {
            System.out.println("OPERACIÓN DENEGADA: " + e.getMessage());
        } catch (PresupuestoExcedidoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void menuCalendarioYPartidos() {
        System.out.println("\n--- CALENDARIO Y PARTIDOS ---");
        System.out.println("[1] Generar Calendario | [2] Ver Calendario Completo | [3] Consultar Jornada Específica");
        System.out.println("[4] Ver Siguiente en Cola | [5] Ver Toda la Cola | [6] Vaciar Cola | [7] Disputar Partido | [8] Volver");
        int op = Integer.parseInt(teclado.nextLine());
        switch (op) {
            case 1 -> liga.generarCalendario();
            case 2 -> liga.mostrarCalendarioCompleto();
            case 3 -> {
                System.out.print("Número de jornada: ");
                liga.consultarJornada(Integer.parseInt(teclado.nextLine()));
            }
            case 4 -> liga.mostrarSiguientePartido();
            case 5 -> liga.mostrarPartidosPendientes();
            case 6 -> {
                System.out.print("¿Seguro que desea vaciar la cola? (S/N): ");
                if (teclado.nextLine().equalsIgnoreCase("S")) liga.vaciarColaPartidos();
            }
            case 7 -> disputarPartidoConsola();
            case 8 -> {}
            default -> System.out.println("Opción inválida.");
        }
    }

    private static void disputarPartidoConsola() {
        Partido p = liga.getColaPartidosPendientes().peek();
        if (p == null) {
            System.out.println("¡No hay partidos en la cola. Ve a Generar el calendario primero!.");
            return;
        }

        try {
            if (!p.getLocal().convocatoriaValida()) { System.out.println("Convocatoria inválida del equipo local."); return; }
            if (!p.getVisitante().convocatoriaValida()) { System.out.println("Convocatoria inválida del equipo visitante."); return; }
        } catch (JugadorSancionadoException e) {
            System.out.println("Partido suspendido: " + e.getMessage());
            return;
        }

        System.out.println("\nPARTIDO INMINENTE: " + p.getLocal().getNombre() + " vs " + p.getVisitante().getNombre());
        try {
            System.out.print("Puntos/Kills de " + p.getLocal().getNombre() + ": ");
            int pL = Integer.parseInt(teclado.nextLine());
            System.out.print("Puntos/Kills de " + p.getVisitante().getNombre() + ": ");
            int pV = Integer.parseInt(teclado.nextLine());
            System.out.println("\nCandidatos a MVP (Titulares Locales y Visitantes):");
            int index = 1;
            List<Jugador> candidatos = new ArrayList<>();
            for (Jugador j : p.getLocal().getTitulares()) { if (j != null) { System.out.println(index + ". [LOCAL] " + j.getNickname()); candidatos.add(j); index++; } }
            for (Jugador j : p.getVisitante().getTitulares()) { if (j != null) { System.out.println(index + ". [VISITANTE] " + j.getNickname()); candidatos.add(j); index++; } }

            System.out.print("Seleccione el número del MVP: ");
            int mvpIdx = Integer.parseInt(teclado.nextLine()) - 1;
            Jugador mvp = candidatos.get(mvpIdx);

            // Llamada al método registrarDisputaPartido que se encarga de registrar el partido en la base de datos.
            liga.registrarDisputaPartido(p, pL, pV, mvp);
            System.out.println("¡Partido registrado en la base de datos!");
        } catch (JugadorSancionadoException | PartidoInvalidoException | NumberFormatException e) {
            System.out.println("ERROR AL REGISTRAR PARTIDO: " + e.getMessage());
        }
    }

    private static void menuClasificacionYEstadisticas() {
        System.out.println("\n--- RENDIMIENTO SPL ---");
        System.out.println("[1] Tabla de Clasificación | [2] Estadísticas Globales de Jugadores | [3] Winrate por Dios (Picks) | [4] Volver");
        int op = Integer.parseInt(teclado.nextLine());

        switch (op) {
            case 1 -> liga.mostrarClasificacion();
            case 2 -> liga.mostrarEstadisticasJugadores();
            case 3 -> {
                System.out.print("Ingrese ID del Jugador (Ej: J-LEV1): ");
                PersonaLiga p = liga.buscarPersonaPorId(teclado.nextLine()); // Busca la persona por el ID que ingresa el usuario
                if (p instanceof Jugador j) {
                    System.out.println("\n--- REGISTRO DE PICKS: " + j.getNickname() + " ---");
                    boolean tieneDatos = false;
                    // Recorremos el pool de dioses para buscar estadísticas en el Map del jugador
                    for (PersonajeJuego dios : poolDioses) {
                        String stats = j.getEstadisticasPorPersonaje(dios);
                        if (!stats.equals("No hay estadísticas registradas para este personaje.")) {
                            System.out.println("🔱 " + dios.getNombre() + " -> " + stats);
                            tieneDatos = true;
                        }
                    }
                    if (!tieneDatos) System.out.println("No hay partidas registradas con ningún Dios todavía.");
                } else {
                    System.out.println("El ID no pertenece a un jugador.");
                }
            }
        }
    }

    private static void menuIncidencias() {
        System.out.println("\n--- TRIBUNAL DE INCIDENCIAS ---");
        System.out.println("[1] Abrir Expediente (Log) | [2] Leer Expedientes | [3] Volver");
        int op = Integer.parseInt(teclado.nextLine());
        if (op == 1) {
            System.out.print("Mensaje/Motivo: "); String msg = teclado.nextLine();
            System.out.print("Gravedad (LEVE/SANCION/BANNED): "); String tipo = teclado.nextLine();
            // Submenú que determina a quien afecta la incidencia (jugador, equipo, entrenador o general) y registra la incidencia con la información relevante (mensaje, fecha, tipo, entidad afectada)
            System.out.println("\n¿A quién afecta la incidencia?");
            System.out.println("1. Jugador | 2. Equipo | 3. Entrenador | 4. General (Liga)");
            int afec = Integer.parseInt(teclado.nextLine());

            Jugador jugadorAfectado = null;
            Equipo equipoAfectado = null;
            Entrenador entrenadorAfectado = null;

            // Según la opción que elija el usuario, se solicita la información necesaria para identificar al jugador, equipo o entrenador afectado, y se registra la incidencia con esa información. Si es una incidencia general, no se asocia a ninguna entidad específica.
            switch (afec) {
                case 1 ->                     {
                        System.out.print("ID del Jugador: ");
                        PersonaLiga p = liga.buscarPersonaPorId(teclado.nextLine());
                        if (p instanceof Jugador j) {
                            jugadorAfectado = j;
                            // Lógica de sanción automática si la incidencia es grave (SANCION o BANNED)
                            if (tipo.equalsIgnoreCase("SANCION") || tipo.equalsIgnoreCase("BANNED")) {
                                jugadorAfectado.setIsSancionado(true);
                                System.out.println("El jugador " + j.getNickname() + " ha sido sancionado y no podrá jugar.");
                            }
                        } else {
                            System.out.println("El ID ingresado no corresponde a un jugador.");
                        }                          }
                case 2 -> {
                    System.out.print("Nombre del Equipo: ");
                    equipoAfectado = liga.buscarEquipoPorNombre(teclado.nextLine());
                }
                case 3 ->                     {
                        // En este caso, además de registrar la incidencia, también se asocia al entrenador para que quede reflejado en su historial y pueda ser consultado posteriormente. Si el entrenador ya tiene incidencias previas, se le añade esta nueva al historial sin sobrescribir las anteriores.
                        System.out.print("ID del Entrenador: ");
                        PersonaLiga p = liga.buscarPersonaPorId(teclado.nextLine());
                        if (p instanceof Entrenador e) {
                            entrenadorAfectado = e;
                            System.out.println("Se ha asociado la incidencia al Entrenador " + e.getNickname() + ".");
                        } else {
                            System.out.println("El ID ingresado no corresponde a un entrenador.");
                        }                          }
                default -> {
                }
            }

            String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            // Se registra la incidencia creando una nueva instancia, enviando EXACTAMENTE los 7 parámetros que pide tu clase
            IncidenciaLog nuevaIncidencia = new IncidenciaLog(msg, fecha, false, tipo, jugadorAfectado, equipoAfectado, entrenadorAfectado);
            // Se registra la incidencia en la liga
            liga.registrarIncidencia(nuevaIncidencia);
            System.out.println("Expediente registrado correctamente.");

        } else if (op == 2) {
            liga.listarIncidencias();
        }
    }

	// Menú para gestionar el sistema de entrenamiento, permite entrenar a un jugador específico o a todos los titulares de un equipo.
    private static void menuEntrenamiento() {
        System.out.println("\n--- SISTEMA DE ENTRENAMIENTO ---");
        System.out.println("[1] Entrenar Jugador | [2] Entrenar Equipo Completo | [3] Volver");

        // Lee la opción introducida
        int op = Integer.parseInt(teclado.nextLine());

        switch (op) {
            // ENTRENAMIENTO INDIVIDUAL
            case 1 -> {
                System.out.print("ID del Jugador: ");
                PersonaLiga p = liga.buscarPersonaPorId(teclado.nextLine());
                // Comprueba que la persona sea jugador
                if (p instanceof Jugador j) {
                    if (j.entrenar()) {
                        System.out.println("Entrenamiento completado exitosamente.");
                        // Muestra hasta 2 decimales en el reindimiento
                        System.out.println(j.getNickname() + " - Nivel Mecanico: " + j.getNivelMecanico() + " | Nivel Estrategico: " + j.getNivelEstrategico() + " | Rendimiento: " + String.format("%.2f", j.calcularRendimiento()));
                    } else {
                        // Si devuelve false, la probabilidad aleatoria falló. El jugador no mejora.
                        System.out.println("Entrenamiento fallido. El jugador no mejoro sus estadisticas hoy.");
                    }
                } else {
                    // Si el ID introducido no era de un Jugador (era un Entrenador o no existía)
                    System.out.println("El ID ingresado no corresponde a un jugador.");
                }
            }
            // ENTRENAMIENTO DE EQUIPO COMPLETO
            case 2 -> {
                System.out.print("Nombre del Equipo: ");
                // Buscam el equipo en la base de datos de la liga por su nombre exacto
                Equipo eq = liga.buscarEquipoPorNombre(teclado.nextLine());
                // Verifica que el equipo introducido existe realmente
                if (eq != null) {
                    System.out.println("Entrenando a la plantilla titular de " + eq.getNombre() + "...\n");
                    for (Jugador j : eq.getTitulares()) {
                        // Verifica que esa posición (0 a 4) no esté vacía
                        if (j != null) {
                            j.entrenar();
                            // Muestra el resultado del entrenamiento para cada jugador, con su rendimiento actualizado y formateado a 2 decimales
                            System.out.println(j.getNickname() + " - Nivel Mecanico: " + j.getNivelMecanico() + " | Nivel Estrategico: " + j.getNivelEstrategico() + " | Rendimiento: " + String.format("%.2f", j.calcularRendimiento()));
                        } else {
                            // Si en esa posición del array hay un hueco libre, avisa de que falta un jugador
                            System.out.println("[Puesto Vacante]");
                        }
                    }
                    // Mensaje final cuando el bucle for termina de recorrer a los 5 titulares
                    System.out.println("\nSesion de entrenamiento completada.");
                } else {
                    // Si el nombre del equipo introducido no coincide con ninguno registrado
                    System.out.println("Equipo no encontrado.");
                }
            }
        }
    }

    private static void menuAuditoria() {
        System.out.println("\n--- AUDITORÍA DE SISTEMA ---");
        System.out.println("[1] Ver Última Acción | [2] Ver Historial Completo | [3] Deshacer Última Acción | [4] Volver");
        int op = Integer.parseInt(teclado.nextLine());
        switch (op) {
            case 1 -> liga.mostrarUltimaAccion();
            case 2 -> liga.mostrarHistorial();
            case 3 -> liga.deshacerUltimaAccion();
        }
    }

    private static void visorPoolDioses() {
        System.out.println("\n=== ENCICLOPEDIA DE DIOSES (Total: " + poolDioses.size() + ") ===");
        for (PersonajeJuego dios : poolDioses) {
            System.out.println("🔱 " + dios.toString() + " | Dificultad: " + dios.getDificultad() + " | Winrate: " + dios.getWinRate() + "%");
        }
        System.out.println("======================================================");
    }

    // METODO DE INICIALIZACION DE DATOS (Dioses, Equipos, Jugadores) - Simula la carga masiva de datos reales de la SPL 2021 en un estado activo de la temporada (con partidos ya disputados y estadísticas pobladas)
    private static void inicializarSistemaCompleto() {
        System.out.println("[INICIALIZACIÓN] Cargando base de datos SPL Season 2021...");
        try {
            cargarDiosesCompletos();
            cargarEquiposYJugadoresSPL2021();

            // Genera el calendario automáticamente
            liga.generarCalendario();

            // Para que no empiece todo a 0, simula 2 partidos
            simularPartidoInterno(75, 40, 0); // Partido 1 (gana local)
            simularPartidoInterno(50, 60, 3); // Partido 2 (gana visitante)

            System.out.println("[INICIALIZACIÓN]  ¡SISTEMA OPERATIVO Y LISTO PARA USAR!");
        } catch (Exception e) {
            System.out.println("[INICIALIZACIÓN]  Error durante la carga masiva: " + e.getMessage());
        }
    }

    // Método auxiliar solo para la carga inicial. Simula partidos extrayendño datos de la cola directamente.
    private static void simularPartidoInterno(int ptsL, int ptsV, int mvps) {
        try {
            Partido p = liga.getColaPartidosPendientes().peek(); // Mira el siguiente partido en la cola sin sacarlo, para simular que se disputa
            if(p != null) { // Si hay partido
                Jugador mvp; // Jugador MVP se asigna automáticamente
                if (ptsL > ptsV)
					mvp = p.getLocal().getTitulares()[mvps]; // Simplificación para asignar MVP al primer titular del equipo ganador
                else
					mvp = p.getVisitante().getTitulares()[mvps]; // else asigna al primer titular del equipo visitante
				// Llamada al método de registro de partido disputado, que actualiza estadísticas, clasificaciones, etc.
                p.registrarResultado(ptsL, ptsV, mvp);
                liga.getColaPartidosPendientes().poll();
                liga.getPartidosJugados().add(p);
            }
        } catch (PartidoInvalidoException ignored) {} // En este contexto de simulación, si el partido no es válido, simplemente lo ignoramos y no lo registramos.
    }
	// Método auxiliar para cargar un pool de dioses completo con datos reales de la SPL 2021, incluyendo su nombre, panteón, rol, dificultad y winrate aproximado basado en estadísticas públicas de la temporada.
    private static void cargarDiosesCompletos() {
        System.out.println("  -> Cargando Panteones Divinos...");
        // Griegos
        poolDioses.add(new PersonajeJuego("Zeus", "Griego", "Mago", 4, 51.2));
        poolDioses.add(new PersonajeJuego("Ares", "Griego", "Guardián", 3, 49.5));
        poolDioses.add(new PersonajeJuego("Poseidón", "Griego", "Mago", 3, 50.1));
        poolDioses.add(new PersonajeJuego("Athena", "Griego", "Guardián", 5, 53.4));
        poolDioses.add(new PersonajeJuego("Nemesis", "Griego", "Asesino", 6, 48.9));
        poolDioses.add(new PersonajeJuego("Apollo", "Griego", "Cazador", 4, 52.0));
        // Nórdicos
        poolDioses.add(new PersonajeJuego("Thor", "Nórdico", "Asesino", 6, 47.8));
        poolDioses.add(new PersonajeJuego("Loki", "Nórdico", "Asesino", 4, 46.5));
        poolDioses.add(new PersonajeJuego("Odin", "Nórdico", "Guerrero", 2, 51.0));
        poolDioses.add(new PersonajeJuego("Freya", "Nórdico", "Mago", 7, 49.9));
        poolDioses.add(new PersonajeJuego("Ullr", "Nórdico", "Cazador", 9, 45.2));
        poolDioses.add(new PersonajeJuego("Ymir", "Nórdico", "Guardián", 2, 54.1));
		poolDioses.add(new PersonajeJuego("Tyr", "Nórdico", "Guerrero", 3, 78.0)); // Meta 2021 OTP Julio el mejor top del mundo con Tyr y mi dios preferido ^^
        // Egipcios
        poolDioses.add(new PersonajeJuego("Ra", "Egipcio", "Mago", 2, 50.8));
        poolDioses.add(new PersonajeJuego("Anubis", "Egipcio", "Mago", 2, 48.0));
        poolDioses.add(new PersonajeJuego("Osiris", "Egipcio", "Guerrero", 8, 52.3));
        poolDioses.add(new PersonajeJuego("Set", "Egipcio", "Asesino", 9, 53.8));
        poolDioses.add(new PersonajeJuego("Neith", "Egipcio", "Cazador", 1, 49.2));
        // Chinos
        poolDioses.add(new PersonajeJuego("Sun Wukong", "Chino", "Guerrero", 5, 50.5));
        poolDioses.add(new PersonajeJuego("Nu Wa", "Chino", "Mago", 3, 51.1));
        poolDioses.add(new PersonajeJuego("Ao Kuang", "Chino", "Mago", 7, 52.9));
        // Romanos
        poolDioses.add(new PersonajeJuego("Bellona", "Romano", "Guerrero", 4, 50.0));
        poolDioses.add(new PersonajeJuego("Janus", "Romano", "Mago", 8, 48.5));
        poolDioses.add(new PersonajeJuego("Bacchus", "Romano", "Guardián", 3, 51.6));
        // Japoneses
        poolDioses.add(new PersonajeJuego("Amaterasu", "Japonés", "Guerrero", 6, 52.1));
        poolDioses.add(new PersonajeJuego("Susano", "Japonés", "Asesino", 8, 50.7));
        poolDioses.add(new PersonajeJuego("Tsukuyomi", "Japonés", "Asesino", 7, 51.8));
        // Babilonios & Artúricos
        poolDioses.add(new PersonajeJuego("Gilgamesh", "Babilonio", "Guerrero", 5, 55.2)); // Meta 2021
        poolDioses.add(new PersonajeJuego("Tiamat", "Babilonio", "Mago", 10, 54.8)); // Meta 2021
        poolDioses.add(new PersonajeJuego("King Arthur", "Artúrico", "Guerrero", 8, 49.3));
        poolDioses.add(new PersonajeJuego("Merlin", "Artúrico", "Mago", 8, 50.9));
        poolDioses.add(new PersonajeJuego("Morgan Le Fay", "Artúrico", "Mago", 6, 52.4));
    }
	// Método auxiliar para simplificar la creación, registro y fichaje de jugadores. Recibe el equipo al que se va a fichar, los datos del jugador y su rol específico dentro del equipo.
    private static void cargarEquiposYJugadoresSPL2021() throws Exception {
        System.out.println("  -> Cargando Equipos y Rosters SPL 2021...");

        // ==========================================
        // 1. ATLANTIS LEVIATHANS
        // ==========================================
        Equipo leviathans = new Equipo("Atlantis Leviathans", "Latam/NA", 500000.0); // Crea un nuevo Equipo con nombre, ciudad y presupuesto
        Entrenador EntrenadorLev = new Entrenador("C-LEV", "Felipe", "Oxilede", 28, 4000.0, 5, "Drafting"); // Crea un nuevo entrenador con ID, nombre, nickname, edad, salario, años de experiencia y especialidad
        liga.altaPersona(EntrenadorLev); // Registra al entrenador en la liga (importante para que luego pueda ser asignado al equipo y para que aparezca en listados de personas)
        liga.crearEquipo(leviathans); // Registra el equipo en la liga
        leviathans.setEntrenador(EntrenadorLev); // Asigna el entrenador al equipo (puede hacerse también desde el menú de fichajes)

		// Ficha a los jugadores titulares del equipo utilizando el método auxiliar para simplificar la creación y registro de jugadores. Se le pasan los datos necesarios para cada jugador, incluyendo su rol específico dentro del equipo.
        ficharAutomatico(leviathans, "J-LEV1", "Julio", "Julio", 21, Rol.TOP);
        ficharAutomatico(leviathans, "J-LEV2", "Francisco", "Panitom", 22, Rol.JUNGLE);
        ficharAutomatico(leviathans, "J-LEV3", "Steven", "Sheento", 20, Rol.MID);
        ficharAutomatico(leviathans, "J-LEV4", "Jarcorr", "Jarcorr", 23, Rol.ADC);
        ficharAutomatico(leviathans, "J-LEV5", "Ronngyu", "Ronngyu", 24, Rol.SUPPORT);

        // ==========================================
        // 2. CAMELOT KINGS
        // ==========================================
        Equipo kings = new Equipo("Camelot Kings", "Europa", 480000.0); // Crea un nuevo Equipo con nombre, ciudad y presupuesto
        Entrenador EntrenadorKings = new Entrenador("C-KIN", "Michael", "Slainy", 30, 4200.0, 6, "Análisis");
        liga.altaPersona(EntrenadorKings);
        liga.crearEquipo(kings);
        kings.setEntrenador(EntrenadorKings);

        ficharAutomatico(kings, "J-KIN1", "Harry", "Variety", 25, Rol.TOP);
        ficharAutomatico(kings, "J-KIN2", "Twig", "CaptainTwig", 27, Rol.JUNGLE);
        ficharAutomatico(kings, "J-KIN3", "Tingz", "BigManTingz", 24, Rol.MID);
        ficharAutomatico(kings, "J-KIN4", "Netrioid", "Netrioid", 21, Rol.ADC);
        ficharAutomatico(kings, "J-KIN5", "Genetics", "Genetics", 22, Rol.SUPPORT);

        // ==========================================
        // 3. JADE DRAGONS
        // ==========================================
        Equipo dragons = new Equipo("Jade Dragons", "Norteamérica", 550000.0);
        Entrenador EntrenadorDragons = new Entrenador("C-DRA", "Rowe", "Rowe", 32, 4500.0, 8, "Mentalidad");
        liga.altaPersona(EntrenadorDragons);
        liga.crearEquipo(dragons);
        dragons.setEntrenador(EntrenadorDragons);

        ficharAutomatico(dragons, "J-DRA1", "Alec", "fineokay", 24, Rol.TOP);
        ficharAutomatico(dragons, "J-DRA2", "Sam", "sam4soccer2", 23, Rol.JUNGLE);
        ficharAutomatico(dragons, "J-DRA3", "Hurri", "Hurriwind", 25, Rol.MID);
        ficharAutomatico(dragons, "J-DRA4", "Peter", "PandaCat", 26, Rol.ADC);
        ficharAutomatico(dragons, "J-DRA5", "Mike", "PolarBearMike", 25, Rol.SUPPORT);

        // ==========================================
        // 4. OLYMPUS BOLTS
        // ==========================================
        Equipo bolts = new Equipo("Olympus Bolts", "Norteamérica", 460000.0);
        Entrenador EntrenadorBolts = new Entrenador("C-BOL", "Chuck", "ElChuckles", 29, 3900.0, 4, "Estrategia");
        liga.altaPersona(EntrenadorBolts);
        liga.crearEquipo(bolts);
        bolts.setEntrenador(EntrenadorBolts);

        ficharAutomatico(bolts, "J-BOL1", "Haddix", "Haddix", 21, Rol.TOP);
        ficharAutomatico(bolts, "J-BOL2", "Lasbra", "Lasbra", 24, Rol.JUNGLE);
        ficharAutomatico(bolts, "J-BOL3", "Venenu", "Venenu", 23, Rol.MID);
        ficharAutomatico(bolts, "J-BOL4", "John", "BaRRaCCuDDa", 28, Rol.ADC);
        ficharAutomatico(bolts, "J-BOL5", "Jake", "AwesomeJake408", 22, Rol.SUPPORT);

        // ==========================================
        // 5. TARTARUS TITANS
        // ==========================================
        Equipo titans = new Equipo("Tartarus Titans", "Mixto", 490000.0);
        Entrenador EntrenadorTitans = new Entrenador("C-TIT", "Cabrakan", "TitansEntrenador", 35, 3800.0, 7, "Mecánica");
        liga.altaPersona(EntrenadorTitans);
        liga.crearEquipo(titans);
        titans.setEntrenador(EntrenadorTitans);

        ficharAutomatico(titans, "J-TIT1", "Benji", "Benji", 26, Rol.TOP);
        ficharAutomatico(titans, "J-TIT2", "Scary", "ScaryD", 25, Rol.JUNGLE);
        ficharAutomatico(titans, "J-TIT3", "Paul", "Paul", 22, Rol.MID);
        ficharAutomatico(titans, "J-TIT4", "Spin", "CycloneSpin", 24, Rol.ADC);
        ficharAutomatico(titans, "J-TIT5", "Aror", "Aror", 27, Rol.SUPPORT);

        // ==========================================
        // 6. ONI HOMIES
        // ==========================================
        Equipo homies = new Equipo("Oni Homies", "Europa/NA", 440000.0);
        Entrenador EntrenadorHomies = new Entrenador("C-ONI", "Neil", "OniEntrenador", 31, 3700.0, 5, "Motivación");
        liga.altaPersona(EntrenadorHomies);
        liga.crearEquipo(homies);
        homies.setEntrenador(EntrenadorHomies);

        ficharAutomatico(homies, "J-ONI1", "Nika", "Nika", 24, Rol.TOP);
        ficharAutomatico(homies, "J-ONI2", "Fred", "QvoFred", 26, Rol.JUNGLE);
        ficharAutomatico(homies, "J-ONI3", "Dardez", "Dardez", 23, Rol.MID);
        ficharAutomatico(homies, "J-ONI4", "Vote", "Vote", 25, Rol.ADC);
        ficharAutomatico(homies, "J-ONI5", "Neil", "NeilMah", 28, Rol.SUPPORT);

        // ==========================================
        // 7. VALHALLA VALKYRIES
        // ==========================================
        Equipo valks = new Equipo("Valhalla Valkyries", "Europa", 420000.0);
        Entrenador EntrenadorValks = new Entrenador("C-VAL", "Valk", "ValkEntrenador", 33, 3500.0, 6, "Drafting");
        liga.altaPersona(EntrenadorValks);
        liga.crearEquipo(valks);
        valks.setEntrenador(EntrenadorValks);

        ficharAutomatico(valks, "J-VAL1", "Duck", "Duck3y", 25, Rol.TOP);
        ficharAutomatico(valks, "J-VAL2", "Dzoni", "Dzoni", 21, Rol.JUNGLE);
        ficharAutomatico(valks, "J-VAL3", "Zyrhoes", "Zyrhoes", 27, Rol.MID);
        ficharAutomatico(valks, "J-VAL4", "Emil", "emilitoo", 26, Rol.ADC);
        ficharAutomatico(valks, "J-VAL5", "Raffer", "iRaffer", 29, Rol.SUPPORT);

        // ==========================================
        // 8. SOLAR SCARABS
        // ==========================================
        Equipo scarabs = new Equipo("Solar Scarabs", "Mixto", 410000.0);
        Entrenador EntrenadorScarabs = new Entrenador("C-SCA", "Scarab", "SlainyJr", 26, 3000.0, 3, "Análisis");
        liga.altaPersona(EntrenadorScarabs);
        liga.crearEquipo(scarabs);
        scarabs.setEntrenador(EntrenadorScarabs);

        ficharAutomatico(scarabs, "J-SCA1", "SoT", "SoloOrTroll", 21, Rol.TOP);
        ficharAutomatico(scarabs, "J-SCA2", "Scream", "Screammmmm", 24, Rol.JUNGLE);
        ficharAutomatico(scarabs, "J-SCA3", "Boro", "oBoronic", 20, Rol.MID);
        ficharAutomatico(scarabs, "J-SCA4", "Zap", "Zapman", 29, Rol.ADC);
        ficharAutomatico(scarabs, "J-SCA5", "Bobby", "Inbowned", 22, Rol.SUPPORT);
    }

    // Método auxiliar privado para crear un jugador, registrarlo en la liga y ficharlo como titular rápidamente.
    private static void ficharAutomatico(Equipo equipo, String id, String nom, String nick, int edad, Rol rol) throws Exception {
        Jugador j = new Jugador(id, nom, nick, edad, 2000.0, rol);
        liga.altaPersona(j);
        equipo.ficharTitular(j);
    }

    // Métodos auxiliares GESTION DE PERSONAS
    private static void altaPersona() throws PersonaDuplicadaException {
        System.out.println("\n--- REGISTRO DE NUEVA PERSONA ---");
        System.out.print("ID: "); String id = teclado.nextLine();
        System.out.print("Nombre Real: "); String nom = teclado.nextLine();
        System.out.print("Nickname: "); String nick = teclado.nextLine();
        System.out.print("Edad: "); int edad = Integer.parseInt(teclado.nextLine());
        System.out.print("Salario Base (€): "); double sal = Double.parseDouble(teclado.nextLine());

        System.out.print("¿Tipo? [J] Jugador | [E] Entrenador: ");
        String tipo = teclado.nextLine();

        if (tipo.equalsIgnoreCase("J")) {
            System.out.println("Roles: 1.TOP | 2.JUNGLE | 3.MID | 4.ADC | 5.SUPPORT");
            int rolId = Integer.parseInt(teclado.nextLine());
            Rol rol = Rol.values()[rolId - 1]; // Casteo para obtener la opción numérica en el enum correspondiente (asumiendo que el usuario ingresa un número del 1 al 5)
            liga.altaPersona(new Jugador(id, nom, nick, edad, sal, rol));
			liga.registrarAccion("Alta de jugador: " + nick + " (ID: " + id + ")");
            System.out.println("Jugador registrado exitosamente.");
        } else if (tipo.equalsIgnoreCase("E")) { // Si el usuario elige registrar un entrenador, se le piden los datos específicos de entrenador (años de experiencia y especialidad) y se crea una nueva instancia de Entrenador con esos datos.
            System.out.print("Años de experiencia: "); int exp = Integer.parseInt(teclado.nextLine());
            System.out.print("Especialidad: "); String esp = teclado.nextLine();
            liga.altaPersona(new Entrenador(id, nom, nick, edad, sal, exp, esp));
			liga.registrarAccion("Alta de entrenador: " + nick + " (ID: " + id + ")");
            System.out.println("Entrenador registrado exitosamente.");
        }
    }

    private static void buscarPersona() {
        System.out.print("Ingrese ID a buscar: ");
        PersonaLiga p = liga.buscarPersonaPorId(teclado.nextLine()); //
        if (p != null) {
            p.mostrarResumen();
        } else {
            System.out.println("Persona no encontrada.");
        }
    }

	private static void modificarPersona() {
        System.out.print("ID de la persona a modificar: ");
        PersonaLiga p = liga.buscarPersonaPorId(teclado.nextLine());

        if (p == null) {
            System.out.println("Persona no encontrada.");
            return;
        }

        System.out.println("\nModificando a: " + p.getNickname() + " (ID: " + p.getIdentificador() + ")");
        // Añadimos visualmente la opción 4 solo si es Entrenador
        System.out.print("[1] Nickname | [2] Edad | [3] Salario");
        if (p instanceof Entrenador) System.out.print(" | [4] Especialidad");

        System.out.print("\nSeleccione opción: ");
        int mod = Integer.parseInt(teclado.nextLine());

        String cambioRealizado = "";

        switch (mod) {
            case 1 -> {
                String antiguoNick = p.getNickname();
                System.out.print("Nuevo nickname (Actual: " + antiguoNick + "): ");
                String nuevoNick = teclado.nextLine();
                p.setNickname(nuevoNick);
                cambioRealizado = "Nickname cambiado de '" + antiguoNick + "' a '" + nuevoNick + "'";
                System.out.println("Actualizado: " + antiguoNick + " -> " + nuevoNick);
            }
            case 2 -> {
                int antiguaEdad = p.getEdad();
                System.out.print("Nueva edad (Actual: " + antiguaEdad + "): ");
                int nuevaEdad = Integer.parseInt(teclado.nextLine());
                p.setEdad(nuevaEdad);
                cambioRealizado = "Edad cambiada de " + antiguaEdad + " a " + nuevaEdad;
                System.out.println("Actualizado: " + antiguaEdad + " años -> " + nuevaEdad + " años");
            }
            case 3 -> {
                double antiguoSalario = p.getSalarioBase();
                System.out.print("Nuevo salario (Actual: " + antiguoSalario + "€): ");
                double nuevoSalario = Double.parseDouble(teclado.nextLine());
                p.setSalarioBase(nuevoSalario);
                cambioRealizado = "Salario cambiado de " + antiguoSalario + "€ a " + nuevoSalario + "€";
                System.out.println("Actualizado: " + antiguoSalario + "€ -> " + nuevoSalario + "€");
            }
            case 4 -> {
                // Si es un entrenador, le damos la opción de cambiar su especialidad. Si no es entrenador, esta opción no aparecerá
                if (p instanceof Entrenador e) {
                    String antiguaEsp = e.getEspecialidad();
                    System.out.print("Nueva especialidad (Actual: " + antiguaEsp + "): ");
                    String nuevaEsp = teclado.nextLine();
                    e.setEspecialidad(nuevaEsp);
                    cambioRealizado = "Especialidad cambiada de '" + antiguaEsp + "' a '" + nuevaEsp + "'";
                    System.out.println("Actualizado: " + antiguaEsp + " -> " + nuevaEsp);
                } else {
                    System.out.println("Opción no válida para jugadores.");
                }
            }
            default -> System.out.println("Opción inválida.");
        }
        // Registro en la pila de auditoría de la Liga
        if (!cambioRealizado.isEmpty()) {
            liga.registrarAccion(cambioRealizado); // Este método guarda el String en el Stack
            System.out.println("Cambio registrado en auditoría.");
        }
    }
    private static void eliminarPersona() {
        System.out.print("ID de la persona a eliminar: ");
        String id = teclado.nextLine();
        PersonaLiga p = liga.buscarPersonaPorId(id);
		// Ocurría que verificaba un titular y no lo eliminaba pero saltaba mensaje de persona eliminada, ahora primero verifica que la persona existe, luego intenta eliminarla y solo si la liga confirma que se eliminó, muestra el mensaje de confirmación. Si no existía, muestra el mensaje de persona no encontrada.
        if (p != null) {
            // Guarda el resultado de la eliminación para verificar si fue exitosa
            boolean exito = liga.eliminarPersona(id);
            // Solo imprime la confirmación si la Liga realmente lo eliminó
            if (exito) {
				liga.registrarAccion("Eliminación de persona: " + id);
                System.out.println("Persona eliminada del sistema exitosamente.");
            }
        } else {
            System.out.println("Persona no encontrada.");
        }
    }


    // Métodos auxiliares GESTION DE EQUIPOS
    private static void crearEquipo() throws EquipoDuplicadoException {
        System.out.print("Nombre del Equipo: "); String nom = teclado.nextLine();
        System.out.print("Ciudad Base: "); String ciu = teclado.nextLine();
        System.out.print("Presupuesto (€): "); double pre = Double.parseDouble(teclado.nextLine());

        liga.crearEquipo(new Equipo(nom, ciu, pre)); //
		liga.registrarAccion("Nuevo equipo creado: " + nom);
        System.out.println("Equipo creado exitosamente.");
    }

    private static void verPlantillaEquipo() {
        System.out.print("Nombre del Equipo: ");
        Equipo eq = liga.buscarEquipoPorNombre(teclado.nextLine()); //
        if (eq != null) {
            eq.mostrarPlantilla(); // Este método debe estar en Equipo.java
        } else {
            System.out.println("Equipo no encontrado.");
        }
    }
}
