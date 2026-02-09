
-- 1. Crea una vista llamada V_CLIENTES_VALENCIA que muestre el nombre, apellidos y ciudad de todos los clientes que viven en 'Valencia'. 

```
CREATE VIEW V_CLIENTES_VALENCIA AS
SELECT nombre, apellidos, ciudad
FROM clientes
WHERE ciudad = 'Valencia';   
```

-- 2. Crea una vista llamada V_PEDIDOS_DETALLADOS. Debe mostrar el ID del pedido, la fecha, el total, y el nombre completo del cliente (nombre + apellidos) y el nombre completo del comercial.

```
CREATE VIEW V_PEDIDOS_DETALLADOS AS
select p.idPedido as Pedido, 
       p.fechaPedido as Fecha, 
       SUM(d.cantidadPedida * d.PrecioUnidad) as Total, 
       concat(c.nombreContacto, ' ', c.apellidoContacto) as 'Nombre_cliente',
	   concat (e.nombre, ' ', e.apellido) as comercial
from pedidos p 
INNER JOIN clientes c ON (p.idCliente = c.idCliente) 
INNER JOIN empleados e ON (c.idEmpleadoResponsable = e.idEmpleado) 
INNER JOIN detallespedidos d ON (p.idPedido = d.idPedido)
group by p.idPedido
```


-- 3. Crea una vista llamada V_ESTADISTICAS_COMERCIAL. Debe mostrar el nombre del comercial y la suma total de todos sus pedidos realizados, pero solo para aquellos comerciales que hayan vendido más de 5000000 euros en total. 
```
CREATE VIEW V_ESTADISTICAS_COMERCIAL AS
SELECT concat(e.nome, ' ', e.sobrenome) AS empleados, 
       SUM(d.cantidadPedido * d.PrecioUnidad) AS Total, 
       count(p.idPedido) 
FROM pedidos e 
INNER JOIN clientes c ON (p.idCliente = c.idCliente) 
INNER JOIN detallespedidos d ON (p.idPedido = d.idPedido) 
WHERE total > 500000 
GROUP BY e.idEmpleado 
-- HAVING total > 500000;    
```

-- 4. Una vez creada la vista V_CLIENTES_, ¿cómo consultarías solo a los clientes cuyo apellido sea 'García' usando esa vista? 
```
SELECT * 
FROM V_CLIENTES_ 
WHERE apellido = 'García';   
```


-- 5. Cambia la vista V_CLIENTES_VALENCIA para que ahora también incluya la columna codiigo postal 
```
CREATE OR REPLACE VIEW V_CLIENTES_VALENCIA AS
SELECT nombre, apellidos, ciudad, codigoPostal
FROM clientes
WHERE ciudad = 'Valencia';   
```

-- 6. Elimina la vista V_PEDIDOS_DETALLADOS de la base de datos 
```
DROP VIEW V_PEDIDOS_DETALLADOS;   
```