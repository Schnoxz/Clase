-- Lista todos los departamentos de un determinado centro

DELIMITER $$
CREATE OR REPLACE PROCEDURE ac08listDepartamentos(IN pCodCen CHAR(4))
BEGIN
    SELECT d.CodDep, d.NomDep, d.PreAnu, d.TiDir FROM departamento d
    WHERE d.CodCen = pCodCen
    ORDER BY d.NomDep;
END$$
DELIMITER ;


-- Lista los departamentos de un centro y si recibe valor nulo, devuelve todos los departamentos

DELIMITER $$
CREATE OR REPLACE PROCEDURE ac08listDepartamentosPlus(IN pCodCen CHAR(4))
BEGIN
    -- Uso del IF para la condicion NULL, selecciona todos los departamentos
    IF pCodCen IS NULL THEN SELECT d.CodDep, d.NomDep, d.CodCen, d.PreAnu, d.TiDir FROM departamento d
    ORDER BY d.CodCen, d.NomDep;
    -- Si no es NULL, cumple la función principal de listar solo de un centro
    ELSE
        SELECT d.CodDep, d.NomDep, d.CodCen, d.PreAnu, d.TiDir FROM departamento d
        WHERE d.CodCen = pCodCen
        ORDER BY d.NomDep;
    END IF;
END$$
DELIMITER ;



-- Incrementa el salario de los empleados x cantidad a partir de un parámetro de entrada

DELIMITER $$

CREATE OR REPLACE PROCEDURE ac08updSalarioEmpleadosParam(IN pIncremento DECIMAL(12,2))
BEGIN
    -- Aplica el nuevo salario con el UPDATE y usa el SET al pasar
    UPDATE empleado
    SET SalEmp = SalEmp + pIncremento;

    -- Salarios después
    SELECT CodEmp, NomEmp, SalEmp AS SalarioDespues
    FROM empleado
    ORDER BY CodEmp;
END$$

DELIMITER ;

-- Comprobación, aumento el salario de todos los empleados
CALL ac08updSalarioEmpleadosParam(100000);


-- Devuelve la cantidad de empleados en un parámetro de salida

DELIMITER $$

CREATE OR REPLACE PROCEDURE ac08contarEmpleados(OUT totalEmpleados INT)
BEGIN
    SELECT COUNT(*) INTO totalEmpleados
    FROM empleado;
END$$

DELIMITER ;

-- Comprobación
CALL ac08contarEmpleados(@total);
SELECT @total AS TotalEmpleados;



-- Devuelve la cantidad de empleados de un determinado partamento (el cual se introduce por parámetro de entrada) en un parámetro de salida


DELIMITER $$
-- IN parámetro de entrada el código del departamento y OUT para el de salida
CREATE PROCEDURE ac08contarEmpleadosDpto(IN pCodDep CHAR(5), OUT totalEmpleados INT)
BEGIN
    SELECT COUNT(*) INTO totalEmpleados FROM empleado
    WHERE CodDep = pCodDep;
END$$

DELIMITER ;

-- Comprobación
CALL ac08contarEmpleadosDpto('PROZS', @total);
SELECT @total AS EmpleadosEnPROZS;

CALL ac08contarEmpleadosDpto('VENZS', @total);
SELECT @total AS EmpleadosEnVENZS;



-- Devuelve el sueldo menor, mayor y promedio de todos los empleados (con SET)

DELIMITER $$

CREATE OR REPLACE PROCEDURE ac08sueldosSet(OUT sueldoMinimo DECIMAL(12,2), OUT sueldoMaximo DECIMAL(12,2), OUT sueldoPromedio DECIMAL(12,2)
)
BEGIN
    -- Paso valor a los parámetros que se han definido via consulta
    SET sueldoMinimo = (SELECT MIN(SalEmp) FROM empleado);
    SET sueldoMaximo = (SELECT MAX(SalEmp) FROM empleado);
    SET sueldoPromedio = (SELECT AVG(SalEmp) FROM empleado);
END$$

DELIMITER ;

-- Comprobación
CALL ac08sueldosSet(@min, @max, @avg);
SELECT @min  AS SueldoMinimo, @max  AS SueldoMaximo, @avg  AS SueldoPromedio;


-- Devuelve el sueldo menor, el mayor y el promedio de todos los empleados

DELIMITER $$

CREATE OR REPLACE PROCEDURE ac08sueldosSelectInto(OUT sueldoMinimo DECIMAL(12,2), OUT sueldoMaximo DECIMAL(12,2), OUT sueldoPromedio DECIMAL(12,2)
)
BEGIN
    SELECT MIN(SalEmp), MAX(SalEmp), AVG(SalEmp)
    INTO sueldoMinimo, sueldoMaximo , sueldoPromedio
    FROM empleado;
END$$

DELIMITER ;

-- Prueba
CALL ac08sueldosSelectInto(@min, @max, @avg);
SELECT @min  AS SueldoMinimo, @max  AS SueldoMaximo, @avg  AS SueldoPromedio;