package Objetos.HerenciaAbstracción.Ejercicio1;

public class Moto extends Vehiculo {
    private boolean tieneSidecar;
    private double deposito;

    public Moto(String marca, String modelo, int año, boolean tieneSidecar, double deposito) {
        super(marca, modelo, año);
        this.tieneSidecar = tieneSidecar;
        this.deposito = deposito;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Sidecar: " + (tieneSidecar ? "Sí" : "No"));
    }

    @Override
    public void calcularAutonomia() {
        // Fórmula simple: 20 km por litro
        double autonomia = deposito * 20;
        System.out.println("Autonomía moto: " + autonomia + " km");
    }
}
