package EjerciciosPruebaExamen.Ejercicio5;

//Subclase de Ataque que representa un ataque ofensivo que causa daño al rival
public class AtaqueDaño extends Ataque {
 // Puntos de daño que este ataque inflige al Pokémon rival
 private int puntosDaño;

 // Constructor que inicializa todos los atributos: los heredados del padre más el propio
 public AtaqueDaño(String nombreAtaque, int costeEnergia, TipoElemento elemento, int puntosDaño) {
     // Llamamos al constructor de Ataque para inicializar nombreAtaque, costeEnergia y elemento
     super(nombreAtaque, costeEnergia, elemento);
     // Inicializamos el atributo propio de esta subclase
     this.puntosDaño = puntosDaño;
 }

 // Getter: devuelve los puntos de daño del ataque
 public int getPuntosDaño() {
     return puntosDaño; // Retornamos los puntos de daño
 }

 // Sobreescribimos toString() para añadir los puntos de daño al texto del padre
 @Override
 public String toString() {
     // Llamamos al toString() del padre y le añadimos el daño al final
     // Formato: "Lanzallamas [FUEGO] - Coste: 40 | Daño: 90"
     return super.toString() + " | Daño: " + puntosDaño;
 }

 // Implementación del método ejecutar de la interfaz Movimiento
 // Intenta usar el ataque sobre el Pokémon p, lanzando excepción si no hay energía
 @Override
 public void ejecutar(Pokemon p) throws EnergiaInsuficienteException {
     // Comprobamos si la energía actual del Pokémon es menor que el coste del ataque
     if (p.getEnergiaActual() < costeEnergia) {
         // Calculamos cuánta energía le falta para poder usar el ataque
         int energiaFaltante = costeEnergia - p.getEnergiaActual();
         // Lanzamos la excepción personalizada con la energía que le falta
         throw new EnergiaInsuficienteException(energiaFaltante);
     }
     // Si tiene energía suficiente, restamos el coste al Pokémon usando getter y setter
     p.setEnergiaActual(p.getEnergiaActual() - costeEnergia);
     // Imprimimos el mensaje de confirmación del ataque con el daño causado
     System.out.println("¡Has usado " + nombreAtaque + " causando " + puntosDaño + " de daño al rival!");
 }
}