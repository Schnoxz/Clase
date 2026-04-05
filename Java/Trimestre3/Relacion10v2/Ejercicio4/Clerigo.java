package Relacion10v2.Ejercicio4;

public class Clerigo extends Personaje {

    private String nombreDios;

    // Constructor — fuerza >= 18, inteligencia entre 12 y 16
    public Clerigo(String nombre, String raza, int fuerza, int inteligencia,
                   int puntosVidaMax, String nombreDios) throws PersonajeException {
        super(nombre, raza, fuerza, inteligencia, puntosVidaMax);
        if (fuerza < 18) {
            throw new PersonajeException("Un Clérigo debe tener fuerza mínima de 18.");
        }
        if (inteligencia < 12 || inteligencia > 16) {
            throw new PersonajeException("Un Clérigo debe tener inteligencia entre 12 y 16.");
        }
        this.nombreDios = nombreDios;
    }

    public String getNombreDios() { return nombreDios; }
    public void setNombreDios(String nombreDios) { this.nombreDios = nombreDios; }

    // Cura a un personaje aumentando 10 puntos de vida sin superar el máximo
    public void curar(Personaje objetivo) throws PersonajeException {
        int nuevaVida = objetivo.getPuntosVidaActual() + 10;
        if (nuevaVida > objetivo.getPuntosVidaMax()) {
            nuevaVida = objetivo.getPuntosVidaMax(); // no supera el máximo
        }
        objetivo.setPuntosVidaActual(nuevaVida);
        System.out.println(getNombre() + " curó a " + objetivo.getNombre()
                + ". Vida actual: " + objetivo.getPuntosVidaActual());
    }

    @Override
    public String toString() {
        return "[CLÉRIGO] " + super.toString()
                + "\n  Dios: " + nombreDios;
    }
}
