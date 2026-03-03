// Crea una clase Persona con constructor que lance esas excepciones. En el main prueba varios casos y captura cada excepción por separado, mostrando mensajes descriptivos.
package ValidacionExcepcionesArray;
// IMPORTANTE acordarse de importar desde la carpeta donde haya creado las excepciones, nombre de la carpeta, nombre del paquete, y el nombre de la clase

import ValidacionExcepcionesArray.Excepciones.EdadInvalidaException;
import ValidacionExcepcionesArray.Excepciones.NombreVacioException;

public class ArrayExcepcion {

    // Método principal
    public static void main(String[] args) {
        try {
            // Crear una instancia de Persona con edad y nombre válidos
            Persona persona = new Persona("Juan", 30);
            // Mostrar mensaje de éxito
            System.out.println("Persona creada con éxito: " + persona.getNombre() + ", " + persona.getEdad() + " años.");
        } catch (EdadInvalidaException | NombreVacioException e) {
            // Capturar excepciones y mostrar mensaje de error
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Clase interna Persona
    public static class Persona {

        private String nombre;
        private int edad;

        // Constructor de Persona que lanza excepciones en caso de error de edad o nombre
        public Persona(String nombre, int edad) throws EdadInvalidaException, NombreVacioException {
            if (edad < 0 || edad > 120) { // Mientras que edad sea menor que 0 o mayor que 120
                throw new EdadInvalidaException("La edad debe estar entre 0 y 120"); // Lanza EdadInvalidaException llamando al constructor
            }
            if (nombre == null || nombre.isEmpty()) { // Mientras que nombre sea nulo o vacio
                throw new NombreVacioException("El nombre no puede ser nulo o vacío"); // Lanza NombreVacioException llamando al constructor
            }
            this.nombre = nombre;
            this.edad = edad;
        }

        // Getters para nombre y edad
        public String getNombre() {
            return nombre;
        }

        public int getEdad() {
            return edad;
        }
    }
}
