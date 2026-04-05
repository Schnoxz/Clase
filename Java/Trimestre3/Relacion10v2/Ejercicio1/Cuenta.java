package Relacion10v2.Ejercicio1;
public class Cuenta {
    private String titular;
    private double saldo;

    public Cuenta(String titular) {
        this.titular = titular;
        this.saldo = 0;
    }

    public Cuenta(String titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public String getTitular() { return titular; }
    public double getSaldo()   { return saldo; }

    public void setTitular(String titular) { this.titular = titular; }
    public void setSaldo(double saldo)     { this.saldo = saldo; }

    public void ingresarDinero(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            System.out.printf("Ingreso de %.2f€. Saldo actual: %.2f€%n", cantidad, saldo);
        } else {
            System.out.println("La cantidad a ingresar debe ser positiva.");
        }
    }

    public void sacarDinero(double cantidad) {
        if (cantidad <= 0) {
            System.out.println("La cantidad a sacar debe ser positiva.");
        } else if (cantidad > saldo) {
            System.out.println("Saldo insuficiente.");
        } else {
            saldo -= cantidad;
            System.out.printf("Retirada de %.2f€. Saldo actual: %.2f€%n", cantidad, saldo);
        }
    }

    @Override
    public String toString() {
        return String.format("Titular: %s | Saldo: %.2f€", titular, saldo);
    }
}
