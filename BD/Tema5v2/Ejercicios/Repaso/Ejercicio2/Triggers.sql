/* Crea una tabla que se llame notificaciones que tenga las siguientes columnas:

id (entero sin signo, autoincremento y clave primaria)
fecha_hora: marca de tiempo con el instante del pago (fecha y hora)
total: el valor del pago (real)
codigo_cliente: código del cliente que realiza el pago (entero)
a

Escriba un trigger que nos permita llevar un control de los pagos que van realizando los clientes. Los detalles de implementación son los siguientes:

Nombre: trigger_notificar_pago
Se ejecuta sobre la tabla pago.
Se ejecuta después de hacer la inserción de un pago.
Cada vez que un cliente realice un pago (es decir, se hace una inserción en la tabla pago), el trigger deberá insertar un nuevo registro en una tabla llamada notificaciones.
b

Escriba algunas sentencias SQL para comprobar que el trigger funciona correctamente */


-- a) Trigger

CREATE TABLE IF NOT EXISTS notificaciones (id INT UNSIGNED NOT NULL AUTO_INCREMENT, fecha_hora TIMESTAMP NOT NULL, total DECIMAL(10,2) NOT NULL, codigo_cliente INT NOT NULL, PRIMARY KEY (id));

CREATE TRIGGER trigger_notificar_pago
AFTER INSERT ON pago          -- Se dispara después de insertar en pago
FOR EACH ROW                  -- Por cada fila insertada
BEGIN
    INSERT INTO notificaciones (fecha_hora, total, codigo_cliente) -- ID no hace falta la ser autoincrement
    VALUES (NOW(), NEW.total, NEW.codigo_cliente); -- NEW.columna accede al valor recién insertado
END ??
DELIMITER ;

-- b) Comprobación del trigger

-- 1. Compruebo mi tabla nueva
SELECT * FROM notificaciones;

-- 2. Creo un pago de prueba
INSERT INTO pago (codigo_cliente, forma_pago, id_transaccion, fecha_pago, total)
VALUES (1, 'TRANSFERENCIA', 'TEST-001', '2024-01-15', 1500.00);

-- 3. Compruebo que el trigger haya sido activado
SELECT * FROM notificaciones;

-- 4. Finalmente compruebo que los datos sean correctos
SELECT p.codigo_cliente, p.total AS total_pago, n.fecha_hora, n.total AS total_notificacion FROM pago p
JOIN notificaciones n ON p.codigo_cliente = n.codigo_cliente
WHERE p.id_transaccion IN ('TEST-001');