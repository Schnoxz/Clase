/* Procedimiento: calcular_pagos_pendientes
Descripción: Deberá calcular los pagos pendientes de todos los clientes. Para saber si un cliente tiene algún pago pendiente deberemos calcular cuál es la cantidad de todos los pedidos y los pagos que ha realizado. Si la cantidad de los pedidos es mayor que la de los pagos entonces ese cliente tiene pagos pendientes.
Deberá utilizar las funciones calcular_suma_pedidos_cliente y calcular_suma_pagos_cliente, que ha desarrollado en los ejercicios anteriores.
Deberá insertar en una tabla llamada clientes_con_pagos_pendientes los siguientes datos:

codigo_cliente
suma_total_pedidos
suma_total_pagos
pendiente_de_pago */

-- Creo la tabla con los campos que hacen falta 
CREATE TABLE IF NOT EXISTS clientes_con_pagos_pendientes (codigo_cliente INT NOT NULL, suma_total_pedidos DECIMAL(12,2), suma_total_pagos DECIMAL(12,2), pendiente_de_pago DECIMAL(12,2)
);

-- Creo el procedimiento de pagos pendientes con un cursor
DELIMITER ??
CREATE PROCEDURE calcular_pagos_pendientes()
BEGIN
    -- Declaro las variables
    DECLARE codigo INT;
    DECLARE suma_pedidos DECIMAL(12,2);
    DECLARE suma_pagos DECIMAL(12,2);
    -- Declaro la variable fin
    DECLARE fin INT DEFAULT 0;
    -- Declaro el cursor
    DECLARE cursor_clientes CURSOR FOR SELECT codigo_cliente, calcular_suma_pedidos_cliente(codigo_cliente), calcular_suma_pagos_cliente(codigo_cliente) FROM cliente;
    -- Declaro el handler si no encuentra más filas, se establezca la variable fin a 1
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;

    -- Abro el cursor
    OPEN cursor_clientes;
    -- Bucle para recorrer el cursor
    bucle: LOOP
        -- Recorro el cursor
        FETCH cursor_clientes INTO codigo, suma_pedidos, suma_pagos;
        -- Si el cursor no encuentra más filas, sale del bucle
        IF fin = 1 THEN
            LEAVE bucle;
        END IF;

        -- Pongo la condición para saber si hay pagos pendientes y poder insertarlos en la tabla
        IF suma_pedidos > suma_pagos THEN
        INSERT INTO clientes_con_pagos_pendientes VALUES (codigo, suma_pedidos, suma_pagos, suma_pedidos - suma_pagos);
        END IF;
    END LOOP bucle;
    -- Cierro el cursor
    CLOSE cursor_clientes;
END ??
DELIMITER ;
