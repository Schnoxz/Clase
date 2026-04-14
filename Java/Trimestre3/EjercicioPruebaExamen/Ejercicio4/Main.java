package EjercicioPruebaExamen.Ejercicio4;

public class Main {

    public static void main(String[] args) {

        // Matrices 4x2 (rondas x modalidad)
        int[][] t1 = {{8, 5}, {6, 4}, {9, 7}, {5, 6}};
        int[][] t2 = {{4, 6}, {7, 5}, {6, 8}, {4, 5}};
        int[][] t3 = {{5, 3}, {6, 4}, {7, 5}, {4, 6}};
        int[][] t4 = {{3, 4}, {5, 6}, {4, 3}, {6, 5}};

        // Array de Torneo con 4 posiciones
        Torneo[] torneos = new Torneo[4];

        // Creacion con try-catch por separado para cada excepcion
        try {
            torneos[0] = new TorneoOficial("T01", "Torneo Kanto", Region.KANTO, 8, t1, 5);
            torneos[1] = new TorneoOficial("T02", "Torneo Johto", Region.JOHTO, 6, t2, 3);
            torneos[2] = new TorneoAmistoso("T03", "Copa Hoenn", Region.HOENN, 4, t3, 10);
            torneos[3] = new TorneoAmistoso("T04", "Copa Sinnoh", Region.SINNOH, 5, t4, 7);
        } catch (PuntosNegativosException e) {
            System.out.println("Error en puntuaciones: " + e.getMessage());
        } catch (ParticipantesInsuficientesException e) {
            System.out.println("Error en participantes: " + e.getMessage());
        }

        // Recorrido general
        System.out.println("--- TORNEOS POKEMON ---");

        for (Torneo t : torneos) {
            System.out.println("\n" + t.toString());
            t.mostrarPuntuaciones();
            System.out.println("Puntos totales: " + t.calcularPuntosTotal());

            // try-catch para RondaInvalidaException al llamar a calcularPuntosRonda
            try {
                System.out.println("Puntos ronda 0: " + t.calcularPuntosRonda(0));
            } catch (RondaInvalidaException e) {
                System.out.println("Error de ronda: " + e.getMessage());
            }

            System.out.println("Puntuacion final: " + t.calcularPuntuacionFinal());

            if (t.esFinalistaLiga()) {
                System.out.println("Es finalista de liga");
            } else {
                System.out.println("No es finalista de liga");
            }
        }

        // Estadisticas finales
        System.out.println("\n--- ESTADISTICA FINAL ---");
        torneoMayorPuntuacion(torneos);
        contadorTorneos(torneos);
        mostrarMediaPorModalidad(torneos);
        mejorRondaGlobal(torneos);

        // Prueba de excepciones
        System.out.println("\n--- PRUEBA DE EXCEPCIONES ---");

        // Prueba con puntuacion negativa
        try {
            int[][] matrizMala = {{-3, 5}, {6, 4}, {7, 5}, {4, 6}};
            Torneo torneoMalo = new TorneoOficial("ERR1", "Torneo Malo", Region.KANTO, 4, matrizMala, 2);
        } catch (PuntosNegativosException e) {
            System.out.println("Excepcion capturada: " + e.getMessage());
        } catch (ParticipantesInsuficientesException e) {
            System.out.println("Excepcion capturada: " + e.getMessage());
        }

        // Prueba con participantes insuficientes
        try {
            int[][] matrizOk = {{3, 5}, {6, 4}, {7, 5}, {4, 6}};
            Torneo torneoVacio = new TorneoAmistoso("ERR2", "Torneo Vacio", Region.JOHTO, 1, matrizOk, 3);
        } catch (PuntosNegativosException e) {
            System.out.println("Excepcion capturada: " + e.getMessage());
        } catch (ParticipantesInsuficientesException e) {
            System.out.println("Excepcion capturada: " + e.getMessage());
        }

        // Prueba con ronda invalida
        try {
            torneos[0].calcularPuntosRonda(99);
        } catch (RondaInvalidaException e) {
            System.out.println("Excepcion capturada: " + e.getMessage());
        }
    }

    // Torneo con mayor puntuacion final
    public static void torneoMayorPuntuacion(Torneo[] torneos) {
        Torneo mejorTorneo = torneos[0];
        for (Torneo t : torneos) {
            if (t.calcularPuntuacionFinal() > mejorTorneo.calcularPuntuacionFinal()) {
                mejorTorneo = t;
            }
        }
        System.out.println("Torneo con mayor puntuacion: " + mejorTorneo.getNombre()
                + " (" + mejorTorneo.calcularPuntuacionFinal() + ")");
    }

    // Conteo por tipo con instanceof
    public static void contadorTorneos(Torneo[] torneos) {
        int numOficial = 0;
        int numAmistoso = 0;
        for (Torneo t : torneos) {
            if (t instanceof TorneoOficial) {
                numOficial++;
            }
            if (t instanceof TorneoAmistoso) {
                numAmistoso++;
            }
        }
        System.out.println("Torneos Oficiales: " + numOficial);
        System.out.println("Torneos Amistosos: " + numAmistoso);
    }

    // Media de puntos por modalidad (columna 0 y columna 1)
    public static void mostrarMediaPorModalidad(Torneo[] torneos) {
        int individual = 0;
        int dobles = 0;

        for (Torneo t : torneos) {
            int[][] reg = t.getPuntosPorRonda();
            for (int i = 0; i < reg.length; i++) {
                individual += reg[i][0];
                dobles += reg[i][1];
            }
        }

        System.out.println("Media Individual: " + (double) individual / torneos.length);
        System.out.println("Media Dobles    : " + (double) dobles / torneos.length);
    }

    // Ronda con mas puntos sumando todos los torneos
    public static void mejorRondaGlobal(Torneo[] torneos) {
        int mejorRonda = 0;
        int maxPuntos = 0;

        for (int ronda = 0; ronda < 4; ronda++) {
            int totalRonda = 0;
            for (Torneo t : torneos) {
                try {
                    totalRonda += t.calcularPuntosRonda(ronda);
                } catch (RondaInvalidaException e) {
                    System.out.println("Error de ronda: " + e.getMessage());
                }
            }
            if (totalRonda > maxPuntos) {
                maxPuntos = totalRonda;
                mejorRonda = ronda;
            }
        }

        System.out.println("Ronda con mas puntos: Ronda " + mejorRonda + " con " + maxPuntos + " puntos en total");
    }
}
