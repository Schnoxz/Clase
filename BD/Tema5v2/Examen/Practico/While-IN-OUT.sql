DELIMITER ??

CREATE PROCEDURE generarPlanFormacion()
BEGIN
    DECLARE fin INT DEFAULT 0;
    DECLARE vNomEmp VARCHAR(100);
    DECLARE vNumHi INT;
    DECLARE vContador INT;

    -- Cursor para empleados con hijos (Lógica de ACT14.sql)
    DECLARE curEmpleados CURSOR FOR
        SELECT NomEmp, NumHi FROM empleado WHERE NumHi > 0;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;

    OPEN curEmpleados;

    bucle_principal: LOOP
        FETCH curEmpleados INTO vNomEmp, vNumHi;
        IF fin = 1 THEN LEAVE bucle_principal; END IF;

        -- Por cada hijo, el empleado recibe un módulo de "Conciliación"
        -- Usamos WHILE para demostrar la estructura
        SET vContador = 1;
        WHILE vContador <= vNumHi DO
            -- Imaginemos que insertamos en una tabla de cursos
            INSERT INTO salarios (salario, codigo_empleado) -- Usando tabla existente para el ejemplo
            VALUES (vContador, (SELECT CodEmp FROM empleado WHERE NomEmp = vNomEmp));

            SET vContador = vContador + 1;
        END WHILE;

    END LOOP;

    CLOSE curEmpleados;
END??

DELIMITER ;



