package POO.TiendaOnline;

public class Tienda {
    private String nombre;
    private double precio;
    private int stock;

    // Constructor de la Tienda y sus atributos
    public Tienda(String nombre, double precio, int stock){
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
        System.out.println("Preio del producto:" + precio + " euros");
        return precio;
    }

    public int getStock(){
        System.out.println("Stock disponible: " + stock + " unidades");
        return stock;
    }

    // Metodos set (Precio y Stock)
    public void setPrecio (double ponerPrecio){
        if (precio < 0){
            System.out.println("Error, el precio no puede ser negativo");
        } else {
            this.precio = ponerPrecio;
            System.out.println("El precio se ha actualizado a: " + precio);
        }
     }
     // Metodo setStock en boolean para validar si es o no negativo
     public boolean setStock (int ponerStock){
        if (stock < 0){
            System.out.println("Error, el stock no puede ser negativo");
            return false;
        } else {
            this.stock = ponerStock;
            System.out.println("El stock se ha actualizado a: " + stock);
            return true;
        }
        }
        // Metodo vender en boolean para validar si hay suficiente stock
        public boolean vender (int cantidad){
            if (cantidad > stock){
                System.out.println("No hay suficiente stock para vender");
                return false;
            } else{
                stock -= cantidad;
                System.out.println("Se ha vendido " + cantidad + " unidades. Stock restante: " + stock);
                return true;  
            }
         }

         // Metodo para mostrar informacion del producto completo
         public String mostrarInfo (){
            return "Producto: " + nombre + ", Precio: " + precio + " euros, Stock; " + stock + " unidades.";    
         }
    }
    


