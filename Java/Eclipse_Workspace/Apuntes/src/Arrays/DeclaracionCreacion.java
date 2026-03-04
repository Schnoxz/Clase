package Arrays;

public class DeclaracionCreacion {
	
	// Forma 1: declarar y asignar valores directamente
	int[] numeros = {10, 20, 30, 40, 50};
	// Forma 2: declarar tamaño (los valores son 0 por defecto)
	int[] notas = new int[5];
	notas[0] = 8;
	notas[1] = 7;
	// Longitud del array
	System.out.println(numeros.length); // 5
	// Array de Strings
	String[] nombres = {"Ana", "Luis", "Marta"};
	// Array de objetos (ojo: referencias a null hasta inicializarlas)
	Persona[] personas = new Persona[3]; // 3 referencias NULL
	personas[0] = new Persona("Ana", 20); // ahora sí es un objeto

 }
}
