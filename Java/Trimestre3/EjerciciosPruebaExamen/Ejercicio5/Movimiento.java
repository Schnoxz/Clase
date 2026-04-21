package EjerciciosPruebaExamen.Ejercicio5;

//Interfaz que representa un movimiento/ataque que puede ejecutar un Pokémon
public interface Movimiento {

 // Firma del método que ejecuta el movimiento sobre un Pokémon
 // El throws obliga a quien implemente este método a gestionar la excepción
 void ejecutar(Pokemon p) throws EnergiaInsuficienteException;
}