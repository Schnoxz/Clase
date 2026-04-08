/* Realizar una clase CuentaCredito que herede de la clase Cuenta y que cumpla:
◦Al crear una cuenta de crédito se indica de qué cantidad de crédito se dispone, es decir, a cuánto
puede quedar la cuenta en números rojos. Por ejemplo, si el crédito es de 100€ la cuenta podrá
llegar a tener un saldo igual a –100.
◦Inicialmente, si no se indica nada, el saldo de la cuenta es 0€.
◦Inicialmente, si no se indica nada, el crédito es de 100€.
◦Se deben incluir los métodos get y set para el crédito. El crédito nunca puede superar los 300€.
También habrá que tener en cuenta el saldo actual de la cuenta.
◦Se deberá modificar los métodos de sacarDinero para incluir el  crédito.
Realizar una clase de prueba MenuCuentaCredito que cree una cuenta de crédito y presente un menú
con estas opciones.
1. Ingresar dinero
2. Sacar dinero
3. Mostrar saldo y crédito
4. Salir
Realizar un programa Principal que cree una cuenta ahorro joven y pruebe sus métodos.
 */
package Relacion10v2.Ejercicio1;

import java.util.Scanner;

public class Main {

    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        CuentaCredito ahorrojoven = new CuentaCredito("Javier Jiménez", 400, 120); // Titular, saldo, credito
        MenuCuentaCredito menuBBVA = new MenuCuentaCredito(ahorrojoven); // Creo el menu para la cuenta de credito creada y le paso la cuenta como parametro para que el menu pueda usar los metodos de la clase CuentaCredito
        menuBBVA.mostrarMenu(); // Llamo al metodo mostrarMenu del objeto menuBBVA para mostrar el menu al usuario
    }
}
