package SimulacroTema5;

public class Programador extends Empleado {
	private int incidenciasResueltas;
	
	// Constructor clase Programador
	public Programador (String nombre, int id, String departamento, double[][] horasTrabajadas, int incidenciasResueltas) {
		super(nombre, id, departamento, horasTrabajadas);
	}

	// Getters
	public int getIncidenciasResueltas() { return incidenciasResueltas; }
	
	// Setters
	public void setIncidenciasResueltas(int incidenciasResueltas) { this.incidenciasResueltas = incidenciasResueltas; }
	
	
	// Método sobreescrito calcularProductividad
	@Override
	public double calcularProductividad(int incidenciasResueltas) {
		int productividad = calcularHorasTotalesSemana() + incidenciasResueltas * 2);
		return productividad;
	}

	@Override
	public double calcularProductividad() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// Método booleano merece reconocimiento que devuelve true si la productividad es mayor o igual que 45
	public boolean mereceReconocimiento() {
		if (calcularProductividad(incidenciasResueltas) >= 40) {
			return true;
		} else {
			return false;
		}	
	}
}
