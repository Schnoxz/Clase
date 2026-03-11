package src.Ejercicio1;

import java.util.Random;

public class boletinNotas {

    // Declaro los atributos en privado, creo una matriz de notas que sea double, un array String para las asignaturas y dos variables enteras
    private double[][] notas;
    private String[] asignaturas;
    private int numAlumnos;
    private int numAsignaturas;

    // Constructor de la clase boletinNotas
    public boletinNotas(int numAlumnos, String[] asignaturas) {
        this.numAlumnos = numAlumnos; // Guardo el número de alumnos
        this.asignaturas = asignaturas; // Guardo el array de asignaturas
        this.numAsignaturas = asignaturas.length; // Guardo el número de asignaturas
        this.notas = new double[numAlumnos][numAsignaturas]; // Creo el array de notas y le paso el número de alumnos y el número de asignaturas
    }

    // Metodo que recorre el array para introducir notas de forma aleatoria con Random
    public void notasAleatorias() {
        Random r = new Random(); // Creo un Random para generar notas aleatorias
        // Recorro la matriz de cada alumno y cada asignatura
        for (int i = 0; i < numAlumnos; i++) {
            for (int j = 0; j < numAsignaturas; j++) {
                notas[i][j] = r.nextInt(11); // Genero notas aleatorias entre 0 y 10
            }
        }
    }

    // Devuelve cuántas asignaturas suspende un alumno (0, 1, 2, 3, 4)
    private int suspensosPorAlumno(int alumno) {
        int contador = 0; // Declaro un contador
        // Recorro el array de asignaturas y si la nota es menor a 5, aumento el contador de suspensos
        for (int j = 0; j < numAsignaturas; j++) {
            // El indice alumno nos fija la fila que recorrer y j recorre columna por columna comparando el campo notas
            if (notas[alumno][j] < 5) { // El funcionamiento de esta matriz es comparar en cada asignatura las notas de cada alumno que sean menor que 5
                contador++;
            }
        }
        return contador; // Devuelvo el contador
    }

    // Muestra cuántos alumnos tienen N asignaturas suspensas
    public void mostrarSuspensos() {
        System.out.println("\n--- Número de alumnos suspensos ---");
        for (int n = numAsignaturas; n >= 0; n--) { // Recorro el array de asignaturas y muestro cuántos alumnos tienen N asignaturas suspensas, empezando por el número de asignaturas, y hasta llegar a 0
            int cuenta = 0; // Declaro un contador
            for (int i = 0; i < numAlumnos; i++) { // Recorro el array de alumnos
                if (suspensosPorAlumno(i) == n) { // Si el alumno tiene N asignaturas suspensas, aumento el contador
                    cuenta++;
                }
            }
            System.out.println("Alumnos con " + n + " asignaturas suspensas: " + cuenta);
        }
    }

    // Muestra la nota media de cada asignatura
    public void mediaPorAsignatura() {
        System.out.println("\n--- Nota media por asignatura ---");
        for (int j = 0; j < numAsignaturas; j++) { // Recorro el array de asignaturas
            double suma = 0; // Declaro una variable para la suma
            for (int i = 0; i < numAlumnos; i++) { // Recorro el array de alumnos
                suma += notas[i][j]; // Sumo usando la matriz de notas, las notas de todos los alumnos en cada asignatura
            }
            double media = Math.round(suma / numAlumnos * 100.0) / 100.0; // Calculo de la media y uso Math round para redondear a 2 decimales, casteo multiplicando por 100.0 y dividiendo por 100.0
            System.out.println("La nota media de " + asignaturas[j] + " es " + media);
        }
    }
}
