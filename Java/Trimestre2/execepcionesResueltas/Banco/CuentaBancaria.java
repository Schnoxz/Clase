// Clase CuentaBancaria

public class CuentaBancaria {

    private String titular;
    private double saldo;

    // Constructor
    public CuentaBancaria(String titular, double saldoInicial) {
        if (titular == null || titular.trim().isEmpty()) { // trim() para eliminar espacios en blanco y isEmpty() para comprobar si la cadena está vacía
            throw new IllegalArgumentException("El titular no puede estar vacío.");
        }
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    // Meotod Inresar dinero a la cuenta
    public void ingresar(double cantidad) {
        if (cantidad <= 0) {
            // Lanzar una excepción si la cantidad a ingresar es menor o igual a 0
            throw new IllegalArgumentException("La cantidad a ingresar debe ser mayor que 0.");
        }
        saldo += cantidad;
    }

    // Metodo retirar dinero que lanza una excepcion de tipo SaldoInsuficienteException
    public void retirar(double cantidad) throws SaldoInsuficienteException {
        if (cantidad <= 0) {
            // Igual que antes cambiando el mensaje
            throw new IllegalArgumentException("La cantidad a retirar debe ser mayor que 0.");
        }
        if (cantidad > saldo) {
            // Lanzar una excepción personalizada si el saldo es insuficiente
            throw new SaldoInsuficienteException("Saldo insuficiente: intentas retirar " + cantidad + " pero solo hay " + saldo);
        }
        saldo = saldo - cantidad;
    }

    // Getters
    public double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }

    // toString para mostrar la informacion
    @Override
    public String toString() {
        return "CuentaBancaria{titular='" + titular + "', saldo=" + saldo + "}";
    }
}
