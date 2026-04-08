package SimulacroTema5;

public abstract class Empleado {
	protected String nombre;
	protected int id;
	protected String departamento;
	protected double[][] horasTrabajadas; // Contiene 5 dias y 2 turnos, las filas representan lunes a viernes y las columnas dos turnos de por ejemplo 4h
	
	// Constructor clase Empleado
	public Empleado (String nombre, int id, String departamento, double[][] horasTrabajadas2) {
		this.nombre = nombre;
		this.id = id;
		this.departamento = departamento;
		this.horasTrabajadas = horasTrabajadas2;
	}
	
	// Getters
	public String getNombre() { return nombre; }
	public int getId() { return id; }
	public String getDepartamento() { return departamento; }
	public double[][] getHorasTrabajadas(){ return horasTrabajadas; }
	
	// Setters
	public void setNombre(String nombre) { this.nombre = nombre; }
	public void setId(int id) { this.id = id; }
	public void setDepartamento(String departamento) { this.departamento = departamento; }
	public void setHorasTrabajadas(double[][] horasTrabajadas) { this.horasTrabajadas = horasTrabajadas ; }
	
	
	// Enumerado Departamento 
	public enum Departamento{
		INFORMATICA, ADMINISTRACION, RRHH, VENTAS
	}
	
    // Método toString que muestra todos los atributos sobre la el objeto
    public String toString() {
        return "Nombre: " + nombre + "--- " +  "ID: " + id + "---" + "Departamento:  " + departamento + "---" + "Horas trabajadas: " + horasTrabajadas;
    }
	
	public double calcularHorasTotalesSemana(int[][] a) {
		int resultado = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				resultado += a[i][j];
			}
		}
		return resultado;
	}
	
	// Método abstracto calcular productividad
	public abstract double calcularProductividad();
	
	// Método calcular horas de un dia
	public double calcularHorasDia(int dia) {
		for (int i = 0; i < horasTrabajadas[dia].length; i++) {
			dia += horasTrabajadas[dia][i];
		}

		return dia;
	}
	
	// Método que muestra toda la matriz de horas trabajadas
	public void mostrarHorario() {
		for (int i = 0; i < horasTrabajadas.length; i++) {
			for (int j = 0; j < horasTrabajadas[i].length; j++) {
				System.out.print(horasTrabajadas[i][j] + " ");
			}
		}
	}
}
