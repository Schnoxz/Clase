package SimulacroTema5;

public class Administrativo extends Empleado {
	private int documentosTramitados;
	
	public Administrativo(String nombre, int id, String departamento, double[][] horasTrabajadas, int documentosTramitados) {
		super(nombre, id, departamento, horasTrabajadas);
		this.documentosTramitados = documentosTramitados;
	}
	
	// Getter (he puesto getter y setter en todos los atributos, aunque no se me ha pedido en el enunciado, pero por si acaso)
	public int getDocumentosTramitados() { return documentosTramitados; }
	
	// Setter
	public void setDocumentosTramitados(int documentosTramitados) { this.documentosTramitados = documentosTramitados; }
	
	// Método toString igual que el de la clase padre Empleado pero con el atributo propio de Administrativo
	@Override
	public String toString() {
        return "Nombre: " + nombre + "--- " +  "ID: " + id + "---" + "Departamento:  " + departamento + "---" + "Horas trabajadas: " + horasTrabajadas + "---" + "Documentos Tramitados: " + documentosTramitados;
    }
	
	// Método calcular productividad que suma las horas totales de la semana y el número de documentos tramitados multiplicado por 0.5
	@Override
	public double calcularProductividad() {
		double productividad = calcularHorasTotalesSemana() + documentosTramitados*0.5; // Ya está definida en el enunciado para el Administrativo
		return productividad;
	}
	
	// Método booleano merece reconocimiento que devuelve true si la productividad es mayor o igual que 40
	public boolean mereceReconocimiento() {
		if (calcularProductividad() >= 40) {
			return true;
		} else {
			return false;
		}	
	}
}
