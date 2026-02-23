package Default;
import java.util.Scanner;

public class Ejercicio2 {
	private static final  Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
     
        int n = -1;
        // Condición para numeros negativos e iguales a 0
        while (n != 0 && n < 1) {
            System.out.print("Introduce un número entero positivo: ");
            n = teclado.nextInt();
        }
      
            if (n == 0) {
                System.out.println("Venga máquina, hasta luego");
                 
            }else {
            	  // Imprimo el mensaje por pantalla y lo coloco fuera del while y antes del for para que no se duplique 
                System.out.print("Los números primos de " + n + " son: " );
            }
            
        
      

        int n4 = (int )Math.pow(n,4);


        for (int i = n; i <= n4; i++) {

            // Comprobamos si el numero es mayr o igual que 2
            if (i >= 2) {

                int primo = 1;  // 1 = es primo por definición y su elevado es igual

                if (i != 2) {
                    if (i % 2 == 0) {
                        primo = 0; // pares distintos de 2 no son primos
                    } else {
                        int j = 3; // Sabemos que el 1 es primo y los pares no lo son, solo tenemos que comparar con los impares
                        while (j * j <= i) { // Probamos divisores 
                            if (i % j == 0) {
                                primo = 0; // tiene divisor distinto a 1 por lo que no es primo
                            }
                            j += 2; // Sumamos 2 para que vaya recorriendo todos los impares partiendo de 3
                        }
                    }
                }
                // Confirmamos la condición del primo e imprimimos
                if (primo == 1) {
                    System.out.print(i + " ");
                }
            }
        }

    }
}

    



	    	 
	 	

