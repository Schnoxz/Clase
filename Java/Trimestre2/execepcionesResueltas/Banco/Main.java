public class Main {

    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria("Ana", 100.0);
        System.out.println("Cuenta creada: " + cuenta);

        // 1) Ingreso correcto
        System.out.println("\n--- Ingreso 50 ---");
        try {
            cuenta.ingresar(50.0);
            System.out.println("Ingreso realizado.");
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR (Argumento inválido): " + e.getMessage());
        } finally {
            System.out.println("Saldo actual: " + cuenta.getSaldo());
        }

        // 2) Retirada correcta
        System.out.println("\n--- Retirada 30 ---");
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

        // 3) Retirada con saldo insuficiente
        System.out.println("\n--- Retirada 500 ---");
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

        // 4) Ingreso inválido
        System.out.println("\n--- Ingreso -10 ---");
        try {
            cuenta.ingresar(-10.0);
            System.out.println("Ingreso realizado.");
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR (Argumento inválido): " + e.getMessage());
        } finally {
            System.out.println("Saldo actual: " + cuenta.getSaldo());
        }

        // 5) Retirada inválida
        System.out.println("\n--- Retirada 0 ---");
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
