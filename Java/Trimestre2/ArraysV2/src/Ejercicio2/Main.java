package src.Ejercicio2;

import java.util.Scanner;

public class Main {

    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        // Creamos el objeto juego. El constructor ya inicializa el tablero y elige aleatoriamente quién empieza
        juegoConecta4 juego = new juegoConecta4();

        // Mostramos quién empieza (R o A)
        System.out.println("Jugador 1: " + juego.getJugadorActual());

        boolean activo = true; // Controla si el juego sigue o ha terminado

        // Bucle principal del juego, donde el jugador elige una columna 
        while (activo) {
            juego.mostrarTablero(); // Pintamos el tablero en cada turno
            // Se le pregunta al usuario que eliga una columna donde colocar la ficha
            System.out.print("Jugador " + juego.getJugadorActual() + " - Elige columna (0-9): ");

            int columna; // Columna elegida por el jugador
            try {
                // Lee el dato de entrada del jugador y le da valor a columna
                columna = teclado.nextInt();
            } catch (NumberFormatException e) {
                // Si el jugador escribe una letra o algo que no sea número, capturamos el error
                System.out.println("Entrada inválida.");
                continue; // Volvemos al inicio del while sin cambiar el turno
            }
            // Enviamos la columna elegida al método jugar() y hacemos otro try catch si la columna está fuera de rango
            try {
                int resultado = juego.jugar(columna);
                // Hay 4 casos que ya están definidos en el método jugar, donde se devuelve un -1, 1, 2 o 0
                switch (resultado) {
                    case -1:
                        System.out.println("Columna llena, ve eligiendo otra compi, el que meta la mano en la comia se la corto");
                        break;
                    case 1:
                        juego.mostrarTablero(); // Lo vuelvo a mostrar para que se vea el último movimiento que consigue el 4 en raya
                        System.out.println("¡Ha ganado el payo " + juego.getJugadorActual() + "!");
                        activo = false; // Juego termina
                        break;
                    case 2:
                        juego.mostrarTablero();
                        System.out.println("Empate");
                        activo = false; // Juego termina también en el empate
                        break;
                    case 0:
                        juego.cambiarTurno();
                        break;
                }
                // Capturamos la excepcion creada para cuando el jugador introduzca una columna fuera de rango
            } catch (columnaInvalidaException e) {
                System.out.println(e.getMessage()); // Imprime el mensaje que se ha definido dentro de la excepcion
            }
        }
    }
}
