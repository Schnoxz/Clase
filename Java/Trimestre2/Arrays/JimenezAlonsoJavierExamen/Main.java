package JimenezAlonsoJavierExamen;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	private static final Scanner teclado = new Scanner (System.in);
	
	public static void main (String[] args) {
		
		Producto[] producto = new Producto[5]; // Creo un array llamando al constructor Producto que contenga 5 elementos Producto
		pedirNombre(producto);
		pedirPrecio(producto);
		pedirStock(producto);
		
		Arrays.sort(producto); // Productos ordenados por precio segun compareTo en clase Producto
		productoMasCaro(producto); // Producto mas caro
		reducirStock(Producto, stock);
		
		System.out.println(" Stock reducido: " + reducirStock(producto, stock));
	}
	
	
		
		// Metodo para pedir el nombre de los productos hasta finaliar el array
		public static Producto pedirNombre(String n) {
			Producto[] producto = new Producto[n];
			for (int i = 0; i < producto.length; i ++) {
				System.out.println("Nombre del producto " + (i + 1) +": ");
				String nombre = teclado.nextLine();		
			}
			return producto;
		}
		
		// Metodo para pedir el precio de los productos hasta finalizar el array
		public static Producto pedirPrecio (double precio) {
	        while (precio > 0) {
	            try {
	                System.out.print("Introduce el precio del producto: ");
	                for (int i = 0; i < producto.length; i ++) {
	            } catch (ProductoInvalidoException e) {
	                System.out.println("Error: " + e.getMessage());
	            } catch (NumberFormatException e) {
	                System.out.println("Error: introduce un número válido.");
	            }
	        }
			return producto;
		}
		
		// Metodo para pedir el stock de los productos hasta finalizar el array
		public static Producto pedirStock (int Stock) {
			Producto[] producto = new Producto[n];
			for (int i = 0; i < producto.length; i ++) {
				System.out.println("Nombre del producto " + (i + 1) +": ");
				String nombre = teclado.nextLine();
				Producto[i] = pedir
			return producto;
		}
		
		// Metodo que reduce el stock de cada producto en -2
		public static void reducirStock(Producto[] Producto, int stock) {
			for (int i = 0; i < Producto.length; i++) {
				int nuevoStock = Producto[i].getStock() - 2;
				
		        for (int j = 0; i < Producto.length; j++) {
		            System.out.println("Producto : " Producto[j]);
		}
		
		// Metodo que nos devuelve el producto mas caro teniendo en cuenta el Arrays.sort previamente usado
		public static void productoMasCaro(Producto[] producto) {
			Producto masCaro = producto[producto.length - 1];
			System.out.println("Producto más caro: " + masCaro.getNombre());
		}
		


		
	}
}
