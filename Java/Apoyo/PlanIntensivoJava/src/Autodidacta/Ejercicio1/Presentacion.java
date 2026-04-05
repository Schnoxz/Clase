package Autodidacta.Ejercicio1;

import java.util.Scanner;

public class Presentacion {
	private static final Scanner teclado = new Scanner (System.in);
	
	public static void main (String[] args) {
		// Llamada a los métodos para guardar en formato String las credenciales
		String nombre = NombreUsuario();
		String fechaNacimiento = Nacimiento();
		
		// Validación para la fecha de nacimiento

		System.out.println("Su nombre: " + nombre );
		System.out.println("\nSu fecha de nacimiento: " + fechaNacimiento);
	}
	
		// Método que pregunta y recoge el nombre del usuario 
		public static String NombreUsuario() {
			String userName;
			boolean valido;
			
			do {
				valido = true;
				System.out.print("Introduzca su nombre: ");
				userName = teclado.nextLine();
				
				if (userName.trim().isEmpty()) {
					System.out.print("Error: el nombre no puede estar vacío ");
					valido = false;
				} else if (ValidarFecha.contieneNumero(userName)) {
					System.out.print("Error: el nombre no puede contener números");
					valido = false;
				}
		} while (!valido) ;
	return userName;
}	
		// Método que pregunta y recoge la fecha de nacimiento del usuario
		public static String Nacimiento() {
			boolean valido;
			int añoNacimiento = 0;
			int mesNacimiento = 0;
			int diaNacimiento = 0;
			
			// Validacion año
			do {
				valido = true;
				try {
					System.out.print("Año : ");
					añoNacimiento = teclado.nextInt();
					ValidarFecha.validarAño(añoNacimiento);
				} catch (FechaInvalidaException e) {
					System.out.print("Error: " + e.getMessage());
					valido = false;
				} catch (Exception e) {
					System.out.print("Error: carácter inválido ");
					teclado.nextLine();
					valido = false;
				}
			} while (!valido);
			
			// Validacion Mes
			do {
				valido = true;
				try {
					System.out.print("Mes: ");
					mesNacimiento = teclado.nextInt();
					ValidarFecha.validarMes(mesNacimiento);
				} catch (FechaInvalidaException e) {
					System.out.print("Error :" + e.getMessage());
					valido = false;
				} catch (Exception e) {
					System.out.print("Error: Carácter inválido");
					teclado.nextLine();
					valido = false;
				} 
			} while (!valido);
				
			// Validacion dia
			do {
				valido = true;
				try {
					System.out.print("Dia: ");
					diaNacimiento = teclado.nextInt(); 
					ValidarFecha.validarDia(diaNacimiento, mesNacimiento, añoNacimiento);
				} catch (FechaInvalidaException e) {
					System.out.print("Error " + e.getMessage());
					valido = false;
				} catch (Exception e) {
					System.out.print("Error: carácter inválido ");
					teclado.nextLine();
					valido = false;
				} 
			} while (!valido);
			
			String fechaNacimiento = diaNacimiento + "/" + mesNacimiento + "/" + añoNacimiento;
			return fechaNacimiento;
		}
}
			
			
			




