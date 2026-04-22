# Tema 07 – Estructuras de Almacenamiento Complejas (Java)

---

## 1. Clases Envoltorio (Wrappers)

Los contenedores Java **no admiten tipos primitivos** (`int`, `char`, `boolean`…).
Para usarlos hay que envolverlos en su clase correspondiente:

| Primitivo | Wrapper     |
|-----------|-------------|
| `int`     | `Integer`   |
| `double`  | `Double`    |
| `float`   | `Float`     |
| `boolean` | `Boolean`   |
| `char`    | `Character` |
| `byte`    | `Byte`      |

```java
Integer i = new Integer(5);        // forma antigua (deprecated en Java 9+)
Integer j = Integer.valueOf(5);    // forma recomendada: factory method
int x = i.intValue();              // desenvuelve el objeto a primitivo

// Java hace autoboxing/unboxing automáticamente en la mayoría de casos:
Integer auto = 5;    // autoboxing:  int → Integer
int prim = auto;     // unboxing:    Integer → int
```

---

## 2. Clases y Métodos Genéricos

Permiten escribir código que funciona con **cualquier tipo de objeto**, evitando duplicar lógica.

```java
// El parámetro <T> es un "tipo comodín" que se concreta al invocar el método
public class Utilidades {
    public static <T> void volcarArray(T[] origen, T[] destino) {
        int tamaño = Math.min(origen.length, destino.length);
        for (int i = 0; i < tamaño; i++) {
            destino[i] = origen[i];
        }
    }
}

// Uso: funciona igual para String[], Integer[], Persona[]...
String[]  origen  = {"Luis", "Pepe", "Lola"};
String[]  destino = new String[7];
Integer[] nums    = {10, 20, 30};
Integer[] dest2   = new Integer[7];

Utilidades.volcarArray(origen, destino); // T se infiere como String
Utilidades.volcarArray(nums, dest2);     // T se infiere como Integer
```

> ⚠️ Una clase/estructura genérica sólo puede almacenar **un único tipo** a la vez.
> No se pueden mezclar `String` e `Integer` en la misma instancia.

---

## 3. Contenedores – Jerarquía

```
Iterable
  └── Collection
        ├── List          → ArrayList, LinkedList, Vector (Stack)
        ├── Set           → HashSet, LinkedHashSet, TreeSet
        └── Queue         → PriorityQueue, Deque (ArrayDeque)

Map (independiente de Collection)
  └── SortedMap → NavigableMap → TreeMap
  └── HashMap, HashTable
```

---

## 4. Interface `Collection`

Métodos comunes a **todas** las colecciones:

```java
int     size()                    // número de elementos
boolean isEmpty()                 // true si está vacía
boolean contains(Object o)        // true si contiene el objeto (usa equals())
boolean add(Object o)             // añade; devuelve false si no admite repetidos y ya existe
boolean remove(Object o)          // elimina; usa equals() para encontrarlo
Object[]toArray()                 // convierte a array
void    clear()                   // vacía la colección
Iterator<T> iterator()            // devuelve un iterador para recorrerla
```

> ℹ️ Las colecciones comparan objetos usando `equals()`. Si usas clases propias,
> **asegúrate de sobreescribir `equals()`** (e idealmente `hashCode()`).

---

## 5. Interface `Iterator`

Permite recorrer (y borrar sobre la marcha) cualquier `Collection`:

```java
Iterator<String> it = coleccion.iterator();
while (it.hasNext()) {
    String elemento = it.next();    // devuelve el siguiente
    if (elemento.equals("borrar"))
        it.remove();                // borra el último devuelto por next()
                                    // ⚠️ NO usar coleccion.remove() dentro del while,
                                    //    provoca ConcurrentModificationException
}

// Equivalente más limpio con for-each (no permite borrar mientras recorre):
for (String s : coleccion) {
    System.out.println(s);
}
```

---

## 6. `HashSet` – conjunto sin duplicados

