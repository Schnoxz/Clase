CREATE TABLE IF NOT EXISTS Dashboard_Estadisticas(
    id_informe INT AUTO_INCREMENT PRIMARY KEY,
    fecha_generacion DATETIME,
    total_socios_activos INT,
    ingresos_totales DECIMAL(10,2),
    mensaje_estado VARCHAR(255)
);

-- Creación de triggers

-- 1 Evita pegaos negativos y los establece en 0
DELIMITER ??
CREATE TRIGGER trg_validar_pago_positivo
BEFORE INSERT ON pago
FOR EACH ROW

BEGIN
    IF NEW.cantidad < 0 THEN 
    SET NEW.cantidad = 0;
    END IF;
END ?? 
DELIMITER ;


-- 2 Desactiva socio si la membresia caduca
DELIMITER ??
CREATE TRIGGER trg_desactivar_socio
AFTER UPDATE ON membresia
FOR EACH ROW
BEGIN
    IF OLD.estado != 'Caducada' AND NEW.estado = 'Caducada' THEN
    UPDATE Socio SET estado = 'Inactivo' WHERE id_socio = NEW.id_socio;
    END IF;
END ??
DELIMITER ;

-- Procedimiento que registra un pago con validación
DELIMITER ??

CREATE PROCEDURE sp_registrar_pago(
    IN p_id_membresia INT,
    IN p_cantidad DECIMAL(10,2),
    IN p_metodo VARCHAR(50),
    OUT p_mensaje VARCHAR(255)
)

BEGIN
    DECLARE v_existe INT DEFAULT 0;
    START TRANSACTION;
    SELECT COUNT(*) INTO v_existe FROM Membresia WHERE id_socio = p_id_membresia;
    
    IF v_existe = 0 THEN
        ROLLBACK;
        SET p_mensaje = 'Error: la membresía indicada no existe';
    ELSE
        INSERT INTO Pago(id_membresia, cantidad, fecha_pago, metodo_pago)
        VALUES (p_id_membresia, p_cantidad, NOW(), p_metodo);
        COMMIT;
    END IF;
END ??
DELIMITER ;

-- Procedimiento que envia un equipo a reparar
DELIMITER ??
CREATE PROCEDURE sp_enviar_reparacion(
    IN p_id_equipo INT,
    OUT p_mensaje VARCHAR(255)
)
BEGIN
    DECLAREA v_existe INT DEFAULT 0;
    STAR TRANSACTION;
    SELECT COUNT(*) INTO v_existe FROM Equipamiento WHERE id_equipamiento = p_id_equipo;

    IF v_existe = 0 THEN
        ROLLBACK;
        SET p_mensaje = 'Error: El equipamiento no existe';
    ELSE
        UPDATE Equipamiento SET estado = 'Reparacion' WHERE id_equipamiento = p_id_equipo;

        DELETE FROM Clase_Equipamiento WHERE id_equipamiento = p_id_equipo;
        COMMIT;
        SET p_mensaje = CONCAT ('Exito', p_id_equipo, 'enviado a reparar')
    END IF;
END ??
DELIMITER ;

-- Procedimiento con cursor que recopila emails y los guarda en la dashboard