package CorreccionSimulacroTema5;

public class Administrativo extends Empleado {
    private int documentosTramitados;

    public Administrativo(String id, String nombre, EnumDepartamento departamento, double[][] horasTrabajadas, int documentosTramitados) {
        super(id, nombre, departamento, horasTrabajadas);
        this.documentosTramitados = documentosTramitados;
    }
    // Getter
    public int getDocumentosTramitados(){ return documentosTramitados; }
    // Setter
    public void setDocumentosTramitados(int documentosTramitados){ this.documentosTramitados = documentosTramitados; }

    // Método calcular productividad de Administrativo
    @Override
    public double calcularProductividad(){
        return calcularHorasTotalesSemana() + documentosTramitados*0.5;
    }
    // Método toString que muestra todos los atributos sobre la el objeto de Administrativo
    @Override
    public String toString(){
        return super.toString() + ", Tipo: Administrativo, Documentos tramitados: " + documentosTramitados;
    }

    // Método booleano merece reconocimiento
    @Override
    public boolean mereceReconocimiento() {
        return calcularProductividad() >= 40;
    }
}