- **No permite elementos repetidos** (usa `equals()` + `hashCode()`).
- **Sin orden** garantizado.
- Los métodos `add`, `remove` y `contains` son muy eficientes O(1).

```java
HashSet<String> ciudades = new HashSet<>();
ciudades.add("Madrid");
ciudades.add("Barcelona");
ciudades.add("Sevilla");
ciudades.add("Madrid"); // ignorado: ya existe

// Recorrido con for-each
for (String c : ciudades) {
    System.out.println(c); // el orden puede variar en cada ejecución
}
```

> ⚠️ Si usas objetos propios en un `HashSet`, la clase **debe implementar `hashCode()`**.
> La regla: si `a.equals(b)` es `true`, entonces `a.hashCode() == b.hashCode()`.

---

## 7. `ArrayList` – lista dinámica por índice

- **Permite repetidos** y mantiene el **orden de inserción**.
- Acceso por índice O(1) → muy rápido para leer/modificar por posición.
- Insertar/borrar en el **medio o inicio** es costoso (desplaza todos los siguientes).

```java
ArrayList<String> ciudades = new ArrayList<>();
ciudades.add("Madrid");          // añade al final
ciudades.add("Barcelona");
ciudades.add(1, "Sevilla");      // inserta en posición 1; desplaza los demás

String c = ciudades.get(0);      // "Madrid" – acceso por índice
ciudades.set(0, "Valencia");     // reemplaza la posición 0
ciudades.remove(1);              // elimina la posición 1
int pos = ciudades.indexOf("Barcelona"); // -1 si no existe

System.out.println(ciudades.size());     // número de elementos
```

---

## 8. `LinkedList` – lista enlazada (nodos)

- **Permite repetidos**, mantiene orden.
- Insertar/borrar al **inicio o en el medio** es O(1) → muy eficiente.
- Acceso por índice con `get(i)` es O(n) → **muy lento**, recorre desde el principio.

```java
LinkedList<String> ciudades = new LinkedList<>();
ciudades.add("Madrid");
ciudades.add("Barcelona");
ciudades.add("Madrid"); // los repetidos sí se insertan

// ✅ Forma CORRECTA de recorrer un LinkedList: iterador
Iterator<String> itr = ciudades.iterator();
while (itr.hasNext()) {
    System.out.println(itr.next());
}

// ❌ Forma INCORRECTA (ineficiente, O(n²)):
for (int i = 0; i < ciudades.size(); i++) {
    System.out.println(ciudades.get(i)); // get() recorre desde el inicio cada vez
}
```

### ¿`ArrayList` o `LinkedList`?

| Operación              | `ArrayList` | `LinkedList` |
|------------------------|-------------|--------------|
| Leer por índice        | ✅ Rápido   | ❌ Lento     |
| Insertar al final      | ✅ Rápido   | ✅ Rápido    |
| Insertar al inicio/medio | ❌ Lento  | ✅ Rápido    |
| Borrar al inicio/medio | ❌ Lento    | ✅ Rápido    |

---

## 9. Ordenar listas

### Opción A – Implementar `Comparable` (orden natural)

La propia clase define **cómo compararse a sí misma**:

```java
public class Alumno implements Comparable<Alumno> {
    private String nombre;
    private int nota;

    @Override
    public int compareTo(Alumno otro) {
        // Retorna: 0 si iguales, >0 si this > otro, <0 si this < otro
        if (nota == otro.getNota())  return  0;
        if (nota >  otro.getNota())  return  1;
        return -1;
        // Equivalente más corto: return Integer.compare(nota, otro.getNota());
    }
}

// Uso:
Collections.sort(listaAlumnos); // ordena por nota (compareTo de Alumno)
```

### Opción B – Usar `Comparator` (criterio externo)

Útil para **múltiples criterios** o cuando no puedes modificar la clase:

