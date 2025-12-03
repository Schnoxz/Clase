CREATE DATABASE IF NOT EXISTS Ejercicio1;
USE Ejercicio1;

CREATE TABLE LOCALIDAD (
    Cod_Localidad INT PRIMARY KEY,
    Nombre VARCHAR(40) NOT NULL
);

CREATE TABLE PUB (
    Cod_Pub VARCHAR(40) PRIMARY KEY,
    Nombre VARCHAR(40) NOT NULL,
    Licencia_Fiscal VARCHAR(20) UNIQUE NOT NULL,
    Domicilio VARCHAR(100),
    Fecha_Apertura DATE NOT NULL,
    Horario VARCHAR(70) NOT NULL,
    Cod_Localidad INT NOT NULL,
    CONSTRAINT fk_pub_localidad FOREIGN KEY (Cod_Localidad) REFERENCES LOCALIDAD(Cod_Localidad),
    CONSTRAINT ck_horario CHECK (Horario IN ('HOR1', 'HOR2', 'HOR3'))
);

CREATE TABLE EMPLEADO (
    Dni_Empleado VARCHAR(10) PRIMARY KEY,
    Nombre VARCHAR(40) NOT NULL,
    Domicilio VARCHAR(100)
);

CREATE TABLE TITULAR (
    Dni_Titular VARCHAR(10) PRIMARY KEY,
    Nombre VARCHAR(40) NOT NULL,
    Domicilio VARCHAR(100),
    Cod_Pub VARCHAR(40) NOT NULL,
    CONSTRAINT fk_titular_pub FOREIGN KEY (Cod_Pub) REFERENCES PUB(Cod_Pub)
);

CREATE TABLE EXISTENCIAS (
    Cod_Articulo VARCHAR(20) PRIMARY KEY,
    Nombre VARCHAR(40) NOT NULL,
    Cantidad INT NOT NULL,
    Precio DECIMAL(6,2) NOT NULL,
    Cod_Pub VARCHAR(40) NOT NULL,
    CONSTRAINT fk_existencias_pub FOREIGN KEY (Cod_Pub) REFERENCES PUB(Cod_Pub)
);

CREATE TABLE PUB_EMPLEADO (
    Cod_Pub VARCHAR(40) NOT NULL,
    Dni_Empleado VARCHAR(10) NOT NULL,
    Funcion ENUM('CAMARERO','SEGURIDAD','LIMPIEZA') NOT NULL,
    PRIMARY KEY (Cod_Pub, Dni_Empleado),
    CONSTRAINT fk_pubempleado_pub FOREIGN KEY (Cod_Pub) REFERENCES PUB(Cod_Pub),
    CONSTRAINT fk_pubempleado_empleado FOREIGN KEY (Dni_Empleado) REFERENCES EMPLEADO(Dni_Empleado)
);