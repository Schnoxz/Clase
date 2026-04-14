package EjercicioPruebaExamen.Ejercicio4;

public class TorneoOficial extends Torneo {
    private int medallasPremio;

    public TorneoOficial(String codigo, String nombre, Region region, int numParticipantes,
            int[][] puntosPorRonda, int medallasPremio)
            throws PuntosNegativosException, ParticipantesInsuficientesException {
        super(codigo, nombre, region, numParticipantes, puntosPorRonda);
        this.medallasPremio = medallasPremio;
    }

    public int getMedallasPremio() { return medallasPremio; }
    public void setMedallasPremio(int medallasPremio) { this.medallasPremio = medallasPremio; }

    @Override
    public String toString() {
        return super.toString() + " | Medallas premio: " + medallasPremio;
    }

    @Override
    public double calcularPuntuacionFinal() {
        return (calcularPuntosTotal() * 1.5) + (medallasPremio * 10);
    }

    @Override
    public boolean esFinalistaLiga() {
        return calcularPuntuacionFinal() >= 150;
    }
}