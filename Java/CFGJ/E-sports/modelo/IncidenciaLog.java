package modelo;
// Clase incidencia log donde se registra y almacena las incidencias de los jugadores y equipos, se declarará el tipo de incidencia, el timestamp, una descripcion de la misma y si está o no resuelta

public class IncidenciaLog {

    private String mensaje;
    private String fecha;
    private Boolean resuelta;
    private String tipo;
    private Jugador jugador; // Puede ser null si la incidencia es solo de equipo
    private Equipo equipo;   // Puede ser null si la incidencia es solo de jugador

    public IncidenciaLog(String mensaje, String fecha, Boolean resuelta, String tipo, Jugador jugador, Equipo equipo) {
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.resuelta = resuelta;
        this.tipo = tipo;
        this.jugador = jugador;
        this.equipo = equipo;
    }

    // Getters
    public String getMensaje() { return mensaje; }
    public String getFecha() { return fecha; }
    public Boolean getResuelta() { return resuelta; }
    public String getTipo() { return tipo; }
    public Jugador getJugador() { return jugador; }
    public Equipo getEquipo() { return equipo; }

    // Setters
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setResuelta(Boolean resuelta) { this.resuelta = resuelta; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setJugador(Jugador jugador) { this.jugador = jugador; }
    public void setEquipo(Equipo equipo) { this.equipo = equipo; }

	// Método sobreescrito toString
    @Override
    public String toString() {
        String afectado = ""; // Declaro una variable string vacia llamada afectado donde se irá guardando los datos de la incidencia
		// Si el afectado es jugador, se guarda el nickname del jugador, si es equipo se guarda el nombre del equipo y si está vació se guarda "General"
        if (jugador != null) afectado += "Jugador: " + jugador.getNickname() + " ";
        if (equipo != null) afectado += "Equipo: " + equipo.getNombre();
        if (afectado.isEmpty()) afectado = "General";
		// Se devuelve el string con un timestamp, el tipo de incidencia, la descripcion, el afectado y si esta resuelta
        return "[" + fecha + "] " + tipo + " - " + mensaje + " | Afectado: " + afectado + " | Resuelta: " + resuelta;
    }
}
