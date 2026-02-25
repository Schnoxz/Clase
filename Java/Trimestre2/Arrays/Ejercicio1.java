// Clase Alumno ( atributos, nombre y nota) Condicion, si la nota es menor que 0 o mayor que 10 que lance una excepcion "NotaInvalidException"

import java.util.Arrays;

public class Ejercicio1 {
	public static void main (String[] args) {
		
		Alumno[] alumnos = new Alumno[2]; // Creo un arra
		alumnos[0] = new Alumno("Javi", 6); // Croo una estancia de la clase Alumno y se asigna nombre (String) y nota (int)
		alumnos[1] = new Alumno("Mario", 8);
		alumnos[2] = new Alumno("Marta", 10);
		alumnos[3] = new Alumno("Lucia", 4);
		alumnos[4] = new Alumno("Pablo", 2);
		
		
		System.out.println(Arrays.toString(alumnos)); // Muestro la cadena original sin ordenar ni modificar
		Arrays.sort(alumnos); // Ordeno el array
		System.out.println(Arrays.toString(alumnos)); // Imprimo el array ordenado y usando toString
		System.out.println("El alumnno con la nota mas alta es: " + alumnos[alumnos.length - 1].nombre); // Como ya he ordenado el array, para encontrar el alumno con la nota mas alta le resto 1 a la longitud, es decir comienzo por el mas alto
	}
	
	public static void pedirNombre(Alumno[] alumnos) {
		System.out.println("Introduce el nombre del alumno: "); // Recorro el array y por cada entrada se le asigna un nombre a cada alunno
		for (int i = 0; i < alumnos.length; i++) {
			alumnos[i].setNombre();
		}
	}
	public static void pedirNota(Alumno[] alumnos) {
		System.out.println("Introduce la nota del alumno: "); 
		try {
			for (int i = 0; i < alumnos.length; i++) { // Recorro el array y por cada entrada se le asigna una nota a cada alumnos
				if (alumnos[i].setNota() < 0 || alumnos[i].setNota() > 10) {
					throw new NotaInvalidException("Nota invalida, debe estar entre 0 y 10"); // No 
				}
			}
		}
	}
	
	public static class Alumno implements Comparable<Alumno> { // Clase Alumno con un compareTo para ordenar por nota, implementa la interfaz comparable
		private String nombre;
		private int nota;
	
		
		public Alumno (String nombre, int nota) { // Constructor de la clase alumno 
			this.nombre = nombre;
			this.nota = nota;
		}
		
		public String setNombre() { // Setter para darle un valor al nombre 
			this.nombre = nombre;
			return nombre;
		}
		
		public int setNota() { // Setter para nota
			this.nota = nota;
			return nota;
		}
		
		@Override 
		public int compareTo(Alumno a) { // Metodo compareTo para ordernar de menor a mayor las notas
			if (this.nota < a.nota) {
				return -1;
			} else 
			if (this.nota > a.nota) {
				return 1;
			} else {
				return 0;
			}
			
		}
	}
}
	









