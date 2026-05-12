package modelo;
import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private String ciudad;
    private Entrenador entrenador;
    private Jugador[] titulares = new Jugador[5];
    private List<Jugador> suplentes = new ArrayList<>();
    private Double presupuesto;
    private Integer victorias = 0;
    private Integer derrotas = 0;
    private Integer puntosAFavor = 0;
    private Integer puntosEnContra = 0;

    public Equipo(String nombre, String ciudad, Double presupuesto) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.presupuesto = presupuesto;
    }

	// Getters
    public String getNombre() { return nombre; }
    public String getCiudad() { return ciudad; }
    public Entrenador getEntrenador() { return entrenador; }
    public Jugador[] getTitulares() { return titulares; }
    public List<Jugador> getSuplentes() { return suplentes; }
    public Double getPresupuesto() { return presupuesto; }
    public Integer getVictorias() { return victorias; }
    public Integer getDerrotas() { return derrotas; }
    public Integer getPuntosAFavor() { return puntosAFavor; }
    public Integer getPuntosEnContra() { return puntosEnContra; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public void setEntrenador(Entrenador entrenador) { this.entrenador = entrenador; }
    public void setPresupuesto(Double presupuesto) { this.presupuesto = presupuesto; }

	// Método que actuaría como setter conjunto para victorias, derrotas, puntosAFavor y puntosEnContra
    // Actualiza las estadísticas del equipo tras acabar un partido, lo he juntado todo en uno con este metodo y asi no se escapa nada que se deba calcular desde fuera
    public void registrarResultadoPartido(boolean victoria, Integer puntosAFavor, Integer puntosEnContra) {
		// Si el resultado es victoria, se suma 1 a victorias, si no se suma 1 a derrotas
        if (victoria) {
            this.victorias++;
        } else {
            this.derrotas++;
        }
		// Se suman los puntos al atributo correspondiente
        this.puntosAFavor += puntosAFavor;
        this.puntosEnContra += puntosEnContra;
    }

    // Añade un jugador a la lista dinámica de suplentes
    public void añadirSuplente(Jugador jugador) { this.suplentes.add(jugador); }

    // Elimina un jugador de la lista dinámica de suplentes
    public void eliminarSuplente(Jugador jugador) {
        this.suplentes.remove(jugador);
    }

    // Método que nos permite calcular el coste total de la plantilla
    public Double calcularCostePlantilla() {
        Double total = 0.0; // Inicializo la variable a 0
		// Si existe un entrenador, al total le sumo su coste llamando al metodo calcularCosteMensual de la clase Entrenador
        if (entrenador != null) {
            total += entrenador.calcularCosteMensual();
        }
		// Recorro el array de titulares con un for each y si el jugador no es null, al total le sumo su coste llamando al metodo calcularCosteMensual de la clase Jugador
        for (Jugador j : titulares) {
            if (j != null) total += j.calcularCosteMensual();
        }
		// Lo mismo que antes pero con el array de suplentes
        for (Jugador j : suplentes) {
            total += j.calcularCosteMensual();
        }
        return total;
    }
}
