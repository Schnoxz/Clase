package Objetos.HerenciaAbstracción.Ejercicio2;

public class Empleado {
	private String nombre;
	private Double salario;
	private String departamento;
	
	// Constructor empleado
	public Empleado(String nombre, Double salario, String departamento) {
		this.nombre = nombre;
		this.salario = salario;
		this.departamento = departamento;
		
	}
	// Metodo para mostrar informacion
	public void mostrarInformacion() {
		System.out.println("Nombre: " + nombre);
		System.out.println("Salario: " + salario);
		System.out.println("Depàrtamento: " + departamento);
	}
	
	// Métpdp para calcular bonus de salario
	public double calcularBonus(double salario) {
		salario += (salario * 0.10);
		return salario;
	}
	
	// Getters
	public String getNombre() { return nombre; }
	public Double getSalario() { return salario; }
	public String getDepartamento() { return departamento; }
	
	
	// Setters
	
	public void setNombre(String nombre) { this.nombre = nombre; }
	public void setSalario(Double salario) { this.salario = salario; }
	public void setDepartamento(String departamento) { this.departamento = departamento; }

}
