import java.util.Scanner;


public class Ampliation8 {
 private static final Scanner teclado = new Scanner(System.in);
 public static void main (String[] args) {
	 
	 //Pedimos los números
	 System.out.println("Introduce un número que actúe como inicio: ");
	 int inicio = teclado.nextInt();
	 
	 System.out.println("Introduce un número que actúe como final: ");
	 int fin = teclado.nextInt();
	 
	 System.out.println("Números primos entre " + inicio + " y " + fin + ":");
	 
	 
	 for (int i = inicio; i <= fin; i++) {

		 boolean esPrimo = true;
		 
		 if (i < 2) {
			 esPrimo = false;
		 } else {
			 for (int j = 2; j <= Math.sqrt(i); j++) {
				 if (i % j == 0) {
					 esPrimo = false;
					 break;
				 }
			 }
		 }
		 
		 if (esPrimo) {
			 System.out.print(i + " ");
		 }
		
	 }
	 
	 
	 
}
}

