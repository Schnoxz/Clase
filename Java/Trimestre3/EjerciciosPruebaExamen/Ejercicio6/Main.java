package EjerciciosPruebaExamen.Ejercicio6;

//Clase principal que contiene el método main y el combate Pokémon
public class Main {

 // Método main: punto de entrada del programa
 public static void main(String[] args) {

     // --- CREACIÓN DE OBJETOS ---

     // Instanciamos un Pokémon llamado Charizard con 100 puntos de energía iniciales
     Pokemon charizard = new Pokemon("Charizard", 100);

     // Creamos un array de tipo Ataque con 3 posiciones para guardar los movimientos
     Ataque[] ataques = new Ataque[3];

     // Posición 0: AtaqueDanio "Lanzallamas", cuesta 40 de energía, hace 90 de daño
     ataques[0] = new AtaqueDaño("Lanzallamas", 40, 90);
     // Posición 1: AtaqueCuracion "Respiro", cuesta 50 de energía, cura 100 PS
     ataques[1] = new AtaqueCuracion("Respiro", 50, 100);
     // Posición 2: AtaqueDanio "Llamarada", cuesta 80 de energía, hace 150 de daño
     // Fallará: después de Lanzallamas (40) y Respiro (50), solo quedan 10 de energía
     ataques[2] = new AtaqueDaño("Llamarada", 80, 150);

     // --- PROCESAMIENTO: BUCLE CON MANEJO DE EXCEPCIONES ---

     // Recorremos el array de ataques uno a uno con un for-each
     for (Ataque ataque : ataques) {

         // Mostramos qué movimiento se va a intentar usar con el toString() del ataque
         System.out.println("Intentando usar: " + ataque.toString());

         // Bloque try: intentamos ejecutar el ataque sobre Charizard
         try {
             // Llamamos al método ejecutar pasándole el Pokémon; puede lanzar excepción
             ataque.ejecutar(charizard);
             // Si el ataque se ejecutó sin excepción, mostramos la energía restante
             System.out.println("Energía restante: " + charizard.getEnergiaActual());

         // Bloque catch: capturamos la excepción si Charizard no tenía energía suficiente
         } catch (EnergiaInsuficienteException e) {
             // Imprimimos el mensaje de error definido en la excepción personalizada
             System.out.println(e.getMessage());
         }
     }

     // Al terminar el bucle, mostramos el estado final de Charizard usando su toString()
     System.out.println("Estado final: " + charizard.toString());
 }
}