package EjerciciosPruebaExamen.Ejercicio5;

//Clase que representa a un Pokémon con su especie y su energía actual
public class Pokemon {
 // Nombre de la especie del Pokémon (ej: "Pikachu", "Charizard")
 private String especie;
 // Puntos de energía actuales que tiene el Pokémon para usar ataques
 private int energiaActual;

 // Constructor que inicializa el Pokémon con su especie y su energía inicial
 public Pokemon(String especie, int energiaActual) {
     // Asignamos la especie recibida al atributo de la clase
     this.especie = especie;
     // Asignamos la energía recibida al atributo de la clase
     this.energiaActual = energiaActual;
 }

 // Getter: devuelve la especie del Pokémon
 public String getEspecie() {
     return especie; // Retornamos el nombre de la especie
 }

 // Setter: permite cambiar la especie del Pokémon
 public void setEspecie(String especie) {
     this.especie = especie; // Actualizamos el atributo con el nuevo valor
 }

 // Getter: devuelve la energía actual del Pokémon
 public int getEnergiaActual() {
     return energiaActual; // Retornamos los puntos de energía actuales
 }

 // Setter: permite modificar la energía actual del Pokémon (por ejemplo, al gastar o recuperar)
 public void setEnergiaActual(int energiaActual) {
     this.energiaActual = energiaActual; // Actualizamos el atributo con el nuevo valor
 }

 // Devuelve una representación en texto del Pokémon con su especie y energía actual
 @Override
 public String toString() {
     // Formato: "Charizard (Energía: 60)"
     return especie + " (Energía: " + energiaActual + ")";
 }
}