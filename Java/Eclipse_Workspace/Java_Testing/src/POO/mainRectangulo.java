package POO;
import java.util.*;


public class mainRectangulo {
    private static Scanner teclado = new Scanner(System.in);
    public static void main(String[] args){

        Rectangulo r1 = new Rectangulo (1,1);

        System.out.println("Introduzca la longitud del rectangulo:");
        double longitud = teclado.nextDouble();
        r1.setLongitud(longitud);

        System.out.println("Introduzca el ancho del rectangulo:");
        double ancho = teclado.nextDouble();
        r1.setAncho(ancho);
        
        System.out.println("El area del rectangulo es: " + r1.calcularArea());
        System.out.println("El perimetro del rectangulo es: " + r1.calcularPerimetro());
    }
  
/* Si introducimos un valor incorrecto nos lo indicará con un mensaje, en los metodos setter definimos
la condición predeterminada y al objeto nuevo le hemos asignado 1 a longitud y ancho */

}
