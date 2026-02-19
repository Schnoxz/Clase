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

-- 2. Tabla Membresia 
-- Se crea antes que Socio para permitir la FK en Socio
CREATE TABLE Membresia (
    id_membresia INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(50) NOT NULL, -- Ej: Mensual, Anual, VIP
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    estado VARCHAR(50) DEFAULT 'Vigente'
);

-- 3. Tabla Hija: Socio
-- Relación 1:1 con Usuario y FK hacia Membresia
CREATE TABLE Socio (
    id_socio INT PRIMARY KEY,
    id_membresia INT, -- FK hacia Membresia según diagrama
    fecha_registro DATE DEFAULT (CURRENT_DATE),
    estado VARCHAR(50) DEFAULT 'Activo',
    FOREIGN KEY (id_socio) REFERENCES Usuario(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_membresia) REFERENCES Membresia(id_membresia) ON DELETE SET NULL
);

-- 4. Tabla Hija: Entrenador 
-- Relación 1:1 con Usuario
CREATE TABLE Entrenador (
    id_entrenador INT PRIMARY KEY,
    especialidad VARCHAR(100) NOT NULL,
    certificado VARCHAR(100),
    FOREIGN KEY (id_entrenador) REFERENCES Usuario(id_usuario) ON DELETE CASCADE
);

-- 5. TABLA CLASE
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

-- 6. TABLA EQUIPAMIENTO
CREATE TABLE Equipamiento (
    id_equipamiento INT PRIMARY KEY AUTO_INCREMENT,
    nombre_equipo VARCHAR(100) NOT NULL,
    tipo VARCHAR(50),
    estado VARCHAR(50) DEFAULT 'Operativo',
    fecha_adquisicion DATE
);

-- 7. TABLA PAGO
-- Relación 1:N (Socio realiza Pagos)
CREATE TABLE Pago (
    id_pago INT PRIMARY KEY AUTO_INCREMENT,
    id_socio INT NOT NULL,
    cantidad DECIMAL(10, 2) NOT NULL,
    fecha_pago DATETIME DEFAULT CURRENT_TIMESTAMP,
    metodo_pago VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_socio) REFERENCES Socio(id_socio) ON DELETE CASCADE
);

-- 8. TABLA ASISTENCIA (Relación N:M "Asiste")
-- Corregida: PK Compuesta (id_socio + id_clase)
CREATE TABLE Asistencia (
    id_socio INT NOT NULL,
    id_clase INT NOT NULL,
    fecha_asistencia DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_socio, id_clase),
    FOREIGN KEY (id_socio) REFERENCES Socio(id_socio) ON DELETE CASCADE,
    FOREIGN KEY (id_clase) REFERENCES Clase(id_clase) ON DELETE CASCADE
);

-- 9. TABLA CLASE_EQUIPAMIENTO (Relación N:M "Utiliza")
CREATE TABLE Clase_Equipamiento (
    id_clase INT NOT NULL,
    id_equipamiento INT NOT NULL,
    PRIMARY KEY (id_clase, id_equipamiento),
    FOREIGN KEY (id_clase) REFERENCES Clase(id_clase) ON DELETE CASCADE,
    FOREIGN KEY (id_equipamiento) REFERENCES Equipamiento(id_equipamiento) ON DELETE CASCADE
);


-- Operaciones de insert, alter table, delete, update.

-- A. INSERT
INSERT INTO Usuario (nombre, apellido, email, fecha_nacimiento) 
VALUES ('Carlos', 'Giménez', 'carlos@fitnet.es', '1990-10-15');

INSERT INTO Membresia (tipo, fecha_inicio, fecha_fin, estado)
VALUES ('Anual VIP', '2024-01-01', '2024-12-31', 'Vigente');

-- Vinculamos al usuario como socio y le asignamos la membresía
INSERT INTO Socio (id_socio, id_membresia) VALUES (1, 1);

-- B. ALTER
-- Añadir una columna para el nivel de experiencia en Entrenador
ALTER TABLE Entrenador ADD COLUMN nivel_experiencia ENUM('Junior', 'Senior', 'Master');

-- C. UPDATE
-- Actualizar el estado de una máquina de gimnasio
UPDATE Equipamiento SET estado = 'En Mantenimiento' WHERE id_equipamiento = 3;

-- D. DELETE
-- Borrar una asistencia específica
DELETE FROM Asistencia WHERE id_socio = 1 AND id_clase = 10;



