
public class Categoria {

    private String nombre;
    private double iva;

    // Constructor que recibe por parametro el nombre y el IVA de la categoría
    public Categoria(String nombre, double iva) {
        this.nombre = nombre;
        this.iva = iva;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public double getIva() {
        return iva;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    // Metodo toString con Override obligatorio
    @Override
    public String toString() {
        return nombre + " (IVA: " + iva + "%)";
    }
}
