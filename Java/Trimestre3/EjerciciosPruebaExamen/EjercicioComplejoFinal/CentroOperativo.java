package EjerciciosPruebaExamen.EjercicioComplejoFinal;

public abstract class CentroOperativo implements Supervisable {
   protected String codigo;
   protected Zona zona;
   protected Responsable responsable;
   protected int[][] operaciones; // Matriz 5x2 5 dias y 2 turnos
   protected int[][] incidencias; // Matriz 5x2 5 dias y 2 turnos
   
    public CentroOperativo(String codigo, Zona zona, Responsable responsable, int[][] operaciones, int[][] incidencias) {
        this.codigo = codigo;
        this.zona = zona;
        this.responsable = responsable;
        this.operaciones = operaciones;
        this.incidencias = incidencias;
    }

    // Getters
    public String getCodigo() { return codigo; }
    public Zona getZona() { return zona; }
    public Responsable getResponsable() { return responsable; }
    public int[][] getOperaciones() { return operaciones; }
    public int[][] getIncidencias() { return incidencias; }
    
    // Setters
    public void setCodigo(String codigo){ this.codigo = codigo; }
    public void setZona(Zona zona){ this.zona = zona; }
    public void setResponsable(Responsable responsable){ this.responsable = responsable; }
    public void setOperaciones(int[][] operaciones){ this.operaciones = operaciones; }
    public void setIncidencias(int[][] incidencias){ this.incidencias = incidencias; }

    // Método calcularTotalOperaciones
    public int calcularTotalOperaciones(){
        int total = 0;
        // Recorrido de la matriz y se van sumando a la variable total
        for (int i = 0; i < operaciones.length; i++) {
            for (int j = 0; j < operaciones[i].length; j++) {
                total += operaciones[i][j];
            }
        }
        return total;
    }

    // Método calcularTotalIncidencias
    public int calcularTotalIncidencias(){
        int total = 0;
        for (int i = 0; i < incidencias.length; i++) {
            for (int j = 0; j < incidencias[i].length; j++) {
                total += incidencias[i][j];
            }
        }
        return total;
    }

    // Método calcularOperacionesDia(int dia)
    public int calcularOperacionesDia(int dia){ return operaciones[dia][0] + operaciones[dia][1];} // Como ya sabemos el dia, simplemente sumamos operaciones individuales y duales de la columna 0 y 1

    // Método calcularIncidenciasDia(int dia)
    public int calcularIncidenciasDia(int dia){ return incidencias[dia][0] + incidencias[dia][1]; }

    // Método calcularTasaIncidencias ((totalIncidencias * 100.0) / totalOperaciones)
    public double calcularTasaIncidencias() { return ((calcularTotalIncidencias() * 100.0) / calcularTotalOperaciones()); }

    // Método abstracto mostrarResumenSemanal()
    public void mostrarResumenSemanal(){
    String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
    System.out.println("Resumen semanal del centro " + codigo + ":");
        for (int i = 0; i < operaciones.length; i++) {
            System.out.println(
                dias[i] +
                " -> Operaciones mañana: " + operaciones[i][0] +
                ", Operaciones tarde: " + operaciones[i][1] +
                ", Incidencias mañana: " + incidencias[i][0] +
                ", Incidencias tarde: " + incidencias[i][1]
            );
        }
    }
    // Método abstracti
    public abstract double calcularIndiceEficiencia();
}

