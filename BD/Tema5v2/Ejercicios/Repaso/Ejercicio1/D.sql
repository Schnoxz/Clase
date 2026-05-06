/* Procedimiento: calcular_pagos_pendientes
Descripción: Deberá calcular los pagos pendientes de todos los clientes. Para saber si un cliente tiene algún pago pendiente deberemos calcular cuál es la cantidad de todos los pedidos y los pagos que ha realizado. Si la cantidad de los pedidos es mayor que la de los pagos entonces ese cliente tiene pagos pendientes.
Deberá utilizar las funciones calcular_suma_pedidos_cliente y calcular_suma_pagos_cliente, que ha desarrollado en los ejercicios anteriores. */

DELIMITER ??

CREATE OR REPLACE PROCEDURE calcular_pagos_pendientes()
BEGIN
    -- Muestra los clientes que tienen pagos pendientes y la cantidad de pagos pendientes
    SELECT codigo_cliente, nombre_cliente, calcular_suma_pedidos_cliente(c.codigo_cliente) AS total_pedidos, 
    calcular_suma_pagos_cliente(c.codigo_cliente) AS total_pagos, 
    (calcular_suma_pedidos_cliente(c.codigo_cliente) - calcular_suma_pagos_cliente(c.codigo_cliente)) AS saldo_pendiente 
    FROM cliente c
    HAVING saldo_pendiente > 0;
END??
DELIMITER ;