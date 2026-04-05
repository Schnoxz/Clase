package Relacion10v2.Ejercicio1;

public class CuentaCredito extends Cuenta {

    private double credito; // cuánto puede quedar en negativo
    private static final double CREDITO_MAX = 300;
    private static final double CREDITO_DEFAULT = 100;

    // Constructor sin crédito indicado → crédito por defecto 100€
    public CuentaCredito(String titular) {
        super(titular);
        this.credito = CREDITO_DEFAULT;
    }

    // Constructor con crédito indicado
    public CuentaCredito(String titular, double credito) {
        super(titular);
        setCredito(credito);
    }

    public double getCredito() { return credito; }

    public void setCredito(double credito) {
        if (credito > CREDITO_MAX) {
            System.out.println("El crédito no puede superar " + CREDITO_MAX + "€. Se establece al máximo.");
            this.credito = CREDITO_MAX;
        } else if (credito < 0) {
            System.out.println("El crédito no puede ser negativo.");
        } else {
            // Comprobamos que el nuevo crédito no sea menor que la deuda actual
            if (getSaldo() < 0 && -getSaldo() > credito) {
                System.out.println("No se puede reducir el crédito por debajo de la deuda actual.");
            } else {
                this.credito = credito;
            }
        }
    }

    // Sobreescribimos sacarDinero para incluir el crédito
    @Override
    public void sacarDinero(double cantidad) {
        if (cantidad <= 0) {
            System.out.println("La cantidad a sacar debe ser positiva.");
        } else if (getSaldo() - cantidad < -credito) {
            System.out.printf("Operación denegada. Con %.2f€ de crédito el saldo no puede bajar de -%.2f€%n",
                    credito, credito);
        } else {
            setSaldo(getSaldo() - cantidad);
            System.out.printf("Retirada de %.2f€. Saldo actual: %.2f€%n", cantidad, getSaldo());
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Crédito disponible: %.2f€", credito);
    }
}
