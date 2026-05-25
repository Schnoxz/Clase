CREATE DATABASE IF NOT EXISTS FitNet;
USE FitNet;

-- SECCIÓN 1: CREACIÓN DE TABLAS

-- 1. Tabla Padre: Usuario
-- Eliminado el atributo derivado nombre_completo
CREATE TABLE Usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    fecha_nacimiento DATE NOT NULL
);

-- 2. Tabla Plan (que define los tipos de membresia por precio y duracion)
CREATE TABLE Plan (
    id_plan INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,       -- 'Mensual', 'Trimestral', 'Anual'
    duracion_meses INT NOT NULL,       -- 1, 3, 12
    precio_base DECIMAL(7,2) NOT NULL  -- 29.99, 69.99, 129.99
);

-- 3. Tabla Hija: Socio
-- Relación 1:1 con Usuario
CREATE TABLE Socio (
    id_socio INT PRIMARY KEY,
    fecha_registro DATE DEFAULT (CURRENT_DATE),
    estado VARCHAR(50) DEFAULT 'Activo', -- Ej: Activo, Inactivo
    FOREIGN KEY (id_socio) REFERENCES Usuario(id_usuario) ON DELETE CASCADE
);

-- 4. Tabla Membresia
CREATE TABLE Membresia (
    id_membresia INT PRIMARY KEY AUTO_INCREMENT,
    id_socio INT NOT NULL,
    id_plan INT NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    estado VARCHAR(50) DEFAULT 'Vigente', -- Ej: Vigente, Caducada, Suspendida
    FOREIGN KEY (id_plan) REFERENCES Plan(id_plan) ON DELETE RESTRICT,
    FOREIGN KEY (id_socio) REFERENCES Socio(id_socio) ON DELETE CASCADE
);

-- 5. Tabla Hija: Entrenador
-- Relación 1:1 con Usuario
CREATE TABLE Entrenador (
    id_entrenador INT PRIMARY KEY,
    especialidad VARCHAR(100) NOT NULL,
    certificado VARCHAR(100),
    FOREIGN KEY (id_entrenador) REFERENCES Usuario(id_usuario) ON DELETE CASCADE
);

-- 6. TABLA CLASE
-- Relación 1:N (Un Entrenador imparte N Clases)
CREATE TABLE Clase (
    id_clase INT PRIMARY KEY AUTO_INCREMENT,
    id_entrenador INT NOT NULL,
    nombre_clase VARCHAR(100) NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    dia_semana VARCHAR(20),
    cupo_maximo INT DEFAULT 20,
    FOREIGN KEY (id_entrenador) REFERENCES Entrenador(id_entrenador) ON DELETE RESTRICT
);

-- 7. TABLA EQUIPAMIENTO
CREATE TABLE Equipamiento (
    id_equipamiento INT PRIMARY KEY AUTO_INCREMENT,
    nombre_equipo VARCHAR(100) NOT NULL,
    tipo VARCHAR(50),
    estado VARCHAR(50) DEFAULT 'Operativo', -- Ej: Operativo, En Mantenimiento, Reparación
    fecha_adquisicion DATE
);

-- 8. TABLA PAGO
-- Relación 1:N (Socio realiza Pagos)
CREATE TABLE Pago (
    id_pago INT PRIMARY KEY AUTO_INCREMENT,
    id_membresia INT NOT NULL,
    cantidad DECIMAL(10, 2) NOT NULL,
    fecha_pago DATETIME DEFAULT CURRENT_TIMESTAMP,
    metodo_pago VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_membresia) REFERENCES Membresia(id_membresia) ON DELETE CASCADE
);

-- 9. TABLA ASISTENCIA (Relación N:M "Asiste")
-- PK Compuesta (id_socio + id_clase)
CREATE TABLE Asistencia (
    id_socio INT NOT NULL,
    id_clase INT NOT NULL,
    fecha_asistencia DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_socio, id_clase),
    FOREIGN KEY (id_socio) REFERENCES Socio(id_socio) ON DELETE CASCADE,
    FOREIGN KEY (id_clase) REFERENCES Clase(id_clase) ON DELETE CASCADE
);

