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
