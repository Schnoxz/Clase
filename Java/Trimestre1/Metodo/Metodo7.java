// Realizar un programa que solicite dos números enteros mayores que 0, que calcule el máximo común divisor entre ellos mediante el algoritmo de Euclides y que muestre por pantalla el resultado.
package Metodo;

import java.util.Scanner;

public class Metodo7 {
    private static final Scanner teclado = new Scanner(System.in);

   // Declaro como método el algoritmo de Euclides del mcd
    public static int Euclides(int num1, int num2) {
        while (num2 != 0) {
            int temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }
        return num1;
    }

    public static void main(String[] args) {
        int num1, num2;

      // Solicitamos y validamos el primer numero
        do {
            System.out.print("Introduce el primer número (mayor que 0): ");
            num1 = teclado.nextInt();
        } while (num1 <= 0);

      // Solicitamos y validamos el segundo numero
        do {
            System.out.print("Introduce el segundo número (mayor que 0): ");
            num2 = teclado.nextInt();
        } while (num2 <= 0);

        // Calcula y muestra el MCD
        int mcd = Euclides(num1, num2);
        System.out.println("El Máximo Común Divisor es: " + mcd);
    }
}

    

