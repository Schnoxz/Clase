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
