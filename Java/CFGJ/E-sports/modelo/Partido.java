package modelo;

import excepciones.PartidoInvalidoException;

/* Clase Partido, representa un enfrentamiento entre dos equipos en la liga.
 	Un partido contiene:
 	- Identificador único
 	- Jornada a la que pertenece
	- Equipo local y visitante
 	- Resultado (puntos de cada equipo)
 	- MVP del partido
 	- Estado (jugado o pendiente)
 */
public class Partido {

    private String id;
    private int jornada;
    private Equipo local;
    private Equipo visitante;
	private int puntosLocal;
    private int puntosVisitante;
    private Jugador mvp;
    private Boolean jugado = false;

    //  Constructor de Partido, crea un partido pendiente de disputar entre dos equipos, los puntos y el MVP se asignarán cuando se registre el resultado, se valida de que un equipo no juegue contra si mismo
    public Partido(String id, int jornada, Equipo local, Equipo visitante) throws PartidoInvalidoException {
        // Un equipo no puede jugar contra sí mismo
        if (local.equals(visitante)) {
            throw new PartidoInvalidoException( "Un equipo no puede jugar contra sí mismo. " + "Local y visitante deben ser equipos diferentes.");
        }
        this.id = id;
        this.jornada = jornada;
        this.local = local;
        this.visitante = visitante;
        this.puntosLocal = 0;
        this.puntosVisitante = 0;
        this.mvp = null;
        this.jugado = false;
    }

    // MÉTODOS PRINCIPALES

    /* Método que registra el resultado de un partido ya disputado.
	  	1. Guarda el resultado (puntos de cada equipo)
     	2. Asigna el MVP
     	3. Marca el partido como jugado
      	4. Actualiza las estadísticas de ambos equipos
     	5. Actualiza las estadísticas individuales de jugadores
 */

    public void registrarResultado(int puntosLocal, int puntosVisitante, Jugador mvp) throws PartidoInvalidoException {
        // Verifica que el partido no se haya jugado ya
        if (jugado) {
            throw new PartidoInvalidoException("Este partido ya ha sido disputado. " + "No se puede registrar el resultado dos veces. " + "ID del partido: " + id);
        }
        // Guardar el resultado
        this.puntosLocal = puntosLocal;
        this.puntosVisitante = puntosVisitante;
        this.mvp = mvp;
        this.jugado = true;

        // Actualizar estadísticas de los equipos
        actualizarEstadisticasEquipos();

        // Actualizar estadísticas de los jugadores
        actualizarEstadisticasJugadores();

        // Mensaje de confirmación
        System.out.println("\n RESULTADO REGISTRADO:");
        System.out.println("   " + local.getNombre() + " " + puntosLocal + " - " + puntosVisitante + " " + visitante.getNombre());
        if (mvp != null) {
            System.out.println("   MVP: " + mvp.getNickname());
        }
    }

    /* Método que actualiza las estadísticas de ambos equipos según el resultado. Método privado: solo se llama desde registrarResultado()
		- El equipo con más puntos gana
     	- Se actualizan victorias/derrotas
     	- Se suman los puntos a favor y en contra
     */
    private void actualizarEstadisticasEquipos() {
        if (puntosLocal > puntosVisitante) {
            // Victoria Local
            local.registrarVictoria(puntosLocal);
            visitante.registrarDerrota(puntosLocal);

            // Actualizar puntos en contra
            local.setPuntosEnContra(local.getPuntosEnContra() + puntosVisitante);
            visitante.setPuntosAFavor(visitante.getPuntosAFavor() + puntosVisitante);

        } else if (puntosVisitante > puntosLocal) {
            // Victoria Visitante
            visitante.registrarVictoria(puntosVisitante);
            local.registrarDerrota(puntosVisitante);

            // Actualizar puntos en contra
            visitante.setPuntosEnContra(visitante.getPuntosEnContra() + puntosLocal);
            local.setPuntosAFavor(local.getPuntosAFavor() + puntosLocal);

        } else {
            // En caso de empate, ambos registran como derrota
            local.registrarDerrota(puntosVisitante);
            visitante.registrarDerrota(puntosLocal);

            local.setPuntosEnContra(local.getPuntosEnContra() + puntosVisitante);
            local.setPuntosAFavor(local.getPuntosAFavor() + puntosLocal);
            visitante.setPuntosEnContra(visitante.getPuntosEnContra() + puntosLocal);
            visitante.setPuntosAFavor(visitante.getPuntosAFavor() + puntosVisitante);
        }
    }

