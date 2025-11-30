package MetodoString;
import java.util.*;

public class ValidarEmail12 {
	private static final Scanner teclado = new Scanner (System.in);
    public static void main(String[] args) {
       
        System.out.print("Introduce email: ");
        String email = teclado.nextLine();
        
        boolean valido = true;
        
        // Buscamos posición de la arroba '@' y del último punto '.' manualmente
        int Arroba = -1;
        int Punto = -1;
        
        // Buscamos arroba (solo debe haber una, buscamos la primera)
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                if (Arroba == -1) Arroba = i;
                else valido = false; // Hay más de una arroba
            }
        }
        
        // Buscamos el último punto
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '.') {
                Punto = i; // Se actualiza siempre, quedando el último
            }
        }
        
        // Validaciones estructurales básicas
        if (Arroba == -1 || Punto == -1 || Punto < Arroba) {
            valido = false;
        } else {
            // Analizar USUARIO (inicio hasta arroba)
            String usuario = "";
            for (int i = 0; i < Arroba; i++) usuario += email.charAt(i);
            
            if (usuario.length() < 1 || !Character.isLetter(usuario.charAt(0))) {
                valido = false;
            }
            // Validar caracteres de usuario (letras, num, punto)
            for(int i=0; i<usuario.length(); i++) {
                 char c = usuario.charAt(i);
                 if(!Character.isLetterOrDigit(c) && c != '.') valido = false;
            }

            // Analizar ORGANIZACION (arroba+1 hasta punto)
            String org = "";
            for (int i = Arroba + 1; i < Punto; i++) org += email.charAt(i);
            
            if (org.length() < 1 || !Character.isLetter(org.charAt(0))) {
                valido = false;
            }

            // Analizar FIN (punto+1 hasta final)
            String fin = "";
            for (int i = Punto + 1; i < email.length(); i++) fin += email.charAt(i);
            
            if (fin.length() < 2 || fin.length() > 3) {
                valido = false;
            }
            // Fin solo letras
            for(int i=0; i<fin.length(); i++) {
                 if(!Character.isLetter(fin.charAt(i))) valido = false;
            }
        }
        
        if (valido) System.out.println("Email VÁLIDO");
        else System.out.println("Email INVÁLIDO");
    }
}