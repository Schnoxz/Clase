
import java.util.Arrays;

public class array16 {

    public static void main(String[] args) {
        Alumno[] alumnos = new Alumno[3];
        alumnos[0] = new Alumno("Javi", 7);
        alumnos[1] = new Alumno("Marcos", 4);
        alumnos[2] = new Alumno("Marta", 9);

        // Uso Arrays.sort para que ordene el array, segun mi compareTo, es decir por la nota
        Arrays.sort(alumnos);

        System.out.println("Orden de alumnos por nota");
        for (int i = 0; i < alumnos.length; i++) {
            System.out.println("Alumno: " + alumnos[i].nombre + " --> Nota: " + alumnos[i].nota);
        }
    }

    // Clase alumno pero para tener un coompareTo se debe implementar la interfaz comparable, con un implements y la Alumno entre <>
    public static class Alumno implements Comparable<Alumno> { // El objeto alumno implementa la interfaz comparable

        String nombre;
        double nota;

        // Consutructor
        public Alumno(String nombre, double nota) {
            this.nombre = nombre;
            this.nota = nota;
        }

        // Obligatorio sobreescribir el compareTo con el override
        @Override
        public int compareTo(Alumno otroAlumno) {
            // Codigo del compareTo sin double.compare
            if (this.nota > otroAlumno.nota) {
                return 1;
            } else if (this.nota < otroAlumno.nota) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
