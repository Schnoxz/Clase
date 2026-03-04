package SimulacroPractico;
// Clase alumno que debe contener nombre y nota

public class Alumno implements Comparable<Alumno> {

    private String nombre;
    private double nota;

    // Constructor de la clase alumno con el NotaInvalidaException creado
    public Alumno(String nombre, double nota) throws NotaInvalidaException {
        if (nota < 0 || nota > 10) {
            throw new NotaInvalidaException(nota);
        }
        this.nombre = nombre;
        this.nota = nota;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public double getNota() {
        return nota;
    }

    // setters
    public void setNota(double nota) throws NotaInvalidaException {
        if (nota < 0 || nota > 10) {
            throw new NotaInvalidaException(nota);
        }
        this.nota = nota;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // clase comparable que ordena las notas de menor a mayor
    @Override
    public int compareTo(Alumno a) {
        if (this.nota < a.nota) {
            return -1;
        } else if (this.nota > a.nota) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return nombre + " -> " + nota;
    }
}
