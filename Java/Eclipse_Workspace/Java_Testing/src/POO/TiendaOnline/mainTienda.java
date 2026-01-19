package POO.TiendaOnline;
import java.util.Scanner;

public class mainTienda {
    public static final Scanner teclado = new Scanner (System.in);
    public static void Main (String[] args){
        int opcion = 0;

        //Llamo al constructor de la clase Tienda
        Tienda sudNike1 = new Tienda ("Sudadera Nike", 79.99, 30);
        Tienda pantNike1 = new Tienda ("Pantalon chandal Nike", 69.99, 30);
        Tienda zapNike1 = new Tienda ("Zapatillas deporte Nike", 129.99, 40);
        

        do { 
            System.out.println("\n Tienda oficial Nike");
            System.out.println("1. Pantalones Nike Hombre");
            System.out.println("2. Sudadera Nike Hombre");
            System.out.println("3. Zapatillas Nike Hombre");
            System.out.println("4. Salir");
            opcion = teclado.nextInt();


            switch (opcion){
                case 1:
                    System.out.println("\n Pantalones Nike Hombre");
                    System.out.println(pantNike1.mostrarInfo());
                    break;
                case 2:
                    System.out.println("\n Sudadera Nike Hombre");
                    System.out.println(sudNike1.mostrarInfo());
                    break;
                case 3:
                    System.out.println("\n Zapatillas Nike Hombre");
                    System.out.println(zapNike1.mostrarInfo());
                    break;
                case 4:
                    System.out.println("Gracias por su visita");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 4);



            
        }
    }



