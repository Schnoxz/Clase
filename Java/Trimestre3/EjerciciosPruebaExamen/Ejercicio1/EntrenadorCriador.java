package EjerciciosPruebaExamen.Ejercicio1;

public class EntrenadorCriador extends Entrenador {
	protected int huevosEclosionados;
	
	public EntrenadorCriador(String id, String nombre, Rango rango, int[][] registroVictorias, int huevosEclosionados ) {
		super(id, nombre, rango, registroVictorias);
		this.huevosEclosionados = huevosEclosionados;
}
	// Getter
	public int getHuevosEclosionados() { return huevosEclosionados; }
	
	// Setter 
	public void setHuevosEclosionados (int huevosEclosionados) { this.huevosEclosionados = huevosEclosionados; }
	
	// Método toString para la clase EntrenadorCriador
	@Override
	public String toString() { return super.toString() + " | Huevos Eclosionados: " + huevosEclosionados; }
	
	@Override
	public boolean esAptoParaLiga() { return this.calcularPuntuacion() >= 80; }

	@Override
	public double calcularPuntuacion() { return (this.calcularVictoriasSemanales()* 1.0) + (huevosEclosionados * 5); }
	
}