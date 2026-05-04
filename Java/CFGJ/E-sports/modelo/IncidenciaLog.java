// Clase incidencia log donde se registra y almacena las incidencias de los jugadores y equipos, se declarará el tipo de incidencia, el timestamp, una descripcion de la misma y si está o no resuelta

public class IncidenciaLog {

    private String mensaje;
    private String fecha;
    private boolean resuelta;
    private String tipo;
    private Jugador jugador;
    private Equipo equipo;

    public IncidenciaLog(String mensaje, String fecha, boolean resuelta, String tipo, Jugador jugador, Equipo equipo) {
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.resuelta = resuelta;
        this.tipo = tipo;
        this.jugador = jugador;
        this.equipo = equipo;
    }
}
