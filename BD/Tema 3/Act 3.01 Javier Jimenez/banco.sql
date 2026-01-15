CREATE DATABASE banco;
USE banco;

CREATE TABLE Cliente (
    dni VARCHAR(20),
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    direccion VARCHAR(150),
    ciudad VARCHAR(100),
    CONSTRAINT ClientePK PRIMARY KEY (dni)
);

CREATE TABLE Sucursal (
    numSucursal INT,
    ciudad VARCHAR(100) NOT NULL,
    activoDisponible DECIMAL(15,2),
    CONSTRAINT SucursalPK PRIMARY KEY (numSucursal)
);

CREATE TABLE Cuenta (
    numCuenta INT,
    saldo DECIMAL(15,2),
    numSucursal INT NOT NULL,
    CONSTRAINT CuentaPK PRIMARY KEY (numCuenta),
    CONSTRAINT CuentaSucursalFK FOREIGN KEY (numSucursal)
        REFERENCES Sucursal(numSucursal)
);

CREATE TABLE Cliente_Cuenta (
    dni VARCHAR(20),
    numCuenta INT,
    CONSTRAINT ClienteCuentaPK PRIMARY KEY (dni,numCuenta),
    CONSTRAINT ClienteCuentaClienteFK FOREIGN KEY (dni)
        REFERENCES Cliente(dni),
    CONSTRAINT ClienteCuentaCuentaFK FOREIGN KEY (numCuenta)
        REFERENCES Cuenta(numCuenta)
);

CREATE TABLE Transaccion (
    numTransaccion INT,
    fecha DATE NOT NULL,
    tipoOperacion ENUM('Ingreso','Extraccion') NOT NULL,
    cantidad DECIMAL(15,2) NOT NULL,
    numCuenta INT NOT NULL,
    CONSTRAINT TransaccionPK PRIMARY KEY (numTransaccion),
    CONSTRAINT TransaccionCuentaFK FOREIGN KEY (numCuenta)
        REFERENCES Cuenta(numCuenta)
);

-- Inserciones
INSERT INTO Cliente VALUES
('111A','Antonio','Ruiz','Calle Mayor 1','Madrid'),
('222B','Beatriz','Santos','Calle Sol 5','Sevilla'),
('333C','Carlos','Lopez','Av. Andalucia 12','Granada'),
('444D','Diana','Martinez','Calle Real 8','Valencia'),
('555E','Eduardo','Garcia','Calle Nueva 3','Bilbao');

INSERT INTO Sucursal VALUES
(1,'Madrid',100000),
(2,'Sevilla',80000),
(3,'Granada',60000),
(4,'Valencia',90000),
(5,'Bilbao',70000);

INSERT INTO Cuenta VALUES
(1001,2000,1),
(1002,1500,2),
(1003,3000,3),
(1004,2500,4),
(1005,1800,5);

INSERT INTO Cliente_Cuenta VALUES
('111A',1001),
('222B',1002),
('333C',1003),
('444D',1004),
('555E',1005);

INSERT INTO Transaccion VALUES
(1,'2025-11-01','Ingreso',500,1001),
(2,'2025-11-02','Extraccion',200,1002),
(3,'2025-11-03','Ingreso',1000,1003),
(4,'2025-11-04','Extraccion',300,1004),
(5,'2025-11-05','Ingreso',700,1005);
