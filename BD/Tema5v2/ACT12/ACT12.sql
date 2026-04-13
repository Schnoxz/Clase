-- Procedimiento ac12mediaSalarial a partir de un codigo de departamento devuelve un parametro de salida del salario medio de los empleados de dicho departamento

DELIMITER ??
CREATE PROCEDURE ac12mediaSalarial(IN pCodDep CHAR(5), OUT media DECIMAL(12,2))
BEGIN
    -- Consulto la media del salario y guardo el valor en el parámetro de salida/variable media
    SELECT AVG(SalEmp) INTO media FROM empleado
    WHERE CodDep = pCodDep;
END??
DELIMITER ;



-- Función ac12categoriaDepartamento a partir de un código de departamento devuelve la categoría según el salario medio de sus empleados

DELIMITER ??
CREATE FUNCTION ac12categoriaDepartamento(pCodDep CHAR(5))
RETURNS VARCHAR(20)
BEGIN
    -- Declaro dos variables, la de la media y la de caregoria
    DECLARE media DECIMAL(12,2);
    DECLARE categoria VARCHAR(10);
    -- Llamo al procedimiento que calcula la media
    CALL ac12mediaSalarial(pCodDep, media);
    -- Valido la media con un IF
    IF media < 2000000 THEN SET categoria = 'bajo';
    ELSE
        IF media <= 5000000 THEN SET categoria = 'medio';
        ELSE
            SET categoria = 'alto';
        END IF;
    END IF;

    RETURN categoria; -- Devuelvo la caregoria
END??
DELIMITER ;


-- Creacion de la tabla informe_salarial
CREATE TABLE informe_salarial (
    CodDep CHAR(5),
    NomDep VARCHAR(40),
    NumEmpleados INT,
    SalarioMedio DECIMAL(12,2),
    Categoria VARCHAR(10)
);



-- Procedimiento ac12actualizaInforme que a partir de un código de departamento, actualiza la fila correspondiente de la tabla informe_salarial con la cantidad de empleados, salario medio y categoria.


DELIMITER ??
CREATE PROCEDURE ac12actualizaInforme(IN pCodDep CHAR(5))
BEGIN
    -- Declaro la variable media, numero de empleados y categoria
    DECLARE media  DECIMAL(12,2);
    DECLARE numEmp INT;
    DECLARE categoria VARCHAR(10);
    -- Llam al procedimiento para saber la media del salario en el departamento
    CALL ac12mediaSalarial(pCodDep, media);
    -- Cuento el numero de empleados de ese departamento
    SELECT COUNT(*) INTO numEmp FROM empleado
    WHERE CodDep = pCodDep;
    -- A la variable categoria le otorgo la categoría que merece el departamento con la función anterior
    SET categoria = ac12categoriaDepartamento(pCodDep);
    -- Actualizo la tabla con el salario medio, la cantidad de empleados y la categoria en el departamento en concreto
    UPDATE informe_salarial
    SET NumEmpleados = numEmp, SalarioMedio = media, Categoria = categoria
    WHERE CodDep = pCodDep;
END??
DELIMITER ;

-- Insertar fila solo con CodDep y NomDep (el resto queda NULL)
INSERT INTO informe_salarial (CodDep, NomDep)
VALUES ('PROZS', 'Producción Zona Sur');

-- Rellenar el resto con el procedimiento
CALL ac12actualizaInforme('PROZS');

-- Verifica el resultado
SELECT * FROM informe_salarial;