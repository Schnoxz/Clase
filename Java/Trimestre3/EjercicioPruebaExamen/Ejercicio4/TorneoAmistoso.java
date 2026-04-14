package EjercicioPruebaExamen.Ejercicio4;

public class TorneoAmistoso extends Torneo {
    private int pokemonRegistrados;

    public TorneoAmistoso(String codigo, String nombre, Region region, int numParticipantes, int[][] puntosPorRonda, int pokemonRegistrados)
        throws PuntosNegativosException, ParticipantesInsuficientesException {
        super(codigo, nombre, region, numParticipantes, puntosPorRonda);
        this.pokemonRegistrados = pokemonRegistrados;
    }

    public int getPokemonRegistrados() { return pokemonRegistrados; }
    public void setPokemonRegistrados(int pokemonRegistrados) { this.pokemonRegistrados = pokemonRegistrados; }

    @Override
    public String toString() {
        return super.toString() + " | Pokemon registrados: " + pokemonRegistrados;
    }

    @Override
    public double calcularPuntuacionFinal() {
        return (calcularPuntosTotal() * 1.0) + (pokemonRegistrados * 5);
    }

    @Override
    public boolean esFinalistaLiga() {
        return calcularPuntuacionFinal() >= 100;
    }
}