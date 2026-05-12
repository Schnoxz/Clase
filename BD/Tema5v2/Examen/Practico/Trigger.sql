DELIMITER ??

CREATE TRIGGER tg_verificarEquidadSalarial
BEFORE UPDATE ON empleado FOR EACH ROW
BEGIN
    DECLARE vMediaSalarial DECIMAL(12,2);

    -- Usamos la lógica de cálculo de media de tu ACT12.sql
    SELECT AVG(SalEmp) INTO vMediaSalarial
    FROM empleado
    WHERE CodDep = NEW.CodDep;

    -- Si el nuevo salario es > 200% de la media, lanzamos error
    IF NEW.SalEmp > (vMediaSalarial * 2) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El salario excede el límite de equidad del departamento (200% de la media).';
    END IF;

    -- Si el salario baja, lo registramos en una tabla de incidencias (similar a ACT15.sql)
    IF NEW.SalEmp < OLD.SalEmp THEN
        INSERT INTO logCambiosEmail (idAlumno, fechaHora, oldEmail, newEmail) -- Reutilizando estructura de log
        VALUES (OLD.CodEmp, NOW(), 'Bajada Salarial', CAST(NEW.SalEmp AS CHAR));
    END IF;
END??

DELIMITER ;
