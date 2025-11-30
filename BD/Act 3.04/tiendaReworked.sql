-- EJERCICIO 2: TIENDA 

-- Intentar borrar a Juan Pérez (Falla si tiene pedidos y está en RESTRICT)
-- DELETE FROM cliente WHERE id_cliente = 2; 

-- SOLUCIÓN: Modificar la tabla PEDIDO para aplicar CASCADE
-- 1. Primero averiguamos el nombre de la FK o la borramos si lo sabemos.
-- Suponiendo que la FK se llama 'fk_pedido_cliente':
ALTER TABLE pedido DROP FOREIGN KEY fk_pedido_cliente;

-- 2. Volvemos a crear la FK con ON DELETE CASCADE
ALTER TABLE pedido 
ADD CONSTRAINT fk_pedido_cliente 
FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) 
ON DELETE CASCADE;

-- AHORA SÍ ejecutamos los borrados solicitados:
-- Al borrar el cliente, se borrarán sus pedidos automáticamente.

-- Eliminar al Cliente 2 (Juan Pérez)
DELETE FROM cliente WHERE id_cliente = 2;

-- Eliminar al Cliente 3 (María García)
DELETE FROM cliente WHERE id_cliente = 3;


-- En RESTRICT, no modificamos la tabla, debemos limpiar los datos hijos manualmente.

-- CASO 1: Eliminar Pedido 10 (Tiene productos asociados)
-- Primero borramos los hijos (detalles)
DELETE FROM detalle_pedido WHERE id_pedido = 10;
-- Luego borramos el padre (pedido)
DELETE FROM pedido WHERE id_pedido = 10;

-- CASO 2: Eliminar Producto 101 (Café - Está en pedidos)
-- Primero borramos las referencias en detalles (Cuidado: esto altera pedidos históricos)
DELETE FROM detalle_pedido WHERE id_producto = 101;
-- Luego borramos el producto
DELETE FROM producto WHERE id_producto = 101;

-- CASO 3: Eliminar Producto 104 (Chocolate - NO está en pedidos)
-- Como no tiene hijos, se borra directamente sin errores.
DELETE FROM producto WHERE id_producto = 104;


-- CASO 1: Cambiar ID Cliente de 1 a 100
-- Necesitamos que la FK en PEDIDO tenga ON UPDATE CASCADE.
-- (Reutilizamos el nombre de FK del ejercicio A, añadimos update cascade)

ALTER TABLE pedido DROP FOREIGN KEY fk_pedido_cliente;

ALTER TABLE pedido 
ADD CONSTRAINT fk_pedido_cliente 
FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) 
ON DELETE CASCADE ON UPDATE CASCADE;

-- Ahora ejecutamos el cambio
UPDATE cliente SET id_cliente = 100 WHERE id_cliente = 1;


-- CASO 2: Cambiar ID Producto Leche de 102 a 500
-- Necesitamos modificar la FK en DETALLE_PEDIDO para que soporte CASCADE en Update.
-- Suponemos que la FK se llama 'fk_detalle_producto'

ALTER TABLE detalle_pedido DROP FOREIGN KEY fk_detalle_producto;

ALTER TABLE detalle_pedido 
ADD CONSTRAINT fk_detalle_producto 
FOREIGN KEY (id_producto) REFERENCES producto(id_producto) 
ON DELETE RESTRICT ON UPDATE CASCADE;

-- Ahora ejecutamos el cambio
UPDATE producto SET id_producto = 500 WHERE id_producto = 102;