package JimenezAlonsoJavier.ExamenT5;
// Clase abstracta que implementa la interfz Entrenable
public abstract class Pokemon implements Entrenable {
	// Declaración de variables de la clase Pokemon en privado
	private int numeroPokedex;
	private String nombre;
	private TiposIniciales tipo;
	private int nivelActual;
	private Movimiento[] movimientos;
	private int[][] aprendizaje; // Nivel de aprendizaje y potencia
	// Constructor de la clase pokemon
	public Pokemon(int numeroPokedex, String nombre, TiposIniciales tipo, int nivelActual, Movimiento[] movimientos, int[][] aprendizaje) {
		this.numeroPokedex = numeroPokedex;
		this.nombre = nombre;
		this.tipo = tipo;
		this.nivelActual = nivelActual;
		this.movimientos = movimientos;
		this.aprendizaje = aprendizaje;
	}
	
	// Getters
	public int getNumeroPokedex() { return numeroPokedex; }
	public String getNombre() { return nombre; }
	public TiposIniciales getTipo() { return tipo; }
	public int nivelActual() { return nivelActual; }
	public Movimiento[] getMovimientos() { return movimientos; }
	public int[][] getAprendizaje() { return aprendizaje; }
	
	
	// Método toString sobreescrito
	@Override
	public String toString() {
		return "Número Pokedex: " + numeroPokedex + " | " + " Nombre: " + nombre + " | " + " Tipo: " + tipo + " | " + " Nivel Actual: " + nivelActual + " | " + 
	" Movimientos: " + movimientos + " | " + " Aprendizaje: " + aprendizaje; 
	}
	
	// Método contarMovimientosDisponibles que cuenta cuántos movimientos puede aprender ya, según su nivel actual
	public int contarMovimientosDisponibles() {
		int movimientosTotales = 0;
		for (int i = 0; i < movimientos.length; i++) {
			movimientosTotales += movimientos[i];
		}
		return movimientosTotales;
	}
	
	// Método calcularPotenciaMediaDisponible que devuelve la media de potencia de los movimientos disponibles, si no tiene ninguno, devolverá 0
	public double calcularPotenciaMediaDisponible() {
		int potencia = 0;
		for(int i = 0; i < getMovimientos().length; i ++) {
			for (int j = 0; j < aprendizaje[0][j]; j++) {
				potencia += aprendizaje[0][j];
				double media = (double) potencia / contarMovimientosDisponibles();
				}
			}
		}
	
	// Método mostrarMovimientosDisponibles que muestra solo los movimientos cuyo nivel de aprendizaje sea menor o igual que el nivel actual
	public void mostrarMovimientosDisponibles() {
		System.out.println("Movimientos disponibles");
		for (int i = 0; i < contarMovimientosDisponibles(); i++) {
			for (int j = 0; j < aprendizaje[j][0]; j++) {
				
				
			}
			System.out.println("");
		}
	}
	
	// Método abstracto calcularIndiceCombate
	public abstract double calcularIndiceCombate();
}
