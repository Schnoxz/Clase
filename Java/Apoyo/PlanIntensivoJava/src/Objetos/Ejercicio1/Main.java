package Objetos.Ejercicio1;
import java.util.Scanner;

public class Main {
    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) throws VelocidadNoValidaException {
        CocheConstructor coche = new CocheConstructor("Toyota", "Corolla", 2020, 0);
        coche.mostrarInformacion();

        try {
        System.out.print("¿Cuánto acelera? ");
        double cantidad = teclado.nextDouble();
        coche.acelerar(cantidad);

        System.out.print("¿Cuánto frena? ");
        cantidad = teclado.nextDouble();
        coche.frenar(cantidad);
        } catch (VelocidadNoValidaException e) {
			System.out.println("Error: " + e.getMessage());
		}
        coche.mostrarInformacion();
    }
}