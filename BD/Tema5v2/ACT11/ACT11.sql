/* Base de datos creada para el ejercicio 11 */

DROP DATABASE IF EXISTS pruebas;
CREATE DATABASE IF NOT EXISTS pruebas;
USE pruebas;
-- id entero sin signo (pk), nombre de 50 caracteres, apellidos de 50 caracteres, curso de 50 caracteres
CREATE TABLE alumnado (id INT UNSIGNED PRIMARY KEY, nombre VARCHAR(50), apellidos VARCHAR(50), curso VARCHAR(50)
);
-- Inserciones de los 5 registros, con la id nombre, apellidos y curso
INSERT INTO alumnado VALUES
(1, 'Alberto',  'Morales',         'BD'),
(2, 'Laura',    'García López',    'DAW'),
(3, 'Carlos',   'Fernández Ruiz',  'DAM'),
(4, 'María',    'Rodríguez',       'ASIR'),
(5, 'Javier',   'Martínez',        'SMR');



-- Función para crear el email

DELIMITER ??
CREATE FUNCTION crearEmail(nombre VARCHAR(50), apellidos VARCHAR(50), curso VARCHAR(50))
RETURNS VARCHAR(100)
BEGIN
    -- El email debe ser el primer caracter del nombre en minúscula, los cinco primeros apellidos en minúscula, el número de letras del apellido en el curso en minúscula, @ y la extensión .kursal.es
    RETURN CONCAT( LOWER(LEFT(nombre, 1)), LOWER(LEFT(apellidos, 5)), LENGTH(apellidos), '@', LOWER(curso), '.kursal.es');
END?? 
DELIMITER ;

-- Añado una columna email a la tabla alumnado
ALTER TABLE alumnado ADD COLUMN email VARCHAR(50);

-- Procedimiento que permite crear un email para todo el alumnado con la funcion previa crearEmail

DELIMITER ??
CREATE PROCEDURE ac11actualizarColumnaEmail()
BEGIN
    UPDATE alumnado
    -- Se actualiza la columna email con la función creada
    SET email = crearEmail(nombre, apellidos, curso);
END??
DELIMITER ;

-- Llamada al procedimiento
CALL ac11actualizarColumnaEmail();

-- Selecciono los datos de la tabla alumnado 
SELECT * FROM alumnado;