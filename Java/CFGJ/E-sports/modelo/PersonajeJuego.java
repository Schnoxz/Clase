package modelo;
// Basado en la mecánica del MOBA smite, tendremos personajes o dioses con sus propios atributos tal y como indica el juego original, su procedencia (panteon), su dificultad de uso, la clase del personaje y su winRate

/*  - Griega: Zeus, Ares, Poseidón, Athena...
 	- Nórdica: Thor, Loki, Odin, Freya...
 	- Egipcia: Ra, Anubis, Isis, Osiris...
 	- China: Sun Wukong, Nu Wa, Guan Yu...
 	- Romana: Bellona, Janus, Vulcano...
 	- Japonesa: Amaterasu, Susano, Raijin...
 	- Celta: The Morrigan, Cu Chulainn...
 	- Hindu: Kali, Agni, Rama...
 	- Maya: Ah Muzen Cab, Chaac, Xbalanque...
 */

public class PersonajeJuego {
	// Ejemplo de personaje: "Zeus", panteón: "Griego", clase: "Mago", dificultad: 3, winRate: 48.5
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

	    // Getters
    public String getNombre() { return nombre; }
    public String getPanteon() { return panteon; }
    public String getClase() { return clase; }
    public Integer getDificultad() { return dificultad; }
    public double getWinRate() { return winRate; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPanteon(String panteon) { this.panteon = panteon; }
    public void setClase(String clase) { this.clase = clase; }
    public void setDificultad(Integer dificultad) { this.dificultad = dificultad; }
    public void setWinRate(double winRate) { this.winRate = winRate; }


	// Métodos que comprueban la dificultad del personaje, si es mayor a 7 se considera difícil, entre 4 y 7 es media y menor a 4 es fácil
	public boolean esFacil() { return dificultad < 4; }

	public boolean esMedia() { return dificultad >= 4 && dificultad <= 7; }

	public boolean esDificil() { return dificultad > 7; }

	// Método que comprueba si el personaje es meta segun su winrate, si es mayor a 52 se considera meta, entre 48 y 52 es normal y menor a 48 es que necesita un buffo
	public boolean esMeta() { return winRate >= 52; }
	public boolean esNormal() { return winRate >= 48 && winRate < 52; }
	public boolean necesitaBuff() { return winRate < 48; }

	// Método que te recomienda un personaje según el meta
	public String obtenerRecomendacion() {
		if (esMeta()) {
			return "¡" + nombre + " es una excelente opción! Es un personaje meta con un winrate alto.";
		} else if (esNormal()) {
			return nombre + " es una opción sólida, aunque no es el más fuerte del momento.";
		} else {
			return nombre + " podría necesitar un buffo, ten cuidado al elegirlo.";
		}
	}

	// Método toString para mostrar la información del personaje
    @Override
    public String toString() {
        return nombre + " (" + clase + ") | Panteón: " + panteon;
    }

	// Método que muestra todos los campos con detalle
	public String mostrarDetalle() {
		return "Nombre: " + nombre + "\nPanteón: " + panteon + "\nClase: " + clase + "\nDificultad: " + dificultad + "\nWinRate: " + String.format("%.2f", winRate) + "%";
	}

	// Método que es obligatorio ya que usamos hashmap
    @Override
    public boolean equals(Object o) {
        // Si es el mismo objeto en memoria, son iguales
        if (this == o) return true;

        // Si es null o de otra clase, no son iguales
        if (o == null || getClass() != o.getClass()) return false;

        // Hacer cast a PersonajeJuego
        PersonajeJuego pj = (PersonajeJuego) o;

        // Comparar solo por nombre
        // Objects.equals compara si nombre es null
        return java.util.Objects.equals(nombre, pj.nombre);
	}

    @Override
    public int hashCode() {
        return java.util.Objects.hash(nombre);
    }
}
