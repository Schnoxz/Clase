package MetodoString;
import java.util.Scanner;

public class SimulacroUd3 {
	private static final Scanner teclado = new Scanner (System.in);

    public static void main(String[] args) {
        String frase = pedirFrase();
        boolean continuar = true;

        while (continuar) {
            int usuarioOpcion = menu();
            // Creo un switch para las opciones del menu
            switch (usuarioOpcion) {
                case 1 -> System.out.println("Número de espacios: " + numEspaciados(frase));
                case 2 -> System.out.println("Número de palabras: " + numPalabras(frase));
                case 3 -> System.out.println("Cuántas mayúsculas tiene la ultima palabra: " + numMayus(frase));
                case 4 -> System.out.println("Frase invertida: " + invertir(frase));
                case 5 -> { System.out.println("Nos vemos"); continuar = false;

                }
            }

            // Si el usuario quiere hacer algo mas, pedirle la opcion
            if (continuar) {
                System.out.print("¿Desea hacer algo mas? (s/n): ");
                String answer = teclado.nextLine().toLowerCase();
                if (!answer.equals("si") && !answer.equals("s")) {
                    System.out.println("Nos vemos");
                    continuar = false;
                }
            }
        }
    }
    // Metodo para pedir la frase al usuario por pantalla
    public static String pedirFrase() {

        System.out.print("Introduce una frase: ");
        return teclado.nextLine();
    }
    // Metodo para mostrar el menu y pedir la opcion al usuario
    public static int menu() {

        System.out.println("Menu:");
        System.out.println("1 --> Contar espacios");
        System.out.println("2 --> Contar palabras");
        System.out.println("3 --> Mayusculas en ultima letra de cada palabra");
        System.out.println("4 --> Invertir frase");
        System.out.println("5 --> Salir");
        
        // Inicializar la opcion a 0
        int usuarioOpcion = 0;
        boolean valido = false;
        
        while (!valido) {
            // Pedir la opcion al usuario
            System.out.print("Seleccione una opcion (1-5): ");
             String entrada = teclado.nextLine();
            
            // Variable para contar cuántos dígitos encontramos
            int esNum = 0;
            char caracterEncontrado = ' ';

            // BUSCAMOS EL NÚMERO ENTRE LOS ESPACIOS
            for (int i = 0; i < entrada.length(); i++) {
                char c = entrada.charAt(i);
                if (c != ' ') { // Ignoramos espacios
                    caracterEncontrado = c;
                    esNum++;
                }
            }     
            // Comprobamos si hemos encontrado un único dígito entre 1 y 5
            if (esNum == 1 && caracterEncontrado >= '1' && caracterEncontrado <= '5') {
                usuarioOpcion = caracterEncontrado - '0'; // Conversión manual a entero
                valido = true;
            } else {
                System.out.println("Error: Debe introducir un único número del 1 al 5.");
            }
        }
        return usuarioOpcion;
    }

    public static int numEspaciados(String frase) {
        int contador = 0;
        // Creo un bucle para recorrer la frase y contar los espacios
        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) == ' ') {
                contador++;
            }
        }
        return contador;
    }

    public static int numPalabras(String frase) {
        // En el caso de que la frase este vacia devuelvo 0
        if (frase.isEmpty()) return 0;
        int contador = 0;
        boolean esPalabra = false;
        // Creo un string temporal para añadir un espacio al final y trabajar con el
        String fraseTemp = frase + " "; 
        for (int i = 0; i < fraseTemp.length(); i++) {
            // Si el caracter actual no es un espacio
            if (fraseTemp.charAt(i) != ' ') {
                // Si el caracter actual no es un espacio, es una palabra
                esPalabra = true;
            // Si el caracter actual es un espacio y es una palabra
            } else if (esPalabra) {
                contador++;
                esPalabra = false;
            }
        }
        return contador;
    }
    // Creo un metodo para contar las mayusculas en la ultima letra de cada palabra, por ende empiezo desde el final de la cadena
    public static int numMayus(String frase) {
        int contador = 0;
        // Creo un string temporal para añadir un espacio al final y trabajar con el
        String temp = frase + " "; 
        // Contador invertido
        for (int i = 0; i < temp.length() - 1; i++) {
            // Cuando se encuentre un espacio tras un caracter distinto de espacio nos indica el final de la palabra
            if (temp.charAt(i) != ' ' && temp.charAt(i + 1) == ' ') {
                char letra = temp.charAt(i);
                // Compruebo si es mayuscula
                if (Character.isUpperCase(letra)) {
                    contador++;
                }
            }
        }
        return contador;
    }

    public static String invertir(String frase) {
        String resultado = "";
        String palabraActual = "";
         // metemos un espacio al final para procesar la ultima palabra
        String fraseTrans = frase + " ";
        // Recorrer la frase y invertir las palabras
        for (int i = 0; i < fraseTrans.length(); i++) {
            // Obtener el caracter actual
            char caracter = fraseTrans.charAt(i);
            // Si el caracter actual no es un espacio, es una palabra
            if (caracter != ' ') {
                // Si el caracter actual no es un espacio, es una palabra
                palabraActual += caracter;
            // Si el caracter actual es un espacio y tenemos una palabra
            } else if (!palabraActual.isEmpty()) {
                // Añadir la palabra al resultado y un espacio
                resultado = palabraActual + " " + resultado;
                // Reiniciar la palabra actual
                palabraActual = "";
            }
        }
        return resultado;
    }
}