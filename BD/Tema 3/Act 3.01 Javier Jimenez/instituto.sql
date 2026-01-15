CREATE DATABASE instituto;
USE instituto;

CREATE TABLE Profesor (
    dni VARCHAR(20),
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(150),
    telefono VARCHAR(20),
    CONSTRAINT ProfesorPK PRIMARY KEY (dni)
);

CREATE TABLE Modulo (
    codigo INT,
    nombre VARCHAR(100) NOT NULL,
    dniProfesor VARCHAR(20) NOT NULL,
    CONSTRAINT ModuloPK PRIMARY KEY (codigo),
    CONSTRAINT ModuloProfesorFK FOREIGN KEY (dniProfesor)
        REFERENCES Profesor(dni)
);

CREATE TABLE Grupo (
    idGrupo INT AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    nivelEducativo VARCHAR(50) NOT NULL,
    delegadoExpediente INT NULL,
    CONSTRAINT GrupoPK PRIMARY KEY (idGrupo)
);

CREATE TABLE Alumno (
    numExpediente INT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    fechaNacimiento DATE,
    idGrupo INT NOT NULL,
    CONSTRAINT AlumnoPK PRIMARY KEY (numExpediente),
    CONSTRAINT AlumnoGrupoFK FOREIGN KEY (idGrupo)
        REFERENCES Grupo(idGrupo)
);

ALTER TABLE Grupo
ADD CONSTRAINT GrupoDelegadoFK FOREIGN KEY (delegadoExpediente)
    REFERENCES Alumno(numExpediente);

CREATE TABLE Alumno_Modulo (
    numExpediente INT,
    codigo INT,
    CONSTRAINT AlumnoModuloPK PRIMARY KEY (numExpediente,codigo),
    CONSTRAINT AMAlumnoFK FOREIGN KEY (numExpediente)
        REFERENCES Alumno(numExpediente),
    CONSTRAINT AMModuloFK FOREIGN KEY (codigo)
        REFERENCES Modulo(codigo)
);

-- Inserciones
INSERT INTO Profesor VALUES
('111P','Jose Garcia','Calle Luna 3','600555111'),
('222Q','Maria Sanchez','Calle Sol 7','600555222'),
('333R','Luis Fernandez','Av. Real 10','600555333'),
('444S','Ana Torres','Calle Mayor 12','600555444'),
('555T','Pedro Ruiz','Av. Andalucia 5','600555555');

INSERT INTO Modulo VALUES
(101,'Matematicas','111P'),
(102,'Historia','222Q'),
(103,'Lengua','333R'),
(104,'Fisica','444S'),
(105,'Quimica','555T');

INSERT INTO Grupo (nombre,nivelEducativo) VALUES
('1 ESO','Secundaria'),
('2 ESO','Secundaria'),
('3 ESO','Secundaria'),
('4 ESO','Secundaria'),
('1 Bachillerato','Bachillerato');

INSERT INTO Alumno VALUES
(1,'Carlos','Perez','2010-05-10',1),
(2,'Ana','Torres','2009-03-15',2),
(3,'Luis','Gomez','2008-07-20',3),
(4,'Maria','Lopez','2007-02-25',4),
(5,'David','Ruiz','2006-11-30',5);

UPDATE Grupo SET delegadoExpediente=1 WHERE idGrupo=1;

INSERT INTO Alumno_Modulo VALUES
(1,101),(2,102),(3,103),(4,104),(5,105);
