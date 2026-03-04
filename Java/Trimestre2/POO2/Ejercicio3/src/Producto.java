
public class Producto {

    // static significa que este contador es compartido por TODOS los objetos Producto.
    // Cada vez que se crea un Producto nuevo, se incrementa y se asigna como código único.
    private static int contadorCodigo = 0;

    private int codigo;          // Código único asignado automáticamente
    private String descripcion;
    private double precioSinIva;
    private Categoria categoria;

    // Constructor: recibe descripción, precio sin IVA y categoría.
    // El código se asigna automáticamente incrementando el contador estático.
    public Producto(String descripcion, double precioSinIva, Categoria categoria) {
        contadorCodigo++;              // Incrementamos el contador compartido
        this.codigo = contadorCodigo; // Asignamos el nuevo valor como código
        this.descripcion = descripcion;
        this.precioSinIva = precioSinIva;
        this.categoria = categoria;
    }

    // Getters
    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecioSinIva() {
        return precioSinIva;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    // Setters
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecioSinIva(double precioSinIva) {
        this.precioSinIva = precioSinIva;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // Meotdo que calcula el precio de venta aplicando el IVA de la categoría.
    public double calcularPrecioVenta() {
        return precioSinIva * (1 + categoria.getIva() / 100);
    }

    // toString con Override obligatorio que muestra la información del producto
    @Override
    public String toString() {
        return "[" + codigo + "] " + descripcion + " | Sin IVA: " + precioSinIva + "€" + " | Con IVA: " + String.format("%.2f", calcularPrecioVenta()) + "€" + " | Categoría: " + categoria.getNombre();
    }
}
