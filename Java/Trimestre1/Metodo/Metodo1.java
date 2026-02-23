//Realizar un programa que solicite 3 números cualesquiera y los muestre por pantalla ordenados de menor num1 mayor.
package Metodo;

import java.util.Scanner;

public class Metodo1 {
    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        // Solicito los tres números
        System.out.print("Introduce el primer número: ");
        double num1 = teclado.nextDouble();

        System.out.print("Introduce el segundo número: ");
        double num2 = teclado.nextDouble();

        System.out.print("Introduce el tercer número: ");
        double num3 = teclado.nextDouble();

        // Llamo al método para mostrar los números ordenados
        mostrarOrdenados(num1, num2, num3);
    }

    // Método que ordena y muestra los tres números
    private static void mostrarOrdenados(double num1, double num2, double num3) {
        double menor, medio, mayor;

        if (num1 <= num2 && num1 <= num3) {
            menor = num1;
            if (num2 <= num3) {
                medio = num2;
                mayor = num3;
            } else {
                medio = num3;
                mayor = num2;
            }
        } else if (num2 <= num1 && num2 <= num3) {
            menor = num2;
            if (num1 <= num3) {
                medio = num1;
                mayor = num3;
            } else {
                medio = num3;
                mayor = num1;
            }
        } else {
            menor = num3;
            if (num1 <= num2) {
                medio = num1;
                mayor = num2;
            } else {
                medio = num2;
                mayor = num1;
            }
        }

        System.out.println("\nLos números ordenados de menor num1 mayor son:");
        System.out.println(menor + " - " + medio + " - " + mayor);
    }
}



