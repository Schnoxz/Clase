package SimulacroTema5;

// Clase abstracta empleado que implementa la interfaz Reconocible, dicha interfaz contiene el metodo mereceReconocimiento que va a usarse en las clases que hereden de empleado
public abstract class Empleado implements Reconocible {

    protected String nombre;
    protected int id;
    protected String departamento;
    protected double[][] horasTrabajadas; // Contiene 5 dias y 2 turnos, las filas representan lunes a viernes y las columnas dos turnos de por ejemplo 4h

    // Constructor clase Empleado
    public Empleado(String nombre, int id, String departamento, double[][] horasTrabajadas2) {
        this.nombre = nombre;
        this.id = id;
        this.departamento = departamento;
        this.horasTrabajadas = horasTrabajadas2;
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getId() { return id; }
    public String getDepartamento() { return departamento; }
    public double[][] getHorasTrabajadas() { return horasTrabajadas; }
    
    // Setters 
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setId(int id) { this.id = id; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public void setHorasTrabajadas(double[][] horasTrabajadas) { this.horasTrabajadas = horasTrabajadas; }
    
    // Enumerado Departamento (sinceramente no sé si debo meterlo en el mismo archivo o en otro, revisé los apuntes y no me quedó claro, pero lo meto aquí para que no se me olvide, si hay que moverlo a otro archivo lo tendré en cuenta)
    public enum Departamento {
        INFORMATICA, ADMINISTRACION, RRHH, VENTAS
    }

    // Método toString que muestra todos los atributos sobre la el objeto
    @Override 
    public String toString() {
        return "Nombre: " + nombre + "--- " + "ID: " + id + "---" + "Departamento:  " + departamento + "---" + "Horas trabajadas: " + horasTrabajadas;
    }
    
    // Método para calcular las horas totales de la semana, recorre la matriz de horas trabajadas y suma todas las horas
    public double calcularHorasTotalesSemana() { // He tenido dudas porque pasaba por parametro la matriz, pero al ser un atributo de la clase no necesitaba pasarlo, directamente con horasTrabajadas tenia el dato.
        int resultado = 0;
        for (int i = 0; i < horasTrabajadas.length; i++) {
            for (int j = 0; j < horasTrabajadas[i].length; j++) {
                resultado += horasTrabajadas[i][j];
            }
        }
        return resultado; 
    }

    // Método abstracto calcular productividad
    public abstract double calcularProductividad();

    // Método calcular horas de un dia
    public double calcularHorasDia(int dia) {
    	double total = 0; // Declaro una variable para almacenar el sumatorio de las horas
        for (int i = 0; i < horasTrabajadas[dia].length; i++) {
            total += horasTrabajadas[dia][i];
        }

        return total;
    }

    // Método que muestra toda la matriz de horas trabajadas
    public void mostrarHorario() {
        for (int i = 0; i < horasTrabajadas.length; i++) {
            for (int j = 0; j < horasTrabajadas[i].length; j++) {
                System.out.print(horasTrabajadas[i][j] + " ");
            }
        }
    }
}
