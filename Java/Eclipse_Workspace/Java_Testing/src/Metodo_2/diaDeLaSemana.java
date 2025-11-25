package Metodo_2;
import java.util.*;

public class diaDeLaSemana { 
    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
    	
      // Declaro las variables fueras y dentro del main para que existan en todos los métodos
        int dia, mes, anno;
        boolean fechaValida; // Una bandera para saber si salimos del bucle

        do {
            System.out.println("Introduzca la fecha de forma numérica: ");
            
            System.out.print("Día: ");
            dia = teclado.nextInt();

            System.out.print("Mes: ");
            mes = teclado.nextInt();

            System.out.print("Año: ");
            anno = teclado.nextInt();

            // Llamo al método para validar la fecha, si no es correcto se repite la introducción de datos del usuario
            fechaValida = validarFecha(dia, mes, anno);

            if (!fechaValida) {
                System.out.println("La fecha introducida no es correcta. Inténtalo de nuevo.\n");
            }

        } while (!fechaValida); // Si no es válida, repite.

        // Llamo al método para calcular el dia y muestro los datos
        String resultado = calcularDiaSemana(dia, mes, anno);
        System.out.println("\n El día " + dia + "/" + mes + "/" + anno + " fue: " + resultado);
    }



    // Método que devuelve true si la fecha es correcta, false si falla
    private static boolean validarFecha(int dia, int mes, int anno) {
        // Primero: mes o año imposibles
        if (mes < 1 || mes > 12 || anno < 0) {
            return false;
        }
        
       // Validar los días dentro de cada mes
        int diasMaximos = obtenerDiasDelMes(mes, anno);
        if (dia < 1 || dia > diasMaximos) {
            System.out.println("(Ese mes solo tiene " + diasMaximos + " días)");
            return false;
        }
        
        return true; 
    }

    // Método para obtener días del mes y comprobar los datos introducidos
    public static int obtenerDiasDelMes(int mes, int anno) {
        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            return 30;
        } else if (mes == 2) {
            // Si fuese bisiesto
            if ((anno % 4 == 0 && anno % 100 != 0) || (anno % 400 == 0)) {
                return 29; 
            } else {
                return 28;
            }
        } else {
            return 31;
        }
    }

    // Método para el calculo del día de la semana
    private static String calcularDiaSemana(int dia, int mes, int anno) {


        int a = (14 - mes) / 12;
        int y = anno - a;
        int m = mes + 12 * a - 2;
        int d = (dia + y + y / 4 - y / 100 + y / 400 + (31 * m) / 12) % 7;

        switch (d) {
            case 0: return "Domingo";
            case 1: return "Lunes";
            case 2: return "Martes";
            case 3: return "Miércoles";
            case 4: return "Jueves";
            case 5: return "Viernes";
            case 6: return "Sábado";
            default: return "Error";
        }
    }
}
	
	
