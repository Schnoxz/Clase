package MetodoString;
import java.util.Scanner;

public class Palindromo3 {
	public static final Scanner teclado = new Scanner(System.in);
	
    public static void main(String[] args) {
       
        
        System.out.print("Introduce una frase para verificar: ");
        String frase = teclado.nextLine(); // Usamos nextLine() para leer la frase entera con espacios
        
    
        // Limpio la cadena pasando a minus y limpiando los espacios
        String limpia = frase.toLowerCase().replace(" ", "");
        
        // Llamo al método
        String invertida = invertirCadena(limpia);
        
        System.out.println("Original limpia: " + limpia);
        System.out.println("Invertida:       " + invertida);
        
        comparador(limpia, invertida);
    }
        
    // Método para comprobar que la frase invertida sea palíndromo o no    
    public static void comparador (String limpia, String invertida) {
	   // Si la cadena original es igual a la cadena invertida se confirma, si no se niega
        if (limpia.equals(invertida)) {
            System.out.println("Es un palíndromo");
        } else {
            System.out.println("No es un palíndromo.");
        }      
   	}

    // Metodo para invertir la cadena
    public static String invertirCadena(String frase) {
        String resultado = "";
        // Recorre la frase desde el final hacia el principio con un contador inverso
        for (int i = frase.length() - 1; i >= 0; i--) {
            resultado += frase.charAt(i); // Sumo y asigno en una cadena los caracteres contados inversamente para formar la frase invertida
        }  
        return resultado;
    }
}