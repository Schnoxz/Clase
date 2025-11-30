package MetodoString;
import java.util.*;

public class Ahorcado10 {
	private static final Scanner teclado = new Scanner(System.in);
	
    public static void main(String[] args) {
           // 1. Configuración inicial
       // Nota del enunciado: usamos una palabra fija por ahora[cite: 135].
        String secreto = "programacion"; 
        
        // Generamos la cadena inicial de guiones (ej: "____________")
        String visible = generarGuiones(secreto);
        
        int fallos = 0;
        int maxFallos = 7;// Límite del enunciado[cite: 134].
        
        System.out.println("--- JUEGO DEL AHORCADO ---");
        System.out.println("Adivina la palabra secreta.");

        // 2. Bucle principal del juego
        // Se repite mientras no hayamos ganado (palabras distintas) Y no hayamos perdido (fallos < 7)
        while (!visible.equals(secreto) && fallos < maxFallos) {
            
            System.out.println("\nPalabra: " + visible);
            System.out.println("Vidas restantes: " + (maxFallos - fallos));
            System.out.print("Introduce una letra: ");
            
            // Leemos el carácter y lo pasamos a minúscula
            String entrada = teclado.next();
            char letra = entrada.toLowerCase().charAt(0);

            // 3. Llamada al método lógico
            // Intentamos actualizar la palabra visible con la letra nueva
            String nuevaVisible = actualizarTablero(secreto, visible, letra);

            // 4. Comprobar si hubo cambios
            if (nuevaVisible.equals(visible)) {
                // Si la palabra nueva es IGUAL a la anterior, significa que la letra no estaba
                // (o ya estaba destapada), por tanto es un FALLO.
                fallos++;
                System.out.println("-> ¡Fallo! La letra '" + letra + "' no está.");
            } else {
                // Si son distintas, es que hemos destapado algo
                visible = nuevaVisible;
                System.out.println("-> ¡Bien! Has acertado una letra.");
            }
        }

        // 5. Fin del juego
        System.out.println("\n--------------------------------");
        if (visible.equals(secreto)) {
            System.out.println("¡FELICIDADES! Has adivinado la palabra: " + secreto);
        } else {
            System.out.println("GAME OVER. Te has quedado sin vidas.");
            System.out.println("La palabra era: " + secreto);
        }
    }

    /**
     * Crea una cadena de guiones bajos del mismo tamaño que la palabra secreta.
     */
    public static String generarGuiones(String texto) {
        String resultado = "";
        for (int i = 0; i < texto.length(); i++) {
            resultado = resultado + "_";
        }
        return resultado;
    }

    /**
     * Reconstruye la palabra visible.
     * Recorre la palabra secreta y decide qué poner en cada posición:
     * - Si la letra secreta coincide con la letra jugada -> Pone la letra jugada.
     * - Si no coincide -> Mantiene lo que ya había en 'visible' (sea guion o letra ya acertada).
     */
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
