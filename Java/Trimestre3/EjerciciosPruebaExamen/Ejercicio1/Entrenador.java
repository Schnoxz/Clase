package EjerciciosPruebaExamen.Ejercicio1;

public abstract class Entrenador implements Evaluable {
	protected String id;
	protected String nombre;
	protected Rango rango;
	protected int[][] registroVictorias; // Tamaño 5x2, filas 0 to 4 son dias de lunes a viernes, la columna 0 son victorias en combates individuales y la columna 1 en duales
	
	// Constructor clase Entrenador
	public Entrenador(String id, String nombre, Rango rango, int[][] registroVictorias) {
		this.id = id;
		this.nombre = nombre;
		this.rango = rango;
		this.registroVictorias = registroVictorias;
	}
	
	// Getters
	
	public String getId() { return id; }
	public String getNombre() { return nombre; }
	public Rango getRango() { return rango; }
	public int[][] getRegistroVictorias() { return registroVictorias; }
	
	// Setters

	public void setId(String id) { this.id = id; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	public void setRango (Rango rango) { this.rango = rango; }
	public void setRevistroVictorias(int[][] registroVictorias) { this.registroVictorias = registroVictorias; }
	
	// Método toString
	@Override // Siempre se sobreescribe porque se llama al constructor toString definido de base por java
	public String toString() { return "Id: " + id + " |" + " Nombre Entrenador: " + nombre + " |" + " Rango: " + rango; }
	
	// Método que recorre la matriz y devuelve la suma total de todas las victorias de la semana
	public int calcularVictoriasSemanales() {
		int sumatorio = 0;
		// Recorrido de la matriz
		for(int i = 0; i < registroVictorias.length; i++) {
			for (int j = 0; j < registroVictorias[i].length; j++) {
				sumatorio += registroVictorias[i][j];
			}
		}
		return sumatorio;
	}
			
	
	// Método que recibe por parámetro un índice de fila (0 a 4) y devuelve la suma de las victorias solo de ese dia
	public int calcularVictoriasDia(int dia) {
		return registroVictorias[dia][0] + registroVictorias[dia][1]; // Como ya sabemos el dia, simplemente sumamos batallas individuales y duales
	}
	
	// Método abstracto
	public abstract double calcularPuntuacion();
	
	// Método que muestra por consola la matriz completa en forma de tabla o listado (Dia 0 - Individuales: 3, Dobles 1)
	public void mostrarRegistroCombates() {
		System.out.println("Registro de combates");
		// Creo un bucle for que recorra la matriz, y en el print defino el recorrido del bucle, primero en la columna 0 y luego en la 1
		for(int i = 0; i < registroVictorias.length; i++) {
			System.out.println("Dia " + i + "- Individuales: " + registroVictorias[i][0] + ", Dobles: " + registroVictorias[i][1]);
		}
	}
}
