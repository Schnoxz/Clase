package CorreccionSimulacroTema5;

public class Programador extends Empleado {
    private int incidenciasResueltas;

    public Programador(String id, String nombre, EnumDepartamento departamento, double[][] horasTrabajadas, int incidenciasResueltas) {
        super(id, nombre, departamento, horasTrabajadas);
        this.incidenciasResueltas = incidenciasResueltas;
    }

    // Getter
    public int getIncidencicasResueltas() { return incidenciasResueltas; }

    // Método calcular productividad de Programador
    @Override
    public double calcularProductividad() {
        return calcularHorasTotalesSemana() + incidenciasResueltas*2;
    }

    // Método merece reconocimiento
    @Override
    public boolean mereceReconocimiento() {
        return calcularProductividad() >= 45;
    }

    // Método toString
    @Override
    public String toString() {
        return super.toString() + ", Tipo: Programador, Incidencias resueltas: " + incidenciasResueltas;
    }
}