-- 10. TABLA CLASE_EQUIPAMIENTO (Relación N:M "Utiliza")
CREATE TABLE Clase_Equipamiento (
    id_clase INT NOT NULL,
    id_equipamiento INT NOT NULL,
    PRIMARY KEY (id_clase, id_equipamiento),
    FOREIGN KEY (id_clase) REFERENCES Clase(id_clase) ON DELETE CASCADE,
    FOREIGN KEY (id_equipamiento) REFERENCES Equipamiento(id_equipamiento) ON DELETE CASCADE
);

-- 11. Tabla Dashboard_Estadisticas (para el script estadístico del Reto 5)
-- Creamos esta tabla primero porque el último punto de la práctica nos pide guardar el informe estadístico en un "dashboard"
CREATE TABLE IF NOT EXISTS Dashboard_Estadisticas (
    id_informe INT AUTO_INCREMENT PRIMARY KEY,
    fecha_generacion DATETIME,
    total_socios_activos INT,
    ingresos_totales DECIMAL(10,2),
    mensaje_estado VARCHAR(255)
);


-- SECCIÓN 2: INSERCIÓN DE DATOS

-- 1. Tabla usuario
INSERT INTO Usuario (nombre, apellido, email, telefono, fecha_nacimiento) VALUES
('Carlos', 'Giménez', 'carlos@fitnet.es', '665123456', '1990-10-15'),
('María', 'López', 'maria.lopez@fitnet.es', '672234567', '1985-03-22'),
('Juan', 'Rodríguez', 'juan.rodriguez@fitnet.es', '658345678', '1995-07-10'),
('Ana', 'García', 'ana.garcia@fitnet.es', '691456789', '1988-11-05'),
('Pedro', 'Martínez', 'pedro.martinez@fitnet.es', '644567890', '1992-05-18'),
('Laura', 'Fernández', 'laura.fernandez@fitnet.es', '673678901', '1993-08-30'),
('Diego', 'Sánchez', 'diego.sanchez@fitnet.es', '666789012', '1987-02-14'),
('Sofia', 'Torres', 'sofia.torres@fitnet.es', '659890123', '1994-12-25'),
('Miguel', 'Moreno', 'miguel.moreno@fitnet.es', '681901234', '1989-06-09'),
('Elena', 'Ruiz', 'elena.ruiz@fitnet.es', '652012345', '1991-09-17'),
('Andrés', 'Gómez', 'andres.gomez@fitnet.es', '675123456', '1986-01-03'),
('Valentina', 'Díaz', 'valentina.diaz@fitnet.es', '664234567', '1997-04-21'),
('Lucas', 'Navarro', 'lucas.navarro@fitnet.es', '693345678', '1992-10-08'),
('Daniela', 'Castillo', 'daniela.castillo@fitnet.es', '668456789', '1996-03-12'),
('Oscar', 'Vargas', 'oscar.vargas@fitnet.es', '657567890', '1990-07-28');

-- 2. Tabla plan
INSERT INTO Plan (id_plan, nombre, duracion_meses, precio_base) VALUES
(1, 'Mensual', 1, 29.99),
(2, 'Trimestral', 3, 69.99),
(3, 'Anual', 12, 129.99),
(4, 'Mensual VIP', 1, 49.99),
(5, 'Trimestral VIP', 3, 89.99),
(6, 'Anual VIP', 12, 149.99);

-- 3. Tabla socio
INSERT INTO Socio (id_socio, fecha_registro, estado) VALUES
(1, '2025-01-15', 'Activo'),    -- Carlos
(2, '2025-02-01', 'Activo'),    -- María
(3, '2024-11-20', 'Activo'),    -- Juan
(4, '2025-06-10', 'Activo'),    -- Ana
(5, '2025-12-10', 'Inactivo'),  -- Pedro
(6, '2025-12-01', 'Activo'),    -- Laura
(7, '2026-01-10', 'Activo'),    -- Diego
(8, '2025-02-15', 'Activo'),    -- Sofia
(9, '2025-03-01', 'Inactivo'),  -- Miguel
(10, '2025-03-01', 'Activo'),   -- Elena
(11, '2025-08-20', 'Inactivo'), -- Andrés
(12, '2026-01-20', 'Activo'),   -- Valentina
(13, '2025-04-10', 'Activo'),   -- Lucas
(14, '2025-10-25', 'Inactivo'), -- Daniela
(15, '2025-07-05', 'Activo');   -- Oscar

