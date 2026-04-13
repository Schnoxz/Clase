package EjerciciosPruebaExamen.Ejercicio3;

public class Main {
    public static void main(String[] args) {
        // Creación del array Alumno con 4 posiciones
        Alumno[] alumnos = new Alumno[4];

        // Inicialización de matrices 4x3
        int[][] al1 = {{7, 5, 8}, {6, 4, 9}, {8, 7, 6}, {5, 9, 7}};
        int[][] al2 = {{4, 6, 5}, {7, 8, 4}, {6, 5, 7}, {8, 4, 6}};
        int[][] al3 = {{9, 7, 6}, {5, 8, 9}, {7, 6, 5}, {8, 9, 7}};
        int[][] al4 = {{6, 4, 7}, {8, 5, 6}, {4, 9, 8}, {7, 6, 5}};

        // Creación de 2 AlumnoTeorico y 2 AlumnoPractico
        // String matricula, String nombre, Especialidad especialidad, int[][] notasSemanales, int atributoPropio
        alumnos[0] = new AlumnoTeorico("T01", "Ash", Especialidad.FUEGO, al1, 8);
        alumnos[1] = new AlumnoTeorico("T02", "Misty", Especialidad.AGUA, al2, 5);
        alumnos[2] = new AlumnoPractico("P01", "Brock", Especialidad.PLANTA, al3, 12);
        alumnos[3] = new AlumnoPractico("P02", "Gary", Especialidad.DRAGON, al4, 7);

        System.out.println("--- ACADEMIA DE ENTRENADORES ---");

        for (Alumno a : alumnos) {
            System.out.println("\n" + a.toString());
            a.mostrarNotasSemanales();
            System.out.println("Puntuacion total: " + a.calcularPuntuacionTotal());
            System.out.println("Nota final: " + a.calcularNota());

            if (a.obtieneCertificado()) {
                System.out.println("Obtiene el certificado");
            } else {
                System.out.println("No obtiene el certificado");
            }
        }

        // Estadísticas finales
        System.out.println("\n--- ESTADISTICA FINAL ---");
        alumnoMayorNota(alumnos);
        contadorAlumnos(alumnos);
        mostrarMediaPorAsignatura(alumnos);
        mejorSemanaGlobal(alumnos);
    }

    // Alumno con mayor nota
    public static void alumnoMayorNota(Alumno[] alumnos) {
        Alumno mejorAlumno = alumnos[0];
        for (Alumno a : alumnos) {
            if (a.calcularNota() > mejorAlumno.calcularNota()) {
                mejorAlumno = a;
            }
        }
        System.out.println("Alumno con mayor nota: " + mejorAlumno.getNombre()
                + " (" + mejorAlumno.calcularNota() + ")");
    }

    // Contador de tipos de alumnos
    public static void contadorAlumnos(Alumno[] alumnos) {
        int numTeorico = 0;
        int numPractico = 0;
        for (Alumno a : alumnos) {
            if (a instanceof AlumnoTeorico) numTeorico++;
            if (a instanceof AlumnoPractico) numPractico++;
        }
        System.out.println("Alumnos Teoricos: " + numTeorico);
        System.out.println("Alumnos Practicos: " + numPractico);
    }

    // Media por asignatura (columna 0, 1 y 2)
    public static void mostrarMediaPorAsignatura(Alumno[] alumnos) {
        int combate = 0;
        int captura = 0;
        int cuidado = 0;

        for (Alumno a : alumnos) {
            int[][] reg = a.getNotasSemanales();
            for (int i = 0; i < reg.length; i++) {
                combate += reg[i][0];
                captura += reg[i][1];
                cuidado += reg[i][2];
            }
        }
        // Casteo a double y así no hace falta declarar una variable double media
        System.out.println("Media Combate : " + (double) combate / alumnos.length);
        System.out.println("Media Captura : " + (double) captura / alumnos.length);
        System.out.println("Media Cuidado : " + (double) cuidado / alumnos.length);
    }

    // Semana con mejor media global sumando todos los alumnos
    public static void mejorSemanaGlobal(Alumno[] alumnos) {
        int mejorSemana = 0;
        double maxMedia = 0;

        for (int semana = 0; semana < 4; semana++) {
            double totalSemana = 0;
            for (Alumno a : alumnos) {
                totalSemana += a.calcularMediaSemana(semana);
            }
            if (totalSemana > maxMedia) {
                maxMedia = totalSemana;
                mejorSemana = semana;
            }
        }

        System.out.println("Semana con mejor media global: Semana " + mejorSemana + " con media de " + (maxMedia / alumnos.length));
    }
}