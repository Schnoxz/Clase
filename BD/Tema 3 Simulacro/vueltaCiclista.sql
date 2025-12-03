DROP DATABASE vueltaCiclista;
CREATE DATABASE vueltaCiclista;

CREATE TABLE Equipo (
    idEquipo int primary key,
    nombre varchar(20) not null,
    patrocinador varchar(20) not null,
    director varchar(20) not null,
    nacinalidad varchar(20) not null
);

CREATE TABLE Medio (
    idMedio int primary key,
    tipo varchar(20) not null,
    nombreCadena varchar(20) not null,
    cannon float not null
);

CREATE TABLE Etapa(
    idEtapa int primary key,
    lugarEntrada varchar(20) not null,
    lugarLlegada varchar(20) not null,
    km int not null,
    fecha DATE not null,
    horaSalida Time not null,
    horaLlegada Time not null
);

CREATE TABLE Puerto (
    idPuerto int primary key,
    nombre varchar(20) not null,
    categoria varchar(20)not null,
    idEtapa int not null,
    CoNSTRAINT FK_Puerto_Etapa FOREIGN KEY (idEtapa) REFERENCES Etapa (idEtapa) 
);

CREATE TABLE Ciclista (
    idDorsal int primary key,
    nacionalidad varchar(20) not null,
    nombre varchar(20) not null,
    tiempoTotal Time not null,
    idEquipo int not null,
    CONSTRAINT FK_ciclista_equipo FOREIGN KEY (idEquipo) REFERENCES Equipo(idEquipo)
);

CREATE TABLE ClasificacionEtapa(
    idCiclista int,
    idEtapa int,
    tiempo TIME,
    primary key (idEtapa, idCiclista),

    CONSTRAINT FK_CE_ciclista FOREIGN KEY (idCiclista) REFERENCES Ciclista(idDorsal),
    CONSTRAINT FK_CE_etapa FOREIGN KEY (idEtapa) REFERENCES Etapa(idEtapa)
);

CREATE TABLE Palmares(
    idClasificacion int primary key,
    descripcion varchar(100) not null,
    idCiclista int not null,
    
    CONSTRAINT FK_palmares_ciclista FOREIGN KEY (idCiclista) REFERENCES Ciclista(idDorsal)
);

CREATE TABLE Entrevista(
    idCiclista int,
    idMedio int,
    fechaEntrevista DATE not null,
    totalEntrevistas int not null,

    Primary key (idCiclista, idMedio, fechaEntrevista),
    CONSTRAINT FK_entrevista_ciclista FOREIGN KEY (idCiclista) REFERENCES Ciclista(idDorsal),
    CONSTRAINT FK_palmares_medio FOREIGN KEY (idMedio) REFERENCES Medio(idMedio)
);