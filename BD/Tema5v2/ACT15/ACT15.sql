-- Selecciono la base de datos que contiene la tabla alumnado
USE pruebas;

-- El ejercicio me pide trabajar sobre la tabla logCambiosEmail y la tabla logAlumnosEliminados, no las tenia en la base de datos prueba asi que las he creado con los campos que se especifican en el ejercicio, los tipo int los declaro UNSIGNED para que no sean negativos
CREATE TABLE IF NOT EXISTS logCambiosEmail (id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, idAlumno INT UNSIGNED NOT NULL, fechaHora DATETIME NOT NULL, oldEmail VARCHAR(100) NOT NULL, newEmail VARCHAR(100) NOT NULL);

CREATE TABLE IF NOT EXISTS logAlumnosEliminados (id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, idAlumno INT UNSIGNED NOT NULL, fechaHora DATETIME NOT NULL,nombre VARCHAR(50) NOT NULL,apellido VARCHAR(50) NOT NULL, email VARCHAR(100));



-- 1. triggerCrearEmailBeforeInsert, si el email de la fila a insertar es NULL, lo genera automáticamente usando la función crearEmail()
-- Borro el trigger si ya existia
DROP TRIGGER IF EXISTS triggerCrearEmailBeforeInsert;
-- Creo el trigger
DELIMITER ??
CREATE TRIGGER triggerCrearEmailBeforeInsert
-- BEFORE INSERT porque quiero modificar el valor del email antes de que se inserte la fila en la tabla alumnado,for each row porque quiero que se ejecute para cada fila
BEFORE INSERT ON alumnado FOR EACH ROW
BEGIN   
    -- Si el email es nulo, creo uno automaticamente usando la funcion crearEmail que ya teniamos 
    IF NEW.email IS NULL THEN
        SET NEW.email = crearEmail(NEW.nombre, NEW.apellidos, NEW.curso);
    END IF;
END??
DELIMITER ;

-- 2. triggerGuardarEmailAfterUpdate, cada vez que se actualiza el email de un alumno (y realmente ha cambiado), inserta un registro en logCambiosEmail
-- Borro el trigger si ya existia
DROP TRIGGER IF EXISTS triggerGuardarEmailAfterUpdate;
-- Creo el trigger
DELIMITER ??
CREATE TRIGGER triggerGuardarEmailAfterUpdate
-- AFTER UPDATE porque quiero modificar el valor del email despues de que se actualice la fila en la tabla alumnado, for each row porque quiero que se ejecute para cada fila
AFTER UPDATE ON alumnado FOR EACH ROW
BEGIN
    -- Solo actúa si el email ha cambiado realmente o si el antiguo email era nulo y el nuevo no lo es y viceversa. Con el <> comparo que sean diferentes
    IF OLD.email <> NEW.email OR (OLD.email IS NULL AND NEW.email IS NOT NULL) OR (OLD.email IS NOT NULL AND NEW.email IS NULL) THEN
        -- Inserto el registro en la tabla logCambiosEmail que he creado antes
        INSERT INTO logCambiosEmail (idAlumno, fechaHora, oldEmail, newEmail)
        -- Introduzco el id, la fecha de NOW() que es la actual, el email antiguo y el nuevo, nos guarda en un log los cambios realizados, de qué campo y el timestamp de cúando se realizó
        VALUES (OLD.id, NOW(), OLD.email, NEW.email);
    END IF;
END??
DELIMITER ;


-- 3. ac1104triggerGuardarAlumnosAfterDelete, cada vez que se elimina un alumno, inserta un registroen logAlumnosEliminados con sus datos
-- Borro el trigger si ya existia
DROP TRIGGER IF EXISTS ac1104triggerGuardarAlumnosAfterDelete;
-- Creo el trigger
DELIMITER ??
CREATE TRIGGER ac1104triggerGuardarAlumnosAfterDelete
-- AFTER DELETE porque quiero modificar el valor del email despues de que se elimine la fila en la tabla alumnado, for each row porque quiero que se ejecute para cada fila
AFTER DELETE ON alumnado FOR EACH ROW
BEGIN
    -- Inserto el registro en la tabla logAlumnosEliminados
    INSERT INTO logAlumnosEliminados (idAlumno, fechaHora, nombre, apellido, email)
    -- Parecido al anterior, introduzco el antiguo id, la fecha actual, el antiguo nombre, apellido e email. Para tener constancia dentro de un log de los alumnos eliminados y cúando se eliminaron
    VALUES (OLD.id, NOW(), OLD.nombre, OLD.apellidos, OLD.email);
END??
DELIMITER ;


-- Casos de prueba:

-- Trigger 1: inserción sin el email y comprobar que se genera automático
INSERT INTO alumnado (id, nombre, apellidos, curso)
VALUES (6, 'Elena', 'Sánchez Vega', 'DAW');

-- Consulto en la tabla si se ha generado correctamente
SELECT id, nombre, apellidos, email FROM alumnado WHERE id = 6;

-- Trigger 2: actualizo el email y miro el log
UPDATE alumnado SET email = 'prueba@manual.es' WHERE id = 6;

-- Consulto la tabla del log para ver el registro actualizado
SELECT * FROM logCambiosEmail;

-- Trigger 3: eliminar un alumno y mirar el log
DELETE FROM alumnado WHERE id = 6;

-- Compruebo que se ha añadido el alumno eliminado al log
SELECT * FROM logAlumnosEliminados;