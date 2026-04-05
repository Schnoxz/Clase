package Objetos.HerenciaAbstracción.Ejercicio1;

public class Coche extends Vehiculo {
    private int numPuertas;
    private double deposito; // litros

    public Coche(String marca, String modelo, int año, int numPuertas, double deposito) {
        super(marca, modelo, año); // primero el padre
        this.numPuertas = numPuertas;
        this.deposito = deposito;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo(); // reutiliza el del padre
        System.out.println("Puertas: " + numPuertas);
    }

    @Override
    public void calcularAutonomia() {
        // Fórmula simple: 12 km por litro
        double autonomia = deposito * 12;
        System.out.println("Autonomía gasolina: " + autonomia + " km");
    }
}
