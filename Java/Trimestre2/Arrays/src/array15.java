// Subir nota a todos, Recorre el array de alumnos y suma 1 punto a todo

public class array15 {

    public static void main(String[] args) {
        Alumno[] alumnos = new Alumno[3];
        alumnos[0] = new Alumno("Javi", 6);
        alumnos[1] = new Alumno("Marcos", 7);
        alumnos[2] = new Alumno("Marta", 9);
        for (int i = 0; i < alumnos.length; i++) { // Recorro el array de todos los alumnos
            alumnos[i].nota = alumnos[i].nota + 1; // Selecciono solo la nota y sumo 1 a cada nota de cada alumno
        }
        for (int i = 0; i < alumnos.length; i++) {
            // Myuestro los datos
            System.out.println("Alumno: " + alumnos[i].nombre + " --> Nota: " + alumnos[i].nota);
        }

    }

    // Clase Alumno con nombre y nota
    public static class Alumno {

        String nombre;
        double nota;

        // Constructor de la clase Alumno
        public Alumno(String nombre, double nota) {
            this.nombre = nombre;
            this.nota = nota;
        }
    }
}
