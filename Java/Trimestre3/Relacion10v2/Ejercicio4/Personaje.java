package Relacion10v2.Ejercicio4;

public class Personaje {

    private String nombre;
    private String raza;          // "humano", "elfo", "enano", "orco"
    private int fuerza;           // 0-20
    private int inteligencia;     // 0-20
    private int puntosVidaMax;    // 0-100
    private int puntosVidaActual; // 0-puntosVidaMax

    // Constructor — los puntos de vida actuales empiezan igual que los máximos
    public Personaje(String nombre, String raza, int fuerza, int inteligencia, int puntosVidaMax)
            throws PersonajeException {
        this.nombre = nombre;
        setRaza(raza);
        setFuerza(fuerza);
        setInteligencia(inteligencia);
        setPuntosVidaMax(puntosVidaMax);
        this.puntosVidaActual = puntosVidaMax;
    }

    // Getters
    public String getNombre()         { return nombre; }
    public String getRaza()           { return raza; }
    public int getFuerza()            { return fuerza; }
    public int getInteligencia()      { return inteligencia; }
    public int getPuntosVidaMax()     { return puntosVidaMax; }
    public int getPuntosVidaActual()  { return puntosVidaActual; }

    // Setters con validación
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRaza(String raza) throws PersonajeException {
        if (!raza.equals("humano") && !raza.equals("elfo")
                && !raza.equals("enano") && !raza.equals("orco")) {
            throw new PersonajeException("Raza inválida: " + raza
                    + ". Debe ser humano, elfo, enano u orco.");
        }
        this.raza = raza;
    }

    public void setFuerza(int fuerza) throws PersonajeException {
        if (fuerza < 0 || fuerza > 20) {
            throw new PersonajeException("La fuerza debe estar entre 0 y 20.");
        }
        this.fuerza = fuerza;
    }

    public void setInteligencia(int inteligencia) throws PersonajeException {
        if (inteligencia < 0 || inteligencia > 20) {
            throw new PersonajeException("La inteligencia debe estar entre 0 y 20.");
        }
        this.inteligencia = inteligencia;
    }

    public void setPuntosVidaMax(int puntosVidaMax) throws PersonajeException {
        if (puntosVidaMax < 0 || puntosVidaMax > 100) {
            throw new PersonajeException("Los puntos de vida máximos deben estar entre 0 y 100.");
        }
        this.puntosVidaMax = puntosVidaMax;
    }

    public void setPuntosVidaActual(int puntosVidaActual) throws PersonajeException {
        if (puntosVidaActual < 0 || puntosVidaActual > puntosVidaMax) {
            throw new PersonajeException("Los puntos de vida actuales deben estar entre 0 y "
                    + puntosVidaMax + ".");
        }
        this.puntosVidaActual = puntosVidaActual;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre
                + "\n  Raza: " + raza
                + "\n  Fuerza: " + fuerza
                + "\n  Inteligencia: " + inteligencia
                + "\n  Vida: " + puntosVidaActual + "/" + puntosVidaMax;
    }
}
