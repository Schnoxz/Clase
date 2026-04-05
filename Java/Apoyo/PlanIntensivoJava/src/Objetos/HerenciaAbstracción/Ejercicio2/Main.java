package Objetos.HerenciaAbstracción.Ejercicio2;

public class Main {
	public static void main (String[] args) {
		
		Empleado empleado1 = new Empleado("Juan Pérez", 30000.0, "Ventas");
		Desarrollador desarrollador1 = new Desarrollador("María Gómez", 40000.0, "Desarrollo", "Java");
		Gerente gerente1 = new Gerente("Carlos Rodríguez", 50000.0, "Gerencia", 5);
		GerenteTecnico gerenteTecnico1 = new GerenteTecnico("Laura Martínez", 60000.0, "Gerencia Técnica", 2	, new String[]{"Redes", "Seguridad"});
		
		System.out.println("Información del empleado:");
		empleado1.mostrarInformacion();
		System.out.println("\nInformación del desarrollador:");
		desarrollador1.mostrarInformacion();
		System.out.println("\nInformación del gerente:");
		gerente1.mostrarInformacion();
		System.out.println("\nInformación del gerente técnico:");
		gerenteTecnico1.mostrarInformacion();
		
		System.out.println("\nBonus del empleado: " + empleado1.calcularBonus(empleado1.getSalario()));
		System.out.println("Bonus del desarrollador: " + desarrollador1.calcularBonus(desarrollador1.getSalario()));
		System.out.println("Bonus del gerente: " + gerente1.calcularBonus(gerente1.getSalario()));
		System.out.println("Bonus del gerente técnico: " + gerenteTecnico1.calcularBonus(gerenteTecnico1.getSalario()));
	}
}
