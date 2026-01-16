package POO;

public class Rectangulo {
 // Inicializo atributos privados en 1 dado el enunciado
    private double longitud = 1;
    private double ancho = 1;

    // Constructor del rectangulo
    public Rectangulo (double longitud, double ancho) {
    }

    // Metodos getter y setter de la longitud y el ancho
    public double getLongitud() {
        return this.longitud;
    }
    
    public void setLongitud (double Longitud) {
        if (Longitud > 0 && Longitud < 20){ 
            this.longitud = Longitud;
        } else {
            System.out.println("Error, la longitud debe ser mayor que 0 y menor que 20");
        }
        
    }

    public double getAncho() {
        return this.ancho;
    }

    public void setAncho (double Ancho){
        if (Ancho > 0 && Ancho < 20){
            this.ancho = Ancho;
        } else {
            System.out.println("Error, el ancho debe ser mayor que 0 y menor que 20");
        }
    }


    // Metodos para el calculo del area y perimetro

    public double calcularArea() {
        return this.longitud * this.ancho;
    }

    public double calcularPerimetro(){
        return 2 * (this.longitud + this.ancho);
    }
}
