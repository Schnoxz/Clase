package SimulacroTema5;
// Interfaz Reconocible, segun el enunciado, esta interfaz va a ser implementada por la clase Empleado, y por tanto por sus subclases Programador y Administrativo, ya que el método mereceReconocimiento se va a usar en estas clases como forma de validación
public interface Reconocible {

    // Metodo boolean merece reconocimiento
    public boolean mereceReconocimiento();
}
