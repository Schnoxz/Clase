package Objetos.HerenciaAbstracción.Ejercicio1;

public class Vehiculo {
    private String marca;
    private String modelo;
    private int año;

    public Vehiculo(String marca, String modelo, int año) {
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
    }

    public void mostrarInfo() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Año: " + año);
    }

    public void acelerar(double cantidad) {
        System.out.println(marca + " " + modelo + " acelera " + cantidad + " km/h");
    }

    public void calcularAutonomia() {
        System.out.println("Autonomía no definida");
    }

    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAño() { return año; }
}
