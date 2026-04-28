package JimenezAlonsoJavier.ExamenT5;

public class Movimiento {
	// Declaración de atributos en privado para la clase Movimiento
	private String nombre;
	private TiposIniciales tipo;
	private int potencia;
	// Método constructor de la clase Movimiento
	public Movimiento(String nombre, TiposIniciales tipo, int potencia) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.potencia = potencia;
	}
	
	// Getters
	public String getNombre() { return nombre; }
	public TiposIniciales getTipo() { return tipo; }
	public int getPotencia() { return potencia; }
	
	// Método sobreescrito toString
	@Override
	public String toString() {
		return "Nombre Movimiento :" + nombre + " | " + "Tipo: " + tipo + " | " + "Potencia: " + potencia; 
	}
}
