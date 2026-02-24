// Encontrar el mayor Crea un array de 10 enteros y muestra cuál es el número mayor.

public class array5 {
    public static void main(String[] args) {
        // Declaro el array con 10 enteros
        int [] numeros = {5, 3, 8, 1, 4, 9, 2, 7, 6, 0};
        // Creo una variable donde iré guardando los numeros mayores hasta encontrar el mayor de todos los elementos
        int mayor = 0;
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] > mayor) { // Si es mayor que el numero anterior que se guardo en la variable se guarda el nuevo numero
                mayor = numeros[i];
            }
        }
        System.out.println("El número mayor del array es: " + mayor);
    }

}
