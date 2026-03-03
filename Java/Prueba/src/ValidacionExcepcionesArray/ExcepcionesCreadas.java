// Crea excepciones personalizadas EdadInvalidaException → si la edad no está entre 0 y 120 NombreVacioException → si el nombre es null o vacío
package ValidacionExcepcionesArray;

// NombreVacioException → si el nombre esta vacio o es null
public class ExcepcionesCreadas {
    // EdadInvalidaException → si la edad no está entre 0 y 120

    // Clase EdadInvalidaException que hereda de la clase Exception
    public class EdadInvalidaException extends Exception {

        // Constructor de la clase que con el super llama al constructor de la clase padre y le pasa un mensaje
        public EdadInvalidaException(String message) {
            super(message);
        }
    }

    // Clase NombreVacioException que hereda de la clase Exception
    public class NombreVacioException extends Exception {

        // ""
        public NombreVacioException(String message) {
            super(message);
        }
    }
}
