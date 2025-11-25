import java.util.Scanner;

public class Introduccion5 {
    private static final Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
        final int Combinacion = 8412;
        int intentos = 5;
        int numUsuario;

       
        System.out.println("Adivina la combinación de 4 dígitos. Tienes " + intentos + " intentos.");

        while (intentos > 0 ) {
            System.out.println("Introduce la combinación: ");
            numUsuario = teclado.nextInt();

            if (numUsuario == Combinacion) {
                System.out.println("La caja ha sido abierta con éxito.");
                break;
                
            } else {
                System.out.println("Combinación incorrecta.");
                intentos--;
                System.out.println("Te quedan " + intentos + " intento(s).");
            } 

            if (numUsuario < 1000 || numUsuario > 9999) {
                System.out.println("La combinación debe ser de 4 dígitos");
                
        }
     }
    }

    }
    
