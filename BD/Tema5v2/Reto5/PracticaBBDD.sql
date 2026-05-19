USE FitNet;


-- 1. Creación de la tavla Dashboard_Estadisticas

-- Creamos esta tabla primero porque el último punto de la práctica nos pide guardar el informe estadístico en un "dashboard"
CREATE TABLE IF NOT EXISTS Dashboard_Estadisticas (
    id_informe INT AUTO_INCREMENT PRIMARY KEY,
    fecha_generacion DATETIME,
    total_socios_activos INT,
    ingresos_totales DECIMAL(10,2),
    mensaje_estado VARCHAR(255)
);


-- 2. Triggers

-- TRIGGER 1: Evitar pagos negativos o a cero
-- Uso before insert porque se valida el valor de 'cantidad' antes de que se guarde en la tabla

DELIMITER $$
CREATE TRIGGER trg_validar_pago_positivo
BEFORE INSERT ON Pago
FOR EACH ROW
BEGIN
    -- Verifica si la cantidad introducida es menor que 0
    IF NEW.cantidad < 0 THEN
        -- Como estamos en el BEFORE, podemos modificar el valor antes de guardarlo
        SET NEW.cantidad = 0;
    END IF;
END $$
DELIMITER ;

-- TRIGGER 2: Desactivar socio si su membresía caduca.
-- Uso after porque quiero que la membresía se actualice correctamente. Una vez confirmado ese se dispara la actualización en la tabla Socio

DELIMITER $$
CREATE TRIGGER trg_desactivar_socio
AFTER UPDATE ON Membresia
FOR EACH ROW
BEGIN
    -- Solo actua si el estado acaba de cambiar estrictamente a 'Caducada'.
    IF OLD.estado != 'Caducada' AND NEW.estado = 'Caducada' THEN
        -- Actualiza el socio correspondiente segun el ID
        UPDATE Socio SET estado = 'Inactivo' WHERE id_socio = NEW.id_socio;
    END IF;
END $$
DELIMITER ;


-- 3. Procedimientos Almacenados con Transacciones

-- PROCEDIMIENTO 1: Registrar un pago con validación

DELIMITER $$
CREATE PROCEDURE sp_registrar_pago(
    IN p_id_membresia INT,
    IN p_cantidad DECIMAL(10,2),
    IN p_metodo VARCHAR(50),
    OUT p_mensaje VARCHAR(255)
)
BEGIN
    DECLARE v_existe INT DEFAULT 0; -- Variable para comprobar si la membresía existe
    START TRANSACTION;
    SELECT COUNT(*) INTO v_existe FROM Membresia WHERE id_socio = p_id_membresia; -- Verificamos que la membresía existe antes de intentar registrar el pago

    IF v_existe = 0 THEN
        -- Si la membresía no existe, deshace cualquier cambio con rollback y manda un error, similar al control de errores que estamos haciendo con java
        ROLLBACK;
        SET p_mensaje = 'Error: La membresía indicada no existe';
    ELSE
        -- Si existe, inserta el pago.
        INSERT INTO Pago (id_membresia, cantidad, fecha_pago, metodo_pago)
        VALUES (p_id_membresia, p_cantidad, NOW(), p_metodo);
        COMMIT;
    END IF;
END $$
DELIMITER ;

-- PROCEDIMIENTO 2: Enviar equipo a reparar

DELIMITER $$
CREATE PROCEDURE sp_enviar_reparacion(
    IN p_id_equipo INT,
    OUT p_mensaje VARCHAR(255)
)
BEGIN
    DECLARE v_existe INT DEFAULT 0; -- Igual que antes, valida si existe o no
    START TRANSACTION;
    SELECT COUNT(*) INTO v_existe FROM Equipamiento WHERE id_equipamiento = p_id_equipo;

    IF v_existe = 0 THEN
        ROLLBACK;
        SET p_mensaje = 'Error: El equipamiento no existe';
    ELSE
        -- Acción 1: Lo marcamos como en reparación.
        UPDATE Equipamiento SET estado = 'Reparación' WHERE id_equipamiento = p_id_equipo;

        -- Acción 2: Lo borramos de la relación con las clases para que ningún
        -- entrenador lo espere en su clase.
        DELETE FROM Clase_Equipamiento WHERE id_equipamiento = p_id_equipo;

        COMMIT; -- Confirmamos las DOS acciones conjuntas.
        SET p_mensaje = CONCAT('Éxito: Equipo ', p_id_equipo, ' enviado a reparar y sacado de las clases.');
    END IF;
END $$
DELIMITER ;



-- 4. Procedimientos Almacenados con Cursores


