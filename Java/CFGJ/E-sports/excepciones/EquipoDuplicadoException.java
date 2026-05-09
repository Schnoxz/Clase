// Se lanza al intentar crear un equipo con el mismo nombre que uno ya existente
public class EquipoDuplicadoException extends Exception {
	public EquipoDuplicadoException(String message) {
		super(message);
	}

}
