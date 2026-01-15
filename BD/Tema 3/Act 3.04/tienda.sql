-- ######################################################################
-- 1. CREACIÓN DE LA BASE DE DATOS Y TABLAS
-- ######################################################################

-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS Tienda;
USE Tienda;

-- 1. Tabla Clientes (Maestra)
CREATE TABLE Clientes (
    id_cliente INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ciudad VARCHAR(50) NOT NULL
);

-- 2. Tabla Productos (Maestra)
CREATE TABLE Productos (
    id_producto INT PRIMARY KEY,
    nombre_producto VARCHAR(100) NOT NULL UNIQUE,
    precio DECIMAL(6, 2) NOT NULL
);

-- 3. Tabla Pedidos
CREATE TABLE Pedidos (
    id_pedido INT PRIMARY KEY,
    id_cliente INT NOT NULL,
    fecha_pedido DATE NOT NULL,
    total DECIMAL(8, 2) NOT NULL,
    
    -- Si el cliente es eliminado o actualizado, los pedidos también (CASCADE)
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente)
    ON DELETE CASCADE 
    ON UPDATE CASCADE
);

-- 4. Tabla Detalles_Pedido
CREATE TABLE Detalles_Pedido (
    id_pedido INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(6, 2) NOT NULL,
    
    UNIQUE (id_pedido, id_producto), 
    
    PRIMARY KEY (id_pedido, id_producto),

    -- No permite eliminar un pedido si tiene detalles (RESTRICT)
    FOREIGN KEY (id_pedido) REFERENCES Pedidos(id_pedido)
    ON DELETE RESTRICT 
    ON UPDATE CASCADE,
    
    -- No permite eliminar un producto si está en un detalle (RESTRICT)
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT
);

-- ######################################################################
-- 2. INSERCIÓN DE DATOS DE EJEMPLO
-- ######################################################################

-- Clientes
INSERT INTO Clientes (id_cliente, nombre, ciudad) VALUES
(1, 'Ana López', 'Madrid'),
(2, 'Juan Pérez', 'Barcelona'),
(3, 'María García', 'Valencia');

-- Productos
INSERT INTO Productos (id_producto, nombre_producto, precio) VALUES
(101, 'Café Molido 250g', 5.50),
(102, 'Leche Entera 1L', 1.20),
(103, 'Pan de Molde', 2.10),
(104, 'Chocolate Negro', 3.90);

-- Pedidos (relacionados con Clientes)
INSERT INTO Pedidos (id_pedido, id_cliente, fecha_pedido, total) VALUES
(10, 1, '2025-10-15', 7.70), -- Pedido de Ana (id=1)
(11, 2, '2025-10-16', 5.50), -- Pedido de Juan (id=2)
(12, 1, '2025-10-16', 4.20); -- Segundo Pedido de Ana (id=1)

-- Detalles_Pedido (relacionados con Pedidos y Productos)
INSERT INTO Detalles_Pedido (id_pedido, id_producto, cantidad, precio_unitario) VALUES
(10, 101, 1, 5.50), -- Pedido 10: Café
(10, 102, 1, 1.20), -- Pedido 10: Leche
(11, 101, 1, 5.50), -- Pedido 11: Café
(12, 103, 1, 2.10), -- Pedido 12: Pan
(12, 102, 1, 1.20); -- Pedido 12: Leche