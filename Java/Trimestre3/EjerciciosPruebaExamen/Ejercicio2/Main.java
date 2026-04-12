package EjerciciosPruebaExamen.Ejercicio2;

public class Main {
	public static void main (String[] args) {
		// Creacion del array CnetroPokemon con 4 posiciones 
		CentroPokemon[] Centros = new CentroPokemon[4];
		
		// Inicializacion de matrices
		int[][] cen1 = {{2,3,0}, {4,5,0}, {1,5,1}, {1,4,2}, {1,3,0} };
		int[][] cen2 = {{4,1,1}, {4,2,0}, {6,5,0}, {3,6,2}, {4,3,1} };
		int[][] cen3 = {{4,2,2}, {1,5,1}, {5,5,2}, {4,1,1}, {6,3,0} };
		int[][] cen4 = {{6,3,1}, {3,5,2}, {3,5,1}, {1,3,2}, {4,3,2} };
		
		// Creacion de 2 centro pokemon Nurse y 2 robots
		// String id, String region, TipoCentro tipoCentro, int[][] registroAtenciones
		Centros[0] = new CentroNurseJoy("C01", "Prado", TipoCentro.PUEBLO, cen1, 3);
		Centros[1] = new CentroNurseJoy("C02", "Canela", TipoCentro.CIUDAD, cen2, 2);
		Centros[2] = new CentroRobot("R01", "Region 22", TipoCentro.GIMNASIO,cen3, 5);
		Centros[3] = new CentroRobot("R02", "Paleta", TipoCentro.ELITE_CUATRO, cen4, 2);
				
		 System.out.println("--- CENTROS POKEMONS ---");
		 
		 for (CentroPokemon c : Centros) {
			 System.out.println("\n" + c.toString());
			 c.mostrarRegistro();
			 System.out.println("Total de pokemons atendidos: " + c.totalPokemonAtendidos());
			 System.out.println("Eficiencia : " + c.calcularEficiencia());
			 
			 if (c.superaAuditoria()) {
				 System.out.println("Supera la Auditoria");
			 } else { 
				 System.out.println("No supera la auditoria");
			 }
		 }
		 
		 // Estadística Final
		 System.out.println("--- ESTADISTICA FINAL ---");
		 centroMayorEficiencia(Centros);
		 contadorCentros(Centros);
		 mostrarMediaPorRareza(Centros);
		 mejorDiaAtenciones(Centros);
	}
		 // Centro con mayor eficiencia
		 public static void centroMayorEficiencia(CentroPokemon[] Centros) {
			CentroPokemon mejorCentro = Centros[0];
			for (CentroPokemon c : Centros) {
				if (c.calcularEficiencia() > mejorCentro.calcularEficiencia()) {
					mejorCentro = c;
				}
			}
			System.out.println("Centro con mayor eficiencia " + mejorCentro.getRegion() + " (" + mejorCentro.calcularEficiencia() + ")");
	}
		 
		 // Contador de tipos de centros
		 public static void contadorCentros(CentroPokemon[] Centros) {
			 int numRobot = 0;
			 int numNurse = 0;
			 for (CentroPokemon c : Centros) {
				 if (c instanceof CentroNurseJoy) numNurse++;
				 if (c instanceof CentroRobot) numRobot++;
		 }
			 System.out.println("Centros NurseyJoy: " + numNurse);
			 System.out.println("Centros Robots: " + numRobot);
			 
		 }
		 
		 // Media de atenciones por rareza
		 public static void mostrarMediaPorRareza(CentroPokemon[] centros) {
			    int comunes = 0;
			    int raros = 0;
			    int legendarios = 0;

			    for (CentroPokemon c : centros) {
			        int[][] reg = c.getRegistroAtenciones();
			        for (int i = 0; i < reg.length; i++) {
			            comunes += reg[i][0];
			            raros += reg[i][1];
			            legendarios += reg[i][2];
			        }
			    }
			    
			    double mediaComunes = comunes/centros.length;
			    double mediaRaros = raros/centros.length;
			    double mediaLegendarios = legendarios/centros.length;
			    System.out.println("Media Comunes: " + mediaComunes);
			    System.out.println("Media Raros: " + mediaRaros);
			    System.out.println("Media Legendarios : " + mediaLegendarios);
			}
		 
		 public static void mejorDiaAtenciones(CentroPokemon[] centros) {
			    int mejorDia = 0;
			    int maxAtenciones = 0;

			    for (int dia = 0; dia < 5; dia++) {
			        int totalDia = 0;
			        for (CentroPokemon c : centros) {
			            int[][] reg = c.getRegistroAtenciones();
			            totalDia += reg[dia][0] + reg[dia][1] + reg[dia][2];
			        }
			        if (totalDia > maxAtenciones) {
			            maxAtenciones = totalDia;
			            mejorDia = dia;
			        }
			    }

			    System.out.println("Día con más atenciones: Día " + mejorDia + " con " + maxAtenciones + " Pokémon en total");
			}
}


