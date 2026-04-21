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

Iterator iterator() --> Devuelve un objeto iterador que nos permite recorrer los elementos de la colección.

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