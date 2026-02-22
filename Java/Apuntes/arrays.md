# ARRAYS

## ¿Qué es un Array?

Es una colección ordenada de elementos **del mismo tipo** donde cada elemento se asocia a un índice o posición que ocupa.

Los elementos de un array se almacenan en posiciones contiguas en memoria, por lo que un vector se determina por el tipo de elemento y el número del mismo.

Necesitamos **"Declarar, Crear e Inicializar"** un Array, son las 3 operaciones esenciales.
<br>
<br>

## Declaración

Se declara de dos formas distintas:

    tipo[] nombre;
    tipo nombre []; 

Donde tipo, nos indica el tipo de elemento del vector ya sea ***int, double, String*** y el nombre es el que le damos para identificar a ese array en concreto, como cualquier otra variable que creemos.

    double [] vectorNotas; // array de elementos de tipo double
    String[] clase; // array de tipo string con los nombres de los alumnos
    Cuenta [] vectorCuentas; // array de objetos cuenta

<br>
<br>

## Creación

Hay que indicar el número de elementos del array para poder reservar la memoria necesaria que contiene todos esos elementos

``` nombre = new tipo[numeroDeElementos];```

Donde nombre indica el array que hemos declarado anteriormente, tipo es el tipo de elemento, y dentro de **[]** el número de elementos que contendrá

Creo un array llamado **"vectorNotas"**, el cual contiene 30 elementos de tipo double:

```double[] vectorNotas;```

```vectorNotas = new double[30];```

Es comun declarar y crear el array en una sola linea:

```tipo[] nombre = new tipo[tamaño];```

<br>
<br>

**IMPORTANTE**

De momento solo se ha reservado memoria pero no tenemos datos en el array, asi que si hacemos un print nos dará null, 0 o false dependiendo del tipo de dato que hayamos declarado.
Aqui entraría el proceso de inicialiazación

<br>
<br>


## Inicialización

Es posible inicializarlo con valores en su momento de creación:

Es comun declarar y crear el array en una sola linea:

    // Se crearia el array con tamaño 8
    double[] vectorNotas = {4, 3, 5, 6, 8, 1, 10, 7};
    // Tamaño 5
    String[] diasLaborables = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};

<br>
<br>


## Acceso 

Para acceder a un valor de un elemento dentro del array, se utiliza el nombre de la variable array seguido del subíndice entre corchetes, cuidado que aquí funciona por posición y no valor numérico aunque el array sea **int**.

Por ejemplo, quiero acceder a la nota del alumno 4 y el 4 es la posición **"3"** entonces sería ```vectorNotas[3]```
<br>
<br>



Podemos operar con los arrays: 

    double[] vectorNotas = new double[30];
    vectorNotas[5] = 8;
    vectorNotas[9] = vectorNotas [5] + 1;

--------------------------------------------------------------------------------
<br>
<br>

Si intentamos acceder a un elemento, con un **"[x]"** fuera de rango, nos devolverá un exception: 

```ArrayIndexOutOfBoundsException```

Para prevenir esto podemos usar **.length** para ver la longitud


## Recorrido

Siempre que se vayan a usar la totalidad de los elementos de un array debemos crear un recorrido, un bucle **for** para no pasarse del límite

    for (int i = 0; i < nombreVector.length; i++)

<br>

Por lo general, al recorrer un **Array**, se recorre de forma completa, podemos simplificar con un **for each**

    for (String nombre : vectorNombres){
         System.out.print(nombre);
    }

En cada iteración del bucle, la variable nombre cambiará de valor hasta completar todos los elementos dentro de **vectorNombres**, desde el índice 0 hasta el último.

<br>
<br>

## Foreach

Foreach no modifica los elementos originales del array, simplemente los recorre y los guarda en en **una variable deñ ámbito for** no relacionada con los propios elementos, es un paso de parámetro por valor.


<br>

## Arrays como parámetro

Un array puede pasarse como parámetro a un método, el paso es por **referencia**, es decir cualquier cambio que se haga dentro de un método a un elemento del array se va a conservar aunque salga del método.

