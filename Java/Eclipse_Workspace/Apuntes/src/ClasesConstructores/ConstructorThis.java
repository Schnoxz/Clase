package ClasesConstructores;

public class ConstructorThis {
	// Llamar a un constructor desde otro con this()
	
	public class Circulo {
		private double radio;
		private String color;
		
		public Circulo() {
			this(1.0, "rojo"); // Llama al constructor completo con valores por defecto
		}
		
		public Circulo(double radio, String color) {
			this.radio = radio;
			this.color = color;
		}
	}

}
