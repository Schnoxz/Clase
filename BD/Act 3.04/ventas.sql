CREATE DATABASE IF NOT EXISTS ventas; 

USE ventas;

CREATE TABLE cliente ( 
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(25), 
    primer_apellido VARCHAR(15) NOT NULL, 
    ciudad VARCHAR(100), 
    categoria INT);

CREATE TABLE comercial ( id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
                        nombre VARCHAR(100) NOT NULL, 
                        apellido1 VARCHAR(100) NOT NULL, 
                        apellido2 VARCHAR(100), 
                        ciudad VARCHAR(100), 
                        comision FLOAT);
