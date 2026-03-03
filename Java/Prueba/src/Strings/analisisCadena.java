//Pide al usuario una frase por teclado y:
//Muestra el número de palabras, vocales y consonantes.
//Comprueba si es un palíndromo (ignorando mayúsculas y espacios).
//Invierte la frase palabra a palabra (ej: "Hola mundo" → "mundo Hola").
//Sustituye todas las vocales por *.
//Muestra las palabras ordenadas por longitud.
package Strings;

import java.util.*;

public class analisisCadena {

    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        // Pide al usuario una frase
        System.out.println("Introduce una frase: ");
        String frase = teclado.nextLine();
        // Imprime por pantalla todos los datos llamando a cada metodo
        System.out.println("Número de palabras : " + contadorPalabras(frase) + "\nNúmero de vocales: " + contadorVocales(frase) + "\nNúmero de consonantes: " + contadorConsonantes(frase));
        System.out.println("Palíndromo: " + esPalindromo(frase));
        System.out.println("La frase invertida es: " + invertirFrase(frase));
        System.out.println("Vocales sustituidas por *: " + sustituirVocales(frase));
        System.out.println("Palabras ordenadas por longitud: " + ordenarLongitud(frase));

    }

    // Constructor de la clase Plabra
    public static class Frase {

        // Atributos palabra y longitud
        private String palabra;
        private int longitud;
    }

    // Metodo para invertir la frase
    public static String invertirFrase(String frase) {

        String[] palabras = frase.split(" ");
        String fraseInvertida = "";
        for (int i = palabras.length - 1; i >= 0; i--) {
            fraseInvertida += palabras[i] + " ";
        }
        return fraseInvertida;
    }

    // Metodo para sustituir las vocales
    public static String sustituirVocales(String frase) {
        String vocales = "aeiouAEIOU";
        for (int i = 0; i < vocales.length(); i++) {
            frase = frase.replace(vocales.charAt(i), '*');
        }
        return frase;

    }

    // Metodo para contar las palabras
    public static String contadorPalabras(String frase) {
        String palabras[] = frase.split(" ");
        return String.valueOf(palabras.length);
    }

    // Metodo para contar las vocales
    public static String contadorVocales(String frase) {
        String vocales = "aeiouAEIOU";
        int contador = 0;
        for (int i = 0; i < frase.length(); i++) {
            for (int j = 0; j < vocales.length(); j++) {
                if (frase.charAt(i) == vocales.charAt(j)) {
                    contador++;
                }
            }
        }
        return String.valueOf(contador);

    }

    // Metodo para contar las consonantes
    public static String contadorConsonantes(String frase) {
        String consonantes = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
        int contador = 0;
        for (int i = 0; i < frase.length(); i++) {
            for (int j = 0; j < consonantes.length(); j++) {
                if (frase.charAt(i) == consonantes.charAt(j)) {
                    contador++;
                }
            }
        }
        return String.valueOf(contador);

    }

    // Metodo para saber si es palíndromo
    public static String esPalindromo(String frase) {
        String fraseInvertida = invertirFrase(frase);
        if (frase.equals(fraseInvertida)) {
            return "Es palíndromo";
        } else {
            return "No es palíndromo";
        }
    }

    // Metodo para ordenar la cadena de elementos segun su longitud de cada uno
    public static String ordenarLongitud(String frase) {
        // Creo un array de palabras a partir de la frase y con el split las separo
        String[] palabras = frase.split(" ");
        // Ordeno el array con .sort usando un comparator
        Arrays.sort(palabras, new Comparator<String>() {
            // Obligatorio sobreescribir el compareTo con el override
            @Override
            // El compareTo siempre se declara int porque nos devuelve un valor int (1, 0, -1)
            public int compare(String palabra1, String palabra2) {
                int long1 = palabra1.length();
                int long2 = palabra2.length();
                if (long1 < long2) {
                    return -1;
                } else if (long1 > long2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        return String.join(" ", palabras); // Unimos las palabras con un espacio entre ellas para formar de nuevo una cadena y devolvera ordenada
    }
}
