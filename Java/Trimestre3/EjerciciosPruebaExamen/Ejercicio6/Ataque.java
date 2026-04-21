package EjerciciosPruebaExamen.Ejercicio6;

//Clase abstracta que representa un ataque genérico de Pokémon (sin tipo elemental en este ejercicio)
//Al ser abstracta, no puede instanciarse directamente; las subclases deben implementar ejecutar()
public abstract class Ataque {
 // Nombre del ataque (ej: "Lanzallamas")
 protected String nombreAtaque;
 // Coste en puntos de energía que consume usar este ataque
 protected int costeEnergia;

 // Constructor que inicializa los dos atributos del ataque
 public Ataque(String nombreAtaque, int costeEnergia) {
     // Asignamos el nombre del ataque
     this.nombreAtaque = nombreAtaque;
     // Asignamos el coste de energía
     this.costeEnergia = costeEnergia;
 }

 // Getter: devuelve el nombre del ataque
 public String getNombreAtaque() {
     return nombreAtaque; // Retornamos el nombre del ataque
 }

 // Getter: devuelve el coste de energía del ataque
 public int getCosteEnergia() {
     return costeEnergia; // Retornamos el coste en energía
 }

 // Devuelve una representación en texto del ataque con nombre y coste
 @Override
 public String toString() {
     // Formato: "Lanzallamas - Coste: 40"
     return nombreAtaque + " - Coste: " + costeEnergia;
 }

 // Método abstracto que las subclases deben implementar obligatoriamente
 // El throws avisa de que la implementación puede lanzar EnergiaInsuficienteException
 public abstract void ejecutar(Pokemon p) throws EnergiaInsuficienteException;
}