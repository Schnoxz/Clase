package EjerciciosPruebaExamen.EjercicioComplejoFinal;

public class Responsable {
    private String dni;
    private String nombre;
    private int antiguedad;

    // Constructor
    public Responsable(String dni, String nombre, int antiguedad) {
        this.dni = dni;
        this.nombre = nombre;
        this.antiguedad = antiguedad;
    }

    //Getters
    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public int getAntiguedad() { return antiguedad; }

    //Setters
    public void setDni(String dni) { this.dni = dni; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setAntiguedad(int antiguedad) { this.antiguedad = antiguedad; }

    // Método toString
    public String toString() {
        return "Responsable: " + nombre + " | " 
            + "DNI: " + dni + " | " +  
            "Antiguedad: " + antiguedad;
    }
}