-- 4. Tabla membresia
INSERT INTO Membresia (id_socio, id_plan, fecha_inicio, fecha_fin, estado) VALUES
(1, 4, '2026-01-15', '2027-01-15', 'Vigente'),     -- ID Membresía 1 (Carlos VIP)
(2, 1, '2026-02-01', '2026-03-01', 'Vigente'),     -- ID Membresía 2 (María Mensual)
(3, 2, '2025-11-01', '2026-02-01', 'Vigente'),     -- ID Membresía 3 (Juan Trimestral)
(4, 4, '2025-06-01', '2026-06-01', 'Vigente'),     -- ID Membresía 4 (Ana VIP)
(5, 3, '2024-02-19', '2025-02-19', 'Caducada'),    -- ID Membresía 5 (Pedro Anual caducada)
(6, 1, '2026-02-01', '2026-03-01', 'Vigente'),     -- ID Membresía 6 (Laura Mensual)
(7, 4, '2025-03-01', '2026-03-01', 'Suspendida'),  -- ID Membresía 7 (Diego VIP suspendido)
(8, 1, '2025-02-15', '2025-03-15', 'Vigente'),     -- ID Membresía 8 (Sofia Mensual)
(9, 2, '2025-08-01', '2025-11-01', 'Caducada'),    -- ID Membresía 9 (Miguel Trimestral caducada)
(10, 4, '2025-03-01', '2026-03-01', 'Suspendida'); -- ID Membresía 10 (Elena VIP suspendida)

-- 5. Tabla entrenador
-- Algunos ids coinciden con Usuario (socios que son entrenadores)
INSERT INTO Entrenador (id_entrenador, especialidad, certificado) VALUES
(1, 'Musculación', 'NASM-CPT'),
(2, 'Cardio y Fitness', 'ACE-CPT'),
(3, 'Pilates y Flexibilidad', 'Mat-Pilates-Cert'),
(4, 'CrossFit', 'CrossFit-L2'),
(6, 'Yoga y Meditación', 'RYT-200'),
(8, 'Boxeo y Defensa', 'Boxing-Cert'),
(11, 'Nutrición Deportiva', 'ISSN-SNS'),
(15, 'Entrenamiento Funcional', 'NASM-CES');

-- 6. Tabla clase
INSERT INTO Clase (id_entrenador, nombre_clase, fecha, hora, dia_semana, cupo_maximo) VALUES
(1, 'Musculación Avanzada', '2026-02-23', '08:00:00', 'Lunes', 15),
(1, 'Musculación Avanzada', '2026-02-25', '08:00:00', 'Miércoles', 15),
(2, 'Cardio HIIT', '2026-02-24', '10:00:00', 'Martes', 20),
(2, 'Cardio HIIT', '2026-02-26', '10:00:00', 'Jueves', 20),
(3, 'Pilates Matutino', '2026-02-23', '09:30:00', 'Lunes', 12),
(4, 'CrossFit Intenso', '2026-02-24', '18:00:00', 'Martes', 18),
(4, 'CrossFit Intenso', '2026-02-27', '18:00:00', 'Viernes', 18),
(6, 'Yoga Relajante', '2026-02-25', '19:00:00', 'Miércoles', 25),
(8, 'Boxeo Principiante', '2026-02-23', '17:00:00', 'Lunes', 10),
(11, 'Seminario Nutrición', '2026-02-28', '14:00:00', 'Sábado', 30),
(15, 'Entrenamiento Funcional', '2026-02-24', '19:30:00', 'Martes', 16),
(1, 'Musculación Principiante', '2026-02-26', '19:00:00', 'Jueves', 20);

-- 7. Tabla equipamiento
INSERT INTO Equipamiento (nombre_equipo, tipo, estado, fecha_adquisicion) VALUES
('Mancuerna ajustable 20kg', 'Pesa Libre', 'Operativo', '2023-05-10'),
('Barra Olímpica', 'Pesa Libre', 'Operativo', '2023-06-15'),
('Máquina Leg Press', 'Máquina', 'Operativo', '2024-01-20'),
('Cinta de Correr Profesional', 'Cardio', 'En Mantenimiento', '2023-08-05'),
('Bicicleta Estática', 'Cardio', 'Operativo', '2024-03-12'),
('Elíptica', 'Cardio', 'Operativo', '2024-02-01'),
('Colchoneta Yoga Premium', 'Accesorios', 'Operativo', '2024-06-20'),
('Sacos de Boxeo', 'Boxeo', 'Operativo', '2023-12-10'),
('Banco de Pesas', 'Accesorios', 'Operativo', '2024-04-15'),
('Máquina Abdominales', 'Máquina', 'Operativo', '2024-07-01'),
('Polea Alta', 'Máquina', 'Reparación', '2023-10-25'),
('Aro de Yoga', 'Accesorios', 'Operativo', '2024-08-30');

