import java.util.Scanner;

public class Introduccion10 {
    private static final Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
       

        System.out.print("Introduce la base (impar): ");
        int base = teclado.nextInt();
        while (base % 2 == 0) {
            System.out.print("La base debe ser impar. Introduce de nuevo: ");
            base = teclado.nextInt();
        }

        System.out.print("Introduce un carácter: ");
        char caracter = teclado.next().charAt(0);

        // Generar el triángulo
        for (int i = 1; i <= base; i += 2) {
            for (int j = 0; j < i; j++) {
                System.out.print(caracter);
            }
            System.out.println();
        }

    }
}
