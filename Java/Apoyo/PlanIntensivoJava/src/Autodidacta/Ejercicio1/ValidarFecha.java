package Autodidacta.Ejercicio1;

public class ValidarFecha {

	// Declaro un atributo como año actual que podrá ser consultado por aquellos metodos que lo necesiten 
    private static final int AÑO_ACTUAL = 2026;

    public static void validar(int diaNacimiento, int mesNacimiento, int añoNacimiento) throws FechaInvalidaException {
    	// Llamada a los metodos que comprobarán la validec del año, mes y dia de nacimiento
        validarAño(añoNacimiento);
        validarMes(mesNacimiento);
        // Para validar el dia será necesario comprobar si es año bisiesto y el mes
        validarDia(diaNacimiento, mesNacimiento, añoNacimiento);
    }

    // Método que valida el año, comprueba que no se salga del rango de la constante año actual ni el máximo de años que puedes tener
    public static void validarAño(int anio) throws FechaInvalidaException {
        if (anio > AÑO_ACTUAL || anio < AÑO_ACTUAL - 120) {
            throw new FechaInvalidaException("El año debe estar entre " + (AÑO_ACTUAL - 120) + " y " + AÑO_ACTUAL + "."); // Mensaje lanzado
        }
    }

    // Método que valida el mes que esté dentro del rango normal de 12 
    public static void validarMes(int mes) throws FechaInvalidaException {
        if (mes < 1 || mes > 12) {
            throw new FechaInvalidaException("El mes debe estar entre 1 y 12."); // Mensaje lanzado 
        }
    }

    // Método que valida que el día esté dentro del rango del mes, consultando a su vez si el año es bisiesto
    public static void validarDia(int dia, int mes, int anio) throws FechaInvalidaException {
        int maxDias = diasDelMes(mes, anio);
        if (dia < 1 || dia > maxDias) {
            throw new FechaInvalidaException("El mes " + mes + " tiene como máximo " + maxDias + " días."); // Mensaje lanzado
        }
    }
    
    // Método de validación que comprueba si un año es bisiesto o no
    public static boolean esBisiesto(int anio) {
        return (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);
    }
    
    // Método que comprueba cuántos dias contiene el mes, según el calendario y si el año en concreto es bisiesto o no
    public static int diasDelMes(int mes, int anio) {
        switch (mes) {
            case 2:  return esBisiesto(anio) ? 29 : 28;
            case 4: case 6: case 9: case 11: return 30;
            default: return 31;
        }
    }
    
    // Método que comprueba si hay numeros en las credenciales del nombre de usuario
    public static boolean contieneNumero (String userName) {
    	for (int i = 0; i < userName.length(); i++) {
    		char caracter = userName.charAt(i);
    		if (caracter >= '0' && caracter <= '9') {
    			return true;
    		}
    	}
    	return false;
    }
}