package String;
import java.util.*;

public class vocalesFinal_sinEspacios {
	private static final Scanner teclado = new Scanner (System.in);
	
	public static void main (String[] args) {
		
		System.out.print("Introduce una frase: ");
		String frase = teclado.nextLine();
		String limpio = frase.toLowerCase(); // Paso a minusculas toda la cadena y la llamo "limpio", vamos a emplear ese String en vez del original
		// Llamo al metodo
		String organizado = organizar (limpio);
		System.out.print("Frase tras organizarlo: " + organizado);
}
	// Creo un metodo para organizar una cadena de texto en orden de consonantes y luego vocales y agruparlas en una nueva cadena
	public static String organizar (String limpio) {
		String consonante = "";
		String vocal = "";
		
		for (int i = 0;i < limpio.length(); i++){
			char c = limpio.charAt(i);
			
			if (c == ' ') continue;
			
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				vocal = vocal + c;
			} else {
				consonante = consonante + c;
			}
		}
		
		return consonante + vocal;
	}
}


