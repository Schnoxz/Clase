package EjerciciosPruebaExamen.Ejercicio1;

public class Main {
	public static void main(String[] args) {
        // Llamo a la clase entrenador y creo 4 posiciones vacias para luego rellenarlas
        Entrenador[] entrenadores = new Entrenador[4];
        
		// Inicializo las matrices manualmente
        int[][] vitAsh = { {3, 1}, {2, 0}, {4, 2}, {1, 1}, {5, 3} }; 
        int[][] vitGary = { {4, 2}, {3, 1}, {5, 3}, {2, 2}, {4, 4} };
        int[][] vitBrock = { {1, 1}, {2, 2}, {1, 0}, {3, 1}, {2, 2} };
        int[][] vitMisty = { {2, 0}, {1, 1}, {0, 0}, {2, 1}, {3, 2} };
        
        // Instancio 2 Estrategas y 2 Criadores
        // id, nombre, rango, 
        entrenadores[0] = new EntrenadorEstratega("E01", "Ask Ketchum", Rango.LIDER_GIMNASIO, vitAsh, 8);
        entrenadores[1] = new EntrenadorEstratega("E02", "Brock", Rango.CAMPEON, vitBrock, 10);
        entrenadores[2] = new EntrenadorCriador("C01", "Misty", Rango.ALTO_MANDO, vitMisty, 15);
        entrenadores[3] = new EntrenadorCriador("C02", "Gary", Rango.NOVATO, vitGary, 12);
        
        System.out.println("--- REGISTRO POKEMON ---");
        
        // Recorro los arrays de cada entrenador para que muestre sus datos, su registro de combate, sus victorias totales de la semana y puntuacion final
        
        for (Entrenador e : entrenadores) {
        	System.out.println("\n" + e.toString());
        	e.mostrarRegistroCombates();
        	System.out.println("Victorias Semanales: " + e.calcularVictoriasSemanales());
        	System.out.println("Puntuación Final: " + e.calcularPuntuacion());
        	
        	// Si es apto o no para la liga
        	if (e.esAptoParaLiga()) {
        		System.out.println("Liga Pokemon: APTO");
        	} else {
        		System.out.println("Liga Pokemon: NO APTO");
        	}
        }
        
        // Mejor entrenador, conteo de distintos tipos de Entrenadores, media general de victorias individuales y doble de topdos los entrenadores  y mejor dia individual 
        System.out.println("--- ESTADISTICAS ---");
        mostrarMejorEntrenador(entrenadores);
        mostrarTipos(entrenadores);
        mostrarMedia(entrenadores);
        mostrarMejorDia(entrenadores);
	}
        
       // Método para calcular y mostrar el mejor entrenador
        public static void mostrarMejorEntrenador(Entrenador[] entrenadores) {
        	double puntuacionMaxima = -1;
        	String nombre = "";
        	
        	for(Entrenador e : entrenadores) {
        		double puntuacion = e.calcularPuntuacion();
        		if (puntuacion > puntuacionMaxima ) {
        			puntuacionMaxima = puntuacion;
        			nombre = e.getNombre();
        		}
        	}	
        	System.out.println("Mejor entrenador: " + nombre + "Puntuacion: " + puntuacionMaxima);
	}
        
        // Método para contar el total de tipos de entrenadores distintos
        public static void mostrarTipos(Entrenador[] entrenadores) {
        	int Estrategas = 0;
        	int Criadores = 0;
        	for(Entrenador e : entrenadores) {
        		if (e instanceof EntrenadorEstratega) {
        			Estrategas++;
        		} else if (e instanceof EntrenadorCriador){
        			Criadores ++;
        		}	
        	}
        	System.out.println("Estrategas: " + Estrategas + " ---" + " Criadores: " + Criadores);
        }
        
        // Método para calcular las medias de victorias totales de cada formato para todos los entrenadores
        public static void mostrarMedia(Entrenador[] entrenadores) {
        	int Individuales = 0;
        	int Dobles = 0;
        	
        	for (Entrenador e : entrenadores) {
        		int[][] reg = e.getRegistroVictorias();
        		for (int i = 0; i < reg.length; i++) {
        			Individuales += reg[i][0];
        			Dobles += reg[i][1];
        		}
        	}
        	
        	double mediaIndividual = Individuales / entrenadores.length;
        	double mediaDoble = Dobles / entrenadores.length;
        	System.out.println("Media de combates Individuales " + mediaIndividual);
        	System.out.println("Media de combates Dobles " + mediaDoble);	
       }
        
        // Método apra encontrar al mejor entrenador de cada día
        public static void mostrarMejorDia(Entrenador[] entrenadores) {
        	int victoriasMaximasDia = 0;
        	String nombre = ""; // Variable para guardar el nombre del entrenador
        	
        	for (Entrenador e : entrenadores) {
        		int[][] reg = e.getRegistroVictorias();
        		for (int i = 0; i < reg.length; i++) {
        			int victoriasDia = e.calcularVictoriasDia(i);
        			if (victoriasDia > victoriasMaximasDia) {
        				victoriasMaximasDia = victoriasDia;
        				nombre = e.getNombre();
        			}
        		}
        	}
        	System.out.println("El entrenador con el mejor día es: " + nombre + ". Con un total de " + victoriasMaximasDia + " Victorias");
        }
}