-- 8. Tabla pago
INSERT INTO Pago (id_membresia, cantidad, fecha_pago, metodo_pago) VALUES
(1, 159.99, '2026-01-15 10:30:00', 'Tarjeta Crédito'),
(2, 29.99, '2026-02-01 09:15:00', 'Transferencia'),
(3, 89.99, '2026-02-01 14:20:00', 'Tarjeta Débito'),
(4, 159.99, '2026-01-20 16:45:00', 'PayPal'),
(6, 29.99, '2026-02-10 13:30:00', 'Tarjeta Crédito'),
(8, 29.99, '2026-01-05 09:00:00', 'Transferencia');

-- 9. Tabla asistencia
INSERT INTO Asistencia (id_socio, id_clase, fecha_asistencia) VALUES
(1, 1, '2026-02-23 08:05:00'),
(2, 3, '2026-02-24 10:05:00'),
(3, 1, '2026-02-23 08:10:00'),
(4, 6, '2026-02-24 18:05:00'),
(6, 5, '2026-02-23 09:35:00'),
(8, 8, '2026-02-25 19:05:00'),
(10, 9, '2026-02-23 17:05:00'),
(12, 3, '2026-02-24 10:15:00'),
(13, 2, '2026-02-25 08:10:00'),
(15, 11, '2026-02-24 19:35:00'),
(1, 3, '2026-02-24 10:10:00'),
(2, 5, '2026-02-23 09:40:00'),
(3, 6, '2026-02-24 18:10:00'),
(4, 8, '2026-02-25 19:10:00'),
(6, 9, '2026-02-23 17:10:00'),
(8, 1, '2026-02-23 08:15:00'),
(10, 11, '2026-02-24 19:40:00'),
(12, 6, '2026-02-24 18:15:00'),
(13, 8, '2026-02-25 19:15:00'),
(15, 5, '2026-02-23 09:45:00'),
(1, 4, '2026-02-24 13:00:00');

-- 10. Tabla Clase_Equipamiento
INSERT INTO Clase_Equipamiento (id_clase, id_equipamiento) VALUES
(1, 1),  -- Musculación Avanzada - Mancuerna
(1, 2),  -- Musculación Avanzada - Barra
(1, 9),  -- Musculación Avanzada - Banco
(2, 1),  -- Musculación Avanzada (Miércoles) - Mancuerna
(3, 4),  -- Cardio HIIT - Cinta
(3, 5),  -- Cardio HIIT - Bicicleta
(4, 6),  -- Cardio HIIT (Jueves) - Elíptica
(5, 7),  -- Pilates - Colchoneta
(6, 2),  -- CrossFit - Barra
(6, 3),  -- CrossFit - Leg Press
(7, 2),  -- CrossFit (Viernes) - Barra
(8, 7),  -- Yoga - Colchoneta
(8, 12), -- Yoga - Aro
(9, 8),  -- Boxeo - Sacos
(11, 1); -- Entrenamiento Funcional - Mancuerna




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

DELIMITER ??
CREATE TRIGGER trg_validar_pago_positivo
BEFORE INSERT ON Pago
FOR EACH ROW
BEGIN
    -- Verifica si la cantidad introducida es menor que 0
    IF NEW.cantidad < 0 THEN
        -- Como estamos en el BEFORE, podemos modificar el valor antes de guardarlo
        SET NEW.cantidad = 0;
    END IF;
END ??
DELIMITER ;

-- TRIGGER 2: Desactivar socio si su membresía caduca.
-- Uso after porque quiero que la membresía se actualice correctamente. Una vez confirmado ese se dispara la actualización en la tabla Socio

DELIMITER ??
CREATE TRIGGER trg_desactivar_socio
AFTER UPDATE ON Membresia
FOR EACH ROW
BEGIN
    -- Solo actua si el estado acaba de cambiar estrictamente a 'Caducada'.
    IF OLD.estado != 'Caducada' AND NEW.estado = 'Caducada' THEN
        -- Actualiza el socio correspondiente segun el ID
        UPDATE Socio SET estado = 'Inactivo' WHERE id_socio = NEW.id_socio;
    END IF;
