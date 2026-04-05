package Objetos.HerenciaAbstracción.Ejercicio2;

public class Gerente extends Empleado {
	private int numEmpleadosACargo;
	
	
	// Constructor gerente
	public Gerente(String nombre, Double salario, String departamento, int numEmpleadosACargo) {
		super(nombre, salario, departamento);
		this.numEmpleadosACargo = numEmpleadosACargo;
	}
	
	// Getters
	public int getNumEmpleadosACargo() { return numEmpleadosACargo; }
	
	// Setters
	public void setNumEmpleadosACargo(int numEmpleadosACargo) { this.numEmpleadosACargo = numEmpleadosACargo; }
	
	// Llamo al método mostrarInformacion de la clase padre y luego añado el número de empleados a cargo del gerente
	@Override
	public void mostrarInformacion() {
		super.mostrarInformacion();
		System.out.println("Número de empleados a cargo: " + numEmpleadosACargo);
	}
	
	// Método para calcular el bonus del gerente, un 20% adicional del salario base
	@Override
	public double calcularBonus(double salario) {
		return salario + (salario * 0.25); 
	}
	
	
}
