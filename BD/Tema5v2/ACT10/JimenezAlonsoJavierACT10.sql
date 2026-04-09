/* 
Comprueba las funciones existentes en la base de datos empresa. 
*/




/*
-- PROCEDIMIENTO ac07contarEmpleados

-- Nos muuestra la cantidad total de empleados
DELIMITER $$
CREATE OR REPLACE PROCEDURE ac07contarEmpleados()
BEGIN
    SELECT COUNT(*) AS totalEmpleados FROM empleado;
END$$
DELIMITER ;

*/

-- Funcion ac10contarEmpleado y ac1009contarEmpleadosDpto reescribiendo los procedimientos ac10contarEmpleados y ac1003contarEmpleadosDpto de la actividad ACT 07.

-- Funcion ac10contarEmpleados
DELIMITER ??
-- Como vamos a contar el total, retornamos un int
CREATE OR REPLACE FUNCTION ac10contarEmpleados()
RETURNS INT
BEGIN
    DECLARE totalEmpleados INT; -- Creo una variable donde guarde el sumatorio de empleados
    SELECT COUNT(*) INTO totalEmpleados FROM empleado; -- Uso COUNT(*) para contar el total de empleados y lo guardo en la variable
    RETURN ??

DELIMITER ;
-- Select de la funcion 
SELECT ac10contarEmpleados();

-- Funcion ac1009contarEmpleadosDpto
DELIMITER ??

CREATE OR REPLACE FUNCTION ac1009contarEmpleadosDpto(pCodDep CHAR(5)) -- Ahora necestio el codigo del departamento
RETURNS INT
BEGIN
    DECLARE totalEmpleados INT; -- Misma variable
    SELECT COUNT(*) INTO totalEmpleados FROM empleado -- Misma consulta
    WHERE empleado.CodDep = pCodDep; -- Ahora verifico el departamento
    RETURN totalEmpleados;
END ??

DELIMITER ;

-- Select de la función con un código de departamento válido
SELECT ac1009contarEmpleadosDpto('JEFZS');



-- La función ac10presupuestoCentro que, a partir del código de un centro, devuelva su presupuesto (calculado como la suma de los presupuestos de sus departamentos).

DELIMITER ??

CREATE OR REPLACE FUNCTION ac10presupuestoCentro(pCodCen CHAR(4)) -- Paso por arametro del codigo del centro
RETURNS DECIMAL(10,2)
BEGIN
    DECLARE totalPresupuesto DECIMAL(10,2); -- Variable para guardar el sumatorio
    SELECT SUM(departamento.PreAnu) INTO totalPresupuesto FROM departamento -- Segun la tabla existe el campo de presupuesto anual
    WHERE departamento.CodCen = pCodCen;
    RETURN totalPresupuesto;
END ??
DELIMITER ;

-- Select de la función con un código de centro valido
SELECT ac10presupuestoCentro('DIGE');

-- La función ac10totalHabilidadesEmpleado que, a partir de un código de un empleado, devuelva cuantas habilidades tiene.

DELIMITER ??
CREATE OR REPLACE FUNCTION ac10totalHabilidadesEmpleado(pCodEmp CHAR(5)) -- Paso por arametro del codigo del empleado
RETURNS INT
BEGIN 
    DECLARE totalHabilidades INT; -- Variable para guardar el sumatorio
    SELECT COUNT(*) INTO totalHabilidades FROM habemp -- Tabla donde estan las habilidades y empleados
    WHERE habemp.CodEmp = pCodEmp; -- Verifico que sea el empleado que esté en el campo dentro de habemp en CodEmp
    RETURN totalHabilidades;
END ??
DELIMITER ;

-- Select de la función con un código de empleado válido
SELECT ac10totalHabilidadesEmpleado(1);

-- La función ac10totalEmpleadosHabilidad que, a partir de un código de una habilidad, devuelva cuantos empleados la tienen.

DELIMITER ??
CREATE OR REPLACE FUNCTION ac10totalEmpleadosHabilidad(pCodHab CHAR(5)) -- Paso por arametro del codigo de la habilidad
RETURNS INT
BEGIN 
    DECLARE totalEmpleados INT; -- Variable para guardar el sumatorio
    SELECT COUNT(*) INTO totalEmpleados FROM habemp -- Tabla donde estan las habilidades y empleados
    WHERE habemp.CodHab = pCodHab; -- Verifico que sea la habilidad que esté en el campo dentro de habemp en CodHab

    RETURN totalEmpleados;
END ??
DELIMITER ;

-- Select de la función con un código de habilidad valido
SELECT ac10totalEmpleadosHabilidad('GEREN');



-- La función ac10directorCentro que, a partir del código de un centro, devuelva el nombre de su director.
DELIMITER ??
CREATE OR REPLACE FUNCTION ac10directorCentro(codigo CHAR(5))
RETURNS VARCHAR(50)

BEGIN
    DECLARE director VARCHAR(50);
    SELECT NomEmp INTO director -- Paso el valor del campo NomEmp a la variable director
    FROM centro c INNER JOIN empleado e ON c.CodEmpDir = e.CodEmp -- Saco el empleado director del centro
    WHERE c.CodCen = codigo; -- Verifico que sea el centro que esté en el campo dentro de centro en CodCen
    RETURN director;
END ??

DELIMITER ;

-- Select de la función con un código de centro valido
SELECT ac10directorCentro('DIGE');



-- La función ac10emailEmpleado que, a partir de un código de empleado, devuelva su email con la siguiente nomenclatura: CodEmp@CodDep.CodCen.com

DELIMITER ??
CREATE OR REPLACE FUNCTION ac10emailEmpleado(codigoEmp INT)
RETURNS VARCHAR(50)
BEGIN   
    DECLARE email VARCHAR(50);
    SELECT CONCAT(e.CodEmp, '@', e.CodDep, '.', d.CodCen, '.com') INTO email -- Concateno los valores en formato email y lo asigno a la variable
    FROM empleado e INNER JOIN departamento d ON e.CodDep = d.CodDep -- Saco el departamento del empleado
    WHERE e.CodEmp = codigoEmp; -- Verifico que sea el empleado que esté en el campo dentro de empleado en CodEmp
    RETURN email;
END ??
DELIMITER ;

-- Select de la función con un código de empleado valido
SELECT ac10emailEmpleado(1);




-- La función ac10validaHijosEmpleados que a partir de un código de un empleado, compruebe si la cantidad de hijos de la tabla empleado coinciden con los de la tabla hijo.

DELIMITER ??
CREATE OR REPLACE FUNCTION ac10validaHijosEmpleados(codigoEmp INT)
RETURNS BOOLEAN -- Booleano porque nos pide comprobar 
BEGIN
    DECLARE hijosEmpleado, hijosHijo INT; -- Declaro dos variables que se van a comprar a posteriori
    SELECT COUNT(*) INTO hijosEmpleado FROM empleado WHERE CodEmp = codigoEmp; -- Contamos los hijos del empleado
    SELECT COUNT(*) INTO hijosHijo FROM hijo WHERE CodEmp = codigoEmp; -- Se comprueba que los hijos sea la misma cantidad que la de la tabla hijo
    RETURN hijosEmpleado = hijosHijo;
END ??
DELIMITER ;

-- Select de la función con un código de empleado valido
SELECT ac10validaHijosEmpleados(1);