END ??
DELIMITER ;


-- 3. Procedimientos Almacenados con Transacciones

-- PROCEDIMIENTO 1: Registrar un pago con validación

DELIMITER ??
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
END ??
DELIMITER ;

-- PROCEDIMIENTO 2: Enviar equipo a reparar

DELIMITER ??
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
        -- Acción 1: Lo marcamos como en reparación
        UPDATE Equipamiento SET estado = 'Reparación' WHERE id_equipamiento = p_id_equipo;

        -- Acción 2: Lo borramos de la relación con las clases para que ningún
        -- entrenador lo espere en su clase
        DELETE FROM Clase_Equipamiento WHERE id_equipamiento = p_id_equipo;

        COMMIT; -- Confirmamos las DOS acciones conjuntas
        SET p_mensaje = CONCAT('Éxito: Equipo ', p_id_equipo, ' enviado a reparar y sacado de las clases.');
    END IF;
END ??
DELIMITER ;



-- 4. Procedimientos Almacenados con Cursores


-- PROCEDIMIENTO 3: Recopilar emails y guardarlos en el dashboard
DELIMITER ??
CREATE PROCEDURE sp_emails(OUT p_lista_emails VARCHAR(4000), OUT p_mensaje VARCHAR(255))
BEGIN
    DECLARE v_terminado INT DEFAULT 0;
    DECLARE v_email VARCHAR(100) DEFAULT ''; -- Variable para almacenar el email actual del cursor
    DECLARE c_socios_activos CURSOR FOR SELECT u.email FROM Usuario u -- Cursor para seleccionar los emails de los socios activos, cruzando Usuario con Socio para obtener solo los que están activos
        INNER JOIN Socio s ON u.id_usuario = s.id_socio
        WHERE s.estado = 'Activo';

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET v_terminado = 1;

    SET p_lista_emails = '';
    OPEN c_socios_activos;
	-- Bucle para recorrer los emails de los socios activos
    bucle_emails: LOOP
        FETCH c_socios_activos INTO v_email; -- Se obtiene el email del cursor y se guarda en la variable v_email
		 -- Condición de salida del bucle, si no hay más registros sale
        IF v_terminado = 1 THEN
            LEAVE bucle_emails;
        END IF;
        SET p_lista_emails = CONCAT(p_lista_emails, v_email, ', '); -- Se concatena el email a la lista, separando por comas
    END LOOP bucle_emails;

    CLOSE c_socios_activos;

    -- Guardamos la lista generada en el dashboard para tener registro de la campaña
    INSERT INTO Dashboard_Estadisticas (fecha_generacion, total_socios_activos, ingresos_totales, mensaje_estado)
    VALUES (NOW(), NULL, NULL, CONCAT('Campaña email: ', p_lista_emails));

    SET p_mensaje = 'Generación de lista de emails completada y guardada en el dashboard.';
END ??
DELIMITER ;

-- PROCEDIMIENTO 4: Contador de máquinas operativas
-- Recorre las máquinas y cuenta cuántas están operativas
DELIMITER ??
CREATE PROCEDURE sp_contador_maquinas(OUT p_mensaje VARCHAR(255)) -- Solo una variable de salida para mostrar el resultado final
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
    SET p_mensaje = CONCAT('Contador finalizado. Hay ', v_contador, ' equipos operativos en el gimnasio.');
END ??
DELIMITER ;



-- 5. Funciones Almacenadas

-- FUNCIÓN 1: Ingresos totales solo de membresías vigentes
DELIMITER ??
CREATE FUNCTION fn_ingresos_totales() RETURNS DECIMAL(10,2)
BEGIN
    DECLARE v_total DECIMAL(10,2) DEFAULT 0;
    -- Se suma la cantidad de todos los pagos de membresías vigentes, cruzando las tablas Pago y Membresia para asegurarnos de contar solo los pagos relacionados con membresías que están actualmente vigentes.
    SELECT SUM(p.cantidad) INTO v_total
    FROM Pago p INNER JOIN Membresia m ON p.id_membresia = m.id_membresia
    WHERE m.estado = 'Vigente';

    IF v_total IS NULL THEN
        SET v_total = 0;
    END IF;
    RETURN(v_total);
