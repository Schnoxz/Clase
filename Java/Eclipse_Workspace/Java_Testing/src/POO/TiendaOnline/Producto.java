package POO.TiendaOnline;

public class Producto {
    private String nombre;
    private double precio;
    private int stock;

    // Constructor de la Producto y sus atributos
    public Producto (String nombre, double precio, int stock){
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
    // Metodos get (Nombre, Precio y Stock) 
    public String getNombre(){
        System.out.println("Nombre del producto: " + nombre);
        return nombre;
    }

    public double getPrecio(){
        System.out.println("Preio del producto: " + precio + " euros");
        return precio;
    }

    public int getStock(){
        return stock;
    }

    // Metodos set (Precio y Stock)
    public void setPrecio (double ponerPrecio){
        if (ponerPrecio < 0){
            System.out.println("Error, el precio no puede ser negativo");
        } else {
            this.precio = ponerPrecio;
        }
     }
     // Metodo setStock en boolean para validar si es o no negativo
     public boolean setStock (int ponerStock){
        if (ponerStock < 0){
            System.out.println("Error, el stock no puede ser negativo");
            return false;
        } else {
            this.stock = ponerStock;
            return true;
        }
        }
        // Metodo vender en boolean para validar si hay suficiente stock
        public boolean vender (int cantidad){
            if (cantidad > this.stock){
                System.out.println("No hay suficiente stock para vender");
                return false;
            } else{
                this.stock -= cantidad;
                System.out.println("\nSe ha vendido " + cantidad + " unidades. Stock restante: " + stock);
                return true;  
            }
         }

         // Metodo para mostrar informacion del producto completo
         public String mostrarInfo (){
            return "Producto: " + nombre + ", Precio: " + precio + " euros, Stock; " + stock + " unidades.";    
         }
    }
    


