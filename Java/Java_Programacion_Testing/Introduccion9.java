

public class Introduccion9 {
    public static void main(String[] args) {


        // Bucle for que va desde el 0 al 10 y muestra la tabla de multiplicar
        for (int i = 0; i <= 10; i++) {
            System.out.println("Tabla del " + i);
            
            // Nos enseña la tabla de multiplicar del número 
            for (int j = 0; j <= 10; j++) {
                System.out.println(i + " x " + j + " = " + (i * j));
            }
            
            //Separacion de lineas entre tablas
            System.out.println();
        }
    }
}
