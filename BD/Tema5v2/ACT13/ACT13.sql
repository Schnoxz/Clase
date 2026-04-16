--1 Crea la tabla empleado_copia con la misma estructura que la tabla empleado (mediante CREATE TABLE .... LIKE ...)..
--2 Crea el procedimiento ac1111empleadosSinHijos, para que, utilizando cursores y recuperando todos los empleados que no tienen hijos en la consulta, recorra el resultado e inserte en empleado_copia aquellos empleados que no tiene hijos.
--3 Crea el procedimiento ac1111empleadosNumHijos, para que, utilizando cursores con parámetros, recorra la tabla de empleados e inserte en empleado_copia aquellos empleados que tienen la cantidad de hijos recibidos como un parámetro de entrada del procedimiento.

-- 1. Crear tabla copia
DROP TABLE IF EXISTS empleado_copia;
CREATE TABLE empleado_copia LIKE empleado;


-- 2. Procedimiento: empleados sin hijos
DELIMITER ??
CREATE OR REPLACE PROCEDURE ac1111empleadosSinHijos()
BEGIN
    DECLARE fin     INT DEFAULT 0;
    DECLARE vCodEmp INT;
    DECLARE vCodDep CHAR(5);
    DECLARE vExTel  VARCHAR(9);
    DECLARE vFecIn  DATE;
    DECLARE vFecNa  DATE;
    DECLARE vNif    VARCHAR(9);
    DECLARE vNom    VARCHAR(40);
    DECLARE vNumHi  INT;
    DECLARE vSal    DECIMAL(12,2);

    DECLARE cur CURSOR FOR
        SELECT CodEmp, CodDep, ExTelEmp, FecInEmp, FecNaEmp, NifEmp, NomEmp, NumHi, SalEmp
        FROM empleado
        WHERE CodEmp NOT IN (SELECT DISTINCT CodEmp FROM hijo);

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;

    OPEN cur;
    WHILE fin = 0 DO
        FETCH cur INTO vCodEmp, vCodDep, vExTel, vFecIn, vFecNa, vNif, vNom, vNumHi, vSal;
        IF fin = 0 THEN
            INSERT INTO empleado_copia
                VALUES (vCodEmp, vCodDep, vExTel, vFecIn, vFecNa, vNif, vNom, vNumHi, vSal);
        END IF;
    END WHILE;
    CLOSE cur;

END ??
DELIMITER ;


-- 3. Procedimiento: empleados con N hijos (cursor con parámetro)
DELIMITER ??
CREATE OR REPLACE PROCEDURE ac1111empleadosNumHijos(IN pNumHijos INT)
BEGIN
    DECLARE fin INT DEFAULT 0;
    DECLARE vCodEmp INT;
    DECLARE vCodDep CHAR(5);
    DECLARE vExTel VARCHAR(9);
    DECLARE vFecIn DATE;
    DECLARE vFecNa DATE;
    DECLARE vNif VARCHAR(9);
    DECLARE vNom VARCHAR(40);
    DECLARE vNumHi INT;
    DECLARE vSal DECIMAL(12,2);

    DECLARE cur CURSOR FOR
        SELECT CodEmp, CodDep, ExTelEmp, FecInEmp, FecNaEmp, NifEmp, NomEmp, NumHi, SalEmp
        FROM empleado
        WHERE NumHi = pNumHijos;   -- ← parámetro de entrada usado en el cursor

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;

    OPEN cur;
    WHILE fin = 0 DO
        FETCH cur INTO vCodEmp, vCodDep, vExTel, vFecIn, vFecNa, vNif, vNom, vNumHi, vSal;
        IF fin = 0 THEN
            INSERT INTO empleado_copia VALUES (vCodEmp, vCodDep, vExTel, vFecIn, vFecNa, vNif, vNom, vNumHi, vSal);
        END IF;
    END WHILE;
    CLOSE cur;

END ??
DELIMITER ;