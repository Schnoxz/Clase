package Relacion10v2.Ejercicio1;

public class Cuenta {
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
			return;
		}
		this.saldo += cantidad;
		System.out.println("Se han ingresado: " + cantidad + "€" + " correctamente");
		System.out.println("Saldo actual: " + this.saldo + "€");
	}

	public void sacarDinero(double cantidad) {
		// Validación
		if (cantidad <= 0) {
			System.out.println("La cantidad a retirar no debe ser negativa");
			return;
	}
		if(cantidad > this.saldo) { // Esta cuenta no puede estar en números rojos como CuentaCredito por lo que la validación consta solo con el limite del saldo actual
			System.out.println("Saldo insuficiente");
			return;
		}

		this.saldo -= cantidad;
		System.out.println("Se han retirado: " + cantidad + "€" + " correctamente");
		System.out.println("Saldo actual: " + this.saldo + "€");
	}
}
