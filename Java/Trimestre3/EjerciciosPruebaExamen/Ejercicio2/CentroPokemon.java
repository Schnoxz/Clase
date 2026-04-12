package EjerciciosPruebaExamen.Ejercicio2;

public abstract class CentroPokemon implements Auditable {
	protected String id;
	protected String region;
	protected TipoCentro tipoCentro;
	protected int[][] registroAtenciones; // 5x3, filas 0/4 lunes a viernes. Columna 0 (Pokemon comunas) Columna 1 (Pokemon raros) Columna 2 (Pokemon legendarios)
	
	// Constructor clase CentroPokemon
	public CentroPokemon(String id, String region, TipoCentro tipoCentro, int[][] registroAtenciones) {
		this.id = id;
		this.region = region;
		this.tipoCentro = tipoCentro;
		this.registroAtenciones = registroAtenciones;
	}
	
	// Getters
	public String getId() { return id; }
	public String getRegion() { return region; }
	public TipoCentro getTipoCentro() { return tipoCentro; }
	public int[][] getRegistroAtenciones() { return registroAtenciones; }
	
	// Setters
	public void setId(String id) { this.id = id; }
	public void setRegion(String region) { this.region = region; }
	public void setTipoCentro(TipoCentro tipoCentro) { this.tipoCentro = tipoCentro; }
	public void setRegistroAtenciones(int[][] registroAtenciones) { this.registroAtenciones = registroAtenciones; }
	
	// Método toString
	@Override
	public String toString() {
		return "Id " + id + " | " + "Region " + region + " | " + "Tipo de Centro: " + tipoCentro; 
	}
	
	// Método que suma toda la matriz de pokemons atendidos
	public int totalPokemonAtendidos() {
		int totalPokemon = 0;
		for (int i = 0; i < registroAtenciones.length; i++) {
			for (int j = 0; j < registroAtenciones[i].length; j++) {
				totalPokemon += registroAtenciones[i][j];
			}
		}
		return totalPokemon;
	}
	
	// Método que suma el total de una columna concreta que se le pasa por parámetro al metodo
	public int atencionesPorRareza(int columna) {
		int totalPokemonRareza = 0;
		for (int i = 0; i < registroAtenciones.length; i++) { 
				totalPokemonRareza += registroAtenciones[i][columna];
			}
		return totalPokemonRareza;
	}
	
	// Metodo que imprime la matriz
	public void mostrarRegistro() {
		System.out.println("Registro de pokemons");
		for(int i = 0; i < registroAtenciones.length; i++) {
			System.out.println("Dia " + i + " - Comunes: " + registroAtenciones[i][0] + ", Raros: " + registroAtenciones[i][1] + ", Legendarios " + registroAtenciones[i][2] );
		}
	}
	
	// Método abstracto que calcula la eficiencia
	public abstract double calcularEficiencia();
	
}
