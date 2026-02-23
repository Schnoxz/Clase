//Dado el array: int[] numeros = {4, -3, 7, 0, -2, 8};  Cuenta cuántos números son positivos

public class array3 {

    public static void main(String[] args) {
        //Defino el array
        int[] numeros = {4, -3, 7, 0, -2, 8};
        // Defino una variable para acumular los positivos
        int count = 0;
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] > 0) { // Condicion dentro del array donde i sea positivo y se va sumando
                count++;
            }
        }
        System.out.println("Hay " + count + " números positivos en el array.");
    }

}
