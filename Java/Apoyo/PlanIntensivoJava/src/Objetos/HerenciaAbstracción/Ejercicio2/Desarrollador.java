package Objetos.HerenciaAbstracción.Ejercicio2;

public class Desarrollador extends Empleado {
	private String lenguajePrincipal;
	
	// Constructor desarrollador
	public Desarrollador(String nombre, Double salario, String departamento, String lenguajePrincipal) {
		super(nombre, salario, departamento);
		this.lenguajePrincipal = lenguajePrincipal;
	}
	
	// Getters`
	public String getLenguajePrincipal() { return lenguajePrincipal; }
	
	// Setters
	public void setLenguajePrincipal(String lenguajePrincipal) { this.lenguajePrincipal = lenguajePrincipal; }
	
	// Llamo al método mostrarInformacion de la clase padre y luego añado el lenguaje principal del desarrollador
	@Override
	public void mostrarInformacion() {
		super.mostrarInformacion();
		System.out.println("Lenguaje principal: " + lenguajePrincipal);
	}
	
	// Método para calcular el bonus del desarrollador, un 15% adicional del salario base
	@Override
	public double calcularBonus(double salario) {
		return salario + (salario * 0.15); 
	}
}
