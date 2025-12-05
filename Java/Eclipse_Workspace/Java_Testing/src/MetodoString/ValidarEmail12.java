package MetodoString;
import java.util.*;

public class ValidarEmail12{
      private static final Scanner teclado = new Scanner (System.in);

    public static void main(String[] args) {
        
        System.out.print("Introduce email: ");
        String email = teclado.nextLine();
        
        boolean valido = true; 
        int Arroba = -1;
        int Punto = -1;

        // Buscamos arroba 
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                if (Arroba == -1) Arroba = i;
                else valido = false; // Hay más de una arroba
            }
        }   
        // Buscamos el último punto 
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '.') {
                Punto = i; 
            }
        }

        if (Arroba == -1 || Punto == -1 || Punto < Arroba) {
            valido = false;
        } else {
            // Si la estructura es correcta, llamamos a los métodos que validan cada parte
            if (!validarUsuario(email, Arroba)) {
                valido = false;
            }
            if (!validarOrganizacion(email, Arroba, Punto)) {
                valido = false;
            }
            if (!validarExtension(email, Punto)) {
                valido = false;
            }
        }
        
        if (valido) System.out.println("Email VÁLIDO");
        else System.out.println("Email INVÁLIDO");
    }
    // Método para validar la parte del usuario
    public static boolean validarUsuario(String email, int Arroba) {
        String usuario = "";
        for (int i = 0; i < Arroba; i++) usuario += email.charAt(i);
        
        // Usuario debe tener al menos un caracter y empezar por letra
        if (usuario.length() < 1 || !Character.isLetter(usuario.charAt(0))) {
            return false;
        }
        // Que sean letras o numeros
        for(int i=0; i<usuario.length(); i++) {
             char c = usuario.charAt(i);
             if(!Character.isLetterOrDigit(c) && c != '.') return false;
        }
        return true;
    }

    public static boolean validarOrganizacion(String email, int Arroba, int Punto) {
        // Tu bucle para sacar la organización char a char
        String org = "";
        for (int i = Arroba + 1; i < Punto; i++) org += email.charAt(i);
        // Tus validaciones de organización
        if (org.length() < 1 || !Character.isLetter(org.charAt(0))) {
            return false;
        }
        return true;
    }
    // Método para validar la extensión (.es, .com)
    public static boolean validarExtension(String email, int Punto) {
        String fin = "";
        for (int i = Punto + 1; i < email.length(); i++) fin += email.charAt(i); 
        // Que sea .es o .com
        if (fin.length() < 2 || fin.length() > 3) {
            return false;
        }
        // Que sean letras
        for(int i=0; i<fin.length(); i++) {
             if(!Character.isLetter(fin.charAt(i))) return false;
        }
        return true;
    }
}