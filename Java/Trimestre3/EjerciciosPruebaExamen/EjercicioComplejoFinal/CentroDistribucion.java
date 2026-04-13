package EjerciciosPruebaExamen.EjercicioComplejoFinal;

public class CentroDistribucion extends CentroOperativo {
    private int paquetesUrgentes;

    public CentroDistribucion(String codigo, Zona zona, Responsable responsable, int[][] operaciones, int[][] incidencias, int paquetesUrgentes) {
        super(codigo, zona, responsable, operaciones, incidencias);
        this.paquetesUrgentes = paquetesUrgentes;
    }

    // Getter
    public int getPaquetesUrgentes() { return paquetesUrgentes; }

    // Método heredad calcularIndiceEficiencia()
    @Override
    public double calcularIndiceEficiencia() { return calcularTotalOperaciones() - calcularTotalIncidencias() + paquetesUrgentes * 1.5; }

    // Método abstracto necesitaAuditoria()
    @Override
    public boolean necesitaAuditoria() {
        if (calcularTasaIncidencias() > 20) {
            return true;
        }

        for (int dia = 0; dia < 5; dia++) {
            if (calcularOperacionesDia(dia) < 8) {
                return true;
            }
        }

        if (calcularTotalIncidencias() > 15) {
            return true;
        }

        return false;
    }
    
    // Método String heredado del padre
    @Override
    public String toString(){
        return super.toString() + "Tipo: Centro Distribucion | Paquetes Urgentes: " + paquetesUrgentes;
    }
}
