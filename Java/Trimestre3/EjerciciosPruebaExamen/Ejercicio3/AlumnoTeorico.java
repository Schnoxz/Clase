package EjerciciosPruebaExamen.Ejercicio3;

public class AlumnoTeorico extends Alumno {
    private int librosLeidos;

    public AlumnoTeorico(String matricula, String nombre, Especialidad especialidad, int[][] notasSemanales, int librosLeidos) {
        super(matricula, nombre, especialidad, notasSemanales);
        this.librosLeidos = librosLeidos;
    }

    public int getLibrosLeidos() { return librosLeidos; }
    public void setLibrosLeidos(int librosLeidos) { this.librosLeidos = librosLeidos; }

    @Override
    public String toString() {
        return super.toString() + " | Libros leidos: " + librosLeidos;
    }

    @Override
    public double calcularNota() {
        return (calcularPuntuacionTotal() * 0.8) + (librosLeidos * 5);
    }

    @Override
    public boolean obtieneCertificado() {
        return calcularNota() >= 120;
    }
}