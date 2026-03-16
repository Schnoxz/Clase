package src.Ejercicio3;

public class Main {

    public static void main(String[] args) {
        // Llamo al constructor de la clase FaltasAlumnosAsignaturas y genero una matriz con 30 alumnos y 5 asignaturas y le paso el array String de asignarturas
        FaltasAlumnosAsignaturas faltasAlumnosAsignaturas = new FaltasAlumnosAsignaturas(30, new String[]{"PROGRAMACION", "BBDD", "SISTEMAS", "HTML", "FILIPPO"});
        // Llamo a los metodos con un enunciado para cada uno
        System.out.println("\nLista de alumnos con faltas que superan a la media");
        faltasAlumnosAsignaturas.listarAsignaturas(); // Método para listar cada asignatura, su nombre y el número del alumno que tiene la mayor cantidad de faltas injustificadas, asi como el número de faltas que tiene
        System.out.println("\nLista de alumnos con retrasos superiores a la media");
        faltasAlumnosAsignaturas.listarRetrasosUpMedia(); // Metodo para listar los alumnos que tienen un número de retrasos superior a la media de los retrasos de todos los alumnos
        System.out.println("\nAsignatura con la menor cantidad de restrasos por alumno");
        faltasAlumnosAsignaturas.menorRetrasosAsignatura(); // Metodo que muestra la asignatura que tiene menor número de retrasos
    }
}
