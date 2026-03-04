package SimulacroPractico;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        Alumno[] alumnos = pedirAlumno(5);
        mostrarLista("Lista original", alumnos);
        Arrays.sort(alumnos);
        mostrarMejorAlumno(alumnos);
        subirNota(alumnos, 1);
        mostrarLista("Lista final (+1 punto)", alumnos);
    }

    // ── Pide por teclado los datos de N alumnos ──────────────
    public static Alumno[] pedirAlumno(int n) {
        Alumno[] alumnos = new Alumno[n];
        for (int i = 0; i < alumnos.length; i++) {
            System.out.print("Nombre del alumno " + (i + 1) + ": ");
            String nombre = teclado.nextLine();
            alumnos[i] = pedirNotaAlumno(nombre);
        }
        return alumnos;
    }

    // ── Pide la nota con validación, repite si es inválida ───
    public static Alumno pedirNotaAlumno(String nombre) {
        Alumno alumno = null;
        while (alumno == null) {
            try {
                System.out.print("Nota de " + nombre + " (0-10): ");
                double nota = Double.parseDouble(teclado.nextLine());
                alumno = new Alumno(nombre, nota);
            } catch (NotaInvalidaException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Error: introduce un número válido.");
            }
        }
        return alumno;
    }

    // ── Muestra la lista de alumnos con un título ─────────────
    public static void mostrarLista(String titulo, Alumno[] alumnos) {
        System.out.println("\n-- " + titulo + " --");
        for (int i = 0; i < alumnos.length; i++) {
            System.out.println(alumnos[i]);
        }
    }

    // ── Muestra el alumno con la nota más alta ────────────────
    public static void mostrarMejorAlumno(Alumno[] alumnos) {
        Alumno mejor = alumnos[alumnos.length - 1];
        System.out.println("\nMejor alumno: " + mejor.getNombre() + " con un " + mejor.getNota());
    }

    // ── Sube la nota de todos los alumnos sin superar 10 ─────
    public static void subirNota(Alumno[] alumnos, double puntos) {
        for (int i = 0; i < alumnos.length; i++) {
            double nuevaNota = alumnos[i].getNota() + puntos;
            if (nuevaNota > 10) {
                nuevaNota = 10;
            }
            try {
                alumnos[i].setNota(nuevaNota);
            } catch (NotaInvalidaException e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
    }
}
