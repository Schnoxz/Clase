// Se desea realizar un programa que implemente un juego que consiste en acertar un número secreto. El ordenador deberá generar aleatoriamente un número secreto entre 1 y 100 y el jugador tratará de acertarlo 
// Cada vez que el jugador introduzca un número, se comprobará si es el número secreto, en cuyo caso termina el juego. Si no lo es, se informará que el número introducido es mayor o menor que el número secreto
// Si es número no estuviese en el rango de 1-100, debe mostrar un mensaje “El número debe estarentre 1 y 100” y también debe contarse como un fallo
import java.util.Random;
import java.util.Scanner;

public class Introduccion4 {
    private static final Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
        Random random = new Random();
        int numAleatorio = random.nextInt(100) + 1; // Genera un número aleatorio entre 1 y 100
        int intentos = 5;
        int numUsuario;

        System.out.println("Adivina el número entre 1 y 100. Tienes " + intentos + " intentos.");

        while (intentos > 0) {
            System.out.print("Introduce tu número: ");
            numUsuario = teclado.nextInt();

            // Verifico si el número está fuera del rango con un if y establezco las demás condiciones con un else if
            if (numUsuario < 1 || numUsuario > 100) {
                System.out.println("El número debe estar entre 1 y 100.");
                intentos--; // Se cuenta como intento fallido
            } else if (numUsuario == numAleatorio) {
                System.out.println("¡Felicidades! Has adivinado el número.");
                break; // Termina el juego
            } else if (numUsuario < numAleatorio) {
                System.out.println("El número secreto es mayor.");
                intentos--;
            } else {
                System.out.println("El número secreto es menor.");
                intentos--;
            }

            // Muestra los intentos restantes si aún quedan
            if (intentos > 0) {
                System.out.println("Te quedan " + intentos + " intento(s).");
            }
        }

        // Mensaje final si se agotan los intentos
        if (intentos == 0) {
            System.out.println("Se acabaron los intentos. El número secreto era: " + numAleatorio);
        }
    }
}

