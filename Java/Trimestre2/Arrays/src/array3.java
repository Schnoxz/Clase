
//Dado el array: int[] numeros = {4, -3, 7, 0, -2, 8};  Cuenta cuántos números son positivos
public class array3 {

    public static void main(String[] args) {
        //Defino el array
        int[] numeros = {4, -3, 7, 6, -2, 8};
        // Defino una variable para acumular los positivos
        int contador = 0;
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] > 0) { // Condicion dentro del for donde i sea positivo y se va sumando
                contador++;
            }
        }
        System.out.println("Hay " + contador + " números positivos en el array.");
    }

}
