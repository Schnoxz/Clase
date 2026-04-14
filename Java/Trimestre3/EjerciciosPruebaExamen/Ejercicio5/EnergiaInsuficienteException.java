package EjerciciosPruebaExamen.Ejercicio5;

//Excepción personalizada que se lanza cuando un Pokémon no tiene energía suficiente para atacar
//Hereda de Exception (checked exception) para obligar a usar bloques try-catch
public class EnergiaInsuficienteException extends Exception {

 // Constructor que recibe la energía que le falta al Pokémon para poder atacar
 public EnergiaInsuficienteException(int energiaFaltante) {
     // Llamamos al constructor del padre (Exception) con el mensaje de error
     // Concatenamos el texto fijo con el número de energía faltante
     super("ERROR: Tu Pokémon está agotado. Le faltan " + energiaFaltante + " puntos de energía para este ataque.");
 }
}