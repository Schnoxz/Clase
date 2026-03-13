package src.Ejercicio2;

import java.util.Random;
// Los metodos que se han definido private es porque solo se van a usar en la funcion (?) jugar que va a ser el principal que se encargue del funcionamiento del conecta

public class juegoConecta4 {

    // Número de filas del tablero 10 lineas verticales, como creo que es algo inamovible de las normas del juego las voy a declarar final y es una constante
    private final int FILAS = 10;
    // Número de lineas horizontales, al igual que las filas la declaro final y es una constante
    private final int COLUMNAS = 10;
    // Para planear el tablero decido que solo puede tener 3 estados, vacio, rojo o amarillo
    private char vacio = '*';     // Posición vacia del tablero
    private char rojo = 'R';      // Ficha del jugador rojo
    private char amarillo = 'A';  // Ficha del jugador amarillo
    private char[][] tablero; // La matriz será 10x10, es decir, 10 filas y 10 columnas
    private char[] jugadores; // Declaro un array de jugadores que se le asignará el valor que se asignó previamente al rojo y amarillo (R, A)
    private int turno; // Variable para definir el turno en el que estamos de la secuencia del juego

    // Constructor que inicializa tanto el tablero como los jugadores
    public juegoConecta4() {
        tablero = new char[FILAS][COLUMNAS]; // Matriz 10x10
        jugadores = new char[]{rojo, amarillo}; // Crea los dos jugadores
        inicializarTablero(); // Llama al metodo que nos genera el 10x10 vacío
        turno = new Random().nextInt(2); // Genera el turno de forma aleatoria entre 0 y 1
    }

    // Metodo que recorre la matriz generando el tablero 10x10 con los vacíos (*)
    private void inicializarTablero() {
        for (int i = 0; i < FILAS; i++) // i que recorra las filas
        {
            for (int j = 0; j < COLUMNAS; j++) { // j que recorra las columnas
                tablero[i][j] = vacio; // A cada posición se le otorga el valor vacio por defecto
            }
        }
    }

    // Imprime el tablero por pantalla como si fuera una tabla de posición x y, con el contenido de cada celda
    public void mostrarTablero() { // public porque lo uso en el main
        System.out.println("\n 0 1 2 3 4 5 6 7 8 9"); // Orden de las columnas para que el jugador sepa de forma visual las posiciones
        for (int i = 0; i < FILAS; i++) { // Recorre las filas
            System.out.print(i + " "); // Imprime la fila
            for (int j = 0; j < COLUMNAS; j++) { // Recorre las columnas
                System.out.print(tablero[i][j] + " "); // Imprime el contenido

            }
            System.out.println(); // Salto de linea para que se vean las filas
        }
    }

    // Metodo para poner las fichas en su posicióm, funciona tal que se indica la columna y se busca la primera celda vacia desde abajo que es donde caen las fichas
    // Para saber si está llena o vacia hago que devuelva la fila en la que se ha puesto y si no un -1
    private int depositar(int columna, char ficha) { // private porque solo lo uso en el jugar
        for (int i = FILAS - 1; i >= 0; i--) {  // Empieza desde abajo hacia arriba
            if (tablero[i][columna] == vacio) {  // Si está vacia la posición
                tablero[i][columna] = ficha;  // Coloca la ficha
                return i; // Devuelve la posición de la fila donde cayó
            }
        }
        return -1; // Si recorre toda la fila hasta arriba y no hay hueco devuelve -1
    }

    // Metodo que comprueba el ganado, recibiendo la fila y columna donde cayó la última ficha para saber dónde buscar, recorriendo la misma hasta llegar a 4 fichas seguidas
    private boolean comprobarGanador(int fila, int columna, char ficha) { // private porque solo lo uso en el jugar
        int count = 0;
        // Recorrido horizontal de la fila donde cayó la úñtima ficha
        // Si encuentra 4 celdas seguidas con la misma ficha, hay ganador
        for (int j = 0; j < COLUMNAS; j++) {
            if (tablero[fila][j] == ficha) { // Si en la celda hay una ficha de ese jugador, se suma 1 al contador
                count += 1;
            } else { // Si no se reinicia
                count = 0;
            }
            if (count == 4) {
                return true;
            }
        }
        count = 0; // Reinicio el contador, que ya me ha pasado de tener fallos y no saber por qué, no habia reiniciado el contador y seguia sumando verticalmente

        // Ahora recorro verticalmente la columna donde ha caido la ficha
        // Igual que antes pero de forma vertical, se va contando cuantas fichas hay seguidas hasta llegar a 4
        for (int i = 0; i < FILAS; i++) {
            if (tablero[i][columna] == ficha) {
                count += 1;
            } else {
                count = 0;
            }
            if (count == 4) {
                return true;
            }
        }
        return false; // No hay 4 en raya ni horizontal ni vertical
    }

    // Comprueba si el tablero está lleno mirando solo la fila 0 (la de arriba).
    // Si la fila superior no tiene ninguna celda vacía, el tablero está completamente lleno ya que van desde arriba hacia abajo
    private boolean tableroLleno() {
        for (int j = 0; j < COLUMNAS; j++) {
            if (tablero[0][j] == vacio) {
                return false; // Si queda alguna celda vacía, no está lleno

            }
        }
        return true; // Todas las celdas superiores están ocupadas → tablero lleno
    }

    // Metodo que devuelve el carácter del jugador que tiene el turno actual
    public char getJugadorActual() {
        return jugadores[turno]; // R o A
    }

    // Metodo que cambia el turno al otro jugador
    // Como solo tenemos a dos jugadores, lo he planteado tal que resolviendo valores binarios 0 y 1, nos indique el jugador actual, con un resto podemos resolverlo
    public void cambiarTurno() {
        turno = (turno + 1) % 2; // Indico que el turno es el resto de la division entre 2, si es 0 % 2 = 0, si es 1 % 2 = 1
    }

    // Método principal que genera toda la partida. Recibe por paránmetro la columna en la que se va a poner la ficha
    // Usa los metodos anteriores para indicar el jugador, depositar, comprobarGanador y tableroLleno para saber si hay ganador o tablero lleno
    public int jugar(int columna) throws columnaInvalidaException { // public porque lo uso en el main y la excepcion creada para cuando el jugador introduzca una columna fuera de rango
        if (columna < 0 || columna >= COLUMNAS) { // Comprobamos si la columna está en el rango que hemos definido en la constante
            throw new columnaInvalidaException("Columna fuera de rango: " + columna); // Lanza la excepcion
        }
        char jugadorActual = getJugadorActual(); // Guardamos quién juega ahora
        int fila = depositar(columna, jugadorActual); // Intentamos depositar la ficha que ha elegido el jugador
        if (fila == -1) { // Condición para saber si la columna estaba llena
            return -1; // La columna estaba llena
        }
        if (comprobarGanador(fila, columna, jugadorActual)) {
            return 1; // Hay ganador
        }
        if (tableroLleno()) {
            return 2;   // Empate
        }
        return 0;  // Sigue el juego
    }
}
