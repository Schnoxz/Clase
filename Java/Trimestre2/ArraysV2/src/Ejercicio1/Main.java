package src.Ejercicio1;

public class Main {

    public static void main(String[] args) {
        String[] asignaturas = {"PROGRAMACION", "BBDD", "SISTEMAS", "HTML", "FILIPPO"};
        // Creo un boletin con 30 alumnos y 5 asignaturas y le paso el array de asignaturas
        boletinNotas boletin = new boletinNotas(30, asignaturas);
        boletin.notasAleatorias(); // Llamada al método que genera notas aleatorias de todos los alumnos
        boletin.mostrarSuspensos(); // Llamada al método que muestra la cantidad de suspensos dividido en notas
        boletin.mediaPorAsignatura();
    }
}
