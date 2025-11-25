import java.util.Scanner;

public class Introduccion1 {
    private static final Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
      
        // Defino numeros reales
        double num1, num2, num3;
        double menor, igual, mayor;

        System.out.print("Introduce el primer número: ");
        num1 = teclado.nextDouble();

        System.out.print("Introduce el segundo número: ");
        num2 = teclado.nextDouble();

        System.out.print("Introduce el tercer número: ");
        num3 = teclado.nextDouble();

        // Comparaciones para ordenar
        if (num1 <= num2 && num1 <= num3) {
            menor = num1;
            if (num2 <= num3) {
                igual = num2;
                mayor = num3;
            } else {
                igual = num3;
                mayor = num2;
            }
        } else if (num2 <= num1 && num2 <= num3) {
            menor = num2;
            if (num1 <= num3) {
                igual = num1;
                mayor = num3;
            } else {
                igual = num3;
                mayor = num1;
            }
        } else {
            menor = num3;
            if (num1 <= num2) {
                igual = num1;
                mayor = num2;
            } else {
                igual = num2;
                mayor = num1;
            }
        }

        // Mostrar resultados
        System.out.println("\nLos números ordenados de menor a mayor son:");
        System.out.println(menor + " - " + igual + " - " + mayor);

       
        
    }
}

