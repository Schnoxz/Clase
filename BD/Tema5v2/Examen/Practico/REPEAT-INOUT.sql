DELIMITER ??

CREATE PROCEDURE simularAgotamientoPresupuesto(
    IN pCodDep CHAR(5),
    IN pGastoMensualInicial DECIMAL(12,2),
    INOUT pMesesResistencia INT -- Entrada: estimación inicial / Salida: resultado real
)
BEGIN
    DECLARE vPresupuestoActual DECIMAL(12,2);
    DECLARE vTasaInflacion DECIMAL(4,3) DEFAULT 1.02; -- 2% mensual
    DECLARE vMesesContados INT DEFAULT 0;

    -- Obtenemos el presupuesto inicial de la tabla (basado en ACT12.sql)
    SELECT PreAnu INTO vPresupuestoActual FROM departamento WHERE CodDep = pCodDep;

    -- Bucle REPEAT: Se ejecuta al menos una vez
    REPEAT
        SET vPresupuestoActual = vPresupuestoActual - pGastoMensualInicial;
        SET pGastoMensualInicial = pGastoMensualInicial * vTasaInflacion;
        SET vMesesContados = vMesesContados + 1;
    -- Se detiene cuando el presupuesto es insuficiente o superamos la estimación del usuario
    UNTIL vPresupuestoActual <= 0 OR vMesesContados >= 120 END REPEAT;

    SET pMesesResistencia = vMesesContados;
END??

DELIMITER ;
