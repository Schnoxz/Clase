package Objetos.HerenciaAbstracción.Ejercicio2;
import java.util.Arrays;

public class GerenteTecnico extends Gerente {
	private String[] areasTecnicas;
	
	 // Constructor gerente técnico
	public GerenteTecnico(String nombre, Double salario, String departamento, int numEmpleadosACargo, String[] areasTecnicas) {
		super(nombre, salario, departamento, numEmpleadosACargo); // El número de empleados a cargo se establece en 0 para el gerente técnico
		this.areasTecnicas = areasTecnicas;
	}
	
	// Getters
	
	public String[] getAreasTecnicas() { return areasTecnicas; }
	
	// Setters
	
	public void setAreasTecnicas(String[] areasTecnicas) {
		this.areasTecnicas = areasTecnicas;
	}
	
	// Llamo al Método mostrarInformacion de la clase y padre y le añado areasTecnicas del GerenteTecnico
	@Override
	public void mostrarInformacion() {
		super.mostrarInformacion();
		System.out.println("Areas técnicas: " + Arrays.toString(areasTecnicas));
	}
	
	// Llamo al método que calcula el bonus de salario adicional y lo sobreescribo con el del Gerente Tecnico
	@Override
	public double calcularBonus(double salario) {
		return salario + (salario * 0.35); 
	}
}
