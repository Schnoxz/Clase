package Relacion10v2.Ejercicio2;
class Main {
    public static void main(String[] args) {
        Operario   operario1  = new Operario("Juan",  1000, 3);
        Informatico informatico1 = new Informatico("María", 2000, "DESARROLLO");
        Directivo  directivo1 = new Directivo("Carlos", 3500, "Ventas");

        System.out.println(operario1);
        System.out.println(informatico1);
        System.out.println(directivo1);

        // Prueba de límite de sueldo
        System.out.println("\n-- Intentamos subir el sueldo del operario a 2000€ --");
        operario1.setSueldo(2000); // excede el máximo de 1200€
        System.out.println(operario1);
    }
}
