CREATE DATABASE centroSalud;
USE centroSalud;

CREATE TABLE Medico (
    dniMedico VARCHAR(20),
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefonoMedico VARCHAR(20),
    anioGraduado YEAR,
    CONSTRAINT MedicoPK PRIMARY KEY (dniMedico)
);

CREATE TABLE Paciente (
    dniPaciente VARCHAR(20),
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefonoPaciente VARCHAR(20),
    dniMedico VARCHAR(20) NOT NULL,
    CONSTRAINT PacientePK PRIMARY KEY (dniPaciente),
    CONSTRAINT PacienteMedicoFK FOREIGN KEY (dniMedico)
        REFERENCES Medico(dniMedico)
);

CREATE TABLE Sala (
    idSala INT AUTO_INCREMENT,
    ubicacion VARCHAR(100) NOT NULL,
    CONSTRAINT SalaPK PRIMARY KEY (idSala)
);

CREATE TABLE Horario (
    dniMedico VARCHAR(20),
    idSala INT,
    horario VARCHAR(50) NOT NULL,
    CONSTRAINT HorarioPK PRIMARY KEY (dniMedico,idSala,horario),
    CONSTRAINT HorarioMedicoFK FOREIGN KEY (dniMedico)
        REFERENCES Medico(dniMedico),
    CONSTRAINT HorarioSalaFK FOREIGN KEY (idSala)
        REFERENCES Sala(idSala)
);

-- Inserciones
INSERT INTO Medico VALUES
('111A','Pedro','Martinez','600111111',2010),
('222B','Lucia','Fernandez','600222222',2015),
('333C','Juan','Lopez','600333333',2012),
('444D','Marta','Ruiz','600444444',2018),
('555E','Sergio','Gomez','600555555',2020);

INSERT INTO Paciente VALUES
('AAA1','Carlos','Perez','611111111','111A'),
('AAA2','Ana','Torres','622222222','222B'),
('AAA3','Luis','Fernandez','633333333','333C'),
('AAA4','Maria','Lopez','644444444','444D'),
('AAA5','David','Garcia','655555555','555E');

INSERT INTO Sala (ubicacion) VALUES
('Planta 1'),('Planta 2'),('Planta 3'),('Urgencias'),('Pediatria');

INSERT INTO Horario VALUES
('111A',1,'Manana'),
('222B',2,'Tarde'),
('333C',3,'Noche'),
('444D',4,'Manana'),
('555E',5,'Tarde');
