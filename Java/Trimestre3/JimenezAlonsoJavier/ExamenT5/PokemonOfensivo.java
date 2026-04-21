package JimenezAlonsoJavier.ExamenT5;

public class PokemonOfensivo extends Pokemon{
	// Atributo propio de la clase PokemonOfensivo
	private int ataquesFuertes; //Numero de movimientos con potencia mayor que 70, recogidos en la clase auxiliar Movimiento del atributo potencia
	
	public PokemonOfensivo(int numeroPokedex, String nombre, TiposIniciales tipo, int nivelActual, Movimiento[] movimientos, int[][] aprendizaje, int ataquesFuertes) {
		super(numeroPokedex, nombre, tipo, nivelActual, movimientos, aprendizaje);
		this.ataquesFuertes = ataquesFuertes;
	}
	// Método de la interfaz que comprueba si necesita mejorar o no, cuando tiene menos de 2 movimientos disponibles o la potencia media es menor que 50
	@Override
	public boolean necesitaMejorar() {
		if(this.contarMovimientosDisponibles() < 2) {
			return true;
		}
		if (this.calcularPotenciaMediaDisponible() < 50) {
			return true;
		}
		return false;
	}
	
	// Método que calcula el indice de combate segun la formula : indice = calcularPotenciaMediaDisponible() + ataquesFuertes * 2
	@Override
	public double calcularIndiceCombate() {
		return (this.calcularPotenciaMediaDisponible() + ataquesFuertes * 2);
	}
	
	// Método toString
	@Override
	public String toString() {
		return super.toString() + " | Ataques fuertes: " + ataquesFuertes;
	}
}
