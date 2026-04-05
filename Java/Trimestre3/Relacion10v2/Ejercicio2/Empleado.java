package Relacion10v2.Ejercicio2;
public class Empleado {
    private String nombre;
    private double sueldo;
    protected double sueldoMax; // cada subclase define el suyo

    public Empleado(String nombre, double sueldo, double sueldoMax) {
        this.nombre = nombre;
        this.sueldoMax = sueldoMax;
        setSueldo(sueldo);
    }

    public String getNombre()  { return nombre; }
    public double getSueldo()  { return sueldo; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public void setSueldo(double sueldo) {
        if (sueldo < 0) {
            System.out.println("El sueldo no puede ser negativo.");
        } else if (sueldo > sueldoMax) {
            System.out.printf("El sueldo no puede superar %.2f€. Se establece al máximo.%n", sueldoMax);
            this.sueldo = sueldoMax;
        } else {
            this.sueldo = sueldo;
        }
    }

    @Override
    public String toString() {
        return String.format("Nombre: %s | Sueldo: %.2f€", nombre, sueldo);
    }
}

// ─────────────────────────────────────────────
// Operario — sueldo máximo 1200€, nave 1-5
// ─────────────────────────────────────────────
class Operario extends Empleado {
    private int nave;
    private static final double SUELDO_MAX = 1200;

    public Operario(String nombre, double sueldo, int nave) {
        super(nombre, sueldo, SUELDO_MAX);
        setNave(nave);
    }

    public int getNave() { return nave; }

    public void setNave(int nave) {
        if (nave < 1 || nave > 5) {
            System.out.println("La nave debe estar entre 1 y 5.");
        } else {
            this.nave = nave;
        }
    }

    @Override
    public String toString() {
        return "[Operario] " + super.toString() + " | Nave: " + nave;
    }
}

class Informatico extends Empleado {
    private String especialidad; // DESARROLLO, SISTEMAS, BD
    private static final double SUELDO_MAX = 2500;

    public Informatico(String nombre, double sueldo, String especialidad) {
        super(nombre, sueldo, SUELDO_MAX);
        setEspecialidad(especialidad);
    }

    public String getEspecialidad() { return especialidad; }

    public void setEspecialidad(String especialidad) {
        if (!especialidad.equals("DESARROLLO") && !especialidad.equals("SISTEMAS")
                && !especialidad.equals("BD")) {
            System.out.println("Especialidad inválida. Debe ser DESARROLLO, SISTEMAS o BD.");
        } else {
            this.especialidad = especialidad;
        }
    }

    @Override
    public String toString() {
        return "[Informático] " + super.toString() + " | Especialidad: " + especialidad;
    }
}
class Directivo extends Empleado {
    private String departamento;
    private static final double SUELDO_MAX = 3500;

    public Directivo(String nombre, double sueldo, String departamento) {
        super(nombre, sueldo, SUELDO_MAX);
        this.departamento = departamento;
    }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    @Override
    public String toString() {
        return "[Directivo] " + super.toString() + " | Departamento: " + departamento;
    }
}