    /* Método que actualiza las estadísticas individuales de los jugadores.
     	- Incrementa su contador de partidas jugadas
     	- Incrementa su contador de MVPs
     */
    private void actualizarEstadisticasJugadores() {
        // Actualizar partidas jugadas de titulares del local
        for (Jugador j : local.getTitulares()) {
            if (j != null) {
                j.incrementarPartidas();
            }
        }
        // Actualizar partidas jugadas de titulares del visitante
        for (Jugador j : visitante.getTitulares()) {
            if (j != null) {
                j.incrementarPartidas();
            }
        }
        // Actualizar MVPs del jugador MVP
        if (mvp != null) {
            mvp.incrementarMVP();
        }
    }

    // Método que calcula qué equipo ganó el partido, compara los puntos de ambos equipos. (En un match real no habrían puntos si no condiciones como eliminar el titan/nexo pero para hacerlo más simple)

    public Equipo calcularGanador() {
        // Si no se ha jugado, no hay ganador
        if (!jugado) {
            return null;
        }
        // Comparar puntos
        if (puntosLocal > puntosVisitante) {
            return local;
        } else if (puntosVisitante > puntosLocal) {
            return visitante;
        } else {
            // Empate
            return null;
        }
    }

    /* Método que muestra la informaciñon completa del partido
     	- ID y jornada
    	- Equipos enfrentados
     	- Resultado (si se jugó)
     	- Ganador y MVP (si se jugó)
     	- Estado (pendiente/jugado)
	*/

    public void mostrarInfo() {
        System.out.println("\n┌─────────────────────────────────────────┐");
        System.out.println("│ PARTIDO " + id + " - JORNADA " + jornada);
        System.out.println("└─────────────────────────────────────────┘");

        // Mostrar enfrentamiento
        System.out.println("\n  " + local.getNombre() + " (Local)");
        System.out.println("      VS");
        System.out.println("   " + visitante.getNombre() + " (Visitante)");

        // Si ya se jugó, mostrar resultado
        if (jugado) {
            System.out.println("\n RESULTADO:");
            System.out.println("   " + local.getNombre() + " " + puntosLocal + " - " +
                             puntosVisitante + " " + visitante.getNombre());

            // Mostrar ganador
            Equipo ganador = calcularGanador();
            if (ganador != null) {
                System.out.println("\n🏆 GANADOR: " + ganador.getNombre());
            } else {
                System.out.println("\n   EMPATE");
            }

            // Mostrar MVP
            if (mvp != null) {
                System.out.println("⭐ MVP: " + mvp.getNickname() + " [" + mvp.getRol() + "]");
            }
        } else {
            System.out.println("\n ESTADO: PENDIENTE DE JUGAR");
        }
    }

    // GETTERS
    public String getId() { return id; }
    public int getJornada() { return jornada; }
    public Equipo getLocal() { return local; }
    public Equipo getVisitante() { return visitante; }
    public int getPuntosLocal() { return puntosLocal; }
    public int getPuntosVisitante() { return puntosVisitante; }
    public Jugador getMvp() { return mvp; }
    public boolean isJugado() { return jugado; }

    // SETTERS
    public void setId(String id) { this.id = id; }

    public void setJornada(int jornada) { this.jornada = jornada; }
    public void setLocal(Equipo local) { this.local = local; }
    public void setVisitante(Equipo visitante) { this.visitante = visitante; }
    public void setPuntosLocal(int puntosLocal) { this.puntosLocal = puntosLocal; }
    public void setPuntosVisitante(int puntosVisitante) { this.puntosVisitante = puntosVisitante; }
    public void setMvp(Jugador mvp) { this.mvp = mvp; }
    public void setJugado(boolean jugado) { this.jugado = jugado; }

    // toString que representa el partido, dependiendo de si se ha jugado o no
    @Override
    public String toString() {
        if (jugado) {
            // Partido ya jugado: mostrar resultado
            return "J" + jornada + ": " + local.getNombre() + " " + puntosLocal + "-" + puntosVisitante + " " + visitante.getNombre();
        } else {
            // Partido pendiente
            return "J" + jornada + ": " + local.getNombre() + " vs " + visitante.getNombre() + " [PENDIENTE]";
        }
    }
}
