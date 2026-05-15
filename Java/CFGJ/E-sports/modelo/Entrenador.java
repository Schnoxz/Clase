package modelo;
/* Es el coach que dirige al equipo, se extiende de PersonaLiga pero no implementa entrenable al no ser jugador
	- Tiene una especialidad (Mecánica, Estratega, Motivacion, etc)
	- Su experiencia repercute en su salario
*/
public class Entrenador extends PersonaLiga {

    // Atributos que se exigen en el ejercicio
    private int experiencia;
    private String especialidad; // Estratega, Mecanica, Motivacion, Draft. (Draft se refiere a la eleccion de personajes, el counterpick o el draft de su propio equipo en un partido)
    private int victoriasTotales;

    public Entrenador(String id, String nombre, String nickname, Integer edad, Double salarioBase, Integer experiencia, String especialidad) {
        super(id, nombre, nickname, edad, salarioBase);
        this.experiencia = experiencia;
        this.especialidad = especialidad;
        this.victoriasTotales = 0;
    }

	// Getters
	public int getExperiencia() { return experiencia; }
	public String getEspecialidad() { return especialidad; }
	public int getVictoriasTotales() { return victoriasTotales; }

	// Setters, victoriasTotales tendrá un propio método que incrementaría las victorias al acabar un partido
	public void setExperiencia(int experiencia) { this.experiencia = experiencia; }
	public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    // Implementación del método abstracto
    @Override
    public Double calcularCosteMensual() {
        // Ejemplo: Base + un plus por cada año de experiencia
        return getSalarioBase() + (experiencia * 50.0);
    }

	// METODOS UNICOS DE LA CLASE ENTRENADOR

	// Metodo que incrementa las victorias totales al acabar un partido
	public void incrementarVictorias(){
		this.victoriasTotales++;
	}

	// Método que reinicia el recuento de victorias totales cuando comienza una nueva temporada
	public void reiniciarVictorias(){
		this.victoriasTotales = 0;
	}

	// Métodos obligatorios

	// mostrarResumen de la clase Entrenador
    @Override
    public void mostrarResumen() {
        System.out.println("ENTRENADOR: " + getNombre() + " (" + getNickname() + ")");
        System.out.println("  Especialidad: " + especialidad);
        System.out.println("  Experiencia: " + experiencia + " años");
        System.out.println("  Victorias conseguidas: " + victoriasTotales);
        System.out.println("  Coste mensual: " + String.format("%.2f", calcularCosteMensual()) + "€");
    }

	// toString de la clase Entrenador
	@Override
	public String toString() {
		return super.toString() + " | Especialidad: " + especialidad + " | Experiencia: " + experiencia +  "años" + " | Victorias: " + victoriasTotales;
	}
}
