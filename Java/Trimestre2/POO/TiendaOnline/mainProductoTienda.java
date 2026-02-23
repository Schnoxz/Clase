package POO.TiendaOnline;
import java.util.Scanner;

public class mainProductoTienda {
    public static final Scanner teclado = new Scanner (System.in);
    public static void main (String[] args){
        


        //Llamo al constructor de la clase Tienda
        Producto sudNike1 = new Producto ("Sudadera Negra Nike", 79.99, 30);
        Producto sudNike2 = new Producto ("Sudadera Blanca Nike", 69.99, 30);

        Producto pantNike1 = new Producto ("Pantalon chandal Negro Nike", 69.99, 30);
        Producto pantNike2 = new Producto ("Pantalon chandal Blanco Nike", 59.99, 30);

        Producto zapNike1 = new Producto ("Zapatillas deporte Negras Nike", 129.99, 40);
        Producto zapNike2 = new Producto ("Zapatillas deporte Blancas Nike", 119.99, 40);
        
        int opcion;
        int subOpcion;

        do { 
            System.out.println("\n Tienda oficial Nike");
            System.out.println("1. Pantalones Nike Hombre");
            System.out.println("2. Sudadera Nike Hombre");
            System.out.println("3. Zapatillas Nike Hombre");
            System.out.println("4. Salir");
            opcion = teclado.nextInt();


            switch (opcion){
                // Muestro la seccion pantalones y los articulos en rl

                // Menu Pantalones
                case 1:
                    System.out.println("\n Pantalones Nike Hombre");
                    System.out.println("1. " + pantNike1.mostrarInfo());
                    System.out.println("2. " + pantNike2.mostrarInfo());
                    System.out.println("3. Volver al menu");
                    subOpcion = teclado.nextInt();

            switch(subOpcion){
                
                case 1: ventaRealizada(pantNike1);
                        break;
                case 2: ventaRealizada(pantNike2);
                        break;
                case 3: 
                        break; 
                default: System.out.println("Funcion incorrecta");
                    }
                break;

                // Menu Sudaderas
                case 2:
                    System.out.println("\n Sudaderas Nike Hombre");
                    System.out.println("1. " + sudNike1.mostrarInfo());
                    System.out.println("2. " + sudNike2.mostrarInfo());
                    System.out.println("3. Volver al menu");
                    subOpcion = teclado.nextInt();

            switch(subOpcion){

                case 1: ventaRealizada(sudNike1);
                        break;
                case 2: ventaRealizada(sudNike2);
                        break;
                case 3: 
                        break; 
                default: System.out.println("Funcion incorrecta");
                    }
                break;

                // Menu Zapatillas
                case 3: 
                    System.out.println("\n Zapatillas deporte Nike Hombre");
                    System.out.println("1. " + zapNike1.mostrarInfo());
                    System.out.println("2. " + zapNike2.mostrarInfo());
                    System.out.println("3. Volver al menu");
                    subOpcion = teclado.nextInt();
                 
            switch(subOpcion){

                case 1 -> ventaRealizada(zapNike1);
                case 2 -> ventaRealizada(zapNike2);
                case 3 -> {
                    }
                default -> System.out.println("Funcion incorrecta");
                    }
                break;


                // Menu Salida
                case 4: System.out.println("Hasta pronto :3 ");
                break;

                default: System.out.println("Funcion Incorrecta");
            }
        } while (opcion != 4);
    }

    // METODO AUX xiquitito xiquitito para el tema gestion de ventas y no repetir mensajes por pantalla ni comprobaciones individuales
        public static void ventaRealizada (Producto p)  {
            System.out.println("Cantidad de unidades: ");
            int cantidad = teclado.nextInt();

            //Llamo al metodo vender del objeto que se ha recibido
            boolean realizado = p.vender(cantidad);

            if (realizado == true) {
                double total = cantidad * p.getPrecio();
                System.out.println("\nCompra finalizada con exito" + "    Total: " + total + " euros");

                if (cantidad >= 3) {
                    System.out.println("Eres buen comprador amego segarro !!");
                }
            }
        }
    }



            
        
    





