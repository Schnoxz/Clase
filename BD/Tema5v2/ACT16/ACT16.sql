USE empresa;

-- 1. triggerHolaHijo, al insertar un nuevo hijo, incrementa NumHi del empleado
-- Borro el trigger si ya existias
DROP TRIGGER IF EXISTS triggerHolaHijo;
-- Creo el trigger
DELIMITER ??
CREATE TRIGGER triggerHolaHijo
-- AFTER INSERT porque quiero modificar el valor del email despues de que se inserte la fila en la tabla alumnado, for each row porque quiero que se ejecute para cada fila
AFTER INSERT ON hijo FOR EACH ROW
BEGIN
    -- Actualizo de la tabla empleado el campo NumHi donde CodEmp sea igual al CodEmp del hijo insertado, le sumo 1 al valor original
    UPDATE empleado
    SET NumHi = NumHi + 1
    WHERE CodEmp = NEW.CodEmp;
END??
DELIMITER ;


-- 2. triggerAdiosHijo, al eliminar un hijo, decrementa NumHi del empleado
-- Borro el trigger si ya existias
DROP TRIGGER IF EXISTS triggerAdiosHijo;
-- Creo el trigger
DELIMITER ??
CREATE TRIGGER triggerAdiosHijo
-- AFTER DELETE porque quiero modificar el valor del email despues de que se elimine la fila en la tabla alumnado, for each row porque quiero que se ejecute para cada fila
AFTER DELETE ON hijo FOR EACH ROW
BEGIN
    -- Igual que antes pero le resto 1 y CodEmp es igual al CodEmp del hijo eliminado
    UPDATE empleado
    SET NumHi = NumHi - 1
    WHERE CodEmp = OLD.CodEmp;
END??
DELIMITER ;


-- Casos de prueba: 

-- Compruebo el estado del empleado 7
SELECT CodEmp, NomEmp, NumHi FROM empleado WHERE CodEmp = 7;

-- Trigger 1: se inserta un hijo al empleado 7
INSERT INTO hijo (CodEmp, NumHij, FecNaHi, NomHi)
VALUES (7, 1, '2010-05-20', 'Forzado Pérez, Ana');

-- Compruebo que NumHi pasa a 1
SELECT CodEmp, NomEmp, NumHi FROM empleado WHERE CodEmp = 7;

-- Trigger 2: eliminar el hijo recién insertado
DELETE FROM hijo WHERE CodEmp = 7 AND NumHij = 1;

-- Compruebo que NumHi pasa a 0
SELECT CodEmp, NomEmp, NumHi FROM empleado WHERE CodEmp = 7;

-- 3. Creación de la tabla salarios
CREATE TABLE IF NOT EXISTS salarios (id INT AUTO_INCREMENT PRIMARY KEY, fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP, salario DECIMAL(12,2) NOT NULL CHECK (salario > 0), codigo_empleado INT(10), CONSTRAINT fk_salarios_empleado  FOREIGN KEY (codigo_empleado) REFERENCES empleado(CodEmp)
);

-- 4. triggerSalariosEmpleadoAfterInsert
-- Borro el trigger si ya existias
DROP TRIGGER IF EXISTS triggerSalariosEmpleadoAfterInsert;
-- Creo el trigger
DELIMITER ??
CREATE TRIGGER triggerSalariosEmpleadoAfterInsert
-- AFTER INSERT porque voy a insertar un registro en la tabla salarios despues de que se inserte una fila en la tabla empleado, for each row porque quiero que se ejecute para cada fila
AFTER INSERT ON empleado FOR EACH ROW
BEGIN
    -- Inserto el registro en la tabla salarios
    INSERT INTO salarios (salario, codigo_empleado)
    -- Introduzco el salario y el codigo del empleado
    VALUES (NEW.SalEmp, NEW.CodEmp);
END??
DELIMITER ;

-- 5. triggerSalariosEmpleadoAfterUpdate
-- Borro el trigger si ya existias
DROP TRIGGER IF EXISTS triggerSalariosEmpleadoAfterUpdate;
-- Creo el trigger
DELIMITER ??
CREATE TRIGGER triggerSalariosEmpleadoAfterUpdate
-- AFTER UPDATE porque voy a insertar un registro en la tabla salarios despues de que se actualice una fila en la tabla empleado, for each row porque quiero que se ejecute para cada fila
AFTER UPDATE ON empleado FOR EACH ROW
BEGIN
    -- Solo actúa si el salario ha cambiado previamente, comparo el nuevo salario con el antiguo, si son diferentes entonces inserto el registro en la tabla salarios
    IF NEW.salEmp <> OLD.SalEmp THEN
        -- Inserto el registro en la tabla salarios
        INSERT INTO salarios (salario, codigo_empleado)
        -- Introduzco el nuevo salario y el codigo del empleado
        VALUES (NEW.SalEmp, NEW.CodEmp);
    END IF;
END??
DELIMITER ;


-- Casos de prueba

-- Inserto un nuevo empleado
INSERT INTO empleado (CodDep, NomEmp, SalEmp)
VALUES ('PROZS', 'Prueba Trigger', 2000000);

-- Consulto los salarios
SELECT * FROM salarios;

-- Actualizo el salario del nuevo empleado (debe registrarse cambio)
UPDATE empleado
SET SalEmp = 2500000
WHERE NomEmp = 'Prueba Trigger';

-- Hago otro update pero con el mismo salario (no debe registrarse cambio)
UPDATE empleado
SET SalEmp = 2500000
WHERE NomEmp = 'Prueba Trigger'
AND CodEmp = 11;

-- Consulto nuevamente los salarios, debe haber un registro del salario inicial y otro del cambio, pero no del update sin cambio
SELECT * FROM salarios;