```java
// Clase comparadora independiente
public class ComparadorPorNombre implements Comparator<Alumno> {
    @Override
    public int compare(Alumno a1, Alumno a2) {
        return a1.getNombre().compareTo(a2.getNombre()); // orden alfabético
    }
}

Collections.sort(listaAlumnos, new ComparadorPorNombre());
```

### Opción C – Lambda (forma más concisa)

```java
// Clase anónima equivalente a la anterior, en una sola línea:
Collections.sort(listaAlumnos,
    (a1, a2) -> a1.getNombre().compareTo(a2.getNombre())
);

// O usando el método de referencia de Comparator (aún más corto):
listaAlumnos.sort(Comparator.comparing(Alumno::getNombre));
```

---

## 10. `HashMap` – mapa clave → valor

- Colección de **pares (clave, valor)**.
- **No permite claves duplicadas** (una nueva inserción con clave existente reemplaza el valor).
- Sí permite valores duplicados.
- **No hereda de `Collection`**.

```java
HashMap<String, Integer> edades = new HashMap<>();
edades.put("Ana",  25);   // inserta par
edades.put("Luis", 30);
edades.put("Ana",  26);   // reemplaza el valor anterior de "Ana"

int edad = edades.get("Luis");    // 30 — null si la clave no existe
edades.remove("Luis");            // elimina la pareja

// Recorrer todas las parejas
for (Map.Entry<String, Integer> entry : edades.entrySet()) {
    System.out.println(entry.getKey() + " → " + entry.getValue());
}

// Recorrer sólo valores o sólo claves
for (String clave : edades.keySet())         { ... }
for (Integer valor : edades.values())        { ... }
```

> `HashMap` admite claves `null`. `HashTable` no (y además sus métodos están
> sincronizados, útil para multihilo pero más lento).

---

## 11. Pilas (`Stack`) – LIFO

**L**ast **I**n – **F**irst **O**ut: el último en entrar es el primero en salir.

```java
Stack<Integer> pila = new Stack<>();
pila.push(1);   // [1]
pila.push(2);   // [1, 2]
pila.push(3);   // [1, 2, 3]

int cima = pila.peek(); // 3 – consulta sin extraer
int top  = pila.pop();  // 3 – extrae y devuelve el elemento del tope → [1, 2]

boolean vacia = pila.isEmpty();
```

---

## 12. Colas (`Queue`) – FIFO

**F**irst **I**n – **F**irst **O**ut: el primero en entrar es el primero en salir.

```java
// LinkedList implementa Queue (entre otras interfaces)
Queue<String> cola = new LinkedList<>();
cola.offer("Primero");  // enqueue – añade al final
cola.offer("Segundo");
cola.offer("Tercero");

String cabeza = cola.element(); // "Primero" – consulta sin extraer
String sale   = cola.remove();  // "Primero" – extrae la cabeza → [Segundo, Tercero]

// Versiones "seguras" (devuelven null en vez de lanzar excepción):
cola.peek();   // equivalente a element() pero devuelve null si vacía
cola.poll();   // equivalente a remove() pero devuelve null si vacía
```

---

## 13. Interfaces funcionales y Lambdas

Una **interfaz funcional** tiene exactamente **un método abstracto**
(como `Comparator`, `Predicate`, `Runnable`…).

Una **expresión lambda** implementa ese método de forma anónima y compacta:

```java
// Sintaxis:  (parámetros) -> { cuerpo }

// Con bloque:
(a, b) -> { return a.compareTo(b); }

// Sin llaves si es una sola expresión con retorno (return implícito):
(a, b) -> a.compareTo(b)

// Sin paréntesis si hay un solo parámetro:
x -> x * 2
```

### Ejemplo: ordenar con lambda vs clase anónima vs clase separada

