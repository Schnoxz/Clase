package Arrays;

public class BuscarMayorMenor {
	// Con String (compareTo nativo)
	String[] palabras = {"manzana", "kiwi", "pera", "banana"};

	String mayor = palabras[0];
	String menor = palabras[0];

	for (int i = 1; i < palabras.length; i++) {
	    if (palabras[i].compareTo(mayor) > 0) mayor = palabras[i];  // mayor alfabéticamente
	    if (palabras[i].compareTo(menor) < 0) menor = palabras[i];  // menor alfabéticamente
	}

	System.out.println("Mayor: " + mayor); // pera
	System.out.println("Menor: " + menor); // banana
}

// Con objetos propios que implementan Comparable
public class Alumno implements Comparable<Alumno> {
 private String nombre;
 private int nota;

 public Alumno(String nombre, int nota) {
     this.nombre = nombre;
     this.nota = nota;
 }

 @Override
 public int compareTo(Alumno otro) {
     return this.nota - otro.nota; // ascendente por nota
     // Para String usarías: return this.nombre.compareTo(otro.nombre);
 }
}

//Buscar mayor y menor en un array de objetos:
Alumno[] alumnos = {
 new Alumno("Ana", 8),
 new Alumno("Luis", 5),
 new Alumno("Marta", 9)
};

Alumno mayor = alumnos[0];
Alumno menor = alumnos[0];

for (int i = 1; i < alumnos.length; i++) {
 if (alumnos[i].compareTo(mayor) > 0) mayor = alumnos[i];
 if (alumnos[i].compareTo(menor) < 0) menor = alumnos[i];
}

System.out.println("Mayor nota: " + mayor.getNombre()); // Marta
System.out.println("Menor nota: " + menor.getNombre()); // Luis

 }
}
