// Contar pares e impares. Dado un array de enteros, cuenta cuántos son pares y cuántos impares.
import java.util.Scanner;

public class array6 {
    private static final Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
        // Declaro un array con enteros 
        int[] num = {1, 2, 3 , 4, 5, 6 ,7, 8, 9, 10, 11};
        // Creo variables donde guardo contador de pares e impares
        int par = 0;
        int impart = 0;

        for (int i = 0; i < num.length; i++) {
            if (num[i] % 2 == 0){
                par ++;
            } else {
                impart ++;
            }
            }
        System.out.println("Hay : " + par + " numeros pares");
        System.out.println("Hay : " + impart + " numeros impares");
        }
    }


