public class Main {
    public static void main(String[] args) {

        boletinNotas boletin = new boletinNotas(30, new String[]{
            "PROGRAMACION", "BBDD", "ENTORNO", "SISTEMAS", "HTML"
        });
        boletin.cargarNotasAleatorias();
        boletin.mostrarSuspensosPorAlumno();
        boletin.mostrarMediasPorAsignatura();
    }
}

