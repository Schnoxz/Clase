package JimenezAlonsoJavier.ExamenT5;

import EjerciciosPruebaExamen.Ejercicio5.Movimiento;
import EjerciciosPruebaExamen.EjercicioComplejoFinal.CentroDistribucion;
import EjerciciosPruebaExamen.EjercicioComplejoFinal.CentroOperativo;
import EjerciciosPruebaExamen.EjercicioComplejoFinal.CentroSeguridad;

public class Main {
	public static void main(String[] args) {
		// Creo un array donde almaceno 4 Pokemon llamando al constructor Pokemon
		Pokemon[] pokemons = new Pokemon[4];
		// Creo e inicializo 4 arrays de movimiento con 4 movimientos cada uno
		Movimiento m1 = new Movimiento("Cola ferrea", TiposIniciales.AGUA, 12);
		Movimiento m2 = new Movimiento("Cola aguja", TiposIniciales.AGUA, 12);
		Movimiento m3 = new Movimiento("Cola fuego", TiposIniciales.AGUA, 12);
		Movimiento m4 = new Movimiento("Cola tierra", TiposIniciales.AGUA, 12);
		
		
		
		// Con el array del objeto Pokemon que he creado, los voy a inicializar con las subclases PokemonOfensivo y Defensivo, 2 para cada uno
		pokemons[0] = new PokemonOfensivo(1, "Digimon1", TiposIniciales.AGUA, 20, m1, 4, 2);
		pokemons[1] = new PokemonOfensivo(2, "Digimon2" , TiposIniciales.FUEGO, 24, m2, 5, 3);
		pokemons[2] = new PokemonDefensivo(3, "Digimon3", TiposIniciales.PLANTA, 30, m3, 6, 4);
		pokemons[3] = new PokemonDefensivo(4, "Digimo4" , TiposIniciales.AGUA, 44, m4, 7, 5);
	
	
	// Llamada a los metodos
	mostrarInformacion(pokemons);
	pokemonMayorIndice(pokemons);
	contarTipos(pokemons);
	pokemonMasMovimientos(pokemons);
	masPotente(pokemons);
}
	// Mostrar toda la información de todos los pokemons, datos generales, movimientos disponibles, potencia media, indice de , si necesita mejorar o  no
    public static void mostrarInformacion(Pokemon[] pokemons) {
        System.out.println("========== INFORMACIÓN COMPLETA ==========");

        for (int i = 0; i < pokemons.length; i++) {
            System.out.println(pokemons[i]);
            System.out.println("Movimientos disponibles: " + pokemons[i].contarMovimientosDisponibles());
            System.out.println("Potencia media: " + pokemons[i].calcularPotenciaMediaDisponible());
            System.out.println("Indice de combate: " + pokemons[i].calcularIndiceCombate());
            System.out.println("Necesita mejorar " + pokemons[i].necesitaMejorar());
        }
    }
	// Mostrar el pokemon con mayor indice de combate, su nombre , el tipo y su indice
	public static void pokemonMayorIndice(Pokemon[] pokemons) {
		Pokemon mayorIndice = pokemons[0];
		
        for (int i = 1; i < pokemons.length; i++) {
            if (pokemons[i].calcularIndiceCombate() > mayorIndice.calcularIndiceCombate()) {
            	mayorIndice = pokemons[i];
            }
        }
        
        System.out.println("========== Pokemon con mayor indice de combate ==========");
        System.out.println("Nombre: " + mayorIndice.getNombre());
        System.out.println("Tipo: " + mayorIndice.getTipo());
        System.out.println("Índice: " + mayorIndice.calcularIndiceCombate());
	}
	// Contador de tipos ofensivos y defensivos
    public static void contarTipos(Pokemon[] pokemons) {
        int ofensivo = 0;
        int defensivo = 0;

        for (int i = 0; i < pokemons.length; i++) {
            if (pokemons[i] instanceof PokemonOfensivo) {
                ofensivo++;
            } else if (pokemons[i] instanceof PokemonDefensivo) {
                defensivo++;
            }
            System.out.println("Pokemons ofensivos: " + ofensivo);
            System.out.println("Pokemons defensivos: " + defensivo);
        }
        
    }
    
    // Buscar el movimiento  mas potente disponible entre todos los pokemons, muestra el nombre del movimiento, el del pokemon y su potencia
    public static void masPotente(Pokemon[] pokemons) {
    	int potente = 0;
    	int menosPotente = -1;
    	
        for (int movimiento = 0; movimiento < 4; movimiento++) {
            int totalMovimientos = 0;

            for (int i = 0; i < pokemons.length; i++) {
            	totalMovimientos += pokemons[i].contarMovimientosDisponibles(movimiento);
            }
        }
    }
    
    // Muestra el pokemon con mas movimientos disponibles, muestra su nombre y la cantidad de movimientos disponibles
    public static void pokemonMasMovimientos(Pokemon[] pokemons) {
    	Pokemon pokemonMax = pokemons[0];
    	int movimientos = 0;
    	
    	for(int i = 0; i < pokemons.length; i++) {
    		for(int movimientos = 0; movimientos < 4; movimientos ++) {
    			
    		}
    	}
    }
}
