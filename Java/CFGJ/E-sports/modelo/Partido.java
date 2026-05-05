
public class Partido {

    private String id;
    private int jornada;
    private Equipo local;
    private Equipo visitante;
    private int puntosLocal;
    private int puntosVisitante;
    private Jugador mvp;
    private Boolean jugado = false;

    public Partido(String id, int jornada, Equipo local, Equipo visitante) {
        this.id = id;
        this.jornada = jornada;
        this.local = local;
        this.visitante = visitante;
    }

    // Métodos que registran los resultados y actualizan las estadisticas
}
