package POO;

public class Cuenta {
    private double saldo;
    private int contadorIngresos;
    private int contadorReintegros;

    public Cuenta (double saldoInicial) {
        if (saldoInicial >= 0) {
            this.saldo = saldoInicial;
		} else {
			this.saldo = 0;
        }      
        this.contadorIngresos = 0;
        this.contadorReintegros = 0;
    }

    public void reintegro(double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
		this.saldo -= cantidad;
        this.contadorReintegros++;
    }
}
    public void ingreso(double cantidad) {
        if (cantidad >= 0) {
        this.saldo += cantidad;
        this.contadorIngresos++;
    }
}

    public double getSaldo() {
        return this.saldo;
    }

    public double contadorIngreso() {
    	return this.contadorIngresos;
    }
    
    public double contadorReintegro() {
    	return this.contadorReintegros; 	  
    }
    
}


  
    
