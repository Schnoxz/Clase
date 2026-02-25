// Buscar el alumno con mejor nota, Dado un array de Alumno, muestra el alumno con mayor nota

public class array14 {

    public static void main(String[] args) {
        Alumno[] alumnos = new Alumno[3];

        alumnos[0] = new Alumno("Javi", 6);
        alumnos[1] = new Alumno("Marcos", 7);
        alumnos[2] = new Alumno("Marta", 9);

        Alumno mejorNota = alumnos[0]; // Inicializo la variable mejorNota que se va a usar posteriormente
        for (int i = 1; i < alumnos.length; i++) {
            if (alumnos[i].nota > mejorNota.nota) { // Condicion si la nota actual es mayor que la anterior
                mejorNota = alumnos[i]; // Guardo la nota nueva
            }
        }
        System.out.println("El alumno con mejor nota es: " + mejorNota.nombre);

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