END ??
DELIMITER ;

-- FUNCIÓN 2: Socios activos con membresía vigente
DELIMITER ??
CREATE FUNCTION fn_socios_activos() RETURNS INT
BEGIN
    DECLARE v_conteo INT DEFAULT 0;
    -- Cruzamos Socio con Membresia para contar solo los que tienen membresía vigente actualmente
    SELECT COUNT(DISTINCT s.id_socio) INTO v_conteo
    FROM Socio s
    INNER JOIN Membresia m ON s.id_socio = m.id_socio
    WHERE s.estado = 'Activo'
      AND m.estado = 'Vigente';
    RETURN(v_conteo);
END ??
DELIMITER ;


-- 6. Script que genera el informe para el dashboard
-- Este script es un procedimiento almacenado que llama a las funciones anteriores, recopila sus resultados y los guarda en la tabla Dashboard_Estadisticas, además de mostrar el informe generado al final, con un timestamp de registro

DELIMITER ??
CREATE PROCEDURE sp_generar_dashboard()
BEGIN
    DECLARE v_ingresos DECIMAL(10,2) DEFAULT 0.0;
    DECLARE v_socios_activos INT DEFAULT 0;
    -- Se llaman a las funciones para obtener los datos necesarios para el informe
    SET v_ingresos = fn_ingresos_totales();
    SET v_socios_activos = fn_socios_activos();
    -- Se inserta el registro con la fecha y hora actual
    INSERT INTO Dashboard_Estadisticas (fecha_generacion, total_socios_activos, ingresos_totales)
    VALUES (NOW(), v_socios_activos, v_ingresos);
    -- Se muestra el informe en orden descendente para que el último registro sea el primero en verse como en java el principio del stack, último en entrar, primero en salir
    SELECT * FROM Dashboard_Estadisticas ORDER BY id_informe DESC LIMIT 1;
END ??
DELIMITER ;


USE FitNet;


-- CASOS DE PRUEBA

-- TRIGGER 1: trg_validar_pago_positivo
-- Comprueba que un pago negativo se convierte a 0


-- CASO OK: pago normal, se guarda tal cual
INSERT INTO Pago (id_membresia, cantidad, metodo_pago)
VALUES (1, 49.99, 'PayPal');
SELECT * FROM Pago ORDER BY id_pago DESC LIMIT 1;
-- Resultado esperado: cantidad = 49.99

-- CASO PRUEBA: pago negativo, el trigger lo corrige a 0
INSERT INTO Pago (id_membresia, cantidad, metodo_pago)
VALUES (1, -20.00, 'Transferencia');
SELECT * FROM Pago ORDER BY id_pago DESC LIMIT 1;
-- Resultado esperado: cantidad = 0.00



-- TRIGGER 2: trg_desactivar_socio
-- Comprueba que al caducar una membresía el socio pasa a Inactivo


-- Primero vemos el estado actual del socio 7 (Diego, Suspendida)
SELECT id_socio, estado FROM Socio WHERE id_socio = 7;
SELECT id_membresia, id_socio, estado FROM Membresia WHERE id_socio = 7;

-- CASO PRUEBA: cambiamos la membresía de Diego a Caducada
UPDATE Membresia SET estado = 'Caducada' WHERE id_membresia = 7;

-- Comprobamos que el trigger actualizó el socio automáticamente
SELECT id_socio, estado FROM Socio WHERE id_socio = 7;
-- Resultado esperado: estado = 'Inactivo'

-- CASO donde el trigger NO actúa: cambio que no es a 'Caducada'
UPDATE Membresia SET estado = 'Suspendida' WHERE id_membresia = 2;
SELECT id_socio, estado FROM Socio WHERE id_socio = 2;
-- Resultado esperado: socio 2 sigue Activo (el trigger no se dispara)



-- PROCEDIMIENTO 1: sp_registrar_pago


-- CASO OK: membresía existente
CALL sp_registrar_pago(1, 49.99, 'PayPal', @mensaje);
SELECT @mensaje AS Resultado;
-- Resultado esperado: Éxito: Pago de 49.99€ registrado correctamente.

-- CASO ERROR: membresía que no existe
CALL sp_registrar_pago(99, 29.99, 'Efectivo', @mensaje);
SELECT @mensaje AS Resultado;
-- Resultado esperado: Error: La membresía indicada no existe

