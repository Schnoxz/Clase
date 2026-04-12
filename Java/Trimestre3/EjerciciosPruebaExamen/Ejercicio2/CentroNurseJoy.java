package EjerciciosPruebaExamen.Ejercicio2;

public class CentroNurseJoy extends CentroPokemon {
	protected int añosExperiencia;
	
	// Constructor clase CnetroNurseJoy
	public CentroNurseJoy(String id, String region, TipoCentro tipoCentro, int[][] registroAtenciones, int añosExperiencia) {
		super(id, region, tipoCentro, registroAtenciones);
		this.añosExperiencia = añosExperiencia;
	}
	
	// Getter
	public int getAñosExperiencia() { return añosExperiencia; }
	
	// Setter
	public void setAñosExperiencia(int añosExperiencia) { this.añosExperiencia = añosExperiencia; }
	
	// Método toString heredado
	@Override
	public String toString() {
		return super.toString() + " | Años de Experiencia: " + añosExperiencia;
	}
	
	// Método de interfaz booleano para comprobar si supera la auditoria
	@Override
	public boolean superaAuditoria() { return this.calcularEficiencia() >= 90; }

	@Override
	public double calcularEficiencia() { return (totalPokemonAtendidos() * 1.2) + (añosExperiencia * 8); }
}
