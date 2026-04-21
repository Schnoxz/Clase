package EjerciciosPruebaExamen.Ejercicio5;

//Subclase de Ataque que representa un movimiento de curación para el propio Pokémon
public class AtaqueCuracion extends Ataque {
 // Puntos de salud que este ataque recupera al usarlo
 private int puntosSaludRecuperados;

 // Constructor que inicializa los atributos heredados del padre más el propio
 public AtaqueCuracion(String nombreAtaque, int costeEnergia, TipoElemento elemento, int puntosSaludRecuperados) {
     // Llamamos al constructor de Ataque para inicializar nombreAtaque, costeEnergia y elemento
     super(nombreAtaque, costeEnergia, elemento);
     // Inicializamos el atributo propio de esta subclase
     this.puntosSaludRecuperados = puntosSaludRecuperados;
 }

 // Getter: devuelve los puntos de salud que recupera este ataque
 public int getPuntosSaludRecuperados() {
     return puntosSaludRecuperados; // Retornamos la cantidad de salud recuperada
 }

 // Sobreescribimos toString() para añadir la salud recuperada al texto del padre
 @Override
 public String toString() {
     // Llamamos al toString() del padre y le añadimos la curación al final
     // Formato: "Respiro [VOLADOR] - Coste: 50 | Cura: 100 PS"
     return super.toString() + " | Cura: " + puntosSaludRecuperados + " PS";
 }

 // Implementación del método ejecutar de la interfaz Movimiento
 // Intenta usar el ataque curativo sobre el Pokémon p, lanzando excepción si no hay energía
 @Override
 public void ejecutar(Pokemon p) throws EnergiaInsuficienteException {
     // Comprobamos si la energía actual del Pokémon es menor que el coste del ataque
     if (p.getEnergiaActual() < costeEnergia) {
         // Calculamos cuánta energía le falta para poder usar el movimiento
         int energiaFaltante = costeEnergia - p.getEnergiaActual();
         // Lanzamos la excepción personalizada con la energía que le falta
         throw new EnergiaInsuficienteException(energiaFaltante);
     }
     // Si tiene energía suficiente, restamos el coste al Pokémon usando getter y setter
     p.setEnergiaActual(p.getEnergiaActual() - costeEnergia);
     // Imprimimos el mensaje de confirmación de la curación con los PS recuperados
     System.out.println("¡Has usado " + nombreAtaque + " y te has curado " + puntosSaludRecuperados + " PS!");
 }
}
