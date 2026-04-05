package Objetos.HerenciaAbstracción.Ejercicio1;

public class CocheElectrico extends Coche {
    private double capacidadBateria; // kWh

    public CocheElectrico(String marca, String modelo, int año, int numPuertas, double capacidadBateria) {
        super(marca, modelo, año, numPuertas, 0); // deposito = 0, no usa gasolina
        this.capacidadBateria = capacidadBateria;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Batería: " + capacidadBateria + " kWh");
    }

    @Override
    public void calcularAutonomia() {
        // Fórmula simple: 6 km por kWh
        double autonomia = capacidadBateria * 6;
        System.out.println("Autonomía eléctrica: " + autonomia + " km");
    }
}
