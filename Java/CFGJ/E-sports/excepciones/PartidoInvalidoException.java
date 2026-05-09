/* Se lanza al intentar crear un partido con datos inválidos por los siguientes motivos:
	- El equipo juegue contra sí mismo
	- El id sea duplicado
	- Se registre resultado con un partido ya jugado
	*/

public class PartidoInvalidoException extends Exception {
	public PartidoInvalidoException(String message) {
		super(message);
	}

}
