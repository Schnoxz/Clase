package Relacion10v2.Ejercicio1;

public class CuentaCredito extends Cuenta {

    private double credito;

    // Clase constructor Cuenta Credito
    public CuentaCredito(String titular, double saldo, double credito) {
        super(titular, saldo);
        setCredito(credito); // Llamo al setter para cumplir con la validación
    }

    // Sobrecarga para cumplir con la condicion de un constructor si no se indica parametro de saldo o credito
    // Se cree con saldo inicializado en 0 y credito 100
    public CuentaCredito(String titular) {
        super(titular, 0);
        this.credito = 100;
    }

    // Getter
    public double getCredito() {
        return credito;
    }

    // Setter
    public void setCredito(double credito) {
        // Validación que no supere 300€
        if (credito > 300) {
            System.out.println("El crédito no debe superar los 300€");
            return; // Se devuelve para que vuelva a introducir el valor, tras imprimir el mensaje de error
        }
        // Validación para que el saldo no sea inválido
        if (this.saldo < -credito) { // Pongo el credito en negativo porque nos dice que podemos tener numeros rojos igual a la cantidad de crédito pero este mismo no puede ser mayor al saldo actual
            System.out.println("El crédito no puede ser menor al saldo actual");
            return;
        }
        this.credito = credito;
    }

    // Sobreescribo el metodo de sacar dinero de la clase Cuenta que es abstracta y se debe implementara en todas sus hijas con su propioa función para validar el crédito
    @Override
    public void sacarDinero(double cantidad) {
        if (cantidad <= 0) {
            System.out.println("La cantidad no puede ser negativa");
            return;
        }

        if (this.saldo - cantidad < -credito) { // Puedes tener la misma cantidad de credito como numero rojo en la cuenta  pero no más de ello
            System.out.println("No se puede retirar esta cantidad, el saldo mínimo debe ser: " + (-credito) + "€");
            return;
        }
        this.saldo -= cantidad;
        System.out.println("Se han retirado " + cantidad + "€" + " correctamente");
        System.out.println("Saldo actual: " + this.saldo + "€");
    }

    // Método para mostrar información de la cuenta
    public void mostrarInfo() {
        System.out.println("Titular: " + this.titular + " ---" + " Saldo: " + this.saldo + "€" + " ---" + " Crédito: " + this.credito + "€");
    }
}
