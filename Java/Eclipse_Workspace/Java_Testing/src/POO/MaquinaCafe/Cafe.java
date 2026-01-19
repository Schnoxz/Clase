package POO.MaquinaCafe;

public class Cafe {
    private double monedero;
    private int depositoCafe;
    private int depositoLeche;
    private int depositoVaso;

    // Constructor de una maquina de café con los atributos depositos de "Lecha, Cafe y Vasos" llenos y un atributo monedero donde se guarda el dinero de cada venta
    public Cafe (double monederoInicial) {
        this.depositoCafe = 50;
        this.depositoLeche = 50;
        this.depositoVaso = 80;
        this.monedero = monederoInicial;   
    }

    // Metodos para el funcionamiento de la maquina 
    // Primer metodo el del café solo (1€, --vaso , --cafe)
    public void servirSolo (){ 
        if (depositoCafe >= 1 && depositoVaso >= 1){
            depositoCafe --;
            depositoVaso --;
            monedero += 1;
            System.out.println("Sirviendo Café solo --> '1€' ");
        } else {
            System.out.println("No se ha podido servir, stock insuficiente");
        }
    }

    // Segundo método para servir leche (0.8€, --vaso, --leche)
    public void servirLeche (){
        if (depositoLeche >= 1 && depositoVaso >= 1){
            depositoLeche--;
            depositoVaso--;
            monedero+= 0.8;
            System.out.println("Sirviendo vaso de leche --> '0.8€' ");
        } else {
            System.out.println("No se ha podido servir, stock insuficiente");
        }
            
    }

    // Tercer método para servir café con leche (1.5€, --vaso, --leche, --cafe)
     public void servirCafeLeche (){
        if (depositoLeche >= 1 && depositoVaso >= 1 && depositoCafe >= 1){
            depositoLeche--;
            depositoVaso--;
            depositoCafe--;
            monedero+= 1.5;
            System.out.println("Sirviendo Café con leche --> '1.5€' ");
        } else {
            System.out.println("No se ha podido server, stock insuficiente");
        }
}

    // Cuarto método, recuento del monedero y depósitos restantes de la máquina cuando se consulte el estado en el menú

    public void recuentoMaquina(){
        System.out.println("Stock Café [ " + depositoCafe+ " ]");
        System.out.println("Stock Leche [ " + depositoLeche + " ]");
        System.out.println("Stock Vaso [ " + depositoVaso + " ]");
        System.out.println("Monedero [ " + monedero + " € ]");
    }
}
