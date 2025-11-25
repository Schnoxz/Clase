package Metodo_2;
import java.util.*;

public class siguienteFecha {
	private static final Scanner teclado = new Scanner (System.in);
	
	public static void main (String[] args) {
		int dia;
		int mes;
		int año;
		do {
		System.out.println("Introduzca el dia de hoy: ");
		dia = teclado.nextInt();
		
		System.out.println("Introduzca el mes actual: ");
		mes = teclado.nextInt();
		
		System.out.println("Introduzca el año actual: ");
		año = teclado.nextInt();
		
		// Calculo si los dias que se introducen son legítimos para ese mes por ejemplo febrero, no todos los años tiene el mismo número o por supuesto tampoco tiene 30 o 31
        int diaMax = diaDelMes(mes, año);

        if (dia < 1 || dia > diaMax || mes < 1 || mes > 12 || año < 1) {
            System.out.println("\n❌ Fecha inválida. Ese mes tiene como máximo " + diaMax + " días.\n");
        }
    } while (dia < 1 || dia > diaDelMes(mes, año) || mes < 1 || mes > 12 || año < 1);
		
		siguiente(dia, mes, año);
}
			
	public static void siguiente (int diaActual, int mesActual, int añoActual) {
		System.out.println("La fecha de hoy es " + diaActual + "-" + mesActual + "-" + añoActual);
		
        int dia = diaActual + 1;
        int mes = mesActual;
        int año = añoActual;

        // Calcular días máximos del mes
        int diaMes = diaDelMes(mes, año);

        // Si se pasa del número de días del mes, reinicio a 1 y avanzo mes
        if (dia > diaMes) {
            dia = 1;
            mes++;
            // Si se pasa de diciembre, reinicio a enero y avanzo el año
            if (mes > 12) {
                mes = 1;
                año++;
            }
        }

        System.out.println("La fecha de mañana es " + dia + "-" + mes + "-" + año);
    }

    // Método  para calcular los días máximos de un mes
    public static int diaDelMes(int mes, int año) {
        int diaMax = 31;

        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            diaMax = 30;
        } else if (mes == 2) {
            if ((año % 4 == 0 && año % 100 != 0) || (año % 400 == 0)) {
                diaMax = 29; // año bisiesto
            } else {
                diaMax = 28;
            }
        }

        return diaMax;
    }
}
