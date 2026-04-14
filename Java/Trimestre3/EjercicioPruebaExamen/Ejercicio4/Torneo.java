package EjercicioPruebaExamen.Ejercicio4;

public abstract class Torneo implements Clasificable {

    protected String codigo;
    protected String nombre;
    protected Region region;
    protected int numParticipantes;
    protected int[][] puntosPorRonda; // 4x2, filas 0-3 son las rondas. Columna 0 (Individual) Columna 1 (Dobles)

    // Clase constructor Torneo - lanza excepciones si los datos no son validos
    public Torneo(String codigo, String nombre, Region region, int numParticipantes, int[][] puntosPorRonda)
            throws PuntosNegativosException, ParticipantesInsuficientesException {

        // Comprobamos que haya suficientes participantes
        if (numParticipantes < 2) {
            throw new ParticipantesInsuficientesException(
                    "Participantes insuficientes: hay " + numParticipantes + " y se necesitan al menos 2");
        }

        // Comprobamos que no haya puntos negativos en la matriz
        for (int i = 0; i < puntosPorRonda.length; i++) {
            for (int j = 0; j < puntosPorRonda[i].length; j++) {
                if (puntosPorRonda[i][j] < 0) {
                    throw new PuntosNegativosException(
                            "Puntuacion negativa en ronda " + i + ", columna " + j + ": valor " + puntosPorRonda[i][j]);
                }
            }
        }

        this.codigo = codigo;
        this.nombre = nombre;
        this.region = region;
        this.numParticipantes = numParticipantes;
        this.puntosPorRonda = puntosPorRonda;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public Region getRegion() {
        return region;
    }

    public int getNumParticipantes() {
        return numParticipantes;
    }

    public int[][] getPuntosPorRonda() {
        return puntosPorRonda;
    }

    // toString
    @Override
    public String toString() {
        return "Codigo: " + codigo + " | Nombre: " + nombre + " | Region: " + region
                + " | Participantes: " + numParticipantes;
    }

    // Suma toda la matriz
    public int calcularPuntosTotal() {
        int total = 0;
        for (int i = 0; i < puntosPorRonda.length; i++) {
            for (int j = 0; j < puntosPorRonda[i].length; j++) {
                total += puntosPorRonda[i][j];
            }
        }
        return total;
    }

    // Suma individual + dobles de una ronda concreta
    public int calcularPuntosRonda(int ronda) {
        if (ronda < 0 || ronda > 3) {
            throw new RondaInvalidaException("Ronda invalida: " + ronda + ". Debe estar entre 0 y 3");
        }
        return puntosPorRonda[ronda][0] + puntosPorRonda[ronda][1];
    }

    // Muestra la tabla de puntuaciones
    public void mostrarPuntuaciones() {
        System.out.println("Puntuaciones por ronda:");
        for (int i = 0; i < puntosPorRonda.length; i++) {
            System.out.println("Ronda " + i + " - Individual: " + puntosPorRonda[i][0]
                    + ", Dobles: " + puntosPorRonda[i][1]);
        }
    }

    // Metodo abstracto
    public abstract double calcularPuntuacionFinal();
}
