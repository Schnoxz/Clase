// Crea un array de 6 números reales.  Calcula y muestra: La suma total  La media


public class array2 {

    public static void main(String[] args) {
        double[] array = new double[6];
        array[0] = 1;
        array[1] = 2;
        array[2] = 3;
        array[3] = 4;
        array[4] = 5;
        array[5] = 6;
        // Suma, donde se recorre el array y se va acumulando en la variable suma
        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        // Media, simplemente divido la variable suma que hemos calculado entre la cantidad de elementos totales del array, en este caso 6
        double media = sum / array.length;
        System.out.println("La suma total es: " + sum);
        System.out.println("La media es: " + media);
    }
}
