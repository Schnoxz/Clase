package MetodoString;
import java.util.*;

public class ValidarUserPass8 {
	public static final Scanner teclado = new Scanner(System.in);
	
	public static void main (String[] args) {
    
		String usuario;
		boolean usrValido = false; // Flag de usuario hasta que se demuestre lo contrario
		
		do { 
			System.out.print("Introduce tu usuario; ");
			usuario = teclado.nextLine();
			// Comienzo la verificación levantando una bandera en true hasta que se demuestre lo contrario
			boolean letras = true;
			// Compruebo la longitud del usuario, si no corresponde con la condición la flag se pasa a false
			if (usuario.length() > 30 || usuario.length() <= 0) {
				letras = false;
			} else {
				// Compruebo que las todos los caracteres sean letras via contador, si no lo son, la flag de letras pasa a false y se devuelve al principio
				for (int i = 0; i < usuario.length(); i++) {
					if (!Character.isLetter(usuario.charAt(i))) {
						letras = false;
						break;
					}
				}
			}
			// Verificamos el usuario si pasa todas las condiciones correctamente, de lo contrario se devuelve el error
			if (letras) {
				usrValido = true;
			} else {
				System.out.println("Usuario incorrecto, inténtelo de nuevo");
			}
			// Mientras se cumpla la primera condición del usuario, pasamos a pedir la contraseña y verificarla
		} while (!usrValido); // Se repite hasta que la flag cambie
		
		String password;
		boolean pasValido = false; // Se levanta la flag en false hasta que se demuestre lo contrario
		
		do {
		System.out.println("Introduzca la contraseña ( debe tener al menos 7 caracteres, una letra, un dígito y un caracter especial: ");
		password = teclado.nextLine();
		// Mido la longitud de la cadena, debe cumplir la condición de lo contrario se devuelve otra vez al inicio
		if (password.length() < 7) {
            System.out.println("Contrasñea demasiado corta");
            continue; 
        }
		// Levantamos 3 banderas que se deben cumplir, de momento están en false hasta que se demuestre
        boolean tieneLetra = false;
        boolean tieneDigito = false;
        boolean tieneEspecial= false;
        // Contador para verificar cada caracter
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            // Si es letra
            if (Character.isLetter(c)) {
                tieneLetra = true;
            // Si es un digito
            } else if (Character.isDigit(c)) { 
                tieneDigito = true;
            } else {
                // Si no es letra ni dígito, es un carácter especial
                tieneEspecial = true;
            }
        }
        // Comprobamos que aparezcan las tres condiciones en la cadena password
        if (tieneLetra && tieneDigito && tieneEspecial) {
            pasValido = true; // Validamos
        } else {
            System.out.println("La contraseña no cumple los requisitos.");
        }

    } while (!pasValido); // Se repite hasta que la flag cambie

    System.out.println("Usuario correcto.");
}
}