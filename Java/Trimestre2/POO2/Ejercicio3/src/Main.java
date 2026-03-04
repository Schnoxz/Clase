
public class Main {

    public static void main(String[] args) {
        // Se crean 3 categorias con sus datos llamando a la clase Categoria
        Categoria alimentacion = new Categoria("Alimentación", 4.0);
        Categoria electronica = new Categoria("Electrónica", 21.0);
        Categoria ropa = new Categoria("Ropa", 10.0);

        // Se crean 3 productos con sus respectivos datos y diferente categoria, llamando a la clase Producto
        Producto p1 = new Producto("Pan", 1.00, alimentacion);
        Producto p2 = new Producto("Portátil", 800.0, electronica);
        Producto p3 = new Producto("Camiseta", 15.0, ropa);

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        // Probar modificación
        System.out.println("\nModificamos el precio del pan");
        p1.setPrecioSinIva(1.20);
        System.out.println(p1);

        // Probar cambio de categoría
        System.out.println("\nCambiamos categoría del portátil");
        p2.setCategoria(new Categoria("Oferta", 10.0));
        System.out.println(p2);
    }
}
