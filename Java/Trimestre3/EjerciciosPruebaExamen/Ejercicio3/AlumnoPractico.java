package EjerciciosPruebaExamen.Ejercicio3;

public class AlumnoPractico extends Alumno {
    private int pokemonesCapturados;

    public AlumnoPractico(String matricula, String nombre, Especialidad especialidad, int[][] notasSemanales, int pokemonesCapturados) {
        super(matricula, nombre, especialidad, notasSemanales);
        this.pokemonesCapturados = pokemonesCapturados;
    }

    public int getPokemonesCapturados() { return pokemonesCapturados; }
    public void setPokemonesCapturados(int pokemonesCapturados) { this.pokemonesCapturados = pokemonesCapturados; }

    @Override
    public String toString() {
        return super.toString() + " | Pokémon capturados: " + pokemonesCapturados;
    }

    @Override
    public double calcularNota() {
        return (calcularPuntuacionTotal() * 1.1) + (pokemonesCapturados * 3);
    }

    @Override
    public boolean obtieneCertificado() {
        return calcularNota() >= 100;
    }
}