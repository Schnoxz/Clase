// Esta clase actua como actor principal en el juego, nos indica el rol que juega, el nivel mecánico, el nivel estrategico, el numero de partidas jugadas, el numero de MVP totales y si esta sancionado
// Además he implementado el uso de Map con la mecánica del propio personaje que el jugador pickea, teniendo sus respectivas estadísticas

import java.util.HashMap;
import java.util.Map;

// Esta clase actua como actor principal en el juego, nos indica el rol que juega, el nivel mecánico, el nivel estrategico, el numero de partidas jugadas, el numero de MVP totales y si esta sancionado
// Además he implementado el uso de Map con la mecánica del propio personaje que el jugador pickea, teniendo sus respectivas estadísticas
public class Jugador extends PersonaLiga implements Entrenable {

    // Atributos de clase Jugador
    // He usado wrappers para que puedan ser tratados como objetos
    private Rol rol;
    private Integer nivelMecanico;
    private Integer nivelEstrategico;
    private Integer partidasJugadas;
    private Integer mvpTotales;
    private Boolean sancionado;

    // Sistema de picks, he querido crear una relación entre jugador y dios/personaje que juegue, ello tendrá sus propias estadisticas
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
    public Double calcularCosteMensual() { // Se declara como un wrapper para que puedan ser tratados como objetos
        return getSalarioBase() + (mvpTotales * 10.0);
    }

    // Método mostrarResumen que muestra por pantalla varios atributos principales del jugador
    @Override
    public void mostrarResumen() {
        System.out.println("JUGADOR [" + getRol() + "]: " + getNickname() + " | Rendimiento: " + calcularRendimiento());
    }

    // Implementación de la interfaz Entrenable que nos dice si el jugador puede entrenar
    @Override
    public void entrenar() {
        // Cada vez que un jugador entrena sube 2 en el nivel mecánico y 1 en el nivel estrategico hasta llegar a 100, esto solo ocurre una vez por cada entrenamiento, se observará como funcionará el sistema de entrenar y si habrá limites
        if (nivelMecanico < 100) {
            nivelMecanico += 2;
        }
        if (nivelEstrategico < 100) {
            nivelEstrategico += 1;
        }
        System.out.println(getNickname() + " ha terminado su entrenamiento.");
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

    // Método toString cib el formato de la clase padre PersonaLiga y se le añaden los datos específicos del Jugador.
    @Override
    public String toString() {
        return super.toString() + " | Rol: " + rol + " | MVP: " + mvpTotales;
    }
}
