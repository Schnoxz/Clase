-- EJERCICIO 1: Ventas

-- Modifico la  columna 'nombre' en la tabla 'cliente' ampliando su tamaño a 100 caracteres y no nulo
ALTER TABLE cliente
MODIFY nombre VARCHAR(100) NOT NULL;

-- Compruebo el cambio que he hecho
DESCRIBE cliente;

-- Modifico el nombre de la columna primer_apellido a apellido1 en la tabla cliente y cambio su tipo a VARCHAR(100) y no nulo
MODIFY primer_apellido apellido1 VARCHAR(100) NOT NULL;

-- Añado una columna llamada apellido2 junto a apellido1 con un varchar de 100 y no nulo
ALTER TABLE cliente
ADD apellido2 VARCHAR(100) NOT NULL AFTER apellido1;

-- Elimino la columna categoria
ALTER TABLE cliente
DROP COLUMN categoria;

-- Modifico la columna comisión en la tabla comercial para almacenar valore de 10 por defecto
ALTER TABLE comercial
MODIFY comosión FLOAT DEFAULT 10;