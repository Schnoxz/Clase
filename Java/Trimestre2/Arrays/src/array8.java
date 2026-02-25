// Copiar un array manualmente Sin usar clone() ni Arrays.copyOf(), copia un array en otro usando un for

public class array8 {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        int[] arrayclonado = new int[array.length];

        for (int i = 0; i < array.length; i++) { // Paso los elementos del array original al clonado
            arrayclonado[i] = array[i];
        }
        for (int i = 0; i < arrayclonado.length; i++) { // Muestro el array clonado
            if (i < arrayclonado.length - 1) { // Condicion hasta que no llegue al ultimo elemento del array
                System.out.print(arrayclonado[i] + ", "); // Muestro los elementos del array divididos por la coma
            } else {
                System.out.print(arrayclonado[i]);
            }
        }
    }
}
