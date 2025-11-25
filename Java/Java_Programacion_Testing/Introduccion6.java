import java.util.Scanner;

public class Introduccion6 {
    private static final Scanner teclado = new Scanner(System.in);
    
    public static void main(String[] args) {
        int alumnos;
        int aprobados = 0;
        int suspensos = 0;
        double nota;
        double porcentajeAprobados;
        double porcentajeSuspensos;
        
        System.out.print("Introduce el número de alumnos: ");
        alumnos = teclado.nextInt();
        
        for (int i = 1; i <= alumnos; i++) {
            System.out.print("Introduce la nota del alumno " + i + ": ");
             nota = teclado.nextDouble();
            
            if (nota >= 5) {
                aprobados++;
            } else {
                suspensos++;
            }
        }
        
        // Calculamos porcentajes
        porcentajeAprobados = (aprobados * 100.0) / alumnos;
        porcentajeSuspensos = (suspensos * 100.0) / alumnos;
        
        // Mostramos resultados
        System.out.println("\nResultados:");
        System.out.println("Número de aprobados: " + aprobados);
        System.out.println("Número de suspensos: " + suspensos);
        System.out.println("Porcentaje de aprobados: " + porcentajeAprobados + "%");
        System.out.println("Porcentaje de suspensos: " + porcentajeSuspensos + "%");
    }
}