-- PROCEDIMIENTO 3: Recopilar emails para enviar promociones de forma automatizada
DELIMITER $$
CREATE PROCEDURE sp_emails(OUT p_lista_emails VARCHAR(4000), OUT p_mensaje VARCHAR(255))
BEGIN
    DECLARE v_terminado INTEGER DEFAULT 0;
    DECLARE v_email VARCHAR(100) DEFAULT "";
    -- Declaración del cursor que recoge los emails
    DECLARE c_socios_activos CURSOR FOR SELECT u.email FROM Usuario u
        INNER JOIN Socio s ON u.id_usuario = s.id_socio -- Inner join que une las tablas donde el id_usuario coincide con el id_socio para sus emails
        WHERE s.estado = 'Activo'; -- Solo los activos

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET v_terminado = 1;

    SET p_lista_emails = ""; -- Variable vacia donde se van a guardar las concatenaciones
    -- Se abre el cursro
    OPEN c_socios_activos;

    bucle_emails: LOOP
        -- Fetch para traer el siguiente email
        FETCH c_socios_activos INTO v_email;
        -- CCondicion de salida
        IF v_terminado = 1 THEN
            LEAVE bucle_emails;
        END IF;
        -- Si hay correo, se concatena
        SET p_lista_emails = CONCAT(p_lista_emails, v_email, ', ');
    END LOOP bucle_emails;
    -- Se cierra
    CLOSE c_socios_activos;
    -- Variable de mensaje para indicar que el proceso se ha completado correctamente
    SET p_mensaje = 'Generación de lista de emails completada con éxito.';
END $$
DELIMITER ;

-- PROCEDIMIENTO 4: Auditoría de máquinas operativas
-- Recorre las máquinas y cuenta cuántas están operativas
DELIMITER $$
CREATE PROCEDURE sp_auditoria_maquinas(OUT p_mensaje VARCHAR(255)) -- Solo una variable de salida para mostrar el resultado final
BEGIN
    DECLARE v_terminado INTEGER DEFAULT 0;
    DECLARE v_nombre VARCHAR(100);
    DECLARE v_contador INT DEFAULT 0;
    -- Cursor declarado para seleccionar solo los equipos que están operativos.
    DECLARE c_equipos CURSOR FOR
        SELECT nombre_equipo FROM Equipamiento WHERE estado = 'Operativo';
    -- Handler
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET v_terminado = 1;
    -- Cursor abierto
    OPEN c_equipos;
    -- Bucle para recorrer los equipos operativos
    bucle_equipos: LOOP
        FETCH c_equipos INTO v_nombre;
        -- Condicion de salida del bucle
        IF v_terminado = 1 THEN
            LEAVE bucle_equipos;
        END IF;
        -- Se suma uno al contador
        SET v_contador = v_contador + 1;
    END LOOP bucle_equipos;
    -- Se cierra
    CLOSE c_equipos;
    -- Mensaje final que muestra el numero total de equipos encontrados operativos
    SET p_mensaje = CONCAT('Auditoría finalizada. Hay ', v_contador, ' equipos operativos en el gimnasio.');
END $$
DELIMITER ;



-- 5. Funciones Almacenadas


-- FUNCIÓN 1: Sumar todos los ingresos históricos
DELIMITER $$
CREATE FUNCTION fn_ingresos_totales() RETURNS DECIMAL(10,2)
BEGIN
    DECLARE v_total DECIMAL(10,2) DEFAULT 0; -- Default para que si encuentra un null no de errores
    --  Guarda la suma en la variable
    SELECT SUM(cantidad) INTO v_total FROM Pago;
    -- Si la tabla Pago está vacía, SUM devuelve NULL. Lo transforma a 0 para evitar errores
    IF v_total IS NULL THEN
        SET v_total = 0;
    END IF;
    RETURN(v_total);
END $$
DELIMITER ;

-- FUNCIÓN 2: Contar socios que están activos actualmente
DELIMITER $$
CREATE FUNCTION fn_socios_activos() RETURNS INT
BEGIN
    DECLARE v_conteo INT DEFAULT 0; -- Variable para almacenar el conteo de socios activos
    -- Cuenta los socios con estado 'Activo' y guarda el resultado en la variable
    SELECT COUNT(*) INTO v_conteo FROM Socio WHERE estado = 'Activo';
    RETURN(v_conteo);
END $$
DELIMITER ;


-- 6. Script que genera el informe para el dashboard
-- Este script es un procedimiento almacenado que llama a las funciones anteriores, recopila sus resultados y los guarda en la tabla Dashboard_Estadisticas, además de mostrar el informe generado al final, con un timestamp de registro.

DELIMITER $$
CREATE PROCEDURE sp_generar_dashboard()
BEGIN
    DECLARE v_ingresos DECIMAL(10,2) DEFAULT 0.0;
    DECLARE v_socios_activos INT DEFAULT 0;
    --  Las llamamos y asignamos su resultado a nuestras variables locales.
    SET v_ingresos = fn_ingresos_totales();
    SET v_socios_activos = fn_socios_activos();
    -- Se inserta el registro con la fecha y hora actual
    INSERT INTO Dashboard_Estadisticas (fecha_generacion, total_socios_activos, ingresos_totales)
    VALUES (NOW(), v_socios_activos, v_ingresos);
    -- Se muestra el informe en orden descendente para que el último registro sea el primero en verse como en java el principio del stack, último en entrar, primero en salir
    SELECT * FROM Dashboard_Estadisticas ORDER BY id_informe DESC LIMIT 1;
END $$
DELIMITER ;
