package Entrenamiento_examen;
import java.util.Scanner;

public class Simulacro2 {
	private static final Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
        

        System.out.println("Elija el palo que desea mostar: Bastos, Copas, Espadas, Oros o Todos");
        System.out.print("Palo elegido: ");
        char palo = teclado.next().toUpperCase().charAt(0); /* Uso toUpperCase para recoger las mayúsculas y minúsculas */

        // Verifico los palos y los agrupo si la opción es T (todos), si no es nada de eso, se devuelve un error por pantalla//
        if (palo == 'B' || palo == 'C' || palo == 'E' || palo == 'O') {
            System.out.println("Cartas de la baraja española:");
            imprimirCartas(palo);
        } else if (palo == 'T') {
            System.out.println("Cartas de la baraja española:");
            imprimirCartas('B');
            imprimirCartas('C');
            imprimirCartas('E');
            imprimirCartas('O');
        } else {
            System.out.println("Opción inválida");
        }

       
    }

    // Asocio cada palo de la baraja a un contador y creo otra clase llamada imprimirCartas para llamarla en cada palo y no tener que repetir todo uno por uno//
    public static void imprimirCartas(char palo) {
        int contador = 1;
        while (contador <= 7) {
            System.out.print(contador + "-" + palo + ", ");
            contador++;
        }

        // Añado el valor de Sota, Caballo y Rey al final del contador y muestra el palo de la baraja //
        System.out.print("S-" + palo + ", ");
        System.out.print("C-" + palo + ", ");
        System.out.println("R-" + palo);
    }
}


