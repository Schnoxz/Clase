// Crear array de objetos, Crea una clase Alumno con: nombre y nota, Crea un array de 3 alumnos, Inicialízalos correctamente (evita null) Muestra sus datos.

public class array13 {

    public static void main(String[] args) {

        Alumno[] alumnos = new Alumno[3];
        // Creo 3 objetos de la clase Alumno y las guardo en el array
        alumnos[0] = new Alumno("Javi", 6);
        alumnos[1] = new Alumno("Marcos", 7);
        alumnos[2] = new Alumno("Marta", 9);
        // Recorro el array y muestro los datos teniendo en cuenta los atributos del objeto
        for (int i = 0; i < alumnos.length; i++) {
            System.out.println("Alumno: " + alumnos[i].nombre + " --> Nota: " + alumnos[i].nota);
        }
    }

    public static class Alumno {

        String nombre;
        double nota;

        // Consutrctor del objeto Alumno, con los atributos nombre y nota
        public Alumno(String nombre, double nota) {
            this.nombre = nombre;
            this.nota = nota;
        }
    }

}
