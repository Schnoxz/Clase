CREATE DATABASE concesionario;
USE concesionario;

CREATE TABLE Cliente (
    idCliente INT AUTO_INCREMENT,
    NIF VARCHAR(20) UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(150),
    ciudad VARCHAR(100),
    telefono VARCHAR(20),
    CONSTRAINT ClientePK PRIMARY KEY (idCliente)
);

CREATE TABLE Coche (
    matricula VARCHAR(20),
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    color VARCHAR(30),
    precio DECIMAL(10,2) NOT NULL,
    idCliente INT NOT NULL,
    CONSTRAINT CochePK PRIMARY KEY (matricula),
    CONSTRAINT CocheClienteFK FOREIGN KEY (idCliente)
        REFERENCES Cliente(idCliente)
);

CREATE TABLE Revision (
    idRevision INT AUTO_INCREMENT,
    matricula VARCHAR(20) NOT NULL,
    cambioAceite BOOLEAN DEFAULT FALSE,
    cambioFiltro BOOLEAN DEFAULT FALSE,
    cambioFreno BOOLEAN DEFAULT FALSE,
    otros TEXT,
    CONSTRAINT RevisionPK PRIMARY KEY (idRevision),
    CONSTRAINT RevisionCocheFK FOREIGN KEY (matricula)
        REFERENCES Coche(matricula)
);

-- Inserciones
INSERT INTO Cliente (NIF,nombre,direccion,ciudad,telefono) VALUES
('12345678A','Luis Gomez','Av. Andalucia 10','Granada','600777111'),
('87654321B','Elena Ruiz','Calle Real 22','Malaga','600777222'),
('11223344C','Carlos Perez','Calle Mayor 5','Sevilla','600777333'),
('22334455D','Ana Torres','Av. Mediterraneo 15','Valencia','600777444'),
('33445566E','David Garcia','Calle Nueva 8','Madrid','600777555');

INSERT INTO Coche (matricula,marca,modelo,color,precio,idCliente) VALUES
('1111AAA','Toyota','Corolla','Rojo',18000,1),
('2222BBB','Ford','Focus','Azul',15000,2),
('3333CCC','Seat','Ibiza','Blanco',12000,3),
('4444DDD','Volkswagen','Golf','Negro',20000,4),
('5555EEE','Renault','Clio','Gris',14000,5);

INSERT INTO Revision (matricula,cambioAceite,cambioFiltro,cambioFreno,otros) VALUES
('1111AAA',TRUE,TRUE,FALSE,'Revision general'),
('2222BBB',FALSE,TRUE,TRUE,'Cambio pastillas de freno'),
('3333CCC',TRUE,FALSE,FALSE,'Cambio aceite y revision basica'),
('4444DDD',TRUE,TRUE,TRUE,'Mantenimiento completo'),
('5555EEE',FALSE,FALSE,TRUE,'Revision frenos');
