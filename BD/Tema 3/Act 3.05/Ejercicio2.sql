CREATE DATABASE IF NOT EXISTS Ejercicio2;
USE Ejercicio2;

-- 1. Tabla independiente (sin FK)
CREATE TABLE RECINTOS (
    Cod_Recinto VARCHAR(30) PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Direccion VARCHAR(200),
    Ciudad VARCHAR(60),
    Telefono VARCHAR(20),
    Horario VARCHAR(100)
);

-- 2. Tabla dependiente de RECINTOS
CREATE TABLE ESPECTACULOS (
    Cod_Espectaculo VARCHAR(30) PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Tipo VARCHAR(20),
    Fecha_Inicial DATE NOT NULL,
    Fecha_Final DATE NOT NULL,
    Interprete VARCHAR(100),
    Cod_Recinto VARCHAR(30) NOT NULL,
    CONSTRAINT fk_espectaculos_recintos FOREIGN KEY (Cod_Recinto)
        REFERENCES RECINTOS(Cod_Recinto)
        ON DELETE RESTRICT ON UPDATE CASCADE
);

-- 3. Tabla dependiente de RECINTOS (PK Compuesta)
CREATE TABLE ZONAS_RECINTOS (
    Cod_Recinto VARCHAR(30) NOT NULL,
    Zona VARCHAR(20) NOT NULL,
    Capacidad INT NOT NULL,
    PRIMARY KEY (Cod_Recinto, Zona),
    CONSTRAINT fk_zonas_recintos_recinto FOREIGN KEY (Cod_Recinto)
        REFERENCES RECINTOS(Cod_Recinto)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- 4. Tabla dependiente de ZONAS_RECINTOS
CREATE TABLE ASIENTOS (
    Cod_Recinto VARCHAR(30) NOT NULL,
    Zona VARCHAR(20) NOT NULL,
    Fila CHAR(5) NOT NULL, -- Cambiado a CHAR(5) por si la fila es "AA" o "10"
    Numero INT NOT NULL,
    PRIMARY KEY (Cod_Recinto, Zona, Fila, Numero),
    CONSTRAINT fk_asientos_zona FOREIGN KEY (Cod_Recinto, Zona)
        REFERENCES ZONAS_RECINTOS(Cod_Recinto, Zona)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- 5. Tabla dependiente de ESPECTACULOS y ZONAS_RECINTOS
CREATE TABLE PRECIOS_ESPECTACULOS (
    Cod_Espectaculo VARCHAR(30) NOT NULL,
    Cod_Recinto VARCHAR(30) NOT NULL,
    Zona VARCHAR(20) NOT NULL,
    Precio DECIMAL(8,2) NOT NULL,
    PRIMARY KEY (Cod_Espectaculo, Cod_Recinto, Zona),
    CONSTRAINT fk_precios_espect FOREIGN KEY (Cod_Espectaculo)
        REFERENCES ESPECTACULOS(Cod_Espectaculo)
        ON UPDATE CASCADE,
    CONSTRAINT fk_precios_zona FOREIGN KEY (Cod_Recinto, Zona)
        REFERENCES ZONAS_RECINTOS(Cod_Recinto, Zona)
        ON UPDATE CASCADE
);

-- 6. Tabla dependiente de ESPECTACULOS
CREATE TABLE REPRESENTACIONES (
    Cod_Espectaculo VARCHAR(30) NOT NULL,
    Fecha DATE NOT NULL,
    Hora TIME NOT NULL,
    PRIMARY KEY (Cod_Espectaculo, Fecha, Hora),
    CONSTRAINT fk_representaciones_esp FOREIGN KEY (Cod_Espectaculo)
        REFERENCES ESPECTACULOS(Cod_Espectaculo)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- 7. Tabla independiente (Cambio de nombre: CLIENTES -> ESPECTADORES)
CREATE TABLE ESPECTADORES (
    Dni_Cliente VARCHAR(10) PRIMARY KEY,
    Nombre VARCHAR(40) NOT NULL,
    Direccion VARCHAR(100),
    Telefono VARCHAR(15),
    Ciudad VARCHAR(40),
    Ntarjeta VARCHAR(30)
);

-- 8. Tabla final que une REPRESENTACIONES, ASIENTOS y ESPECTADORES
CREATE TABLE ENTRADAS (
    Cod_Espectaculo VARCHAR(30) NOT NULL,
    Fecha DATE NOT NULL,
    Hora TIME NOT NULL,
    Cod_Recinto VARCHAR(30) NOT NULL,
    Zona VARCHAR(20) NOT NULL,
    Fila CHAR(5) NOT NULL,
    Numero INT NOT NULL,
    Dni_Cliente VARCHAR(10),
    
    PRIMARY KEY (Cod_Espectaculo, Fecha, Hora, Cod_Recinto, Zona, Fila, Numero),
    
    CONSTRAINT fk_entradas_representacion FOREIGN KEY (Cod_Espectaculo, Fecha, Hora)
        REFERENCES REPRESENTACIONES(Cod_Espectaculo, Fecha, Hora)
        ON DELETE RESTRICT ON UPDATE CASCADE,
        
    CONSTRAINT fk_entradas_asiento FOREIGN KEY (Cod_Recinto, Zona, Fila, Numero)
        REFERENCES ASIENTOS(Cod_Recinto, Zona, Fila, Numero)
        ON DELETE RESTRICT ON UPDATE CASCADE,
        
    CONSTRAINT fk_entradas_espectador FOREIGN KEY (Dni_Cliente)
        REFERENCES ESPECTADORES(Dni_Cliente)
        ON DELETE SET NULL ON UPDATE CASCADE
);