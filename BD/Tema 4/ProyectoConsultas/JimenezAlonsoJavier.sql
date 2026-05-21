CREATE DATABASE IF NOT EXISTS FitNet;
USE FitNet;

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
    nombre VARCHAR(50) NOT NULL,    -- 'Mensual', 'Trimestral', 'Anual'
    duracion_meses INT NOT NULL,    -- 1, 3, 12
    precio_base DECIMAL(7,2) NOT NULL -- 29.99, 69.99, 129.99
);

-- 3. Tabla Hija: Socio
-- Relación 1:1 con Usuario y FK hacia Membresia
CREATE TABLE Socio (
    id_socio INT PRIMARY KEY,
    fecha_registro DATE DEFAULT (CURRENT_DATE),
    estado VARCHAR(50) DEFAULT 'Activo', -- Ej: Activo, Inactivo
    FOREIGN KEY (id_socio) REFERENCES Usuario(id_usuario) ON DELETE CASCADE
);

-- 4. Tabla Membresia
-- Se crea antes que Socio para permitir la FK en Socio
CREATE TABLE Membresia (
    id_membresia INT PRIMARY KEY AUTO_INCREMENT,
    id_socio INT NOT NULL,
    id_plan INT NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    estado VARCHAR(50) DEFAULT 'Vigente', -- Ej: Vigente, Caducada, Suspendida
    FOREIGN KEY (id_plan) REFERENCES Plan(id_plan) ON DELETE RESTRICT, -- FK hacia Plan para definir el tipo de membresía
    FOREIGN KEY (id_socio) REFERENCES Socio(id_socio) ON DELETE CASCADE -- FK hacia Socio para que se vincule la membresía al socio
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
-- Corregida: PK Compuesta (id_socio + id_clase)
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


-- INSERCION DE DATOS

USE FitNet;
-- Me he ayudado con la IA para generar datos, aunque muchos de ellos los he creado manualmente especificamente para los ejemplos de consulta y que tuvieran coherencia

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

INSERT INTO Plan (id_plan, nombre, duracion_meses, precio_BASE) VALUES
(1, 'Mensual', 1, 29.99),
(2, 'Trimestral', 3, 69.99),
(3, 'Anual', 12, 129.99),
(4, 'Mensual VIP', 1, 49.99), -- Plan VIP + 20e
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
(1, 4, '2026-01-15', '2027-01-15', 'Vigente'),    -- ID Membresía 1 (Carlos VIP)
(2, 1, '2026-02-01', '2026-03-01', 'Vigente'),    -- ID Membresía 2 (María Mensual)
(3, 2, '2025-11-01', '2026-02-01', 'Vigente'),    -- ID Membresía 3 (Juan Trimestral)
(4, 4, '2025-06-01', '2026-06-01', 'Vigente'),    -- ID Membresía 4 (Ana VIP)
(5, 3, '2024-02-19', '2025-02-19', 'Caducada'),   -- ID Membresía 5 (Pedro Anual caducada)
(6, 1, '2026-02-01', '2026-03-01', 'Vigente'),    -- ID Membresía 6 (Laura Mensual)
(7, 4, '2025-03-01', '2026-03-01', 'Suspendida'), -- ID Membresía 7 (Diego VIP suspendido)
(8, 1, '2025-02-15', '2025-03-15', 'Vigente'),    -- ID Membresía 8 (Sofia Mensual)
(9, 2, '2025-08-01', '2025-11-01', 'Caducada'),   -- ID Membresía 9 (Miguel Trimestral caducada)
(10, 4, '2025-03-01', '2026-03-01', 'Suspendida');-- ID Membresía 10 (Elena VIP suspendida)


-- 5. Tabla entrenador

-- Algunos ids coinciden con Usuario (socios que son entrenadores)
INSERT INTO Entrenador (id_entrenador, especialidad, certificado) VALUES
(1, 'Musculación', 'NASM-CPT'),           -- Carlos es socio VIP y entrenador
(2, 'Cardio y Fitness', 'ACE-CPT'),       -- María es entrenadora
(3, 'Pilates y Flexibilidad', 'Mat-Pilates-Cert'),  -- Juan es entrenador
(4, 'CrossFit', 'CrossFit-L2'),           -- Ana es socio VIP y entrenadora
(6, 'Yoga y Meditación', 'RYT-200'),      -- Laura es entrenadora
(8, 'Boxeo y Defensa', 'Boxing-Cert'),    -- Sofia es entrenadora
(11, 'Nutrición Deportiva', 'ISSN-SNS'),  -- Andrés es entrenador especializado
(15, 'Entrenamiento Funcional', 'NASM-CES'); -- Oscar es socio y entrenador


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
(1, 159.99, '2026-01-15 10:30:00', 'Tarjeta Crédito'),  -- Pago de Carlos
(2, 29.99, '2026-02-01 09:15:00', 'Transferencia'),     -- Pago de María
(3, 89.99, '2026-02-01 14:20:00', 'Tarjeta Débito'),    -- Pago de Juan
(4, 159.99, '2026-01-20 16:45:00', 'PayPal'),           -- Pago de Ana
(6, 29.99, '2026-02-10 13:30:00', 'Tarjeta Crédito'),   -- Pago de Laura
(8, 29.99, '2026-01-05 09:00:00', 'Transferencia');     -- Pago de Sofia


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


-- 10. Tabla Clase_Equipamiento (Relación N:M entre Clase y Equipamiento)

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



-- CONSULTAS

-- 1. Selecciona los usuarios que tengan una suscripción VIP (Activa o caducada) y que no sean entrenadores. (He usado INNER JOIN + LEFT JOIN + WHERE + AND)

SELECT CONCAT(u.nombre, ' ', u.apellido) AS UsuariosVIP FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio
INNER JOIN membresia m ON s.id_socio = m.id_socio
INNER JOIN plan p ON m.id_plan = p.id_plan
LEFT JOIN entrenador e ON u.id_usuario = e.id_entrenador
WHERE p.nombre LIKE '%VIP'
AND e.id_entrenador IS NULL; -- Solo muestro los usuarios que no son entrenadores, si el id_entrenador es NULL es que no es entrenador

-- 2. Dime el nombre y correo de los usuarios (Activos) que tienen una membresía mensual. (He usado INNER JOIN + WHERE + LIKE + AND)

SELECT CONCAT(u.nombre, ' ', u.apellido) AS NombreCompleto, u.email FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio
INNER JOIN membresia m ON s.id_socio = m.id_socio
INNER JOIN plan p ON m.id_plan = p.id_plan
WHERE p.nombre LIKE '%Mensual%' -- Aparecen tambien los VIP mensuales que tienen id_plan 4
AND m.estado = 'Vigente';

-- 3. Crea una columna con las distintas clases que hay y su cupo maximo, ordena por orden alfabetico (He usado SUM + GROUP BY + ORDER BY)

SELECT c.nombre_clase AS Clases, SUM(c.cupo_maximo) AS CupoMaximo FROM clase c
GROUP BY c.nombre_clase
ORDER BY c.nombre_clase ASC;

-- 4. Selecciona el usuario que más clases practica y muestra su nombre, correo y la cantidad de clases que practica. (He usado COUNT + GROUP BY + ORDER BY)
SELECT CONCAT(u.nombre, ' ', u.apellido) AS NombreCompleto, u.email, COUNT(a.id_clase) AS Clases FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio -- Saco los socios para luego sacar sus asistencias
INNER JOIN asistencia a ON s.id_socio = a.id_socio -- Saco las asistencias del socio y saber cuantas clases tiene
INNER JOIN clase c ON a.id_clase = c.id_clase -- Saco las clases a la que asiste el socio
GROUP BY u.id_usuario
ORDER BY COUNT(a.id_clase) DESC
LIMIT 1; -- Solo muestro 1 usuario

-- 5. Muestra los usuarios VIP y los usuarios Anual, en una misma consulta. (Aqui muestro un ejemplo de UNION con dos consultas separadas)

SELECT CONCAT(u.nombre, ' ', u.apellido) AS NombreCompleto, u.email, p.nombre AS Tipo, m.estado
FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio -- Saco los socios para luego sacar sus membresias
INNER JOIN membresia m ON s.id_socio = m.id_socio -- Saco las membresias de los socios (¡Cambiada la dirección!)
INNER JOIN plan p ON m.id_plan = p.id_plan -- Saco el catálogo para ver el tipo de membresia (¡Nuevo JOIN!)
WHERE p.nombre LIKE '%VIP%'
UNION -- Con el UNION uno ambas consultas y muestro usuarios VIP y Anuales
SELECT CONCAT(u.nombre, ' ', u.apellido) AS NombreCompleto, u.email, p.nombre AS Tipo, m.estado
FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio
INNER JOIN membresia m ON s.id_socio = m.id_socio
INNER JOIN plan p ON m.id_plan = p.id_plan
WHERE p.nombre LIKE '%Anual%';

-- 6. Suma los pagos de los usuarios que tengan membresia mensual y muestra la suma total y la media de esos pagos (He usado SUM + AVG + INNER JOIN + WHERE + LIKE)

SELECT SUM(p.precio_base) AS SumaTotal, AVG(p.precio_base) AS MediaPagos FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio -- Saco los socios para luego sacar sus membresias
INNER JOIN membresia m ON s.id_socio = m.id_socio -- Saco las membresias de los socios para luego sacar el plan y su precio
INNER JOIN plan p ON m.id_plan = p.id_plan -- Saco el plan para luego sacar su precio y hacer la suma y media de los pagos
WHERE p.nombre LIKE '%Mensual%'; -- Aparecen tambien los VIP mensuales que tienen id_plan 4

-- 7. Muestra el nombre de los entrenadores que imparten clases de CrossFit y el número de clases que imparten (He usado COUNT + GROUP BY)

SELECT CONCAT(u.nombre, ' ', u.apellido) AS NombreCompleto, COUNT(c.id_clase) AS ClasesCrossFit FROM usuario u
INNER JOIN entrenador e ON u.id_usuario = e.id_entrenador --
INNER JOIN clase c ON e.id_entrenador = c.id_entrenador
WHERE c.nombre_clase LIKE '%CrossFit%'
GROUP BY e.id_entrenador;

-- 8. Muestra el equipamiento de cada clase (He usado INNER JOIN + ORDER BY)

SELECT c.nombre_clase AS Clase, eq.nombre_equipo AS Equipamiento FROM clase c
INNER JOIN clase_equipamiento ce ON c.id_clase = ce.id_clase -- Saco el equipamiento de cada clase
INNER JOIN equipamiento eq ON ce.id_equipamiento = eq.id_equipamiento -- Saco el nombre del equpamiento
ORDER BY c.nombre_clase ASC;


-- 9. Muestra el nombre de los usuarios que han asistido a clases de Yoga y el nombre del entrenador que imparte esa clase (He usado INNER JOIN + WHERE + LIKE)

SELECT CONCAT(u.nombre, ' ', u.apellido) AS Entrenador FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio -- Saco los socios para luego sacar sus asistencias
INNER JOIN asistencia a ON s.id_socio = a.id_socio -- Saco las asistencias del socio para luego sacar las clases a las que asiste
INNER JOIN clase c ON a.id_clase = c.id_clase -- Saco las clases a las que asiste el socio para luego sacar el entrenador de esa clase
INNER JOIN entrenador e ON c.id_entrenador = e.id_entrenador -- Saco el entrenador de la clase para mostrar su nombre
WHERE c.nombre_clase LIKE '%Yoga%';

-- 10. Muestra los usuarios que hayan pagado solo 1 membresía y el tipo de membresía que han pagado (He usado COUNT + GROUP BY + HAVING)

SELECT CONCAT(u.nombre, ' ', u.apellido) AS Usuario, pl.nombre AS TipoMembresia, COUNT(pa.id_pago) AS TotalPagos FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio -- Saco los socios para luego sacar sus membresias
INNER JOIN membresia m ON s.id_socio = m.id_socio -- Saco las membresias de los socios
INNER JOIN plan pl ON m.id_plan = pl.id_plan -- Saco el plan para mostrar el tipo
INNER JOIN pago pa ON m.id_membresia = pa.id_membresia -- Saco la cantidad de pagos
GROUP BY u.id_usuario, pl.nombre -- Muestra los usuarios y el tipo de membresia que han pagado
HAVING COUNT(pa.id_pago) = 1; -- Solo muestra usuarios con un pago


-- Crear vistas

-- 1. Vista con los datos personales de todos los usuarios VIP (Se usa CREATE VIEW, y si quiero borrarla con un DROP VIEW)
CREATE VIEW DatosVIP AS SELECT CONCAT(u.nombre, ' ', u.apellido) AS NombreCompleto, u.email, p.nombre AS tipo, m.estado FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio
INNER JOIN membresia m ON s.id_socio = m.id_socio -- Enlazamos la membresía al socio
INNER JOIN plan p ON m.id_plan = p.id_plan -- Añadimos el catálogo para saber el nombre del plan
WHERE p.nombre LIKE '%VIP' -- De momento no hay usuarios con VIP que no sea Anual pero si en un futuro los hay uso el LIKE igualmente, no concreto con un '='
AND m.estado = 'Vigente';

-- Eliminación de datos fitlrando valores o con subconsulta

-- 1. Elimina un socio que tenga una membresia caducada (Se usa DELETE FROM)
DELETE FROM socio WHERE id_socio = (SELECT id_socio FROM membresia WHERE estado = 'Caducada' LIMIT 1);
-- Como elijo borrar solo a uno le meto un LIMIT 1, de forma aleatoria.

-- 1. Cambia el entrenador de la clase de yoga con el entrenador de la clase de Boxeo (Como no sé el nombre completo de la clase uso LIKE y tampoco sé que entrenador es cual hago una subconsulta)
-- Puede que no cambie ningun dato en la base de datos actual porque he tenido que retocar campos e igual no existe entrenadores para intercambiar con la clase de yoga o boxeo

--  Con el UPDATE eligo la tabla y SET el campo que quiero actualizar, meto la subconsulta y hago lo mismo por el entrenador a intercambiar con otra subconsulta
UPDATE clase SET id_entrenador = (SELECT id_entrenador FROM clase WHERE nombre_clase LIKE '%Boxeo')
WHERE id_entrenador = (SELECT id_entrenador FROM clase WHERE nombre_clase LIKE '%Yoga');


-- Inserción de datons filtrando valores o con una subconsulta

-- 1. Inserta una nueva clase a cualquier entrenador

INSERT INTO clase (id_entrenador, nombre_clase, fecha, hora, dia_semana, cupo_maximo) VALUES
((SELECT id_entrenador FROM entrenador WHERE especialidad LIKE '%Cardio%'), -- Con la subconsulta elijo un entrenador que sea de cardio
 'Cardio para principiantes', '2026-03-01', '10:00:00', 'Lunes', 20); -- Le asigno una nueva clase con su nombre, fecha, hora, dia de la semana y cupo maximo