-- Verificamos que el pago correcto sí se insertó
SELECT * FROM Pago ORDER BY id_pago DESC LIMIT 3;



-- PROCEDIMIENTO 2: sp_enviar_reparacion


-- Vemos el estado inicial del equipo 1 y sus clases asociadas
SELECT id_equipamiento, nombre_equipo, estado FROM Equipamiento WHERE id_equipamiento = 1;
SELECT * FROM Clase_Equipamiento WHERE id_equipamiento = 1;

-- CASO OK: enviamos la mancuerna (id=1) a reparar
CALL sp_enviar_reparacion(1, @mensaje);
SELECT @mensaje AS Resultado;
-- Resultado esperado: Éxito: Equipo 1 enviado a reparar y sacado de las clases.

-- Verificamos que el estado cambió y desapareció de las clases
SELECT id_equipamiento, nombre_equipo, estado FROM Equipamiento WHERE id_equipamiento = 1;
SELECT * FROM Clase_Equipamiento WHERE id_equipamiento = 1;
-- Resultado esperado: estado = 'Reparación' y sin filas en Clase_Equipamiento

-- CASO ERROR: equipo que no existe
CALL sp_enviar_reparacion(99, @mensaje);
SELECT @mensaje AS Resultado;
-- Resultado esperado: Error: El equipamiento no existe


-- PROCEDIMIENTO CURSOR 1: sp_emails


-- Ejecutamos el procedimiento
CALL sp_emails(@lista, @mensaje);

-- Vemos la lista de emails concatenada
SELECT @lista AS ListaEmails;
-- Resultado esperado: carlos@fitnet.es, maria.lopez@fitnet.es, ...

-- Vemos el mensaje de confirmación
SELECT @mensaje AS Resultado;
-- Resultado esperado: Generación de lista de emails completada y guardada en el dashboard.

-- Verificamos que se guardó el registro en el dashboard
SELECT * FROM Dashboard_Estadisticas ORDER BY id_informe DESC LIMIT 1;
-- Resultado esperado: fila con mensaje_estado = 'Campaña email: ...'

-- PROCEDIMIENTO CURSOR 2: sp_contador_maquinas

-- Vemos cuántos equipos hay operativos antes
SELECT COUNT(*) AS OperativosAntes FROM Equipamiento WHERE estado = 'Operativo';

-- Ejecutamos el procedimiento
CALL sp_contador_maquinas(@mensaje);
SELECT @mensaje AS Resultado;
-- Resultado esperado: Revisión finalizada. Hay X equipos operativos en el gimnasio.

-- Verificamos que el UPDATE se ejecutó en cada equipo operativo
SELECT id_equipamiento, nombre_equipo, estado, fecha_adquisicion
FROM Equipamiento
WHERE estado = 'Operativo';


-- FUNCIÓN 1: fn_ingresos_totales


-- Llamada directa
SELECT fn_ingresos_totales() AS IngresosMembresíasVigentes;
-- Resultado esperado: suma de pagos con membresía Vigente (no caducadas ni suspendidas)

-- Para ver la diferencia, comparamos con la suma total sin filtro
SELECT SUM(cantidad) AS TotalSinFiltro FROM Pago;
-- El valor de fn_ingresos_totales() debe ser menor o igual a este


-- FUNCIÓN 2: fn_socios_activos

-- Llamada directa
SELECT fn_socios_activos() AS SociosConMembresíaVigente;
-- Resultado esperado: número de socios Activos que además tienen membresía Vigente

-- Para ver la diferencia, comparamos con el total de socios activos sin filtro
SELECT COUNT(*) AS TotalActivosSinFiltro FROM Socio WHERE estado = 'Activo';
-- El valor de fn_socios_activos() debe ser menor o igual a este


-- SCRIPT DASHBOARD: sp_generar_dashboard


-- Primera ejecución
CALL sp_generar_dashboard();
-- Resultado esperado: fila con fecha actual, socios activos e ingresos vigentes

-- Segunda ejecución (para ver que acumula registros como un log)
CALL sp_generar_dashboard();

-- Ver el historial completo del dashboard
SELECT * FROM Dashboard_Estadisticas ORDER BY id_informe DESC;
-- Resultado esperado: dos filas con el mismo valor pero distinto id y timestamp
