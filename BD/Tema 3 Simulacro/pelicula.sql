
CREATE DATABASE peliculas;
CREATE TABLE Ciudad (
    idCiudad INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE Cine (
    idCine INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    idCiudad INT NOT NULL,
    
    CONSTRAINT FK_Cine_Ciudad FOREIGN KEY (idCiudad)
        REFERENCES Ciudad(idCiudad)
       
);

CREATE TABLE Sala (
    idSala INT PRIMARY KEY,
    aforo INT NOT NULL,
    idCine INT NOT NULL, 
    
    CONSTRAINT FK_Sala_Cine FOREIGN KEY (idCine)
        REFERENCES Cine(idCine)
);

CREATE TABLE Persona (
    idPersona INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE Pelicula (
    idPelicula INT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    tituloOriginal VARCHAR(200)
);

CREATE TABLE Festival (
    idFestival INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE Dirige (
    idPersona INT NOT NULL,
    idPelicula INT NOT NULL,
    
    PRIMARY KEY (idPersona, idPelicula),
    
    CONSTRAINT FK_Dirige_Persona FOREIGN KEY (idPersona)
        REFERENCES Persona(idPersona),
        
    CONSTRAINT FK_Dirige_Pelicula FOREIGN KEY (idPelicula)
        REFERENCES Pelicula(idPelicula)
);

CREATE TABLE Actua (
    idPersona INT NOT NULL,
    idPelicula INT NOT NULL,
    
    PRIMARY KEY (idPersona, idPelicula),
    
    CONSTRAINT FK_Actua_Persona FOREIGN KEY (idPersona)
        REFERENCES Persona(idPersona),
        
    CONSTRAINT FK_Actua_Pelicula FOREIGN KEY (idPelicula)
        REFERENCES Pelicula(idPelicula)
);


CREATE TABLE Participa (
    idPelicula INT NOT NULL,
    idFestival INT NOT NULL,
    anio INT NOT NULL,
    
    PRIMARY KEY (idPelicula, idFestival),
    
    CONSTRAINT FK_Participa_Pelicula FOREIGN KEY (idPelicula)
        REFERENCES Pelicula(idPelicula),
        
    CONSTRAINT FK_Participa_Festival FOREIGN KEY (idFestival)
        REFERENCES Festival(idFestival)
);


CREATE TABLE Premio (
    idPelicula INT NOT NULL,
    idFestival INT NOT NULL,
    anio INT NOT NULL,
    tipoPremio VARCHAR(100) NOT NULL,
    
    PRIMARY KEY (idPelicula, idFestival),
    
    -- CONSTRAINT UQ_premio_anio_tipo UNIQUE (idSupermercado, funcion), -- si queremos qeu no se repitan el mismo premio en el mismo año
    
    CONSTRAINT FK_Premio_Pelicula FOREIGN KEY (idPelicula)
        REFERENCES Pelicula(idPelicula),
        
    CONSTRAINT FK_Premio_Festival FOREIGN KEY (idFestival)
        REFERENCES Festival(idFestival)
);


CREATE TABLE Proyecta (
    idPelicula INT NOT NULL,
    idSala INT NOT NULL,
    fechaInicio DATE NOT NULL,
    fechaFin DATE NOT NULL,
    recaudacion float,
    numEspectadores int,
        
    PRIMARY KEY (idPelicula, idSala),
    
    CONSTRAINT FK_Proyecta_Pelicula FOREIGN KEY (idPelicula)
        REFERENCES Pelicula(idPelicula),
        
    CONSTRAINT FK_Proyecta_Sala FOREIGN KEY (idSala)
        REFERENCES Sala(idSala)
);