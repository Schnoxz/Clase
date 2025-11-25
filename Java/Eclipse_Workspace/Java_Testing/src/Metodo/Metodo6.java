// Realizar un programa que lea el número de alumnos que hay en una clase. A continuación debe leer la nota de cada uno de ellos en un examen y nos debe informar del número de suspensos y aprobados, así como los porcentajes.
package Metodo;

import java.util.Scanner;

public class Metodo6 {
    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Introduce el número de alumnos: ");
        int alumnos = teclado.nextInt();

        procesarNotas(alumnos); // Llamada al método
    }

    // Método que procesa las notas y muestra los resultados y porcentajes
    public static void procesarNotas(int alumnos) {
        int aprobados = 0;
        int suspensos = 0;
        double nota;

        for (int i = 1; i <= alumnos; i++) {
            System.out.print("Introduce la nota del alumno " + i + ": ");
            nota = teclado.nextDouble();

            if (nota >= 5) {
                aprobados++;
            } else {
                suspensos++;
            }
        }

        double porcentajeAprobados = (aprobados * 100.0) / alumnos;
        double porcentajeSuspensos = (suspensos * 100.0) / alumnos;

        System.out.println("\nResultados:");
        System.out.println("Número de aprobados: " + aprobados);
        System.out.println("Número de suspensos: " + suspensos);
        System.out.println("Porcentaje de aprobados: " + porcentajeAprobados + "%");
        System.out.println("Porcentaje de suspensos: " + porcentajeSuspensos + "%");
    }
}
