package Objetos.Ejercicio1;

public class CocheConstructor {
	private String marca;
	private String modelo;
	private int año;
	private double velocidadActual;
	
	// Constructor coche
	public CocheConstructor (String marca, String modelo, int año, double velocidadActual) {
		this.año = año;
		this.marca = marca;
		this.modelo = modelo;
		this.velocidadActual = 0; // Inicializamos la velocidad actual a 0, ya que el coche parte desde parado
	}

	
	// Getters 
	
	public int getAño() { return año; }
	public String getMarca() { return marca; }
	public String getModelo() { return modelo; }
	public double getVelocidadActual() { return velocidadActual; }
	
	// Setters 
	
	public void setAño(int año) { this.año = año; }
	public void setMarca(String marca) { this.marca = marca; }
	public void setModelo(String modelo) { this.modelo = modelo; }
	
	public void setVelocidadActual(double velocidadActual) { 
		if (velocidadActual >= 0) {
			this.velocidadActual = velocidadActual; // Solo actualizamos la velocidad si es válida (no negativa)	
		}
	}

	
	// Método acelerar
	public void acelerar(double cantidad) throws VelocidadNoValidaException {
		if (cantidad < 0) {
			throw new VelocidadNoValidaException("La cantidad de aceleración no puede ser negativa.");
		}
		velocidadActual += cantidad; 
	}
	
	// Método frenar
	public void frenar(double cantidad) throws VelocidadNoValidaException {
		if (cantidad < 0) {
			throw new VelocidadNoValidaException("La cantidad de frenado no puede ser negativa.");
		}
		velocidadActual -= cantidad; 
		if (velocidadActual < 0) {
			velocidadActual = 0; // La velocidad no puede ser negativa, así que la establecemos a 0
		}
	}
	
	public void mostrarInformacion() {
		System.out.println("Marca: " + marca);
		System.out.println("Modelo: " + modelo);
		System.out.println("Año: " + año);
		System.out.println("Velocidad Actual: " + velocidadActual + " km/h");
	}
}