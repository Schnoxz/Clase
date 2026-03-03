package examenCompleto;

public class Producto {

    // Declaro los atributos en private
    private String nombre;
    private int stock;
    private double precio;

    // Constructor de la clase Producto con parametros nombre, stock y precio
    public Producto(String nombre, int stock, double precio) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }

    // Metodos get de los atributos
    public String getNombre() {
        return nombre.toUpperCase();
    }

    public int getStock() {
        return stock;
    }

    public double getPrecio() {
        return precio;
    }

    // Metodos set de los atributos
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Metodo para vender que usa try catch con el StockException creado
    public void vender(int cantidad) throws StockException {
        if (cantidad > stock) {
            throw new StockException("Stock insuficiente para " + nombre + ". Se piden " + cantidad
                    + " unidades pero solo hay " + stock + ".");
        }
        stock -= cantidad;
    }

    // Metodo toString
    @Override
    public String toString() {
        return "Producto: " + getNombre() + " | Precio: " + precio + " euros" + " | Stock: " + stock + " unidades";
    }
}
