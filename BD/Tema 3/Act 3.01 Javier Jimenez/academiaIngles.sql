CREATE DATABASE academiaIngles;
USE academiaIngles;

CREATE TABLE Profesor (
    idProfesor INT AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    nacionalidad VARCHAR(50),
    CONSTRAINT ProfesorPK PRIMARY KEY (idProfesor)
);

CREATE TABLE Grupo (
    idGrupo INT AUTO_INCREMENT,
    nivel VARCHAR(50) NOT NULL,
    idProfesor INT NOT NULL,
    CONSTRAINT GrupoPK PRIMARY KEY (idGrupo),
    CONSTRAINT GrupoProfesorFK FOREIGN KEY (idProfesor)
        REFERENCES Profesor(idProfesor)
);

CREATE TABLE Alumno (
    idAlumno INT AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    idGrupo INT NOT NULL,
    CONSTRAINT AlumnoPK PRIMARY KEY (idAlumno),
    CONSTRAINT AlumnoGrupoFK FOREIGN KEY (idGrupo)
        REFERENCES Grupo(idGrupo)
);

-- Inserciones
INSERT INTO Profesor (nombre, nacionalidad) VALUES
('Alejandro Magno','Macedonia'),
('Julio Cesar','Roma'),
('Anibal Barca','Cartago'),
('Sun Tzu','China'),
('Aristoteles','Grecia');

INSERT INTO Grupo (nivel, idProfesor) VALUES
('Basico',1),
('Intermedio',2),
('Avanzado',3),
('Conversacion',4),
('Examenes',5);

INSERT INTO Alumno (nombre, idGrupo) VALUES
('Carlos Perez',1),
('Ana Torres',1),
('Luis Fernandez',2),
('Maria Lopez',3),
('David Gomez',4);
