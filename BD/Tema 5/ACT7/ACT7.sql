
-- PROCEDIMIENTO ac07listEmpleadosConHijos

-- Muestra los empleados que tienen hijos
DELIMITER $$
CREATE OR REPLACE PROCEDURE ac07listEmpleadosConHijos()
BEGIN
    SELECT CodEmp, NomEmp, NumHi FROM empleado
    WHERE NumHi > 0;
END$$
DELIMITER ;

-- Llamada:
CALL ac07listEmpleadosConHijos();



-- PROCEDIMIENTO ac07contarEmpleados

-- Nos muuestra la cantidad total de empleados
DELIMITER $$
CREATE OR REPLACE PROCEDURE ac07contarEmpleados()
BEGIN
    SELECT COUNT(*) AS totalEmpleados FROM empleado;
END$$
DELIMITER ;

-- Llamada al PROCEDIMIENTO
CALL ac07contarEmpleados();



-- Incrementa el salario de todos los empleados un 10%
DELIMITER $$
CREATE OR REPLACE PROCEDURE ac07updSalarioEmpleados()
BEGIN
    UPDATE empleado SET SalEmp = SalEmp * 1.10;
END$$
DELIMITER ;

-- Llamada al PROCEDIMIENTO
CALL ac07updSalarioEmpleados();



-- Muestra los procedimientos existentes en la base de datos
SHOW PROCEDURE STATUS WHERE db = 'empresa';



-- Elimino el procedimiento ac07updSalarioEmpleados
DROP PROCEDURE ac07updSalarioEmpleados;
