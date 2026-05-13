DELIMITER ??

CREATE FUNCTION nivelRiesgo(pSaldo DECIMAL(12,2), pAniosAntiguedad INT)
RETURNS VARCHAR(20)
DETERMINISTIC
BEGIN
    DECLARE nivel VARCHAR(20);

    -- Uso de condicionales anidados para un examen complejo
    IF pSaldo < 0 THEN
        SET nivel = 'CRÍTICO';
    ELSEIF pSaldo BETWEEN 0 AND 5000 THEN
        IF pAniosAntiguedad > 5 THEN
            SET nivel = 'ESTABLE';
        ELSE
            SET nivel = 'RIESGO ALTO';
        END IF;
    ELSE
        SET nivel = 'PREFERENTE';
    END IF;

    RETURN nivel;
END??

DELIMITER ;

-- Cómo usarlo en una consulta:
-- SELECT NomEmp, SalEmp, nivelRiesgo(SalEmp, 3) FROM empleado;
