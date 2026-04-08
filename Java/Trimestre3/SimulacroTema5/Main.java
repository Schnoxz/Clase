package SimulacroTema5;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	/*En la clase Principal:
a) Crea un array de Empleado con 4 posiciones.
Dentro del array debes guardar:
2 objetos Programador
2 objetos Administrativo
Esto obliga a usar polimorfismo, ya que la referencia será de tipo Empleado pero los
objetos reales serán de las subclases.
b) Inicializa manualmente las matrices de horas de cada empleado.
Ejemplo de una matriz válida:

double[][] horas1 = {
{4, 3},
{4, 4},
{5, 3},
{4, 4},
{3, 2}
};

c) Recorre el array y muestra para cada empleado:
sus datos
su horario completo
sus horas totales de la semana
su productividad
si merece reconocimiento o no*/
	public static void main(String[] args) {
		Empleado[] empleados = new Empleado[4];
s		empleados[0] = new Programador("Ana", 1, 1, "Java", 44, 32);
		empleados[1] = new Programador("Luis", 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
		empleados[2] = new Administrativo("Maria", 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
		empleados[3] = new Administrativo("Carlos", 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);

		for (Empleado empleado : empleados) {
			System.out.println(empleado);
			System.out.println("Horario completo: " + Arrays.deepToString(empleado.getHorasTrabajadas()));
			System.out.println("Horas totales de la semana: " + empleado.calcularHorasTotalesSemana());
			System.out.println("Productividad: " + empleado.calcularProductividad());
			if (empleado instanceof Programador) {
				Programador programador = (Programador) empleado;
				System.out.println("Merece reconocimiento: " + programador.mereceReconocimiento());
			} else if (empleado instanceof Administrativo) {
				Administrativo administrativo = (Administrativo) empleado;
				System.out.println("Merece reconocimiento: " + administrativo.mereceReconocimiento());
			}
			System.out.println();
		}

		
}
}
