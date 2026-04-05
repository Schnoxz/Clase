package Relacion10v2.Ejercicio4;

public class Mago extends Personaje {

    private String[] hechizos = new String[4]; // máximo 4 hechizos

    // Constructor — inteligencia >= 17, fuerza <= 15, sin hechizos al inicio
    public Mago(String nombre, String raza, int fuerza, int inteligencia, int puntosVidaMax)
            throws PersonajeException {
        super(nombre, raza, fuerza, inteligencia, puntosVidaMax);
        if (inteligencia < 17) {
            throw new PersonajeException("Un Mago debe tener inteligencia mínima de 17.");
        }
        if (fuerza > 15) {
            throw new PersonajeException("Un Mago no puede tener fuerza mayor a 15.");
        }
        // Inicializamos todos los huecos del array a null (sin hechizo)
        for (int i = 0; i < hechizos.length; i++) {
            hechizos[i] = null;
        }
    }

    // Aprende un hechizo buscando el primer hueco libre
    public void aprendeHechizo(String hechizo) throws PersonajeException {
        for (int i = 0; i < hechizos.length; i++) {
            if (hechizos[i] == null) {
                hechizos[i] = hechizo;
                System.out.println(getNombre() + " aprendió el hechizo: " + hechizo);
                return;
            }
        }
        throw new PersonajeException(getNombre() + " no puede aprender más hechizos, ya tiene 4.");
    }

    // Lanza un hechizo sobre un personaje: resta 10 de vida y olvida el hechizo
    public void lanzaHechizo(String hechizo, Personaje objetivo) throws PersonajeException {
        for (int i = 0; i < hechizos.length; i++) {
            if (hechizo.equals(hechizos[i])) {
                // Aplica el daño al objetivo
                int nuevaVida = objetivo.getPuntosVidaActual() - 10;
                if (nuevaVida < 0) nuevaVida = 0; // la vida no puede ser negativa
                objetivo.setPuntosVidaActual(nuevaVida);

                // Olvida el hechizo
                hechizos[i] = null;
                System.out.println(getNombre() + " lanzó " + hechizo
                        + " sobre " + objetivo.getNombre()
                        + ". Vida restante de " + objetivo.getNombre()
                        + ": " + objetivo.getPuntosVidaActual());
                return;
            }
        }
        throw new PersonajeException(getNombre() + " no conoce el hechizo: " + hechizo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[MAGO] ").append(super.toString());
        sb.append("\n  Hechizos: ");
        boolean tieneHechizos = false;
        for (String h : hechizos) {
            if (h != null) {
                sb.append(h).append(" | ");
                tieneHechizos = true;
            }
        }
        if (!tieneHechizos) sb.append("ninguno");
        return sb.toString();
    }
}
