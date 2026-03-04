
public class Main {

    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria("Ana", 100.0);
        System.out.println("Cuenta creada: " + cuenta);

        // Ingreso correcto
        System.out.println("\nIngreso");
        try {
            cuenta.ingresar(50.0);
            System.out.println("Ingreso realizado.");
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR (Argumento inválido): " + e.getMessage());
        } finally {
            System.out.println("Saldo actual: " + cuenta.getSaldo());
        }

        // Retirada correcta
        System.out.println("\nRetirada");
        try {
            cuenta.retirar(30.0);
            System.out.println("Retirada realizada.");
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR (Argumento inválido): " + e.getMessage());
        } catch (SaldoInsuficienteException e) {
            System.out.println("ERROR (Saldo insuficiente): " + e.getMessage());
        } finally {
            System.out.println("Saldo actual: " + cuenta.getSaldo());
        }

        // Retirada con saldo insuficiente
        System.out.println("\nRetirada");
        try {
            cuenta.retirar(500.0);
            System.out.println("Retirada realizada.");
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR (Argumento inválido): " + e.getMessage());
        } catch (SaldoInsuficienteException e) {
            System.out.println("ERROR (Saldo insuficiente): " + e.getMessage());
        } finally {
            System.out.println("Saldo actual: " + cuenta.getSaldo());
        }

        // Ingreso inválido
        System.out.println("\nIngreso");
        try {
            cuenta.ingresar(-10.0);
            System.out.println("Ingreso realizado.");
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR (Argumento inválido): " + e.getMessage());
        } finally {
            System.out.println("Saldo actual: " + cuenta.getSaldo());
        }

        // Retirada inválida
        System.out.println("\nRetirada ");
        try {
            cuenta.retirar(0.0);
            System.out.println("Retirada realizada.");
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR (Argumento inválido): " + e.getMessage());
        } catch (SaldoInsuficienteException e) {
            System.out.println("ERROR (Saldo insuficiente): " + e.getMessage());
        } finally {
            System.out.println("Saldo actual: " + cuenta.getSaldo());
        }
    }
}
