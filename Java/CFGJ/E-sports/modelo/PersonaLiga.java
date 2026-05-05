
abstract public class PersonaLiga {
// Atributos de la clase PersonaLiga
    private String identificador;
    private String nombre;
    private String nickname;
    private int edad;
    private Double salarioBase;

    public PersonaLiga(String identificador, String nombre, String nickname, int edad, double salarioBase) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.nickname = nickname;
        this.edad = edad;
        this.salarioBase = salarioBase;
    }

    // Getters
    public String getIdentificador() { return identificador; } // Solo getter porque no se suele cambiar un id tras ser creado
	public String getNombre() { return nombre; }
	public String getNickname() { return nickname; }
	public int getEdad() { return edad; }
	public double getSalarioBase() { return salarioBase; }
	// Setters
	public void setNombre(String nombre) { this.nombre = nombre; }
	public void setNickname(String nickname) { this.nickname = nickname; }
	public void setEdad(int edad) { this.edad = edad; }
	public void setSalarioBase(double salarioBase) { this.salarioBase = salarioBase; }


    // Métodos necesarios ( mostrarResumen(), calcularCosteMensual(), toString() )
    // Método toString que nos muestra todos los atributos de la clase, en este caso el identificador, el nombre, el nickname, la edad y el salario base
    @Override
    public String toString() {
        return "PersonaLiga{" + "identificador=" + identificador + ", nombre=" + nombre + ", nickname=" + nickname + ", edad=" + edad + ", salarioBase=" + salarioBase + '}';
    }

    // Método mostrarResumen que nos muestra todos los atributos de la clase en este caso el identificador, el nombre, el nickname, la edad y el salario base
    public void mostrarResumen() {
        System.out.println("Identificador: " + identificador);
        System.out.println("Nombre: " + nombre);
        System.out.println("Nickname: " + nickname);
        System.out.println("Edad: " + edad);
        System.out.println("Salario Base: " + salarioBase);
    }

    // Método de calcular el coste mensual de forma abstracta
    public abstract Double calcularCosteMensual();
}
