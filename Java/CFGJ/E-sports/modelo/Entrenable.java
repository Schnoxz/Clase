// Interfaz que nos va a limitar quien puede entrenar y aumentar sus estadísticas, se debe implementar obligatoriamente en la clase Jugador
public interface Entrenable {
	// El metodo entrenar simula una sesión de entrenamiento que aumenta el nivel mecánico y el nivel estrategico
    void entrenar();
	// Calcula el rendimiento actual de un entrenable y devuelve en valor numerico
    double calcularRendimiento();
}
