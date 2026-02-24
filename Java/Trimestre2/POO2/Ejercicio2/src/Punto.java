
public class Punto {

    private int x;
    private int y;

    // Consutructor de punto
    public Punto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters y setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    //Metodo que imprime el punto
    public String toString() {
        return x + "," + y;
    }
}
