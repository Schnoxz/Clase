package EjerciciosPruebaExamen.Ejercicio6;

//Subclase de Ataque que representa un ataque ofensivo que causa daño al rival
public class AtaqueDaño extends Ataque {
 // Puntos de daño que este ataque inflige al Pokémon rival
 private int puntosDanio;

 // Constructor que inicializa los atributos del padre más el atributo propio de daño
 public AtaqueDaño(String nombreAtaque, int costeEnergia, int puntosDanio) {
     // Llamamos al constructor de Ataque para inicializar nombreAtaque y costeEnergia
     super(nombreAtaque, costeEnergia);
     // Inicializamos el atributo propio de esta subclase
     this.puntosDanio = puntosDanio;
 }

 // Getter: devuelve los puntos de daño del ataque
 public int getPuntosDanio() {
     return puntosDanio; // Retornamos los puntos de daño
 }

 // Sobreescribimos toString() para añadir los puntos de daño al texto del padre
 @Override
 public String toString() {
     // Llamamos al toString() del padre y añadimos el daño
     // Formato: "Lanzallamas - Coste: 40 | Daño: 90"
     return super.toString() + " | Daño: " + puntosDanio;
 }

 // Implementación del método abstracto ejecutar: intenta usar el ataque sobre el Pokémon p
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
     System.out.println("¡Has usado " + nombreAtaque + " causando " + puntosDanio + " de daño al rival!");
 }
}
