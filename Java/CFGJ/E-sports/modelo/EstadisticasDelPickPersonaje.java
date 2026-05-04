// Esta clase actua como auxiliar para la clase Jugador, nos indica las estadisticas del pick que juega
public class EstadisticasDelPickPersonaje {
    private int partidasJugadas;
    private int partidasGanadas;

	// Clase constructor de la clase EstadisticasDelPickPersonaje
    public EstadisticasDelPickPersonaje() {
        this.partidasJugadas = 0;
        this.partidasGanadas = 0;
    }

    //  Método añadido para que la clase Jugador pueda registrar las partidas
    public void incrementarPartida(boolean victoria) {
        this.partidasJugadas++;
        if (victoria) {
            this.partidasGanadas++;
        }
    }
	// Método para calcular el winrate de un solo personaje en concreto
    public double calcularWinRate() {
        if (partidasJugadas == 0) return 0.0;
        return ((double) partidasGanadas / partidasJugadas) * 100; // Devuelve en formato %
    }

	// Método toString con todos los atributos
    @Override
    public String toString() {
        return "Jugadas: " + partidasJugadas + " | WinRate: " + String.format("%.2f", calcularWinRate()) + "%"; // uso el formateo a dos decimales, gracias stack overflow
    }
}
