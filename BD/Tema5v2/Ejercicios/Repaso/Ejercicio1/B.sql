/* Función: calcular_suma_pedidos_cliente
Descripción: Dado un código de cliente la función debe calcular la suma total de todos los pedidos realizados por el cliente. Deberá hacer uso de la función calcular_precio_total_pedido que ha desarrollado en el apartado anterior.
Parámetros de entrada: codigo_cliente (INT)
Parámetros de salida: La suma total de todos los pedidos del cliente (DECIMAL) */

DELIMITER ??
-- Funcion que devuelve la suma de los precios de todos los pedidos de un cliente especifico
CREATE FUNCTION calcular_suma_pedidos_cliente(codigo INT) RETURNS DECIMAL(10,2)
BEGIN
    DECLARE suma_pedidos DECIMAL(10,2);
    -- Hago un sumatorio llamando a la funcion calcular_precio_total_pedido, pasando por parametro el id_pedido, y lo añado a la variable suma_pedidos de la tabla pedido
    SELECT SUM(calcular_precio_total_pedido(codigo_pedido)) INTO suma_pedidos FROM pedido 
    WHERE codigo = codigo_cliente; -- Donde el codigo sea igual al codigo_cliente
    RETURN suma_pedidos; -- Devuelvo el precio_total y se muestra el dato
END ??
DELIMITER ;