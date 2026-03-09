package JimenezAlonsoJavierExamen;

// Clase Producto
public class Producto implements Comparable<Producto> { // Se implementa Comparable en la clase del objeto pasando por parametro el objeto a comparar para usar el compareTo
	// Atributos privados
	private String nombre; // Defino el atributo nombre como String y en privado
	private double precio; // El atributo precio como double
	private int stock; // Y el atributo stock como int
	
	// Constructor Producto de la clase Producto
	public Producto (String nombre, double precio, int stock) throws ProductoInvalidoException { // Llamada desde la clase al objeto Exception creado
		if (precio <= 0 || stock < 0) { // Condicion para que el producto sea invalido
			throw new ProductoInvalidoException("Producto Invalido"); // Se lanza la Exception creada cuando ocurre la condición
		}
		
		// Se declara el valor de los atributos
		this.nombre = nombre; 
		this.precio = precio;
		this.stock = stock;
		
	}
	
	// Getters
	
	// Getter para el nombre que devuelve nombre
	public String getNombre() { 
		return nombre; 
	}
	// Getter para el precio que devuelve el precio
	public double getPrecio() {
		return precio;
	}
	// Getter para el stock que devuelve stock
	public int getStock() {
		return stock;
	}
	
	// Setter
	
	// Setter para el nombre 
	public void setNombre(String nombre) {
		
		this.nombre = nombre; // El atributo nombre del objeto con el que estemos trabajando se le otorga el nombre que se le pasa por valor
	}
	
	// Setter para precio con la Exception que se ha declarado 
	public void setPrecio(double precio) throws ProductoInvalidoException {
		if (precio < 0) { // Condicion para que la excepcion salte
			throw new ProductoInvalidoException("Producto Invalido"); // Se lanza la excepcion con su mensaje
		}	
		this.precio = precio;
	}

	public void setStock(int stock) {
		if (stock < 0) { 
			throw new ProductoInvalidoException("Producto Invalido");
		}
		this.stock = stock;
	}


	// Método compareTo para ordenar los productos por precio
	public int compareTo(Producto p) {
		if (this.precio < p.precio) { // Si el precio del objeto que estamos usando es menor al que comparamos, nos devuelve -1
			return -1;
		} else if (this.precio > p.precio) { // Si el precio del objeto que estamos usando es mayor nos devuelve 1
			return 1;
		} else {
			return 0; // Si no es ni menor ni mayor nos indica que es igual por lo que devuelve 0
		}
	}
	
	
    // toString para mostrar la informacion
    @Override
    public String toString() {
        return nombre + precio + stock;
    }
}


