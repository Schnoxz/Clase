import java.util.Random;
import java.util.Scanner;

public class Introduccion4 {
    private static final Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
        Random random = new Random();
        int numAleatorio = random.nextInt(100) + 1; // Número aleatorio entre 1 y 100
        int intentos = 5;
        int numUsuario;

        System.out.println("Adivina el número entre 1 y 100. Tienes " + intentos + " intentos.");

        while (intentos > 0) {
            System.out.println("Introduce tu número: ");
            numUsuario = teclado.nextInt();

            if (numUsuario == 0) {
                intentos--;
                System.out.println("0 no está en el rango permitido.");
                System.out.println("Te quedan " + intentos + " intento(s).");
                continue;
            }

            if (numUsuario < 1 || numUsuario > 100) {
                System.out.println("El número debe estar entre 1 y 100.");
            }

            if (numUsuario == numAleatorio) {
                System.out.println("¡Felicidades! Has adivinado el número.");
                break;
               
            } else if (numUsuario < numAleatorio) {
                System.out.println("El número es mayor.");
            } else {
                System.out.println("El número es menor.");
            }

            // Resto un intento si falla
            intentos--;
            System.out.println("Te quedan " + intentos + " intento(s).");

            }
            // Mensaje cuando se acaban los intentos
            if (intentos == 0) 
            System.out.println("Se acabaron los intentos, el número secreto es: " + numAleatorio);{
                
            }
            
        }
    }
    

