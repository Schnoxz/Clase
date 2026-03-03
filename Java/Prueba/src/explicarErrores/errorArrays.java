package explicarErrores;

public class errorArrays {

    public static void main(String[] args) {
        int[] numeros = new int[5];
        for (int i = 0; i <= numeros.length; i++) { // No es <= el = equivaldria a que tengan 6 posiciones 0,1,2,3,4,5 y solo hay 5 posiciones, 0,1,2,3,4
            numeros[i] = i * 2;
        }

        int suma = 0;
        for (int n : numeros) {
            suma += n;
        }
        System.out.println("Suma: " + suma);
    }
}
