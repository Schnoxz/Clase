package modelo;
// Clase incidencia log donde se registra y almacena las incidencias de los jugadores y equipos, se declarará el tipo de incidencia, el timestamp, una descripcion de la misma y si está o no resuelta

public class IncidenciaLog {

    private String mensaje;
    private String fecha;
    private Boolean resuelta;
    private String tipo;
    private Jugador jugador; // Puede ser null si la incidencia es solo de equipo
    private Equipo equipo;   // Puede ser null si la incidencia es solo de jugador
	private Entrenador entrenador; // Puede ser null si la incidencia no afecta a un entrenador

	// Constructor de la clase IncidenciaLog que recibe los datos de la incidencia y los almacena en las variables correspondientes
    public IncidenciaLog(String mensaje, String fecha, Boolean resuelta, String tipo, Jugador jugador, Equipo equipo, Entrenador entrenador) {
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.resuelta = resuelta;
        this.tipo = tipo;
        this.jugador = jugador;
        this.equipo = equipo;
        this.entrenador = entrenador;
    }

    // Getters
    public String getMensaje() { return mensaje; }
    public String getFecha() { return fecha; }
    public Boolean getResuelta() { return resuelta; }
    public String getTipo() { return tipo; }
    public Jugador getJugador() { return jugador; }
    public Equipo getEquipo() { return equipo; }
    public Entrenador getEntrenador() { return entrenador; }

    // Setters
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setResuelta(Boolean resuelta) { this.resuelta = resuelta; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setJugador(Jugador jugador) { this.jugador = jugador; }
    public void setEquipo(Equipo equipo) { this.equipo = equipo; }
    public void setEntrenador(Entrenador entrenador) { this.entrenador = entrenador; }

	// METODOS DE UTILIDAD

	// Método que marca una incidencia como resuelta, cuando se cierra un caso, se cumple una sacion, etc.
    public void marcarComoResuelta() {
        this.resuelta = true;
        System.out.println("Incidencia marcada como resuelta: " + tipo);
    }

	// Método que verifica a si a un jugador le afecta la incidencia
    public boolean afectaAJugador(Jugador j) { return jugador != null && jugador.equals(j); }

	// Método que verifica si a un equipo le afecta la incidencia
	public boolean afectaAEquipo(Equipo e) { return equipo != null && equipo.equals(e); }
	// Método sobreescrito toString
    @Override
    public String toString() {
        String afectado = ""; // Declaro una variable string vacia llamada afectado donde se irá guardando los datos de la incidencia
		// Si el afectado es jugador, se guarda el nickname del jugador, si es equipo se guarda el nombre del equipo y si está vació se guarda "General"
        if (jugador != null) afectado += "Jugador: " + jugador.getNickname() + " ";
        if (equipo != null) afectado += "Equipo: " + equipo.getNombre();
		if (entrenador != null) afectado += "Entrenador: " + entrenador.getNombre();
        if (afectado.isEmpty()) afectado = "General";
		// Se devuelve el string con un timestamp, el tipo de incidencia, la descripcion, el afectado y si esta resuelta
        return "[" + fecha + "] " + tipo + " - " + mensaje + " | Afectado: " + afectado + " | Resuelta: " + resuelta;
    }
}
