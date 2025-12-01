// Realizar un programa que lea el número de alumnos que hay en una clase. A continuación debe leer la nota de cada uno de ellos en un examen y nos debe informar del número de suspensos y aprobados, así como los porcentajes.
package Default;
import java.util.Scanner;

public class Introduccion6 {
    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        // Variables para almacenar el número total de alumnos y los contadores de aprobados/suspensos
        int alumnos;
        int aprobados = 0;
        int suspensos = 0;

        // Variables para la nota individual y los porcentajes finales
        double nota;
        double porcentajeAprobados;
        double porcentajeSuspensos;

        // Solicito al usuario el número total de alumnos
        System.out.print("Introduce el número de alumnos: ");
        alumnos = teclado.nextInt();

        // Bucle para leer la nota de cada alumno y clasificarla como aprobado o suspenso
        for (int i = 1; i <= alumnos; i++) {
            System.out.print("Introduce la nota del alumno " + i + ": ");
            nota = teclado.nextDouble();

            if (nota >= 5) {
                aprobados++;
            } else {
                suspensos++;
            }
        }

        // Cálculo de los porcentajes de aprobados y suspensos
        porcentajeAprobados = (aprobados * 100.0) / alumnos;
        porcentajeSuspensos = (suspensos * 100.0) / alumnos;

        // Muestra los resultados en pantalla
        System.out.println("\nResultados:");
        System.out.println("Número de aprobados: " + aprobados);
        System.out.println("Número de suspensos: " + suspensos);
        System.out.println("Porcentaje de aprobados: " + porcentajeAprobados + "%");
        System.out.println("Porcentaje de suspensos: " + porcentajeSuspensos + "%");
    }
}