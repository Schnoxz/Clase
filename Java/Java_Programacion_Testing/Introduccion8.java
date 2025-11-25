import java.util.Scanner;
public class Introduccion8 {
    private static final Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
        int ancho, alto;
        char caracter;
       
        System.out.print("Introduce el ancho de la figura: ");
        ancho = teclado.nextInt();

        System.out.print("Introduce el alto de la figura: ");
        alto = teclado.nextInt();

        System.out.print("Introduce el caracter de la figura: ");
        caracter = teclado.next().charAt(0);

        //Iniciamos un bucle for para las filas
     for (int i = 0; i < alto; i++) {
        //Iniciamos un bucle for para las columnas de la figura
        for (int x = 0; x < ancho; x++) {
            // Imprimimos el caracter 
            System.out.print(caracter);
        } // Creamos un salto de línea para crear la figura
        System.out.println();
    }
}
}


