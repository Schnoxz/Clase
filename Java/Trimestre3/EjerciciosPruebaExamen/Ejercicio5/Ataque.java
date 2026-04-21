package EjerciciosPruebaExamen.Ejercicio5;

//Clase abstracta que representa un ataque genérico de Pokémon
//Implementa la interfaz Movimiento, pero como es abstracta no está obligada
//a programar el cuerpo de ejecutar(): lo harán sus subclases
public abstract class Ataque implements Movimiento {

 // Nombre del ataque (ej: "Lanzallamas")
 protected String nombreAtaque;
 // Coste en puntos de energía que consume usar este ataque
 protected int costeEnergia;
 // Tipo elemental del ataque según el enumerado TipoElemento
 protected TipoElemento elemento;

 // Constructor que inicializa los tres atributos del ataque
 public Ataque(String nombreAtaque, int costeEnergia, TipoElemento elemento) {
     // Asignamos el nombre del ataque
     this.nombreAtaque = nombreAtaque;
     // Asignamos el coste de energía
     this.costeEnergia = costeEnergia;
     // Asignamos el tipo elemental
     this.elemento = elemento;
 }

 // Getter: devuelve el nombre del ataque
 public String getNombreAtaque() {
     return nombreAtaque; // Retornamos el nombre del ataque
 }

 // Getter: devuelve el coste de energía del ataque
 public int getCosteEnergia() {
     return costeEnergia; // Retornamos el coste en energía
 }

 // Getter: devuelve el tipo elemental del ataque
 public TipoElemento getElemento() {
     return elemento; // Retornamos el TipoElemento del ataque
 }

 // Devuelve una representación en texto del ataque con nombre, tipo y coste
 @Override
 public String toString() {
     // Formato: "Lanzallamas [FUEGO] - Coste: 40"
     return nombreAtaque + " [" + elemento + "] - Coste: " + costeEnergia;
 }
}