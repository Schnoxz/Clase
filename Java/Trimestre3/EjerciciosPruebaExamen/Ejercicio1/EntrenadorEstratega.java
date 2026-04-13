package EjerciciosPruebaExamen.Ejercicio1;

public class EntrenadorEstratega extends Entrenador {
	protected int medallasConseguidas;
	
	public EntrenadorEstratega(String id, String nombre, Rango rango, int[][] registroVictorias, int medallasConseguidas) {
		super(id, nombre, rango, registroVictorias);
		this.medallasConseguidas = medallasConseguidas;
	}
	
	// Getter
	public int getMedallasConseguidas() { return medallasConseguidas; }
	
	// Setter
	public void setMedallasConseguidas(int medallasConseguidas) { this.medallasConseguidas = medallasConseguidas; }
	
	// Método toString de la clase EntrenadorEstratega
	@Override
	public String toString() { return super.toString() + " | Medallas: " + medallasConseguidas; }
	
	// Método de interfaz Evaluable
	@Override
	public boolean esAptoParaLiga() {
		return this.calcularPuntuacion() >= 100;
	}
	
	// Método abstracto heredado de Entrenador
	@Override
	public double calcularPuntuacion() {
		return (this.calcularVictoriasSemanales()*1.5) + (medallasConseguidas*10);
	}
}
