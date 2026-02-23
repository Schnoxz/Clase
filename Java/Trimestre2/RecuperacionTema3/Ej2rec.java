import java.util.Scanner;

public class Ej2rec {

    // Quita espacios al principio y al final (sin trim ni substring)
    public static String recortarEspacios(String t) {
        int ini = 0;
        int fin = t.length() - 1;

        while (ini < t.length() && t.charAt(ini) == ' ') {
            ini++;
        }
        while (fin >= 0 && t.charAt(fin) == ' ') {
            fin--;
        }

        // si se queda vacío
        if (ini > fin) return "";

        String res = "";
        for (int i = ini; i <= fin; i++) {
            res = res + t.charAt(i);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce datos separados por ';': ");
        String linea = sc.nextLine();

        String actual = "";
        int numToken = 1;

        String tokenMasLargo = "";
        int longMax = -1;

        for (int i = 0; i < linea.length(); i++) {
            char c = linea.charAt(i);

            if (c != ';') {
                actual = actual + c;
            } else {
                String limpio = recortarEspacios(actual);
                System.out.println("Token " + numToken + ": " + limpio);

                if (limpio.length() > longMax) {
                    longMax = limpio.length();
                    tokenMasLargo = limpio;
                }

                numToken++;
                actual = "";
            }
        }

        // último token
        String limpio = recortarEspacios(actual);
        System.out.println("Token " + numToken + ": " + limpio);

        if (limpio.length() > longMax) {
            longMax = limpio.length();
            tokenMasLargo = limpio;
        }

        System.out.println("Total tokens: " + numToken);
        System.out.println("Token mas largo: " + tokenMasLargo + " (" + longMax + ")");

        sc.close();
    }
}