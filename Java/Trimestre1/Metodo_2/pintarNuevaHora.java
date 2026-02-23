package Metodo_2;
import java.util.*;

public class pintarNuevaHora {
	private static final Scanner teclado = new Scanner (System.in);
	
	public static void main (String[] args) {
		int hora;
		int minuto;
		int segundo;
		int segundoASumar;
		do {
		System.out.println("Introduzca la hora actual: ");
		hora = teclado.nextInt();
		
		System.out.println("Introduzca los minutos: ");
		minuto = teclado.nextInt();
		
		System.out.println("Introduzca los segundos: ");
		segundo = teclado.nextInt();
		
		System.out.println("Introduzca los segundos a sumar: ");
		segundoASumar = teclado.nextInt();
		// Creo una condición donde si los valores introducidos son incorrectos se devuelve al usuario al inicio
        if (hora < 0 || hora > 23 || minuto < 0 || minuto > 59 || segundo < 0 || segundo > 59 || segundoASumar < 0) {
            System.out.println("Los datos introducidos son erróneos, introdúzcalos de nuevo\n" );
        }
    } while (hora < 0 || hora > 23 || minuto < 0 || minuto > 59 || segundo < 0 || segundo > 59 || segundoASumar < 0);

    // Si los datos son correctos, llamamos al método
    pintar(hora, minuto, segundo, segundoASumar);
	
}
		

	
	public static void pintar (int horaActual, int minutoActual, int segundoActual, int segundoASumar) {
		System.out.println("La anterior hora era " + " H: " + horaActual + " M: " + minutoActual + " S: " + segundoActual);
		// Paso las variables que se han introducido a segundos 
		int total = horaActual * 3600 + minutoActual * 60 + segundoActual + segundoActual;
		// Le doy un valor a total como rango máximo para que el límite sea un día
		total = total % 86400;
		
		// Calculo la hora nueva
	       int nuevaHora = total / 3600;
	       int nuevoMinuto = (total % 3600) / 60;
	       int nuevoSegundo = total % 60;
		
		System.out.println("La nueva hora es " + " H: " + nuevaHora + " M: " + nuevoMinuto + " S: " + nuevoSegundo);
 }
}
