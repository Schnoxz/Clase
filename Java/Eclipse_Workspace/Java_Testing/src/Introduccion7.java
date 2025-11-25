// Realizar un programa que solicite dos números enteros mayores que 0, que calcule el máximo común divisor entre ellos mediante el algoritmo de Euclides y que muestre por pantalla el resultado.
import java.util.Scanner;

public class Introduccion7 {
    private static final Scanner teclado = new Scanner(System.in);
    //Declaro la función del algoritmo de Euclides del mcd
    public static int Euclides (int num1, int num2) {
        while (num2 != 0) {
            int temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }
        return num1;
    }
    
    public static void main(String[] args) {
        int num1, num2;
       
        //Solicitamos y validamos el primer numero
        System.out.print("Introduce el primer numero: ");
        num1 = teclado.nextInt();

        //Solicitamos y validamos el segundo numero
        System.out.print("Introduce el segundo numero: ");
        num2 = teclado.nextInt();

        int mcd = Euclides (num1, num2);
        System.out.println("El Máximo común divisor es: " + mcd);
    }
}
    

