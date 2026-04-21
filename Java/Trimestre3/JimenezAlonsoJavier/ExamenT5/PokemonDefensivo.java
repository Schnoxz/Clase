package JimenezAlonsoJavier.ExamenT5;

public class PokemonDefensivo extends Pokemon {
	// Atributo propio de la clase PokemonDefensivo en privado
	private int resistencia;
	
	// Constructor de la clase PokemonDefensivo
	public PokemonDefensivo(int numeroPokedex, String nombre, TiposIniciales tipo, int nivelActual, Movimiento[] movimientos, int[][] aprendizaje, int resistencia) {
		super(numeroPokedex, nombre, tipo, nivelActual, movimientos, aprendizaje);
		this.resistencia = resistencia;
	}

	// Método booleano de la interfaz necesitaMejorar que devuelve true si no tiene movimientos disponibles o su nivel actual es menor que 20
	@Override
	public boolean necesitaMejorar() {
		if(this.contarMovimientosDisponibles() == 0) {
			return true;
		} 
		if (this.calcularPotenciaMediaDisponible() < 20) {
			return true;
		}
		return false;
	}
	
	
	// Método que calcula el indice de combate para la clase PokemonDefensivo base a la formula calcularPotenciaMediaDisponible() + resistencia * 1.5
	@Override
	public double calcularIndiceCombate() {
		return (this.calcularPotenciaMediaDisponible() + resistencia * 1.5);
	}
	
	// Método toString que hereda de la clase Pokemon
	@Override
	public String toString() {
		return super.toString() + " | Resistencia: " + resistencia;
	}
}
