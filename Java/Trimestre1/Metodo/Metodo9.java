// Realizar un programa que muestre todas las tablas de multiplicar (del 0 al 10)
package Metodo;

public class Metodo9 {
    public static void main(String[] args) {
        // Bucle para recorrer del 0 al 10 
        for (int i = 0; i <= 10; i++) {
            mostrarTabla(i); // llamo al método cada vez que recorre una nueva tabla
            System.out.println(); 
        }
    }

    // Método que muestra la tabla de multiplicar de un número dado
    public static void mostrarTabla(int numero) {
        System.out.println("Tabla del " + numero);
        for (int j = 0; j <= 10; j++) {
            System.out.println(numero + " x " + j + " = " + (numero * j));
        }
    }
}
