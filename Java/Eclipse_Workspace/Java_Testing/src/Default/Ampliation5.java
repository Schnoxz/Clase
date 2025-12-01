// Conversión de temperaturas. Pide una temperatura en grados Celcius y conviértela a Fahrenheit y Kelvin. 
package Default;
import java.util.Scanner;

public class Ampliation5 {
	private static final Scanner teclado = new Scanner(System.in);
	public static void main(String[] args) {
		
        // Solicito al usuario una temperatura en grados Celsius
        System.out.print("Introduzca su temperatura en grados Celsius: ");
        double celsius = teclado.nextDouble();

        // Conversión de Celsius a Fahrenheit: F = C × 9/5 + 32
        double fahrenheit = celsius * 9 / 5 + 32;

        // Conversión de Celsius a Kelvin: K = C + 273.15
        double kelvin = celsius + 273.15;

        // Muestra los resultados en pantalla
        System.out.println("Fahrenheit: " + fahrenheit);
        System.out.println("Kelvin: " + kelvin);
    }
}
