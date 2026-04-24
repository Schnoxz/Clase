Contenedor vs Array
---
- Crece/Decrece
- Reglas internas
- Evita repeticiones
- Recorrido

COLLECTION
---
- NO hace falta implements (salvo si creamos un collection)
- Agrupa elementos que se pueden recorrer y saber el tamaño
- Añade, elimina y recorre la estructura gracias a un iterator
- No se puede hacer un new, es una interfaz
- Las clases hijas de interface Collection deberán implementar cada uno de los métodos o ser a su vez interfaces


boolean remove(Object element) --> Borra un determinado objeto dentro de la colección, devuelve true si se ha encontrado y borrado, para encontrarlo usa equals

Object[] toArray() --> Devuelve un array con todos los elementos de la colección

void clear() --> elimina elementos de la colección

Iterator iterator() --> Devuelve un objeto iterador que nos permite recorrer los elementos de la colección. Como el i del bucle for que hemos usado siempre

para recorrer y/o modificar una colección debemos usar el metodo iterator() que devuelve un objeto que implementa la interfaz con los siguientes metodos:

- boolean hasNext() --> Devuelve true si quedan más elementos 

- E < tipo > next() --> Devuelve el siguiente elemento

- void remove() --> Borra el ultimo elemento devuelto con el next()

RECORRIDO FOR
---
for (Object 0: Collection)
{
    // Hacer algo con o
}

CLASS HASHSET
-

HashSet< Tipo>  nombre = new HashSet< Tipo >();

- Clase genérica, en tipo se indica los elementos del conjunto
- Obligatorio que la clase Tipo tenga hashCode(), retorna un entero que indica el valor hash del objeto, nos sirve para saber dónde se localiza el objeto en la tabla Hash
- Si hay dos objetos iguales segun equals, ambos retornarán el mismo valor para hashCode()

EJEMPLO: 


public class TestHashSet {

    public static void main(String[] args){
        HashSet< String > ciudades = new HashSet< String >();
        ciudades.add("Madrid");
        ciudades.add("Barcelona");
        ciudades.add("Sevilla");
        ciudades.add("Madrid"); // Repetido

        Iterator< String > iterador = ciudades.iterator();
        while(iterador.hasNext())
            System.out.println("Ciudad :"+ iterador.next());
        // Equivalente a lo anterior
        for (String c : ciudades){
            System.out.println("Ciudad " + c); 
        }

    }
}

LISTA ORDENADA
---
public class ListaOrdenada {
    LinkedList<Integer> lista = new LinkedList<Integer>();

    public void insertarEnOrden(Integer nuevo) {
        int pos = buscarSuSitio(nuevo);
        lista.add(pos, nuevo);
    }

    private int buscarSuSitio(Integer nuevo) {
        boolean encontradoPosicion = false;
        int pos = 0;
        Iterator<Integer> it = lista.iterator();
        Integer elemento;

        while (it.hasNext() && encontradoPosicion == false) {
            elemento = it.next();
            if (nuevo.intValue() < elemento.intValue()) <-- Al ser "<" si un numero se repite se colocará delante de el en la lista ordenada, para solucionar esto debemos poner un menor o igual   
                encontradoPosicion = true;
            else
                pos++;
        }
        return pos;
    }
}

- Una lista puede ordenarse con .sort() (se le pasa la lista por parametro)
- Para ordenar los elementos tiene que implementar la interfaz Comparable, es decir programar compareTo
- compareTo debe programarse para comparar el objeto actual con otro objeto devolviendo 0 si son iguales, 1 si es mayor, -1 si es menor

INTERFAZ COMPARABLE VS COMPARATOR

- Comparable define un ordenamiento natural único dentro de la propia clase, mientras que Comparator permite definir múltiples ordenamientos personalizados de forma externa
- El método de comparator es compare
- El método de comparable es compareTo


INTERFAZ MAP

HashMap
- TipoClave es unica e identifica a un valor
- TipoValor es el valor que se le determina a un elemento y puede repetirse 

Métodos importantes:
- Object get(Object key): Accede al valor de una clave, devuelve null si no existe

- Object put(Object key, Object value): Inserta una pareja, si ya habia un valor para esa clave se lo reemplaza

- Object remove(Object key): Elimina una pareja

- Collection< V > values(): Devuelve una colección con los elementos contenidos en el map


PILAS Y COLAS
---

Pilas

- push(Elemento e): inserta un nuevo elemento
- pop(): extrae un elemento, el último que se haya insertado
- peek(): Consulta el último elemento sin tocarlo


Colas
- element(): devuelve, pero no elimina, la cabeza de la cola
- offer(Elemento e): inserta un elemento en la cola
- remove(): devuelve y elimina la cabeza de la cola

La interfaz Queue implementa Collection por lo que tiene todo sus métodos