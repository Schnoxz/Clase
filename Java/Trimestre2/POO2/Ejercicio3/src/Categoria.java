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

public class Categoria {

    private String nombre;
    private double iva;

    public Categoria(String nombre, double iva) {
        this.nombre = nombre;
        this.iva = iva;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) throws Exception {
        if (iva < 0) {
            throw new Exception("El iva debe ser positivo");
        }
        this.iva = iva;
    }
}
