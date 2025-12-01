CREATE DATABASE Ejercicio1;
USE Ejercicio1;

CREATE TABLE PUB(
    Cod_Pub VARCHAR (40) PRIMARY KEY,
    nombre VARCHAR (40) NOT NULL,
    Licencia_Fiscal VARCHAR (20) UNIQUE NOT NULL,
    Domicilio VARCHAR (100),
    Fecha_Apertura DATE NOT NULL,
    Horario VARCHAR (50) CHECK (Horario IN ('HOR1', 'HOR2', 'HOR3')) NOT NULL,,
    Cod_Localidad INT NOT NULL
)

CREATE TABLE TITULAR (
    Dni_Titular VARCHAR (10) PRIMARY KEY,
    Nombre VARCHAR (40) NOT NULL,
    Domicilio VARCHAR (100),
    Cod_Pub VARCHAR (40) NOT NULL,
    FOREIGN KEY (Cod_Pub) REFERENCES PUB(Cod_Pub)
)

CREATE TABLE EMPLEADO (
    Dni_Empleado VARCHAR (10) PRIMARY KEY NOT NULL,
    Nombre VARCHAR (40) NOT NULL,
    Domicilio VARCHAR (100) 
)

CREATE TABLE EXISTENCIAS (
    Cod_Articulo VARCHAR (20) PRIMARY KEY NOT NULL,
    Nombre VARCHAR (40) NOT NULL, 
    Cantidad INT NOT NULL,
    Precio Decimal (4,2) CHECK (Precio > 0) NOT NULL,
    Cod_Pub VARCHAR (40) NOT NULL
)

CREATE TABLE LOCALIDAD (
    Cod_Localidad INT PRIMARY KEY NOT NULL,
    Nombre VARCHAR (40) NOT NULL
)

CREATE PUB_EMPLEADO (
    Cod_Pub VARCHAR (40) NOT NULL,
    Dni_Empleado VARCHAR (10) NOT NULL,
    Funcion VARCHAR (40) CHECK (Funcion IN ('CAMARERO', 'SEGURIDAD', 'LIMPIEZA')) NOT NULL,
    PRIMARY KEY (Cod_Pub, Dni_Empleaedo, Funcion),
)

