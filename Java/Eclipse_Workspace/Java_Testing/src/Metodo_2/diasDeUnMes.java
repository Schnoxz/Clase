package Metodo_2;
import java.util.Scanner;

public class diasDeUnMes {
    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Introduce el número del mes (1-12): ");
        int mes = teclado.nextInt();

        System.out.print("Introduce el año: ");
        int año = teclado.nextInt();

        // Llamo al método que calcula los días
        int dias = dias(mes, año);
        System.out.println("El mes " + mes + " del año " + año + " tiene " + dias + " días.");

        // Llamo al método que informa si es bisiesto
        esbisiesto(año);
    }

    // Método que informa si un año es bisiesto (no devuelve nada, solo imprime)
    public static void esbisiesto(int año) {
        if ((año % 4 == 0 && año % 100 != 0) || (año % 400 == 0)) {
            System.out.println("El año " + año + " es bisiesto.");
        } else {
            System.out.println("El año " + año + " no es bisiesto.");
        }
    }

    // Creo el método que devuelve los días de un mes y diferencio los meses en la cantidad de días 
    public static int dias(int mes, int año) {
        int dias = 0;

        if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
            dias = 31;
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            dias = 30;
        } else if (mes == 2) {
            if ((año % 4 == 0 && año % 100 != 0) || (año % 400 == 0)) {
                dias = 29;
            } else {
                dias = 28;
            }
        }
        return dias;
    }
}
