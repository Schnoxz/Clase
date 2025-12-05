package MetodoString;
import java.util.*;

public class ValidarUserPass8 {
    public static final Scanner teclado = new Scanner(System.in);
    
    public static void main (String[] args) {
        String usuario;
        boolean userValido = false; 
        
		// Pido el usuario hasta que sea válido
        do { 
            System.out.print("Introduce tu usuario: ");
            usuario = teclado.nextLine();     
            // Llamamos al método para comprobarlo
            if (esUsuarioValido(usuario)) {
                userValido = true;
            } else {
                System.out.println("Usuario incorrecto, inténtelo de nuevo");
            }

        } while (!userValido); 
         
        String password;
        boolean pasValido = false; 
        
        // Pido la contraseña hasta que sea válida
        do {
            System.out.println("Introduzca la contraseña ( debe tener al menos 7 caracteres, una letra, un dígito y un caracter especial: ");
            password = teclado.nextLine();
			// Llamo al metodo para comprobarlo
            if (esPasswordValido(password)) {
                pasValido = true;
            }

        } while (!pasValido); 

        System.out.println("Usuario correcto.");
    }

	// Método para validar el usuario
    public static boolean esUsuarioValido(String usuario) {
        boolean letras = true;
        
        // Compruebo la longitud
        if (usuario.length() > 30 || usuario.length() <= 0) {
            letras = false;
        } else {
            // Compruebo caracteres
            for (int i = 0; i < usuario.length(); i++) {
                if (!Character.isLetter(usuario.charAt(i))) {
                    letras = false;
                    break;
                }
            }
        }
        return letras;
    }
	// Método para validar la contraseña
    public static boolean esPasswordValido(String password) {
        // Mido la longitud
        if (password.length() < 7) {
            System.out.println("Contrasñea demasiado corta");
            return false; 
        }
		// Levanto banderas para cada tipo de caracter
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

        // Comprobamos condiciones finales
        if (tieneLetra && tieneDigito && tieneEspecial) {
            return true; // Validamos
        } else {
            System.out.println("La contraseña no cumple los requisitos.");
            return false;
        }
    }
}