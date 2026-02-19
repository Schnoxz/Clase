import java.util.Scanner;

public class Ej1rec {

    // Comprime: "AAABCCDDDD" -> "A3B1C2D4"
    // (asumimos contadores 1..9)
    public static String comprimir(String texto) {
        String out = "";

        // Si está vacío, devolvemos vacío (por seguridad)
        if (texto.length() == 0) return out;

        char actual = texto.charAt(0);
        int cont = 1;

        for (int i = 1; i < texto.length(); i++) {
            char c = texto.charAt(i);

            if (c == actual) {
                cont++;
            } else {
                out = out + actual + (char)('0' + cont);
                actual = c;
                cont = 1;
            }
        }

        // cerrar último grupo
        out = out + actual + (char)('0' + cont);
        return out;
    }

    // Descomprime: "A3B1C2D4" -> "AAABCCDDDD"
    // Formato fijo: letra + dígito + letra + dígito...
    public static String descomprimir(String comprimido) {
        String out = "";

        for (int i = 0; i < comprimido.length(); i += 2) {
            char letra = comprimido.charAt(i);
            int rep = comprimido.charAt(i + 1) - '0'; // '3' -> 3

            for (int j = 0; j < rep; j++) {
                out = out + letra;
            }
        }

        return out;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce un texto: ");
        String texto = sc.nextLine();

        String comp = comprimir(texto);
        System.out.println("Comprimido: " + comp);

        String decomp = descomprimir(comp);
        System.out.println("Descomprimido: " + decomp);

        sc.close();
    }
}