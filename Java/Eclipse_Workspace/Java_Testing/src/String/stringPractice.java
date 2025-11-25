package String;

public class stringPractice {
public static void main (String[] args) {
	
	String cadena1 = "Hola";
	String cadena2 = cadena1.concat(" Qué tal");
	
	
	String cadena3 = cadena2.replace('u', 'k');
	
	// Subcadena que nos sirve para separar una cadena por posiciones del texto, nos separa hsata el 2 y a partir del 6
	String cadena4 = "marinador";
	String cadena5 = cadena4.substring(2, 6); // "rina"
	
	// Dividimos una cadena con "split" y dentro de split para agrupar múltiples separadores, debe ser dentro de [] 
	// Declaramos estas divisones como posiciones de la cadena, si hay 3 separadores agrupamos las subcadenas empezando desde 0 en posición
	String cadena6 = "319-314.4+12";
	String[] parts = cadena6.split("[-.+]");
	String part1 = parts[0];
	String part2 = parts[1];
	String part3 = parts[2];
	String part4 = parts[3];
	 //Trabajar con cadenas mutantes, podemos cambiar el contenido, con insert determinamos posicion y contenido a insertar
	StringBuilder str = new StringBuilder("abcd");
	str.insert(0, 2.3);
	
	StringBuilder str1 = new StringBuilder("abcdef");
	str1.replace(2, 2, "hola");
	
	// Devuelve la posición que ocupa nuestra subcadena
	String cadena7 = "abcdabc";
	int pos = cadena7.indexOf ("bc"); // 1
	
	// Devuelve la posición de la subcadena a partir de la posición indicada
	String cadena8 = "abcdabca";
	int position = cadena8.indexOf ("bc",4); // 5
	
	// Devuelve la posición de la subcadena empezando desde el final
	String cadena9 = "abcdabcad";
	int posi = cadena9.lastIndexOf ("bc"); // 5
	
	// Comparación de cadenas equals() o compareTo()
	cadena9.equals(cadena8);
	cadena9.compareTo(cadena8);
	// Para no distinguir entre mayúsculas y minúsculas
	if(cadena8.equalsIgnoreCase(cadena9)){
		System.out.println("True");
	} else {
		System.out.println("false");		
  }	
	System.out.println(str1);
}
		


}
	
	

	
	
	

