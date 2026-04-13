package EjerciciosPruebaExamen.Ejercicio3;

public abstract class Alumno implements Certificable {
    protected String matricula;
    protected String nombre;
    protected Especialidad especialidad;
    protected int[][] notasSemanales; // 4x3, filas 0-3 semanas. Columna 0 (Combate) Columna 1 (Captura) Columna 2 (Cuidado)

    // Constructor
    public Alumno(String matricula, String nombre, Especialidad especialidad, int[][] notasSemanales) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.notasSemanales = notasSemanales;
    }

    // Getters
    public String getMatricula() { return matricula; }
    public String getNombre() { return nombre; }
    public Especialidad getEspecialidad() { return especialidad; }
    public int[][] getNotasSemanales() { return notasSemanales; }

    // Setters
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEspecialidad(Especialidad especialidad) { this.especialidad = especialidad; }
    public void setNotasSemanales(int[][] notasSemanales) { this.notasSemanales = notasSemanales; }

    // Método toString
    @Override
    public String toString() {
        return "Matricula: " + matricula + " | Nombre: " + nombre + " | Especialidad: " + especialidad;
    }

    // Método que suma toda la matriz de notas
    public int calcularPuntuacionTotal() {
        int total = 0;
        for (int i = 0; i < notasSemanales.length; i++) {
            for (int j = 0; j < notasSemanales[i].length; j++) {
                total += notasSemanales[i][j];
            }
        }
        return total;
    }

    // Método que devuelve la media de las 3 asignaturas de una semana concreta
    public double calcularMediaSemana(int semana) {
        int totalSemana = 0;
        for (int j = 0; j < notasSemanales[semana].length; j++) {
            totalSemana += notasSemanales[semana][j];
        }
        double media = totalSemana / notasSemanales[semana].length;
        return media;
    }

    // Método que imprime la matriz
    public void mostrarNotasSemanales() {
        System.out.println("Notas semanales:");
        for (int i = 0; i < notasSemanales.length; i++) {
            System.out.println("Semana " + i + " - Combate: " + notasSemanales[i][0] + ", Captura: " + notasSemanales[i][1] + ", Cuidado: " + notasSemanales[i][2]);
        }
    }

    // Método abstracto
    public abstract double calcularNota();
}