/* Función: calcular_suma_pagos_cliente
Descripción: Dado un código de cliente la función debe calcular la suma total de los pagos realizados por ese cliente.
Parámetros de entrada: codigo_cliente (INT)
Parámetros de salida: La suma total de todos los pagos del cliente (DECIMAL) */

DELIMITER ??
-- Funcion que devuelve la suma de los pagos de un cliente especifico
CREATE FUNCTION calcular_suma_pagos_cliente(codigo INT) RETURNS DECIMAL(10,2)
BEGIN
    DECLARE suma_pagos DECIMAL(10,2);
    -- Sumo el total de cada pago y lo guardo en la variable suma_pagos
    SELECT SUM(total) INTO suma_pagos FROM pago 
    WHERE codigo = codigo_cliente; -- Donde el codigo sea igual al codigo_cliente
    IF suma_pagos IS NULL THEN -- Para poder usar la funcion en otro procedimiento y que sume el campo donde exista un null, es traducir a un valor 0 y poder crear un sumatorio
        SET suma_pagos = 0;
    END IF;
    RETURN suma_pagos; -- Devuelvo el precio_total y se muestra el dato
END ??
DELIMITER ;

