package Relacion10v2.Ejercicio1;

import java.util.Scanner;

public class MenuCuentaCredito {
	private final Scanner teclado = new Scanner(System.in);
	private CuentaCredito cuenta;

	// Constructor clase Menu para CuentaCredito
	public MenuCuentaCredito(CuentaCredito cuenta) {
		this.cuenta = cuenta;
	}

	// Método que muestra el menu para el usuairo
	public void mostrarMenu() {
		int opcion;
		do {
			System.out.println("Cajero automático BBVA");
			System.out.println("1. Ingresar dinero");
			System.out.println("2. Sacar dinero");
			System.out.println("3. Mostrar saldo y crédito");
			System.out.println("4. Salir");
			opcion = teclado.nextInt();

		switch(opcion) {
		case 1:
			ingresarDinero();
			break;
		case 2:
			sacarDinero();
			break;
		case 3:
			mostrarSaldoCredito();
			break;
		case 4:
			System.out.println("Ha salido correctamente de su cuenta");
			break;
		default:
			System.out.println("Opción no válida");
		}
	}while(opcion != 4);
}

	// Creo los metodos de ingreso, retiro y mostrar informacion adaptadas al menu
	private void ingresarDinero() {
		System.out.print("Cantidad a ingresar: ");
		double cantidad = teclado.nextDouble();
		cuenta.ingresarDinero(cantidad); // Llamo al metodo de ingresar dinero de la clase CuentaCredito que hereda de Cuenta y le paso la cantidad a ingresar
	}

	private void sacarDinero() {
		System.out.print("Cantidad a sacar: ");
		double cantidad = teclado.nextDouble();
		cuenta.sacarDinero(cantidad); // Llamo al metodo de sacar dinero de la clase CuentaCredito que hereda de Cuenta pero con validacion de credito y le paso la cantidad a retirar
	}

	private void mostrarSaldoCredito() {
		cuenta.mostrarInfo(); // Llamo al metodo mostrarInfo de la clase CuentaCredito que muestra el titular, saldo y credito actual de la cuenta
	}
}

