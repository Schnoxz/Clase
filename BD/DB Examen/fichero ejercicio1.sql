CREATE DATABASE IF NOT EXISTS F1;
USE F1;

CREATE TABLE Persona (
    id_Persona INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    Pais VARCHAR (50) NOT NULL,
    nombreCompleto VARCHAR (100) NOT NULL
);

CREATE TABLE Piloto (
    Apodo VARCHAR (50) NOT NULL,
    id_Piloto INT NOT NULL,
    CONSTRAINT FK_Piloto_Persona FOREIGN KEY (id_Piloto) REFERENCES Persona (id_Persona)
);

CREATE TABLE Jefe_de_Equipo (
    id_Jefe INT PRIMARY KEY NOT NULL,
    CONSTRAINT FK_Jefe_Persona FOREIGN KEY (id_Jefe) REFERENCES Persona (id_Persona)
);

CREATE TABLE Coche (
    id_Coche INT PRIMARY KEY NOT NULL,
    id_Equipo INT NOT NULL,
    id_Piloto INT NOT NULL,
    Motor VARCHAR (10) NOT NULL,
    CONSTRAINT FK_Coche_Equipo FOREIGN KEY (id_Equipo) REFERENCES Equipo (id_Equipo)
);

CREATE TABLE Equipo (
    id_Equipo INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_Jefe INT NOT NULL,
    id_Piloto INT NOT NULL,
    id_Coche INT NOT NULL,
    nombreEquipo VARCHAR (20) NOT NULL,
    patrocinadroEquipo VARCHAR (50) NOT NULL,
    CONSTRAINT FK_Equipo_Jefe FOREIGN KEY (id_Jefe) REFERENCES Jefe_de_Equipo (id_Persona),
    CONSTRAINT FK_Equipo_Piloto FOREIGN KEY (id_Piloto) REFERENCES Piloto (id_Persona),
    CONSTRAINT FK_Equipo_Coche FOREIGN KEY (id_Coche) REFERENCES Coche (id_Coche)
    );

    ALTER TABLE Equipo (
    CHECK IN (FK_Jefe_Equipo =1),
    CHECK IN (FK_Equipo_Piloto >= 2),
    CHECK IN (FK_Equipo_Coche >= 2)
    );


CREATE TABLE Carrera (
    id_Carrera INT PRIMARY KEY NOT NULL,
    id_Equipo INT NOT NULL,
    id_Piloto NOT NULL,
    numCarrera INT NOT NULL,
    Ciudad VARCHAR (40) NOT NULL,
    CONSTRAINT FK_Carrera_Equipo FOREIGN KEY (id_Equipo) REFERENCES Equipo (id_Equipo),
    CONSTRAINT FK_Carrera_Coche FOREIGN KEY (id_Coche) REFERENCES Coche (id_Coche),
    CONSTRAINT FK_Carrera_Piloto FOREIGN KEY (id_Piloto) REFERENCES Piloto (id_Piloto)
);

CREATE TABLE Participan (
    numDorsal INT PRIMARY KEY NOT NULL,
    posicionFinal INT NOT NULL,
    horaFin TIME NOT NULL,
    CONSTRAINT FK_Participa_Piloto FOREIGN KEY (id_Persona) REFERENCES Piloto (id_Persona),
    CONSTRAINT FK_Participa_Equipo FOREIGN KEY (id_Equipo) REFERENCES Equipo (id_Equipo)
);




