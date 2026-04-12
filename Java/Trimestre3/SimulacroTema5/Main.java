package SimulacroTema5;

public class Main {

    public static void main(String[] args) {
    	// Creo el array de Empleado pero no entiendo muy bien el polimorfismo la vd, no sé si está bien
    	Empleado[] empleados = new Empleado[4]; // Llamo a la clase empleado y le doy 4 elementos vacios
    	
    	// Creo los objetos de tipo Programador y Administrativo, con sus respectivos atributos, y los añado al array de empleados
    	// Despues de preguntarte he decidido meterle 4h por turno asi que ahi se queda
    	// Esquema del array (nombre, id, departamento, horas trabajadas, incidencias resueltas/documentos tramitados, dependiendo de si es programador o administrativo)
    	empleados[0] = new Programador("Jesus", 1, "Entorno", new double[][] {{4, 4}, {4, 4}, {4, 4}, {4, 4}, {4, 4}}, 10);
    	empleados[1] = new Programador("David", 2, "Sistemas", new double[][] {{4, 4}, {4, 4}, {4, 4}, {4, 4}, {4, 4}}, 5);
    	empleados[2] = new Administrativo("Jorge", 3, "Administracion", new double[][] {{4, 4}, {4, 4}, {4, 4}, {4, 4}, {4, 4}}, 20);
    	empleados[3] = new Administrativo("Elena", 4, "Administracion", new double[][] {{4, 4}, {4, 4}, {4, 4}, {4, 4}, {4, 4}}, 10);
    	
    	
    	// Recorrido del array de empleados para que muestre toda su información, uso un bucle for porque como son varios
    	for (int i = 0; i < empleados.length; i++) {
			System.out.println(empleados[i].toString()); // Llamo al método toString para que muestre toda la información de cada empleado
			System.out.println("Horario completo: "); empleados[i].mostrarHorario(); // Tenia un fallo y es que estaba metiendo el mostrarhorario dentro del print
			System.out.println("Productividad: " + empleados[i].calcularProductividad()); // Llamo al método calcularProductividad para que muestre la productividad de cada empleado
			System.out.println("Merece reconocimiento: " + empleados[i].mereceReconocimiento()); // Llamo al método mereceReconocimiento para que muestre si el empleado merece reconocimiento o no
			System.out.println("Horas en total de la semana: " + empleados[i].calcularHorasTotalesSemana()); // Llamo al método calcularHorasTotalesSemana para que muestre las horas totales de la semana de cada empleado
		}
    	
    	// Empleado con mayor productividad, recorro el array y voy comparando con algun empleado que guarde como valor a comparar
    	Empleado empleadoMasProductivo = empleados[0]; // Declaro la variable 
    }
    	// Método para el conteo de los programadores y administrativos que hay
    	public int contarProgramadores(Empleado[] empleados) {
			int contadorProgramadores = 0; // Declaro una variable para contar los programadores
			for (int i = 0; i < empleados.length; i++) { // Recorro el array de empleados
				if (empleados[i] instanceof Programador) { // He copiado lo que venia en los apuntes pero la vd no entiendo muy bien lo que hace el instanceof, entiendo que es para comparar el tipo de objeto pero no sé muy bien como funciona, si me lo puedes explicar te lo agradecería
					contadorProgramadores++; // Incremento el contador de programadores
				}
			}
			return contadorProgramadores; 
			
		}
		
		public int contarAdministrativos(Empleado[] empleados) { // Misma metodologia que el método contarProgramadores pero para administrativos
			int contadorAdministrativos = 0; 
			for (int i = 0; i < empleados.length; i++) {
				if (empleados[i] instanceof Administrativo) { 
					contadorAdministrativos++; 
			}
    }
			return contadorAdministrativos++; 
	}
		
		// Calcular la media de horas trabajadas en cada turno, se dividen en turno de mañana y tarde, en teoria como hay 5 dias y 2 turnos, voy a sumar todos los dias que tengan todos los empleado, es decir empleados * dias totales
		public void calcularMediaHoras(Empleado[] empleados) {
			int dias = empleados.length * 5;
			// Para sacar las horas trabajadas de mañana y tarde, sé que son 4 porque lo he definido yo pero no sabría recorrer la matriz para sacarlas, entiendo que usaría el metodo get de horas trabajadas pero el resto no hahahaha
			int totalmañana = empleados.length * 5 * 4;
			int totaltarde = empleados.length * 5 * 4;		
			// Entiendo que en el momento que se cambie el horario de algun trabajador esto deja de servir pero no sabía hacerlo de otra forma
			System.out.println("Media del turno de mañana: " + (totalmañana / dias));
			System.out.println("Media del turno de tarde: " + (totaltarde / dias));

		}
		
		// Lo de calcular al trabajador que más horas ha trabajado en el mismo dia lo dejo 
}
