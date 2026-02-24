/*Crear una clase que represente Producto con las siguientes características:
◦Tienen un código que los identifica de manera única y que se asigna automáticamente en el
momento de la creación.
◦Guardan la descripción y el precio sin IVA.
◦Tienen una categoría. La categoría es una clase que tiene como atributos el nombre de la
categoría y el IVA aplicable a los productos de esa categoría.
La clase Producto debe proporcionar los métodos adecuados:
◦Constructor.
◦Métodos para consulta y modificación de los atributos.
◦Método para calcular el precio de venta  del producto que se obtiene sumándole al precio el IVA
correspondiente */

public class Producto {

    private int codigo;
    private String descripcion;
    private double precioSinIva;
    private Categoria categoria;

    // Metodo constructor del producto
    public Producto(String descripcion, double precioSinIva, int categoria) {
        this.codigo = codigo + 1;
        this.descripcion = descripcion;
        this.precioSinIva = precioSinIva;
        this.categoria = categoria;
    }

    //Metodo para calcular el precio con IVA
    public double calcularPrecio() {
        double porcentajeIva = categoria.getIva();
        double precioFinal = precioSinIva + (precioSinIva * porcentajeIva / 100);
        return precioFinal;
    }

    // Getters y setters
    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioSinIva() {
        return precioSinIva;
    }

    public void setPrecioSinIva(double precio) throws Exception {
        if (precio < 0) {
            throw new Exception("El iva debe ser positivo");
        }
        this.precioSinIva = precio;
    }

    public Categoria setCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // Metodo para calcular precio sumando IVA
    public String toString() {
        return "ID: " + codigo + " - " + descripcion + " - " + "Precio Final:" + calcularPrecio() + "€";
    }
}
