package src.Ejercicio3;

import java.util.Random;

public class FaltasAlumnosAsignaturas {

    private Falta[][] matriz; // Contiene Alumnos y Asignatura
    private int numAlumnos; 
    private int numAsignaturas;

	// Consutructor 
    public FaltasAlumnosAsignaturas(int numAlumnos, int numAsignaturas) {
        this.numAlumnos = numAlumnos;
        this.numAsignaturas = numAsignaturas;
        this.matriz = new Falta[numAlumnos][numAsignaturas];
        rellenarAleatorio();// Llamada al método que crea valores aleatorios
    }

    // Rellena la matriz con datos aleatorios (0-5 faltas de cada tipo)
    private void rellenarAleatorio() {
        Random Aleatorio = new Random();
        for (int i = 0; i < numAlumnos; i++) {
            for (int j = 0; j < numAsignaturas; j++) {
                int justificadas = Aleatorio.nextInt(6); // 0-5
                int injustificadas = Aleatorio.nextInt(6);
                int retrasos = Aleatorio.nextInt(6);
                matriz[i][j] = new Falta(justificadas, injustificadas, retrasos);
            }
        }
    }

    public Falta getFalta(int alumno, int asignatura) {
        return matriz[alumno][asignatura];
    }

    public int getNumAlumnos() {
        return numAlumnos;
    }

    public int getNumAsignaturas() {
        return numAsignaturas;
    }
}