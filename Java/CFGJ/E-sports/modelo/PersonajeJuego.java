package modelo;
// Basado en la mecánica del MOBA smite, tendremos personajes o dioses con sus propios atributos tal y como indica el juego original, su procedencia (panteon), su dificultad de uso, la clase del personaje y su winRate

public class PersonajeJuego {

    private String nombre;
    private String panteon;
    private String clase;
    private Integer dificultad;
    private double winRate;

    public PersonajeJuego(String nombre, String panteon, String clase, Integer dificultad, double winRate) {
        this.nombre = nombre;
        this.panteon = panteon;
        this.clase = clase;
        this.dificultad = dificultad;
        this.winRate = winRate;
    }

    @Override
    public String toString() {
        return nombre + " (" + clase + ") | Panteón: " + panteon;
    }

	// Método que es obligatorio ya que usamos hashmap
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonajeJuego personaje = (PersonajeJuego) o;
        return Object.equals(nombre, personaje.nombre);
    }

    @Override
    public int hashCode() {
        return Object.hash(nombre);
    }
}
