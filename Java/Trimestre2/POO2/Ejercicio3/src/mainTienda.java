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

public class mainTienda {

    public static void main(String[] args) {

        // Crear las categorias con el iva para cada una
        Categoria calzado = new Categoria("Calzado", 21.0);
        Categoria deporte = new Categoria("Ropa de Deporte", 14.0);

        // Crear los productos
        Producto p1 = new Producto("Zapatos", 49.99, calzado);
        Producto p2 = new Producto("Tacones", 32.99, calzado);
        Producto p3 = new Producto("Chandal Adidas", 55.99, deporte);
        Producto p4 = new Producto("Chandal Puma", 59.99, deporte);

        System.out.println("Lista de productos: ");
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(p3.toString());
        System.out.println(p4.toString());

        System.out.println("Precio final de los productos: ");
        System.out.println("Zapatos: " + p1.calcularPrecio() + "€");
        System.out.println("Tacones: " + p2.calcularPrecio() + "€");
        System.out.println("Chandal Adidas: " + p3.calcularPrecio() + "€");
        System.out.println("Chandal Puma: " + p4.calcularPrecio() + "€");

    }
}
}
