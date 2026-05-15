package modelo;
// Esta clase actua como actor principal en el juego, nos indica el rol que juega, el nivel mecánico, el nivel estrategico, el numero de partidas jugadas, el numero de MVP totales y si esta sancionado
// Además he implementado el uso de Map con la mecánica del propio personaje que el jugador pickea, teniendo sus respectivas estadísticas

import java.util.HashMap;
import java.util.Map;

// Esta clase actua como actor principal en el juego, nos indica el rol que juega, el nivel mecánico, el nivel estrategico, el numero de partidas jugadas, el numero de MVP totales y si esta sancionado
// Además he implementado el uso de Map con la mecánica del propio personaje que el jugador pickea, teniendo sus respectivas estadísticas
public class Jugador extends PersonaLiga implements Entrenable, Comparable<Jugador> {

    // Atributos de clase Jugador
    // He usado wrappers para que puedan ser tratados como objetos
    private Rol rol;
    private Integer nivelMecanico; // 0 a 100
    private Integer nivelEstrategico; // 0 a 100
    private Integer partidasJugadas;
    private Integer mvpTotales;
    private Boolean sancionado; // Si el jugador esta sancionado, no puede jugar ni ser convocado como roaster principal

    // Sistema de picks, he querido crear una relación entre jugador y dios/personaje que juegue, tendrá sus propias estadisticas de winrate dentro de un Map
    private Map<PersonajeJuego, EstadisticasDelPickPersonaje> estadisticasPorPersonaje;

    // Constructor de la clase jugador
    public Jugador(String id, String nombre, String nickname, Integer edad, Double salarioBase, Rol rol) {
        // Llamada al constructor de la clase abstracta PersonaLiga
        super(id, nombre, nickname, edad, salarioBase);
        this.rol = rol;
        this.nivelMecanico = 50; // Valor inicial base que tomo respecto a 100
        this.nivelEstrategico = 50;
        this.partidasJugadas = 0;
        this.mvpTotales = 0;
        this.sancionado = false;
        this.estadisticasPorPersonaje = new HashMap<>(); // Inicializo un map donde almaceno las estadisticas de los personajes
    }

    // Getters
    public Rol getRol() { return rol; }
    public Integer getNivelMecanico() { return nivelMecanico; }
    public Integer getNivelEstrategico() { return nivelEstrategico; }
    public Integer getPartidasJugadas() { return partidasJugadas; }
    public Integer getMvpTotales() { return mvpTotales; }
    public Boolean getIsSancionado() { return sancionado; }

    // Setters, no necesito setter de partidasJugadas, mvpTotales y estadisticasPorPersonaje, ya que tendrán su propio metodo que los actualice
    public void setRol(Rol rol) { this.rol = rol; }
    public void setNivelMecanico(Integer nivelMecanico) { this.nivelMecanico = nivelMecanico; }
    public void setNivelEstrategico(Integer nivelEstrategico) { this.nivelEstrategico = nivelEstrategico; }
    public void setIsSancionado(Boolean sancionado) { this.sancionado = sancionado; }

    // Devuelve las estadisticas de un personaje en concreto que se le pasa por parámetro usando el get de la clase EstadisticasDelPickPersonaje y el metodo toString
    public String getEstadisticasPorPersonaje(PersonajeJuego personaje) {
        // He corregido el comportamiento del método con un if, al no tener un personaje que el jugador nunca haya usado se devuelve 'null' en lugar de 'No hay estadísticas registradas para este personaje.' y esto supondría un NullPointerException ya que llama al metodo toString
        if (estadisticasPorPersonaje.containsKey(personaje)) {
            return estadisticasPorPersonaje.get(personaje).toString();
        }
        return "No hay estadísticas registradas para este personaje.";
    }

    // Método que calcula el coste del jugador al mes, se le suma el plus de cuántos mvp ha tenido en la temporada
    @Override
    public Double calcularCosteMensual() {  return getSalarioBase() + (mvpTotales * 10.0); }

    // Método mostrarResumen que muestra por pantalla varios atributos principales del jugador
    @Override
    public void mostrarResumen() {
        System.out.println("JUGADOR [" + getRol() + "]: " + getNickname() + " | Rendimiento: " + calcularRendimiento() + " | Partidas jugadas: " + getPartidasJugadas() + " | MVP totales: " + getMvpTotales() + " | Sancionado: " + getIsSancionado());
    }

    // Implementación de la interfaz Entrenable que nos dice si el jugador puede entrenar
   @Override
    public boolean entrenar() {
        // Math.random() genera un número entre 0.0 y 1.0
        double suerte = Math.random();

        // 70% de probabilidad de éxito (de 0.3 a 1.0)
        if (suerte >= 0.30) {
            // Si tiene éxito sube entre 1 y 5 puntos sus estadísticas
            this.setNivelMecanico(this.getNivelMecanico() + (int)(Math.random() * 5) + 1);
            this.setNivelEstrategico(this.getNivelEstrategico() + (int)(Math.random() * 5) + 1);

            // Lñimite de 100 para las estadísticas
            if (this.getNivelMecanico() > 100) this.setNivelMecanico(100);
            if (this.getNivelEstrategico() > 100) this.setNivelEstrategico(100);

            return true; // Ha funcionado

        } else {
            // No funcionó: El jugador no mejora
            return false;
        }
    }

    @Override
    public double calcularRendimiento() {
        // El rendimiento medio según el enunciado
        return (nivelMecanico + nivelEstrategico) / 2.0;
    }

	// Método que registra los resultados de una partida, se le pasa el personaje que ha jugado, si ha ganado y si ha sido MVP
    public void registrarResultado(PersonajeJuego p, boolean victoria, boolean esMvp) {
        this.partidasJugadas++;
        if (esMvp) {
            this.mvpTotales++;
        }
        // Si no existe el personaje en la pool que nos ofrece el juego, lo añadimos
        if (!estadisticasPorPersonaje.containsKey(p)) {
			// con .put actualizo el map con clave valor, la clave será el personaje y el valor las estadísticas, con el new creo una instancia de la clase EstadisticasDelPickPersonaje nueva para este personaje en concreto
            estadisticasPorPersonaje.put(p, new EstadisticasDelPickPersonaje());
        }
        // Actualizamos las stats del pick
        EstadisticasDelPickPersonaje stats = estadisticasPorPersonaje.get(p);
        stats.incrementarPartida(victoria);
    }

	// Método que incrementa el contador de partidas jugadas en 1, se llama desde la clase Partido cuando se juega una partida
	public void incrementarPartidas() { this.partidasJugadas++; }

    // Método que incrementa el contador de MVPs en 1, se llama cuando el jugador es elegido MVP
    public void incrementarMVP() { this.mvpTotales++; }

    // Método toString cib el formato de la clase padre PersonaLiga y se le añaden los datos específicos del Jugador.
    @Override
    public String toString() {
        return super.toString() + " | Rol: " + rol + " | MVP: " + mvpTotales + " | Rendimiento : " + calcularRendimiento() + " | Sancionado: " + getIsSancionado();
    }

	// Método compareTo para comparar jugadores por su rendimiento, se ordenarán de mayor a menor rendimiento
	@Override
	public int compareTo(Jugador otro) {
        if (this.calcularRendimiento() == otro.calcularRendimiento()) {
            return 0;
        } else if (this.calcularRendimiento() > otro.calcularRendimiento()) {
            return -1; // -1 para orden descendente
        } else {
            return 1;
        }
    }
}
