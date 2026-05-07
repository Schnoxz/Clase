-- =============================================
-- a) Tabla notificaciones + Trigger
-- =============================================

CREATE TABLE IF NOT EXISTS notificaciones (
    id             INT UNSIGNED    NOT NULL AUTO_INCREMENT,
    fecha_hora     TIMESTAMP       NOT NULL,
    total          DECIMAL(10,2)   NOT NULL,
    codigo_cliente INT             NOT NULL,
    PRIMARY KEY (id)
);


DELIMITER ??
CREATE TRIGGER trigger_notificar_pago
AFTER INSERT ON pago          -- Se dispara después de insertar en pago
FOR EACH ROW                  -- Por cada fila insertada
BEGIN
    INSERT INTO notificaciones (fecha_hora, total, codigo_cliente)
    VALUES (NOW(), NEW.total, NEW.codigo_cliente);
    --              ↑ NEW.columna accede al valor recién insertado
END ??
DELIMITER ;


-- =============================================
-- b) Comprobación del trigger
-- =============================================

-- 1. Vemos el estado inicial de notificaciones (debe estar vacía)
SELECT * FROM notificaciones;

-- 2. Insertamos un pago de prueba
INSERT INTO pago (codigo_cliente, forma_pago, id_transaccion, fecha_pago, total)
VALUES (1, 'TRANSFERENCIA', 'TEST-001', '2024-01-15', 1500.00);

-- 3. Comprobamos que el trigger ha insertado automáticamente en notificaciones
SELECT * FROM notificaciones;

-- 4. Insertamos otro pago para verificar que funciona varias veces
INSERT INTO pago (codigo_cliente, forma_pago, id_transaccion, fecha_pago, total)
VALUES (3, 'PAYPAL', 'TEST-002', '2024-01-16', 850.50);

-- 5. Comprobamos que ahora hay dos registros en notificaciones
SELECT * FROM notificaciones;

-- 6. Verificamos que los datos coinciden con los pagos insertados
SELECT p.codigo_cliente, p.total AS total_pago,
       n.fecha_hora, n.total AS total_notificacion
FROM pago p
JOIN notificaciones n ON p.codigo_cliente = n.codigo_cliente
WHERE p.id_transaccion IN ('TEST-001', 'TEST-002');