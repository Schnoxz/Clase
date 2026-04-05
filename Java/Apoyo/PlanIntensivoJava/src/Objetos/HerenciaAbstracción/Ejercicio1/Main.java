package Objetos.HerenciaAbstracción.Ejercicio1;

public class Main {

    public static void main(String[] args) {

        Vehiculo[] vehiculos = new Vehiculo[3];
        vehiculos[0] = new Coche("Toyota", "Corolla", 2020, 4, 50);
        vehiculos[1] = new Moto("Honda", "CB500", 2022, false, 17);
        vehiculos[2] = new CocheElectrico("Tesla", "Model 3", 2023, 4, 75);

        for (Vehiculo v : vehiculos) {
            System.out.println("--- " + v.getMarca() + " " + v.getModelo() + " ---");
            v.mostrarInfo();
            v.calcularAutonomia();
            v.acelerar(50);
            System.out.println();
        }
    }
}
