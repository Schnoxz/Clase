package MetodoString;
import java.util.*;

public class Ahorcado10 {
	private static final Scanner teclado = new Scanner(System.in);
	
    public static void main(String[] args) {
        String secreto = "programacion"; 
        
        // Llamo al metodo que genera una cadena de guiones
        String visible = generarGuiones(secreto);
        int fallos = 0;
        int maxFallos = 7;
        
        System.out.println("--- El AHORCADO ---");
        System.out.println("¿Cuál es la palabra secreta?");


        // Creo un bucle hasta que acierte o se agoten los intentos
        while (!visible.equals(secreto) && fallos < maxFallos) {
            
            System.out.println("\nPalabra: " + visible);
            System.out.println("Vidas restantes: " + (maxFallos - fallos));
            System.out.print("Introduce una letra: ");
            
            // Guardo la letra introducida por el usuario y la paso a minúscula
            String entrada = teclado.next();
            char letra = entrada.toLowerCase().charAt(0);

            
            // Llamo al metodo para que actualice la palabra con la letra introducida
            String nuevaVisible = actualizarTablero(secreto, visible, letra);

            // Se comprueba si ha habido acierto o fallo
            if (nuevaVisible.equals(visible)) {
                // Si la palabra nueva es igual a la anterior, significa que la letra no estaba
                fallos++;
                System.out.println("-> ¡Fallo! La letra '" + letra + "' no está.");
            } else {
                // Si son distintas, es que hemos revelado algo
                visible = nuevaVisible;
                System.out.println("-> ¡Bien! Has acertado una letra.");
            }
        }

        // El mensaje final dependiendo de si ganas o pierdes
        if (visible.equals(secreto)) {
            System.out.println("¡FELICIDADES! Has adivinado la palabra: " + secreto);
        } else {
            System.out.println("GAME OVER. Te has quedado sin vidas.");
            System.out.println("La palabra era: " + secreto);
        }
    }

    // Metodo que genera una cadena de guiones del mismo tamaño que el texto
    public static String generarGuiones(String texto) {
        String resultado = "";
        for (int i = 0; i < texto.length(); i++) {
            resultado = resultado + "_";
        }
        return resultado;
    }

    // Metodo que compara la letra con la palabra secreta y va actualizando la cadena
    public static String actualizarTablero(String secreto, String visibleActual, char letraJugada) {
        String nuevaCadena = "";

        for (int i = 0; i < secreto.length(); i++) {
            char cSecreto = secreto.charAt(i);
            char cVisible = visibleActual.charAt(i);

            if (cSecreto == letraJugada) {
                // Acierto: ponemos la letra nueva
                nuevaCadena = nuevaCadena + letraJugada;
            } else {
                // No acierto: copiamos lo que ya había (guion o letra antigua)
                nuevaCadena = nuevaCadena + cVisible;
            }
        }
        
        return nuevaCadena;
    }
}
