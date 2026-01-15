CREATE DATABASE bicicleta;
USE bicicleta;

CREATE TABLE Inventario (
    idBicicleta INT AUTO_INCREMENT,
    modelo VARCHAR (100) NOT NULL UNIQUE ,
    precio DECIMAL(10,2) NOT NULL CHECK (precio > 0),
    stock INT DEFAULT 5,
    color VARCHAR(50) NOT NULL,
    entrada DATE NOT NULL DEFAULT CURRENT_DATE,
    CONSTRAINT BicicletaPK PRIMARY KEY (idBicicleta)
);

ALTER TABLE Inventario
ADD COLUMN descripcion TEXT;

ALTER TABLE Inventario 
MODIFY COLUMN precio DECIMAL(6,2) NOT NULL;

ALTER TABLE Inventario
ADD CONSTRAINT stock_no_negativo CHECK (stock >= 0);

ALTER TABLE Inventario
MODIFY COLUMN color VARCHAR(30) NOT NULL;

ALTER TABLE Inventario
ADD COLUMN promocionada BOOLEAN DEFAULT FALSE;



