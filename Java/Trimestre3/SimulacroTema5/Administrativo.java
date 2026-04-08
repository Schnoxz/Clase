package SimulacroTema5;

public class Administrativo extends Empleado {
	private int documentosTramitados;
	
	public Administrativo(String nombre, int id, String departamento, double[][] horasTrabajadas, int incidenciasResueltas) {
		super(nombre, id, departamento, horasTrabajadas);
	}
	
	// Getters
	public int getDocumentosTramitados() { return documentosTramitados; }
	
	// Setters
	public void setDocumentosTramitados() { this.documentosTramitados = documentosTramitados; }
	
	// Método toString
	@Override
	public String toString() {
        return "Nombre: " + nombre + "--- " +  "ID: " + id + "---" + "Departamento:  " + departamento + "---" + "Horas trabajadas: " + horasTrabajadas + "---" + "Documentos Tramitados: " + documentosTramitados;
    }
	
	// Método calcular productividad
	public double calcularProductividad(int documentosTramitados) {
		double productividad = calcularHorasTotalesSemana() + documentosTramitados*0.5;
		return productividad;
	}
	
	// Método booleano merece reconocimiento
	public boolean mereceReconocimiento() {
		if (calcularProductividad(documentosTramitados) >= 40) {
			return true;
		} else {
			return false;
		}	
	}
}
