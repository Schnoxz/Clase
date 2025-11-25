import java.util.Scanner;
public class Ampliation_3 {
    private static final Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
    int num;

        System.out.println("Introduzca un número para ver su tabla de multiplicar:");
          num = teclado.nextInt();

            // Nos enseña la tabla de multiplicar del número 
            for (int i = 0; i <= 10; i++) {
                System.out.println(num + " x " + i + " = " + (num * i));
            }
            
            //Separacion de lineas entre tablas
            System.out.println();
        }
    }

