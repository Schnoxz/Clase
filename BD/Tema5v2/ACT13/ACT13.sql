-- 1. CreaCrea la tabla empleado_copia con la misma estructura que la tabla empleado (mediante CREATE TABLE .... LIKE ...)..
CREATE TABLE empleado_copia LIKE empleado;


-- 2. Crea el procedimiento ac1111empleadosSinHijos, para que, utilizando cursores y recuperando todos los empleados que no tienen hijos en la consulta, recorra el resultado e inserte en empleado_copia aquellos empleados que no tiene hijos.
DELIMITER ??
CREATE OR REPLACE PROCEDURE ac1111empleadosSinHijos()
BEGIN
    -- Declaro las variables que voy a usar
  DECLARE e ROW TYPE OF empleado;
  DECLARE fin INT DEFAULT 0;
    -- Declaro el cursor para seleccionar los empleados sin hijos
  DECLARE cur CURSOR FOR
    -- Selecciono los empleados cuyo código no está en la tabla hijo, es decir, los empleados sin hijos
    SELECT * FROM empleado WHERE CodEmp NOT IN (SELECT DISTINCT CodEmp FROM hijo);
    -- Declaro el handler para cuando no se encuentren más filas, se establezca la variable fin a 1
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;
-- Abro el cursor y comienzo el bucle para insertar los empleados sin hijos en la tabla copia
  OPEN cur;
  bucle: LOOP
    -- Traigo el siguiente empleado del cursor y lo inserto en la tabla copia, si no hay más filas, salgo del bucle
    FETCH cur INTO e;
        IF fin = 1 THEN
            LEAVE bucle;
    END IF;
    -- Inserto el empleado en la tabla copia
    INSERT INTO empleado_copia
    VALUES (e.CodEmp, e.CodDep, e.ExTelEmp, e.FecInEmp, e.FecNaEmp, e.NifEmp, e.NomEmp, e.NumHi, e.SalEmp
    );
  END LOOP;
-- CIerro el cursor
  CLOSE cur;
END ??
DELIMITER ;


-- 3. Crea el procedimiento ac1111empleadosNumHijos, para que, utilizando cursores con parámetros, recorra la tabla de empleados e inserte en empleado_copia aquellos empleados que tienen la cantidad de hijos recibidos como un parámetro de entrada del procedimiento.
DELIMITER ??
CREATE OR REPLACE PROCEDURE ac1111empleadosNumHijos(IN pNumHijos INT)
BEGIN
  DECLARE e ROW TYPE OF empleado;
  DECLARE fin INT DEFAULT 0;
    -- La diferencia con el anterior cursor es que aquí selecciono los empleados cuyo número de hijos es igual al parámetro de entrada pNumHijos
  DECLARE cur CURSOR FOR SELECT * FROM empleado WHERE NumHi = pNumHijos;

  DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;

  OPEN cur;
  bucle: LOOP
    FETCH cur INTO e;
        IF fin = 1 THEN
            LEAVE bucle;
    END IF;
    INSERT INTO empleado_copia
    VALUES (e.CodEmp, e.CodDep, e.ExTelEmp, e.FecInEmp, e.FecNaEmp, e.NifEmp, e.NomEmp, e.NumHi, e.SalEmp);
  END LOOP;
-- Cierro el cursor
  CLOSE cur;
END ??
DELIMITER ;


-- No tengo el xampp para probarlo pero creo que funciona, según vi mientras compartías pantalla es lo que se pide
