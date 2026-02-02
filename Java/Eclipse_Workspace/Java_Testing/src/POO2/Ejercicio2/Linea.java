package POO2.Ejercicio2;

public class Linea {
    private Punto puntoA;
    private Punto puntoB;

    // Constructor con excepcion de si son iguales los puntos
    public Linea (Punto puntoA, Punto puntoB) throws Exception{
        // Si son iguales x e y
        if (puntoA.getX() == puntoB.getX() && puntoA.getY() == puntoB.getY()){
            throw new Exception("Puntos A y B deben ser distintos, no iguales");
        }

        this.puntoA = puntoA;
        this.puntoB = puntoB;
    }

    // Getters y setters 
    public Punto getPuntoA(){
        return puntoA;
    }
    
    public void setPuntoA(Punto puntoA){
        this.puntoA = puntoA;
    }
    
    public Punto getPuntoB(){
        return puntoB;
    }
    
    public void setPuntoB(Punto puntoA){
        this.puntoB = puntoA;
    }

    // Metodos para los movimientos izq, der, arriba y abajo

    public void moverIzq(int distancia){
        puntoA.setX(puntoA.getX() - distancia);
        puntoB.setX(puntoB.getX() - distancia);
    }

    public void moverDer(int distancia){
        puntoA.setX(puntoA.getX() + distancia);
        puntoB.setX(puntoB.getX() + distancia); 
    }

    public void moverArriba(int distancia){
        puntoA.setY(puntoA.getY() + distancia);
        puntoB.setY(puntoB.getY() + distancia);
    }

    public void moverAbajo(int distancia){
        puntoA.setY(puntoA.getY() - distancia);
        puntoB.setY(puntoB.getY() - distancia);
    }

    // Metodo que nos comprueba si los puntos son diferentes entre si A.x con 2A.x, B.x con 2B.x etc
    public boolean iguales (Linea Linea2) {
        // Se compara X e Y con A y B
        if (this.puntoA.getX() == Linea2.puntoA.getX() && this.puntoB.getX() == Linea2.puntoB.getX() &&
            this.puntoA.getY() == Linea2.puntoA.getY() && this.puntoB.getY() == Linea2.puntoB.getY()){
            return true;
        }
        return false;
    }
    // Metodo que recoge "toString" de la clase punto y nos lo devuelve en una linea de texto para visualizar 
    public String verLinea(){
        return "[" + puntoA.toString() + "]" + " " + "[" + puntoB.toString() + "]";
    }
}


