package CorreccionSimulacroTema5;

public abstract class Empleado implements InterfazReconocible {

    protected  String id;
    protected String nombre;
    protected EnumDepartamento departamento;
    protected double[][] horasTrabajadas; // 5 x 2

    public Empleado(String id, String nombre, EnumDepartamento departamento, double[][] horasTrabajadas) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.horasTrabajadas = horasTrabajadas;
    }
    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public EnumDepartamento getDepartamento() { return departamento; }
    public double[][] getHorasTrabajadas() { return horasTrabajadas; }
    // Setters
    public void setId(String id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDepartamento(EnumDepartamento departamento) { this.departamento = departamento; }
    public void setHorasTrabajadas(double[][] horasTrabajadas) { this.horasTrabajadas = horasTrabajadas; }

    public double calcularHorasTotalesSemana(){
        double suma = 0;
        for (double[] fila : horasTrabajadas) {
            for (double horas : fila) {
                suma += horas;
            }
        }
        return suma;
    }

    public double calcularHorasDia(int dia) {
        double suma = 0;
        // Recorre la columna dia, de la fila dia para obtener la suma de horas trabajadas
        for (int i = 0; i < horasTrabajadas[dia].length; i++) { 
            suma += horasTrabajadas[dia][i];
        }
        return suma;
    }
    // Método mostrarHorario que muestra toda la matriz
    public void mostrarHorario(){
        // Crea un String dias con todos los dias laborales
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
        // Imprime el horario de cada empleado (nombre + horas trabajadas)
        System.out.println("Horario de " + nombre + ":" );
        // Bucle for que recorre la matriz horasTrabajadas de un dia dividido en turno de tarde y mañana y muestra el horario
        for (int i = 0; i < horasTrabajadas.length; i++) {
            System.out.println(dias[i] + "-->  Mañana: " + horasTrabajadas[i][0] + " | Tarde: " + horasTrabajadas[i][1]); // Solo tenemos dos columnas en turno, por eso se predefine el texto imprimido (mañana y tarde, 0 y 1)
        }
    }

    // Metodo abstracto calcular productividad
    public abstract double calcularProductividad();

    // Método toString que llama a calcularHorasTotalesSemana para mostrar las horas trabajadas
    @Override
    public String toString(){
        return "ID: " + id + " | Nombre: " + nombre + " | Departamento: " + departamento + " | Horas trabajadas: " + calcularHorasTotalesSemana();
    }
}
