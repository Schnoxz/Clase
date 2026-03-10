package src.Ejercicio1;

import java.util.Random; // Para generar de forma aleatoria valores de notas

public class notasAlumnos {
// Declaracion de atributos y arrays en private para las notas, el numero de alumnos y las asignaturas
    private double[][] notas;
    private int numAlumnos;
    private String[] asignaturas; 
    
    // Creacion del constructor
    public boletinNotas(int numAlumnos, String[] asignaturas) {
        this.numAlumnos = numAlumnos;
        this.asignaturas = asignaturas;
        this.notas = new double[numAlumnos][asignaturas.length];
    }


    // Cuantas asignaturas suspende cada alumno
    public int contadorSuspensos(int alumno){
        int suspensos = 0;
        for (int j = 0 ; j < asignaturas.length; j++) {
            if (notas[alumno][j] < 5) {
                suspensos ++;
            }
        }
        return suspensos;
    }

    // Alumnos con asignaturas suspensas (5, 4, 3, 2, 1 y 0)
    public void suspensosAlumnos(){
        int[] contador = new int[asignaturas.length + 1];

        for (int i = 0; i < numAlumnos; i++) {
            int suspensos = contadorSuspensos(i);
            contador[suspensos] ++;
        }
    }