* Solo se debe indicar el nombre del array
* En el prototipo del método se indica que es un array con **[ ]**

    ```LLamada por referencia --> Cambia el valor original```

    ```Llamada por valor --> No cambia el valor original```
<br>
<br>

Ejemplo : 

        public static void main (String[] args) {
            double[] vectorNotas = new double[TOTAL_ALUMNOS];
            insertarNotasEnVector(vectorNotas);
            mostrarVectorNotas(vectorNotas);
        }

        private static void insertarNOtasEnVector(double[] vectorNotas) {
            for (int i = 0; i < vectorNotas.length; i++) {
                System.out.println("Introduce la nota del alumno" + (i + 1));
                vectorNotas[i] = Double.parseDouble(teclado.nextLine());
            }
        }

        private static void mostrarVectorNotas(double[] vectorNotas) {
            for (int i = 0; i < vectorNotas.length; i++) {
                System.out.println("Nota del alumno " + (i +1) + ":" + vectorNotas[i]);
            }
        }
<br>
<br>

## Búsqueda de elementos en arrays

Ejemplo : 

        public static int buscarElementoEnVector(double [] vector, double elementoBuscado) {
            int posicion = -1;
            int i = 0;
            while (i < vector.length && posicion == -1) {
                if (vector [1] == elementoBuscado)
                posicion = i;
                else
                i++;
            }
            return posicion;
        }


<br>
<br>

## Ordenación de elementos "**Arrays.sort**"

Existen varios algoritmos para el orden de los elementos dentro de un array: **Selección, burbuja, quicksort**
<br>

Este último **"*Quicksort*"**, es el que implementa java en el **Arrays.sort**, nos ordena por defecto de forma ascendente: 

* **10 - 5 - 2 - 8 - 7**  

    Pasa a :

* **2 - 5 - 7 - 8 - 10**  
<br>
<br>

**ORDENACION DE UN ARRAY DE OBJETOS**

Si necesitamos ordenar un array de objetos, la clase tiene que implementar la interfaz **Comparable**

        public class Cuenta implements Comparable<Cuenta> {
            @Override
            public int compareTo(Cuenta o) {
                // TODO Establecer el criterio de ordenacion
                return 0;
            }
        }

Esto significa que debe tener implementado el métpdp **compareTo** que describe el criterio de ordenación, en este caso cuando una cuenta es menor o mayor que otra

<br>
<br>

**METODO compareTO**

Funcionamiento :

```
Devuelve cero si el objeto this es igual que el objeto otro
Devuelve un valor menor que cero si this es menor que otro
Devuelve un valor mayor que cero si this es mayor que otro
```

<br>
<br>

## Otros métodos de arrays

Java maneja los arrays como si fueran objetos, por lo que existen una serie de métodos heredados de la clase **Object** que están dentro del paquete de java.lang

- equals. : Devuelve true si los dos arrays son el mismo, compara las referencias
- clone. : Devuelve un array nuevo con los mismos datos que el array original

<br>
<br>

        double[] notas1 = { 5.5, 6, 5, 8, 3};
        double[] notas2 = { 5.5, 6, 5, 8, 3};
        double[] notas 1Clonado;
        double[] notas3;
        notas1Clonado = notas1.clone();
        notas3 = notas1;

        if (notas1.equals(notas2))
            System.out.println("Equals entre notas1 y notas2 dice IGUALES");
        else
            System.out.println("Equals entre notas1 y notas2 dice DISTINTOS);
        if (notas1.equals(notas1Clonado))
            System.out.println("Equals entre notas1 y notas1Clonado dice IGUALES");
        else
            System.out.println("Equals entre notas1 y notas1Clonado dice DISTINTOS");
        if (notas1.equals(notas3))
            System.out.println("Equals entre notas1 y notas3 dice IGUALES");
        else
            System.out.println("Equals entre notas1 y notas3 dice DISTINTOS");


<br>
<br>

La clase **Arrays** tiene métodos estáticos que podemos usar pasándoles como parámetros, principalmente, objetos de tipo arrays.

Ejemplo:

- **Arrays.toString(vectorNombres)** devolverá una cadena con los valores del array, no la referencia

        System.out.println(vectorNombres);
        System.out.println(Arrays.toString(vectorNombres));