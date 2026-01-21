public static void main (String[] args){
int x = 10;
int y = 0;
int res = 0;

try {
    res = x/y;
    System.out.println("Inicio del programa");
    System.out.println("Resultado " + res);
    System.out.println("Fin del programa");
} catch (ArithmeticException e){
    res = Integer.MAX_VALUE;
}
finally {
    System.out.println(res);
}
}



