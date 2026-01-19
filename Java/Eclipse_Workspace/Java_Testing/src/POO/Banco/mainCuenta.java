package POO.Banco;

import java.util.Scanner;

public class mainCuenta {
	private static Scanner teclado = new Scanner (System.in);
    public static void main(String[] args) {
    	//Pido el saldo inicial
    	System.out.print("Ingrese el saldo inicial de la cuenta: ");
    	double saldo = teclado.nextDouble();
    	
    	//Creo el objeto llamando al constructor Cuenta con el saldo inicial
    	Cuenta c1 = new Cuenta(saldo);
    	int opcion; // Se pone fuera porque dentro del bucle no lo deteccta 
    	
    	 // Bucle principal del menú
        do {
            // Muestro el menú de opciones
            System.out.println("\n--- MENÚ CAJERO AUTOMÁTICO ---");
            System.out.println("1. Reintegrar dinero");
            System.out.println("2. Ingresar dinero");
            System.out.println("3. Consultar saldo y número de operaciones realizadas");
            System.out.println("4. Finalizar operación");
            System.out.print("Seleccione una opción: ");
            opcion = teclado.nextInt();

            // Panel de opciones del menú
            switch (opcion) {
            case 1:
                System.out.print("Ingrese la cantidad a retirar: ");
                double reintegro = teclado.nextDouble();
                if (reintegro > c1.getSaldo()) {
                	System.out.print("Saldo insuficiente en la cuenta");
                	break;
                }
                // Usamos el método del objeto para retirar
                c1.reintegro(reintegro); 
                break;

            case 2:
                System.out.print("Ingrese la cantidad a depositar: ");
                double ingreso = teclado.nextDouble();
                // Usamos el método del objeto para ingresar
                c1.ingreso(ingreso);
                break;

            case 3:
                // Consultamos los datos a través de los métodos getter 
                System.out.println("Saldo actual: " + c1.getSaldo());
                System.out.println("Ingresos realizados: " + c1.contadorIngreso());
                System.out.println("Reintegros realizados: " + c1.contadorReintegro());
                break;

            case 4:
                System.out.print("¿Realmente desea salir? (S/N): ");
                String confirmar = teclado.next();
                if (confirmar.equalsIgnoreCase("S")) {
                    System.out.println("Saldo final: " + c1.getSaldo());
                    System.out.println("Gracias por usar el cajero.");
                } else {
                    opcion = 0; 
                }
                break;

            default:
                System.out.println("Opción no válida.");
        }

    } while (opcion != 4); 
}
}
    
