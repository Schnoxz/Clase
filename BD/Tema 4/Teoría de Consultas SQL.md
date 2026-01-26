# **Teoría de Consultas SQL** 

## **1\. Sentencia SELECT básica**

La sentencia SQL SELECT se utiliza para recuperar o consultar datos de una o más tablas en su base de datos. Los datos devueltos se almacenan en una tabla de resultados. La sintaxis básica para seleccionar columnas específicas es: SELECT columna1, columna2 FROM nombreTabla;. Para seleccionar todos los campos disponibles en la tabla, se utiliza el asterisco: SELECT \* FROM nombreTabla;.

## **2\. SELECT con WHERE**

La cláusula WHERE se utiliza para filtrar registros y extraer solo aquellos que cumplen una condición especificada. La sintaxis es: SELECT columna1, columna2 FROM nombreTabla WHERE condición;. Esta cláusula se puede combinar con los operadores lógicos AND, OR y NOT. Los operadores de comparación incluyen:

* \= (Igual), \<\> o \!= (Distinto).

* \> (Mayor), \< (Menor), \>= (Mayor igual), \<= (Menor igual).

* IS NULL / IS NOT NULL para comprobar campos vacíos.

* BETWEEN...AND... para comprobar si un valor está en un rango.

## **3\. LIKE y NOT LIKE**

Este operador permite comprobar si los datos de una cadena coinciden con un patrón. Se utilizan dos caracteres comodín:

* %: Coincide con cualquier número de caracteres, incluso cero.

* \_: Coincide solo con un carácter. Ejemplo para mostrar empleados cuyo nombre empieza por 'A': SELECT \* FROM empleados WHERE nombre LIKE 'A%';.

## **4\. IN y NOT IN**

Permite comprobar si un campo está dentro de un conjunto de valores. Ejemplo para mostrar coches de color Blanco, Rojo o Negro: SELECT \* FROM coches WHERE color IN ('Blanco', 'Rojo', 'Negro');.

## **5\. SELECT DISTINCT**

Se utiliza para descartar filas que tienen valores duplicados en las columnas seleccionadas. SELECT DISTINCT columna FROM nombreTabla;.

## **6\. ORDER BY y Límites**

La cláusula ORDER BY permite ordenar los resultados por una columna determinada en orden ascendente (ASC) o descendente (DESC). Las cláusulas LIMIT y OFFSET se utilizan para indicar el subconjunto de resultados interesados. LIMIT reduce la cantidad de filas a devolver y OFFSET indica desde dónde comenzar a contar. SELECT columna FROM nombreTabla ORDER BY columna ASC LIMIT numLimite OFFSET numDesde;.

## **7\. JOINS**

* **INNER JOIN**: Combina filas que tienen la misma clave definida en la restricción ON. Ejemplo: SELECT coches.matricula, propietarios.nombre FROM coches INNER JOIN propietarios ON propietarios.dni \= coches.dniPropietario;.

* **LEFT JOIN**: Obtiene todos los registros de la tabla izquierda y los coincidentes de la derecha; si no hay coincidencia, devuelve NULL para la derecha.

* **RIGHT JOIN**: Obtiene todos los registros de la tabla derecha, incluso si la izquierda no tiene registros coincidentes. Los alias se utilizan para dar un nombre temporal a una tabla o columna con la palabra clave AS.

## **8\. Expresiones y Funciones**

Se pueden crear expresiones aritméticas dentro de SELECT. Ejemplo: SELECT dni, nombre, (12\*salario+400) AS 'Salario anual' FROM empleados;. Existen funciones matemáticas como abs(), ceil(), floor(), mod(), power() y sqrt() . Las funciones de cadena comunes son:

* lower(cadena): Convierte a minúsculas.

* upper(cadena): Convierte a mayúsculas.

* trim(cadena): Elimina espacios al principio y final.

* length(cadena): Devuelve el número de caracteres.

## **9\. Agregados y Agrupamiento**

Las funciones de agregación permiten resumir información sobre un grupo de filas:

* COUNT(\*) o COUNT(columna): Cuenta la cantidad de filas.

* MIN(columna) / MAX(columna): Valor más pequeño o más grande.

* AVG(columna): Valor promedio.

* SUM(columna): Suma de todos los valores. La cláusula **GROUP BY** organiza datos idénticos en grupos. Para filtrar grupos después de agruparlos se utiliza **HAVING**, mientras que **WHERE** se aplica antes de agrupar. Ejemplo: SELECT idDepartamento, COUNT(dni) FROM empleados GROUP BY idDepartamento HAVING COUNT(dni) \> 3;.

## **10\. Subconsultas**

Una subconsulta es una consulta anidada dentro de otra consulta externa.

* **Comparadores**: Se pueden usar \=, \>, \<, etc., para comparar un único valor devuelto por la subconsulta.

* **IN / NOT IN**: Si la subconsulta devuelve más de un valor.

* **FROM**: El resultado de la subconsulta se usa como una tabla temporal o derivada, siendo obligatorio el uso de un alias.

* **Subconsultas correlacionadas**: Dependen de los datos de la consulta externa y se evalúan una vez por cada fila de esta. Requieren el uso de alias de tabla.

* **EXISTS / NOT EXISTS**: La subconsulta devuelve un valor booleano verdadero si hay alguna fila de resultado.

## **11\. Otros usos y Vistas**

* **INSERT INTO SELECT**: Utiliza el resultado de un SELECT como fuente de datos para un INSERT.

* **UPDATE / DELETE**: Pueden usar subconsultas en la cláusula WHERE para filtrar los datos a modificar o eliminar.

* **Vistas**: Son consultas con nombre almacenadas en el catálogo que funcionan como tablas virtuales. Se crean con CREATE VIEW y no almacenan datos físicamente.  
