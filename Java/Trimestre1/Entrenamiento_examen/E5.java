package Entrenamiento_examen;
import java.util.Scanner;

public class E5 {
	private static final Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
       
        double saldo = 1000;
        int opcion;

        do {
            System.out.println("\n--- MENÚ CAJERO ---");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Ingresar dinero");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                	System.out.println("Su saldo es de: " + saldo);
                    break;
                case 2:
                	System.out.println("Qué cantidad desea ingresar ");
                	int ingreso = teclado.nextInt();
              
                	if (ingreso <0) {
                	System.out.println("Introduzca una cantidad correcta");
                	break;
                	} else {
                	saldo = saldo + ingreso;
                	break;
                	}
                case 3:
                	System.out.println("Qué cantidad desea retirar ");
                	int retirada = teclado.nextInt();
                	
                	if(retirada < saldo) {
                	System.out.println("Saldo insuficiente ");
                	} else{
                	saldo = saldo - retirada;
                	}
                case 4:
                    System.out.println("Gracias por usar el cajero.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 4);
    }
}
