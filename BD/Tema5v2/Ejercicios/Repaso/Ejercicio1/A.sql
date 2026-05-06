/*Función: calcular_precio_total_pedido
Descripción: Dado un código de pedido la función debe calcular la suma total del pedido. Tenga en cuenta que un pedido puede contener varios productos diferentes y varias cantidades de cada producto.
Parámetros de entrada: codigo_pedido (INT)
Parámetros de salida: El precio total del pedido (DECIMAL) */

-- El delimiter se personaliza con el ?? para la funcion, pero el bloque de codigo es global y se finaliza con ;

DELIMITER ??
-- Funcion que devuelve el total de coste de un pedido sumando los precios de todos los productos del pedido
CREATE FUNCTION calcular_precio_total_pedido (codigo INT) RETURNS DECIMAL(10,2)
BEGIN
    DECLARE precio_total DECIMAL(10,2);
    -- Sumo los precios de cada unidad por la cantidad de unidades y los guardo en la variable precio_total
    SELECT SUM(precio_unidad * cantidad) INTO precio_total FROM detalle_pedido -- Tabla detalles_pedido
    WHERE codigo_pedido = codigo; -- Donde el codigo sea igual al codigo_pedido
    RETURN precio_total; -- Devuelvo el precio_total y se muestra el dato
END ??
DELIMITER ;