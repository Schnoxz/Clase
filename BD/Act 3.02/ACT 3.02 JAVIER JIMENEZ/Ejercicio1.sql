CREATE DATABASE biblioteca;
USE biblioteca;

CREATE TABLE libros (
    idLibro INT AUTO_INCREMENT,
    titulo VARCHAR(200) NOT NULL,
    autor VARCHAR(200) NOT NULL,
    anio_publicacion INT,
    categoria VARCHAR(50) NOT NULL,
    CONSTRAINT librosPK PRIMARY KEY (idLibro)
);

CREATE TABLE prestamos (
    idPrestamo INT AUTO_INCREMENT,
    idLibro INT NOT NULL,
    idUsuario INT NOT NULL,
    fecha_prestamo_inicio DATE NOT NULL,
    fecha_prestamo_fin DATE,
    estado_prestamo BOOLEAN NOT NULL,
    CONSTRAINT prestamosPK PRIMARY KEY (idPrestamo),
);    CONSTRAINT prestamosLibroFK FOREIGN KEY (idLibro)
        REFERENCES libros(idLibro)

