
class CapacidadExcedidaException extends Exception { // Crea una excepcion que hereda con el extends de la clase Exception

    // Excepcion personalizada que usa super para llamar al constructor de la clase padre
    public CapacidadExcedidaException(String mensaje) {
        super(mensaje);
    }
}

public class Ejercicio1 {

    // Declaracion de atributos privados para la clase objeto GestionTaller
    private String[] vehiculos;
    private int contador;

    // Consutructor de la clase
    public GestionTaller(int capacidadMaxima) {
        vehiculos = new String[capacidadMaxima];
        contador = 0;
    }

    // Metodo al que se le pasa por parametro una variable String matricula y nos lanza un error previamente deinido si la capacidadMaxima es excedida
    public void registrarVehiculo(String matricula) throws CapacidadExcedidaException {
        if (contador >= vehiculos.length) { // Contador para saber la capacidadMaxima
            throw new CapacidadExcedidaException("Error: El taller está completamente lleno."); // throw new nos crea una excepcion de la clase CapacidadExcedidaException
        }
        vehiculos[contador] = matricula; // Agrega la matricula al array
        contador++; // Suma el contador de vehiculos
    }

    // Metodo auditarTaller que no devuelve nada, contiene un try finally sin catch
    public void auditarTaller() {
        try {
            System.out.println("Iniciando auditoría de vehículos...");
            for (String v : vehiculos) { // for each que recorre el array
                if (v != null) { // Si se encuentra un elemento en el array
                    System.out.println("Vehículo registrado: " + v.toUpperCase()); // Devuelve un mensaje con la matricula en mayusculas
                }
            }
        } finally { // finally hace que si o si se ejecute lo que está definido dentro
            System.out.println("Auditoría finalizada. Cerrando registro.");
        }
    }

    // metodo que devuelve la cantidad de vehiculos
    public int getVehiculosActuales() {
        return this.contador;
    }
}
