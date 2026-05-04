
public class Entrenador extends PersonaLiga {

    // Atributos que se exigen en el ejercicio
    private int experiencia;
    private String especialidad; // Estratega, Mecanica, Motivacion, etc
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

    @Override
    public void mostrarResumen() {
        System.out.println("ENTRENADOR: " + getNombre() + " | Especialidad: " + especialidad);
    }
}
