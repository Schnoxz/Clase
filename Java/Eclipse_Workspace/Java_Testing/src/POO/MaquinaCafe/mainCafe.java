package POO.MaquinaCafe;
import java.util.*;

public class mainCafe {
    private static final Scanner teclado = new Scanner(System.in);
    public static void main(String[] args){

    // Se crea el objeto llamando al constructor, inicializa el monedero en 0
    Cafe m1 = new Cafe(0);
    // He añadido una variable para confirmar que el usuario realmente quiere el producto
    int confirmar = 0;
    int opcion = 0;
    
    // Menú principal de la maquina
    do {
        System.out.println("\n Peruvian company ");
        System.out.println(" 1. Café solo (1 euro) ");
        System.out.println(" 2. Vaso de leche (0.80 euro) ");
        System.out.println(" 3. Café con leche (1.50 euro) ");
        System.out.println(" 4. Estado ");
        System.out.println(" 5. Salir ");

        opcion = teclado.nextInt();
    // Switch para navegar por el menú
        switch (opcion) {
            case 1:
                System.out.println("Pulse de nuevo para confirmar"); // Confirmación adicional al usuario
                confirmar = teclado.nextInt();
                if (confirmar == opcion){
                    m1.servirSolo();
                } else {
                    System.out.println("Cancelado");
                }
                break; // Lo pongo fuera de la condicion porque una vez que el usuario confirma, si dejo el break dentro, puede confirmar items ilimitados en un solo pago
            
            case 2:
                System.out.println("Pulse de nuevo para confirmar");
                confirmar = teclado.nextInt();
                if (confirmar == opcion){
                    m1.servirLeche();
                } else {
                    System.out.println("Cancelado");
                }
                break;

            case 3:
                System.out.println("Pulse de nuevo para confirmar");
                confirmar = teclado.nextInt();
                if (confirmar == opcion){
                    m1.servirCafeLeche();
                } else {
                    System.out.println("Cancelado");
                }
                break;
                
            case 4:
                System.out.println("Pulse de nuevo para confirmar");
                confirmar = teclado.nextInt();
                if (confirmar == opcion){
                    m1.recuentoMaquina();
                } else {
                    System.out.println("Cancelado");
                }
                break;
                
            case 5:
                System.out.println("Apagando...");
                System.out.println(" <> <> <> <> <> ");
                break;

            default:
                System.out.println("Opción incorrecta"); 
        }
    } while (opcion != 5);
  }
} 
