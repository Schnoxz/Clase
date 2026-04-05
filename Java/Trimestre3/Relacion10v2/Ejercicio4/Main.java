package Relacion10v2.Ejercicio4;

public class Main {

    public static void main(String[] args) {
        try {
            // Creamos los personajes
            Mago magoA    = new Mago("Gandalf", "humano", 10, 18, 80);
            Mago magoB    = new Mago("Saruman", "elfo",   8, 20, 70);
            Clerigo clerigo = new Clerigo("Benedictus", "humano", 18, 14, 90, "Paladinus");

            // Imprimimos datos iniciales
            separador("ESTADO INICIAL");
            System.out.println(magoA);
            System.out.println();
            System.out.println(magoB);
            System.out.println();
            System.out.println(clerigo);

            // Mago A aprende 2 hechizos
            separador("APRENDIZAJE DE HECHIZOS");
            magoA.aprendeHechizo("Bola de fuego");
            magoA.aprendeHechizo("Rayo de hielo");

            // Mago B aprende 1 hechizo
            magoB.aprendeHechizo("Tormenta arcana");

            // Imprimimos datos de los magos tras aprender
            separador("MAGOS TRAS APRENDER HECHIZOS");
            System.out.println(magoA);
            System.out.println();
            System.out.println(magoB);

            // Mago A lanza hechizo sobre Mago B
            separador("COMBATE");
            magoA.lanzaHechizo("Bola de fuego", magoB);

            // Mago B lanza hechizo sobre Mago A
            magoB.lanzaHechizo("Tormenta arcana", magoA);

            // Clérigo cura al Mago B
            separador("CURACIÓN");
            clerigo.curar(magoB);

            // Mago A lanza otro hechizo sobre Mago B
            separador("SEGUNDO ATAQUE");
            magoA.lanzaHechizo("Rayo de hielo", magoB);

            // Estado final de los tres personajes
            separador("ESTADO FINAL");
            System.out.println(magoA);
            System.out.println();
            System.out.println(magoB);
            System.out.println();
            System.out.println(clerigo);

        } catch (PersonajeException e) {
            System.out.println("Error de personaje: " + e.getMessage());
        }
    }

    // Método auxiliar para separar secciones visualmente
    private static void separador(String titulo) {
        System.out.println("\n══════════════ " + titulo + " ══════════════");
    }
}
