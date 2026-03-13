package src.Ejercicio3;

public class Falta {

	private int justificadas;
	private int injustificadas;
	private int retrasos;

	public Falta(int justificadas, int injustificadas, int retrasos) {
		this.justificadas = justificadas;
		this.injustificadas = injustificadas;
		this.retrasos = retrasos;
	}

	public int getJustificadas() {
		return justificadas;
	}

	public int getInjustificadas() {
		return injustificadas;
	}

	public int getRetrasos() {
		return retrasos;
	}
}