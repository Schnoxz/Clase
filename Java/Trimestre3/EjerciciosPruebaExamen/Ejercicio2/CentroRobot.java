package EjerciciosPruebaExamen.Ejercicio2;

public class CentroRobot extends CentroPokemon {
	protected int nivelesIA;
	
	// Constructor clase CnetroNurseJoy
	public CentroRobot(String id, String region, TipoCentro tipoCentro, int[][] registroAtenciones, int nivelesIA) {
		super(id, region, tipoCentro, registroAtenciones);
		this.nivelesIA = nivelesIA;
	}
	
	// Getter
	public int getNivelesIa() { return nivelesIA; }
	
	// Setter
	public void setNivelesIA(int nivelesIA) { this.nivelesIA = nivelesIA; }
	
	// Método toString heredado
	@Override
	public String toString() {
		return super.toString() + " | Niveles de IA: " + nivelesIA;
	}
	
	// Método de interfaz booleano para comprobar si supera la auditoria
	@Override
	public boolean superaAuditoria() { return this.calcularEficiencia() >= 110; }

	@Override
	public double calcularEficiencia() { return (totalPokemonAtendidos() * 1.0) + (nivelesIA * 15); }
	
}
