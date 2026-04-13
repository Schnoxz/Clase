package SimulacroTema5;

public class Programador extends Empleado {

    private int incidenciasResueltas;

    // Constructor clase Programador
    public Programador(String nombre, int id, String departamento, double[][] horasTrabajadas, int incidenciasResueltas) {
        super(nombre, id, departamento, horasTrabajadas);
        this.incidenciasResueltas = incidenciasResueltas;
    }

    // Getter
    public int getIncidenciasResueltas() { return incidenciasResueltas; }

    // Setter
    public void setIncidenciasResueltas(int incidenciasResueltas) { this.incidenciasResueltas = incidenciasResueltas; }

    // Método sobreescrito calcularProductividad
    @Override
    public double calcularProductividad() {
        double productividad = calcularHorasTotalesSemana() + incidenciasResueltas * 2; // Operación ya definida en el enunciado para el calculo de la productividad
		return productividad;
    }
    
    // Método toString igual que el de la clase padre Empleado pero con el atributo propio de Programador
    @Override
    public String toString() {
		return "Nombre: " + nombre + "--- " +  "ID: " + id + "---" + "Departamento:  " + departamento + "---" + "Horas trabajadas: " + horasTrabajadas + "---" + "Incidencias Resueltas: " + incidenciasResueltas;
	}

    // Método booleano merece reconocimiento que devuelve true si la productividad es mayor o igual que 45
    public boolean mereceReconocimiento() {
        if (calcularProductividad() >= 40) {
            return true;
        } else {
            return false;
        }
    }
}