```java
// ── 1. Clase separada ─────────────────────────────────────────────────────
class ComparadorDni implements Comparator<Persona> {
    public int compare(Persona p1, Persona p2) {
        return p1.getDni().compareTo(p2.getDni());
    }
}
Collections.sort(lista, new ComparadorDni());

// ── 2. Clase anónima ──────────────────────────────────────────────────────
Collections.sort(lista, new Comparator<Persona>() {
    @Override
    public int compare(Persona p1, Persona p2) {
        return p1.getDni().compareTo(p2.getDni());
    }
});

// ── 3. Lambda (equivalente a las dos anteriores, mucho más conciso) ───────
Collections.sort(lista, (p1, p2) -> p1.getDni().compareTo(p2.getDni()));

// ── 4. Aún más corto con Comparator.comparing ─────────────────────────────
lista.sort(Comparator.comparing(Persona::getDni));
```

---

## 14. Interface `Stream`

Un `Stream` es una **secuencia de elementos** sobre la que se encadenan operaciones.
No modifica la colección original.

```
colección / array
    └── .stream()
          ├── operaciones intermedias (devuelven otro Stream)
          │     filter(), map(), sorted(), distinct(), limit()...
          └── operación terminal (devuelve resultado final)
                forEach(), collect(), count(), findFirst()...
```

### Crear un Stream

```java
// Desde una colección
Stream<Persona> s1 = arrayListPersonas.stream();

// Desde un array
Stream<Persona> s2 = Stream.of(arrayPersonas);

// Explícitamente
Stream<String> s3 = Stream.of("Sevilla", "Córdoba", "Madrid");
```

### Operaciones más comunes

```java
List<Alumno> alumnos = List.of(
    new Alumno("Ana",  8),
    new Alumno("Luis", 5),
    new Alumno("Rosa", 9),
    new Alumno("Luis", 7)
);

// forEach – ejecuta acción sobre cada elemento
alumnos.stream()
       .forEach(a -> System.out.println(a.getNombre()));

// filter – filtra los que cumplen la condición (Predicate)
alumnos.stream()
       .filter(a -> a.getNota() >= 7)   // sólo aprobados con notable o más
       .forEach(a -> System.out.println(a.getNombre()));

// sorted – ordena (usa compareTo o un Comparator)
alumnos.stream()
       .sorted((a1, a2) -> a1.getNombre().compareTo(a2.getNombre()))
       .forEach(System.out::println);

// distinct – elimina duplicados (usa equals)
alumnos.stream()
       .map(Alumno::getNombre)    // map: transforma cada elemento
       .distinct()                // quita nombres repetidos
       .forEach(System.out::println);

// collect – recoge el resultado en una nueva lista
List<Alumno> notables = alumnos.stream()
       .filter(a -> a.getNota() >= 7)
       .collect(Collectors.toList());

// count – cuenta cuántos cumplen la condición
long numAprobados = alumnos.stream()
       .filter(a -> a.getNota() >= 5)
       .count();
```

> ℹ️ Las operaciones intermedias son **lazy** (perezosas): no se ejecutan hasta que
> se llama a una operación terminal. Esto hace que los streams sean eficientes.

---

## Resumen comparativo de estructuras

| Estructura    | Duplicados | Orden | Acceso por índice | Insertar/borrar medio | Uso típico                     |
|---------------|------------|-------|-------------------|-----------------------|--------------------------------|
| `HashSet`     | ❌         | ❌    | ❌                | ✅ O(1)               | Conjuntos únicos, búsqueda rápida |
| `ArrayList`   | ✅         | ✅    | ✅ O(1)           | ❌ O(n)               | Listas con acceso por posición |
| `LinkedList`  | ✅         | ✅    | ❌ O(n)           | ✅ O(1)               | Pilas/colas, inserciones frecuentes |
| `HashMap`     | ❌ claves  | ❌    | Por clave O(1)    | ✅ O(1)               | Asociar clave → valor          |
| `Stack`       | ✅         | LIFO  | ❌ (sólo tope)    | Sólo tope             | Deshacer, parsers, recursión   |
| `Queue`       | ✅         | FIFO  | ❌ (sólo cabeza)  | Sólo extremos         | Procesamiento en orden         |
