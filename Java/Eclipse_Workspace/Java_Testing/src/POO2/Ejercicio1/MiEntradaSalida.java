package POO2.Ejercicio1;

import java.util.Scanner;

public class MiEntradaSalida {
    private static final Scanner teclado = new Scanner(System.in);

    public static int solicitarEntero(String mensaje){
        int numero;
        boolean esCorrecto = false;
        do {
            try {
                System.out.println(mensaje);
                numero = Integer.parseInt(teclado.next());
                esCorrecto = true;
            } catch (Exception e) {
            } 
            
        } while (!esCorrecto);
    }
}
