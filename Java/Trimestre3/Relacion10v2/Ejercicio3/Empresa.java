package Relacion10v2.Ejercicio3;

import java.util.Scanner;
abstract class Vehiculo {
    private String matricula;
    private String gama; // "baja", "media", "alta"

    // Constructor de la clase Vehículo
    public Vehiculo(String matricula, String gama) {
        this.matricula = matricula;
        this.gama = gama;
    }
    // Getters para matrícula y gama
    public String getMatricula() { return matricula; }
    public String getGama()      { return gama; }

    // Precio base según gama
    public double getPrecioBase() {
        switch (gama) {
            case "baja":  return 30;
            case "media": return 40;
            case "alta":  return 50;
            default:      return 30;
        }
    }

    // Cada subclase calcula su precio extra por día
    public abstract double getPrecioExtraPorDia();
    // El precio total de alquiler es el precio base más el extra por día multiplicado por los días de alquiler
    public double calcularPrecioAlquiler(int dias) {
        return (getPrecioBase() + getPrecioExtraPorDia()) * dias;
    }
    // Para mostrar la información del vehículo, incluyendo su matrícula y gama
    @Override
    public String toString() {
        return matricula + " [" + getClass().getSimpleName() + " - gama " + gama + "]";
    }
}


// Coche: extra por combustible
class Coche extends Vehiculo {
    private String combustible; // "gasolina" o "diesel"
    // El constructor recibe la matrícula, gama y tipo de combustible
    public Coche(String matricula, String gama, String combustible) {
        super(matricula, gama);
        this.combustible = combustible;
    }
    // El precio extra es 3.5€ por día para gasolina y 2€ para diesel
    @Override
    public double getPrecioExtraPorDia() {
        return combustible.equals("gasolina") ? 3.5 : 2.0;
    }
    // Para mostrar la información del coche, incluyendo el tipo de combustible
    @Override
    public String toString() {
        return super.toString() + " - " + combustible;
    }
}


// Microbus: extra por plaza
class Microbus extends Vehiculo {
    private int numPlazas;
    // El constructor recibe la matrícula, gama y número de plazas
    public Microbus(String matricula, String gama, int numPlazas) {
        super(matricula, gama);
        this.numPlazas = numPlazas;
    }
    // El precio extra es 5€ por cada plaza
    @Override
    public double getPrecioExtraPorDia() {
        return 5.0 * numPlazas;
    }
    // Para mostrar la información del microbús, incluyendo el número de plazas
    @Override
    public String toString() {
        return super.toString() + " - " + numPlazas + " plazas";
    }
}

// Furgoneta: por PMA
class Furgoneta extends Vehiculo {
    private double pma; // peso máximo autorizado

    public Furgoneta(String matricula, String gama, double pma) {
        super(matricula, gama);
        this.pma = pma;
    }
    // El precio extra es 0.5€ por cada kg de PMA
    @Override
    public double getPrecioExtraPorDia() {
        return 0.5 * pma;
    }
    // Para mostrar la información de la furgoneta, incluyendo su PMA
    @Override
    public String toString() {
        return super.toString() + " - PMA: " + pma + "kg";
    }
}


