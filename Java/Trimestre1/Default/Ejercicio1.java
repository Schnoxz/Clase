package Default;
import java.util.*; //Declaro todas las utilidades

public class Ejercicio1 {
  private static final Scanner teclado = new Scanner(System.in);  // Declaroo el Scanner para introducir datos

  public static void main(String[] args) {
	  //Pido al usuario que ingrese un número
	  System.out.println("Introduzca un número:");
	  // Declaro las variables de los billetes y monedas para que sean enteros, al igual que el número ingresado
	     int num = teclado.nextInt();
	     int billete500, billete200, billete100, billete50, billete20, billete10, billete5;
	     int moneda2, moneda1;
	     
	     // Si el número es 0 o menor, se imprime un mensaje por pantalla para que vuelva a introducir un número valido
	     while (num <= 0) {
	    	 System.out.println("Introduzca un número válido, positivo y mayor que 0: ");
	    	 num = teclado.nextInt();
	     }
	     
	     
	   // Aquí comparo los billetes y monedas que hacen falta para llegar a la cifra ingresada
	     
	    billete500 = num / 500;
			num = num % 500;
	    	if (billete500 > 0) { 
	    	System.out.println(billete500 + " billete(s) de 500");
	    	}

	   	billete200 = num / 200;
	    	num = num % 200;
	    	if (billete200 > 0) {
	    	    System.out.println(billete200 + " billete(s) de 200");
	    	}

	    billete100 = num / 100;
	    	num = num % 100;
	    	if (billete100 > 0) {
	    	    System.out.println(billete100 + " billete(s) de 100");
	    	}

	    billete50 = num / 50;
	    	num = num % 50;
	    	if (billete50 > 0) {
	    	    System.out.println(billete50 + " billete(s) de 50");
	    	}

	   	billete20 = num / 20;
	    	num = num % 20;
	    	if (billete20 > 0) {
	    	    System.out.println(billete20 + " billete(s) de 20");
	    	}

	    billete10 = num / 10;
	    	num = num % 10;
	    	if (billete10 > 0) {
	    	    System.out.println(billete10 + " billete(s) de 10");
	    	}

	    billete5 = num / 5;
	    	num = num % 5;
	    	if (billete5 > 0) {
	    	    System.out.println(billete5 + " billete(s) de 5");
	    	}

	   	moneda2 = num / 2;
	    	num = num % 2;
	    	if (moneda2 > 0) {
	    	    System.out.println(moneda2 + " moneda(s) de 2");
	    	}

	    	moneda1 = num;
	    	if (moneda1 > 0) {
	    	    System.out.println(moneda1 + " moneda(s) de 1");
	    	}

	  
	  
	  
    }
  }