package excepcionesEntrenamiento;

import java.util.Scanner;

public class Calculadora {
    private static final Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Primer número:");
        int num1 = leerEntero();
        System.out.println("Segundo número");
        int num2 = leerEntero();

        System.out.println(".- CALCULADORA -.");
        System.out.println("1. Suma");
        System.out.println("2. Resta");
        System.out.println("3. Multiplicación");
        System.out.println("4. División");

        System.out.println("Elija una opción: ");
        int opcion = teclado.nextInt();

        switch (opcion){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;            
        } while (opcion < 1 || opcion > 4 );
    }



    public static int leerEntero(){
        // Controlo el primer error sobre numeros enteros, si se introduce un numero que no es entero, se muestra mensaje de error y se repite el proceso
        do { 
        System.out.println("Introduzca un número entero: ");
            try {
               int num1 = teclado.nextInt();
               return num1;
            } catch (Exception e) {
                System.out.println("Error: el numero introducido debe ser entero");
            }
        } while (true); // Hasta que no sea entero el proceso se repite
    }

    public static boolean leerOperacion () 
}




