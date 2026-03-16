package src.Ejercicio3;

import java.util.Random;

public class FaltasAlumnosAsignaturas {

    private Falta[][] matriz; // Contiene Alumnos y Asignatura
    private int numAlumnos;
    private String[] nombreAsignaturas;

    // Consutructor
    public FaltasAlumnosAsignaturas(int numAlumnos, String[] nombreAsignaturas) {
        this.numAlumnos = numAlumnos;
        this.nombreAsignaturas = nombreAsignaturas;
        this.matriz = new Falta[numAlumnos][nombreAsignaturas.length];
        rellenarAleatorio();// Llamada al método que crea valores aleatorios
    }

    // Rellena la matriz con datos aleatorios (0-5 faltas de cada tipo)
    private void rellenarAleatorio() {
        Random aleatorio = new Random();
        // Recorre la matriz, la i es el alumno y la j la asignatura
        for (int i = 0; i < numAlumnos; i++) {
            for (int j = 0; j < nombreAsignaturas.length; j++) {
                int justificadas = aleatorio.nextInt(6); // 0-5
                int injustificadas = aleatorio.nextInt(6); // 0-5
                int retrasos = aleatorio.nextInt(6); // 0-5
                // Le otorgo al objeto matriz las faltas justificadas, injustificadas y retrasos
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
        return nombreAsignaturas.length;
    }

    // Método para listar cada asignatura, su nombre y el número del alumno que tiene la mayor cantidad de faltas injustificadas, así como el número de faltas que tiene
    public void listarAsignaturas() {
        // Recorre el array de asignaturas y luego el de alumnos
        for (int i = 0; i < nombreAsignaturas.length; i++) {
            // Declaro dentro del bucle las variables que voy a usar porque las he dejado fuera previamente y al cambiar de asignatura, no se reinician
            int mayor = 0;
            int alumno = 0;
            for (int j = 1; j < numAlumnos; j++) { // Inicio en 1 para no contar el alumno 0 que ya está inicializado
                // Cada vez que recorre una posición comprueba la cantidad de faltas injustificadas llamando al get de la clase Falta, la compara con la mayor y la guarda
                if (matriz[j][i].getInjustificadas() > mayor) {
                    mayor = matriz[j][i].getInjustificadas();
                    alumno = j; // Guarda la posición del array alumno que más faltas injustificadas tiene y la guarda en la variable alumno que se ha creado en este método
                }
            }
            // Muestra el resultado
            System.out.println("En la asignatura " + nombreAsignaturas[i] + " el alumno " + alumno + " tiene " + mayor
                    + " faltas injustificadas.");
        }
    }

    // Método para listar los alumnos que tienen un número de retrasos superior a la media de los retrasos de todos los alumnos
    public void listarRetrasosUpMedia() {
        int media = 0; // Inicializo la media en 0
        for (int i = 0; i < numAlumnos; i++) { // Recorre el array de alumnos
            for (int j = 0; j < nombreAsignaturas.length; j++) { // Recorre el array de asignaturas
                media += matriz[i][j].getRetrasos(); // A la media le asigno el retraso que se haya encontrado por cada alumno en cada asignatura dentro de la matriz
            }
        }
        media /= numAlumnos;
        System.out.println("Media de retrasos: " + media);
        // Calculo la media de todos los retrasos de todos los alumnos
        for (int i = 0; i < numAlumnos; i++) {
            int retrasosAlumno = 0; // Reinicia para cada alumno
            for (int j = 0; j < nombreAsignaturas.length; j++) {
                retrasosAlumno += matriz[i][j].getRetrasos(); // Suma todos los retrasos del alumno en todas las asignaturas
            }
            // Se compara si el retraso del alumno es mayor que la media
            if (retrasosAlumno > media) {
                System.out.println("El alumno " + i + " tiene " + retrasosAlumno + " retrasos");
            }
        }
    }

    // Método que muestra la asignatura que tiene menor número de retrasos.
    public void menorRetrasosAsignatura() {
        // He tenido que inicializar menor como Integer.MAX_VALUE para que no me de error al comparar, al ponerla en 0 me daba 0 siempre porque
        int menor = Integer.MAX_VALUE;
        int asignatura = 0; // Variable que guarda el índice de la asignatura
        for (int i = 0; i < nombreAsignaturas.length; i++) { // Recorro las asignaturas
            int sumaAsignatura = 0; // Reinicio la suma para cada asignatura
            for (int j = 0; j < numAlumnos; j++) { // Recorro los alumnos
                sumaAsignatura += matriz[j][i].getRetrasos(); // Sumo los retrasos de todos los alumnos en esta asignatura
            }
            if (sumaAsignatura < menor) { // Si la suma es menor que la menor actual
                menor = sumaAsignatura; // Actualizo el menor
                asignatura = i; // Guardo el índice de la asignatura
            }
        }
        System.out.println(nombreAsignaturas[asignatura] + " con " + menor + " retrasos.");
    }
}
