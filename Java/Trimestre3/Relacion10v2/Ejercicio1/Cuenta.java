package Relacion10v2.Ejercicio1;

public abstract class Cuenta { // Declaro la clase padre Cuenta como abstracta
	protected String titular;
	protected double saldo; // Lo defino protected porque se usará en los metodos de CuentaCredito para validacion de credito y saldo
	// Constructor clase Cuenta
	public Cuenta (String titular, double saldo) {
		this.saldo = saldo;
		this.titular = titular;
	}
	// Sobrecarga con constructor Cuenta cuando saldo no se define
	public Cuenta(String titular) {
		this.titular = titular;
		this.saldo = 0;
	}
	// Getters
	public String getTitular() { return titular; }
	public double getSaldo() { return saldo; }

	// Setter
	public void setTitular(String titular) { this.titular = titular; }
	public void setSaldo(double saldo) { this.saldo = saldo;}


	// Métodos que usan todas las cuentas

	public void ingresarDinero(double cantidad) {
		// Validación
		if (cantidad <= 0) {
			System.out.println("La cantidad a ingresar no debe ser negativa");
			return; // Valida el dato, imprime el error y devuelve para volver a introducir valor por teclado
		}
		this.saldo += cantidad;
		System.out.println("Se han ingresado: " + cantidad + "€" + " correctamente");
		System.out.println("Saldo actual: " + this.saldo + "€");
	}
	/* El método que se va a reutilizar en otras clases que hereden se crea abstracta porque cada una 
	tendrá una función diferente y obliga a implementar el método */
	public abstract void sacarDinero(double cantidad); 
}
