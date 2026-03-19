-- 1 GESTIÓN DE USUARIOS
------------ *** 1.1 CREAR USUARIO *** ------------
CREATE USER 'usuario'@'host' IDENTIFIED BY 'contraseña'; --> SINTAXIS
CREATE USER 'juan'@'localhost' IDENTIFIED BY '1234'; --> EJEMPLO
-- Se crea el usuario Juan que solo puede conectarse desde el mismo ordenador y su contraseña es 1234.

CREATE USER 'usuario'@'%' IDENTIFIED BY 'abcd' --> SINTAXIS
CREATE USER 'ana'@'%' IDENTIFIED BY 'abcd' --> EJEMPLO
-- Se crea la usuaria Ana que puede conectarse desde cualquier lugar y su contraseña es abcd.



------------ *** 1.2 BORAR USUARIOS *** ------------
DROP USER 'usuario'@'host'; --> SINTAXIS
DROP USER 'juan'@'localhost'; --> EJEMPLO
-- Elimina completamente el usuario Juan de MySQL, si el usuario no existe dará error.



------------ *** 1.3 ASIGNAR PRIVILEGIOS *** ------------
GRANT privilegios ON base_datos.tabla TO 'usuario'@'host'; --> SINTAXIS
-- PERMISO          PERMITE
   SELECT          Consultar
   INSERT        Insertar datos
   UPDATE       Modificar datos
   DELETE         Borrar datos
ALL PRIVILEGES       Todos

GRANT ALL PRIVILEGES ON empresa.* TO 'juan'@'localhost'; --> EJEMPLO DAR TODOS LOS PERMISOS
-- El usuario Juan tiene todos los permisos sobre todas las tablas de la base empresa.

GRANT SELECT ON empresa.empleados TO 'juan'@'localhost'; --> EJEMPLO SOLO LEER DATOS
-- Juan solo puede consultar la tabla empleados, no puede insertar ni borrar datos.

GRANT SELECT, INSERT ON empresa.empleados TO 'juan'@'localhost'; --> EJEMPLO VARIOS PERMISOS
-- Juan puede consultar e insertar datos pero no puede modificarlos o borrarlos.



------------ *** 1.4 QUITAR PERMISOS *** ------------
REVOKE privilegios ON base_datos.tabla FROM 'usuario'@'host'; --> SINTAXIS
REVOKE INSERT ON empresa.empleados FROM 'juan'@'localhost'; --> EJEMPLO
-- Juan ya no puede insertar registros.



------------ *** 1.5 CONSULTAR USUARIOS *** ------------
SELECT user, host FROM mysql.user; --> SINTAXIS
-- USER       HOST
   root     localhost
   juan     localhost



------------ *** 1.6 MODIFICAR USUARIOS *** ------------
ALTER USER 'usuario'@'host' IDENTIFIED BY 'nueva_contraseña'; --> SINTAXIS
ALTER USER 'juan'@'localhost' IDENTIFIED BY '5678'; --> EJEMPLO
-- Se cambia la contraseña del usuario Juan a 5678.



------------ *** 1.7 MOSTRAR PRIVILEGIOS DE USUARIO *** ------------
SHOW GRANTS FOR 'usuario'@'host'; --> SINTAXIS
SHOW GRANTS FOR 'juan'@'localhost'; --> EJEMPLO

GRANT SELECT, INSERT ON empresa.* TO 'juan'@'localhost'
-- Indica que puede hacer Juan (consultas e inserciones).



------------ *** 1.8 INSERTAR UN NUEVO USUARIO *** ------------
INSERT INTO usuario (departamento, email, nombre, password) VALUES ('I+D', 'a0YXG@example.com', 'Maria', 'mariapass');



-- 2 TRANSACCIONES
START TRANSACTION; --> INICIAR TRANSACCIÓN
COMMIT; --> CONFIRMAR CAMBIOS
ROLLBACK; --> DESHACER CAMBIOS
SET AUTOCOMMIT = 0; --> DESACTIVAR MODO DE CONFIRMACIÓN AUTOMÁTICO
SET AUTOCOMMIT = 1; --> ACTIVAR MODO DE CONFIRMACIÓN AUTOMÁTICO


-- EJEMPLO COMPLETO DE TRANSACCIÓN
START TRANSACTION;
UPDATE empleados SET salario = 100 WHERE id = 1;
UPDATE empleados SET salario = 200 WHERE id = 2;
UPDATE empleados SET salario = 300 WHERE id = 3;
COMMIT;
-- Se han actualizado los salarios de los empleados con id 1, 2 y 3, y se han confirmado los cambios con COMMIT.


-- EJEMPLO TRANSACCIÓN QUITAR DINERO A UN USUARIO Y DARLO A OTRO
START TRANSACTION;
UPDATE cuentas SET dinero = dinero - 100 WHERE id = 1;
UPDATE cuentas SET dinero = dinero + 100 WHERE id = 2;
COMMIT;
-- Se ha transferido 100 unidades de dinero de la cuenta 1 a la cuenta 2, y se han confirmado los cambios con COMMIT.


-- EJEMPLO TRANSACCIÓN CON ERROR
START TRANSACTION;
UPDATE empleados SET salario = 100 WHERE id = 1;
UPDATE empleados SET salario = 200 WHERE id = 2;
UPDATE empleados SET salario = 300 WHERE id = 3;
ROLLBACK;
-- Se deshacen los cambios porque ha habido un error en la última consulta.


-- EJEMPLO TRANSACCIÓN CON MODO DE CONFIRMACIÓN AUTOMÁTICO DESACTIVADO
SET AUTOCOMMIT = 0;
START TRANSACTION;
UPDATE empleados SET salario = 100 WHERE id = 1;
UPDATE empleados SET salario = 200 WHERE id = 2;
UPDATE empleados SET salario = 300 WHERE id = 3;
COMMIT;
-- Se han realizado los cambios pero no se han confirmado automáticamente, por lo que es necesario hacer COMMIT para que se apliquen.
SET AUTOCOMMIT = 1;
-- Se vuelve a activar el modo de confirmación automático para que las futuras consultas se confirmen automáticamente.


-- EJEMPLO TRANSACCIÓN CON MODO DE CONFIRMACIÓN AUTOMÁTICO ACTIVADO
SET AUTOCOMMIT = 1;
START TRANSACTION;
UPDATE empleados SET salario = 100 WHERE id = 1;
UPDATE empleados SET salario = 200 WHERE id = 2;
UPDATE empleados SET salario = 300 WHERE id = 3;
COMMIT;
-- Se han realizado los cambios y se han confirmado automáticamente, por lo que no es necesario hacer COMMIT.


-- EJEMPLO TRANSACCIÓN CON ERROR Y MODO DE CONFIRMACIÓN AUTOMÁTICO DESACTIVADO
SET AUTOCOMMIT = 0;
START TRANSACTION;
UPDATE empleados SET salario = 100 WHERE id = 1;
UPDATE empleados SET salario = 200 WHERE id = 2;
UPDATE empleados SET salario = 300 WHERE id = 3;
ROLLBACK;
-- Se deshacen los cambios porque ha habido un error en la última consulta.
SET AUTOCOMMIT = 1;
-- Se vuelve a activar el modo de confirmación automático para que las futuras consultas se confirmen automáticamente.


-- EJERCICIOS DE PRÁCTICA PARA EXAMEN

-- EJ.1 Crea un usuario llamado empleado1 que solo pueda conectarse desde localhost y cuya contraseña sea emp123.
-- Después dale permisos para: consultar datos e insertar datos sobre todas las tablas de la base de datos empresa.
CREATE USER 'empleado1'@'localhost' IDENTIFIED BY 'emp123';
GRANT SELECT, INSERT ON empresa.* TO 'empleado1'@'localhost';

-- EJ.2 Dado el usuario anteriormente creado, modifica sus permisos para que también pueda actualizar datos en la tabla empleados de la base empresa.
GRANT UPDATE ON empresa.empleados TO 'empleado1'@'localhost';

--EJ.3 El usuario empleado1 tiene demasiados permisos. Quita el permiso INSERT sobre la tabla empleados.
REVOKE INSERT ON empresa.empleados FROM 'empleado1'@'localhost';

--EJ.4 Muestra por pantalla todos los permisos que tiene el usuario: empleado1.
SHOW GRANTS FOR 'empleado1'@'localhost';

--EJ.5 Elimina el usuario empleado1.
DROP USER 'empleado1'@'localhost';

--EJ.6 Crea un usuario lector que:
--    Pueda conectarse desde cualquier equipo.
--    Solo pueda consultar datos sobre la base de datos empresa.
CREATE USER 'lector'@'%' IDENTIFIED BY 'lect123';
GRANT SELECT ON empresa.* TO 'lector'@'%';

-- EJ.7 Simula una transferencia bancaria
-- Tabla:
      cuentas
      ------------
      id
      saldo
-- Realiza una transacción que:
--     1️⃣ reste 200€ de la cuenta 1
--     2️⃣ sume 200€ a la cuenta 2
-- Si todo va bien, confirma la operación.
START TRANSACTION;
UPDATE cuentas SET saldo = saldo - 200 WHERE id = 1;
UPDATE cuentas SET saldo = saldo + 200 WHERE id = 2;
COMMIT;

--EJ.8 Realiza una transacción que inserte dos empleados:
--    (10,'Ana',1500)
--    (11,'Luis',1600)
-- en la tabla empleados, después confirma los cambios.
START TRANSACTION;
INSERT INTO empleados VALUES (10,'Ana',1500);
INSERT INTO empleados VALUES (11,'Luis',1600);
COMMIT;

-- EJ.9 Realiza las siguientes operaciones dentro de una transacción:
--     1️⃣ insertar un departamento
--     2️⃣ insertar un empleado que pertenece a ese departamento
-- Si ocurre algún error, cancela toda la operación.
START TRANSACTION;
INSERT INTO departamentos VALUES (1,'Recursos Humanos');
INSERT INTO empleados VALUES (12,'Carlos',1700,1);
COMMIT;

--EJ.10 Desactiva el autocommit de MySQL.
-- Después:
--       1️⃣ inicia una transacción.
--       2️⃣ actualiza el salario de todos los empleados aumentando 100€.
--       3️⃣ cancela la operación para que no se guarden los cambios.
SET AUTOCOMMIT = 0;
START TRANSACTION;
UPDATE empleados SET salario = salario + 100;
ROLLBACK;

--EJ.11 Analiza el siguiente código:
START TRANSACTION;
UPDATE cuentas SET saldo = saldo - 500 WHERE id = 1;
UPDATE cuentas SET saldo = saldo + 500 WHERE id = 2;
ROLLBACK;
-- Pregunta:
-- 1️⃣ ¿Se modifica el saldo de las cuentas?
-- 2️⃣ ¿Por qué?
-- Respuesta:
-- 1️⃣ No se modifica el saldo de las cuentas.
-- 2️⃣ Porque se ha hecho un ROLLBACK, lo que deshace todos los cambios realizados en la transacción, por lo que el saldo de las cuentas no se ve afectado.

--EJ.12 Se ejecuta la sentencia:
UPDATE empleados SET salario = salario + 50;
-- Después se ejecuta:
ROLLBACK;
-- Pregunta:
-- 1️⃣ ¿Se deshace la operación? Explica por qué.
-- Respuesta:
-- 1️⃣ No se deshace la operación porque no se ha iniciado una transacción con START TRANSACTION,
--    por lo que el ROLLBACK no tiene ningún efecto y los salarios de los empleados se han incrementado en 50.

--EJ.13 Analiza el siguiente código:
START TRANSACTION;
INSERT INTO empleados VALUES (20,'Carlos',1700);
COMMIT;
ROLLBACK;
-- Pregunta:  
-- 1️⃣ ¿Se inserta el empleado?
-- 2️⃣ ¿Por qué?
-- Respuesta:
-- 1️⃣ Sí se inserta el empleado.
-- 2️⃣ Porque se ha hecho un COMMIT después de la inserción, lo que confirma los cambios realizados en la transacción,
--    por lo que el empleado se ha insertado en la tabla. El ROLLBACK posterior no tiene ningún efecto porque ya se han confirmado los cambios con COMMIT.

--EJ.14 Escribe una transacción que:
-- 1️⃣ cree un nuevo pedido
-- 2️⃣ inserte 3 productos en ese pedido
-- 3️⃣ confirme los cambios
START TRANSACTION;
INSERT INTO pedidos VALUES (1,'2024-06-01',100);
INSERT INTO productos_pedidos VALUES (1,1,'Producto A',10);
INSERT INTO productos_pedidos VALUES (1,2,'Producto B',20);
INSERT INTO productos_pedidos VALUES (1,3,'Producto C',30);
COMMIT;

-- EJ.15 ¿Qué ocurre si ejecutas:
START TRANSACTION;
DROP TABLE empleados;
ROLLBACK;
-- Pregunta:
-- 1️⃣ ¿Se recupera la tabla?
-- 2️⃣ ¿Por qué?
-- Respuesta:
-- 1️⃣ No se recupera la tabla.
-- 2️⃣ Porque DROP TABLE es una operación que no puede ser deshecha con ROLLBACK, ya que elimina físicamente la tabla y todos sus datos.

-- Crea un usuario dev1 con contraseña dev123 que solo pueda conectarse desde localhost.
CREATE USER 'dev1'@'localhost' IDENTIFIED BY 'dev123';

-- Dale a dev1 permisos para: consultar, insertar y actualizar sobre todas las tablas de la base de datos empresa.
GRANT SELECT, INSERT, UPDATE ON empresa.* TO 'dev1'@'localhost';

-- Modifica los permisos de dev1 para que solo pueda consultar datos en toda la base.
REVOKE INSERT, UPDATE ON empresa.* FROM 'dev1'@'localhost';

-- Quita todos los permisos sobre la tabla empleados al usuario dev1.
REVOKE ALL PRIVILEGES ON empresa.empleados FROM 'dev1'@'localhost';

-- Crea un usuario invitado que: pueda conectarse desde cualquier IP y solo tenga permiso SELECT sobre la tabla productos.
CREATE USER 'invitado'@'%' IDENTIFIED BY '123';
GRANT SELECT ON empresa.productos TO 'invitado'@'%';

-- Muestra todos los usuarios existentes en MySQL.
SELECT * FROM MYSQL.USER;

-- Muestra los permisos del usuario:
SHOW GRANTS FOR 'dev1'@'localhost';

-- ¿Es correcto esto?
GRANT ALL ON empresa TO 'dev1'@'localhost'; --> NO
-- Corrígelo si está mal.
GRANT ALL PRIVILEGES ON empresa.* TO 'dev1'@'localhost'; --> CORRECCIÓN

-- Realiza una transacción que:
--    1️⃣ reste 300€ de la cuenta 1.
--    2️⃣ sume 300€ a la cuenta 2.
--    3️⃣ confirme los cambios.
START TRANSACTION;
UPDATE cuentas SET balance = balance -300
WHERE numeroCuenta = '1';
UPDATE cuentas SET balance = balance +300
WHERE numeroCuenta = '2';
COMMIT;

-- Haz una transacción que:
--    1️⃣ inserte un cliente
--    2️⃣ inserte un pedido asociado
-- Si algo falla, deshaz todo.
START TRANSACTION;
INSERT INTO clientes VALUES (1, 'Nacho');
INSERT INTO pedidos VALUES (1, 1); -- ej: pedido 1 del cliente 1 (Nacho).
COMMIT;
ROLLBACK;
-- NO SE COMO HACER ESTE EJERCICIO Y DUDO QUE ESTO ESTÉ BIEN.

-- Desactiva el autocommit y realiza:
--    Una actualización de salarios (+200€)
--    Deshaz los cambios
SET AUTOCOMMIT = 0;
START TRANSACTION;
UPDATE empleados SET salario = salario +200;
ROLLBACK;

-- Indica qué ocurre:
START TRANSACTION;
UPDATE empleados SET salario = salario + 100;
COMMIT;
-- Los cambios no se van a deshacer ya que se confirman previamente con COMMIT y esto es permanente.

-- Explica el resultado:
UPDATE empleados SET salario = salario + 100;
ROLLBACK;
-- (sin START TRANSACTION)
-- No se deshace la operación, porque al no estar dentro de una transacción explícita
-- y estar autocommit activado por defecto, el UPDATE se confirma automáticamente.

-- Haz una transacción que:
--    Inserte 3 productos
--    Confirme solo si todos se insertan correctamente
START TRANSACTION;
INSERT INTO productos VALUES (1, 'Teclado', 25);
INSERT INTO productos VALUES (2, 'Ratón', 15);
INSERT INTO productos VALUES (3, 'Monitor', 180);
COMMIT;

-- ¿Qué ocurre aquí?
START TRANSACTION;
INSERT INTO empleados VALUES (1,'Ana',1500);
INSERT INTO empleados VALUES (1,'Luis',1600);
ROLLBACK;
-- La segunda sentencia falla porque intenta insertar una clave primaria ya existente.
-- Como luego se hace ROLLBACK, no se guarda ninguno de los dos INSERTS.

-- Analiza:
START TRANSACTION;
DELETE FROM empleados WHERE salario < 1000;
COMMIT;
ROLLBACK;
-- Se confirman los cambios ya que COMMIT se produce antes que ROLLBACK.

-- ¿Qué ocurre aquí?
START TRANSACTION;
CREATE TABLE prueba (id INT);
ROLLBACK;
-- Se crea la tabla ya que CREATE es una sentencia DDL.

--Se quiere crear un usuario app_user que:
--    Pueda conectarse desde cualquier host.
--    Tenga todos los permisos sobre la base tienda.
--    Pero NO pueda borrar datos.
CREATE USER 'app_user'@'%' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON tienda.* TO 'app_userr'@'%';
REVOKE DELETE ON tienda.* FROM 'app_userr'@'%';

-- Simula un proceso de compra:
--    1️⃣ insertar pedido
--    2️⃣ insertar 2 líneas de pedido
--    3️⃣ actualizar stock
-- Todo debe hacerse en una transacción.
START TRANSACTION;
INSERT INTO pedidos VALUES (1, '2026-03-19', 5);
INSERT INTO lineas_pedido VALUES (1, 101, 2, 20); -- Donde 1 = id pedido, 101 = id producto, 2 = u/vendidas, 5 = precio.
INSERT INTO lineas_pedido VALUES (1, 102, 1, 15);
UPDATE productos SET stock = stock - 2 WHERE id = 101; -- Se actualiza el stock del producto 101 con -2 unidades que son las que se han vendido.
UPDATE productos SET stock = stock - 1 WHERE id = 102;
COMMIT;

-- Tienes este código:
SET autocommit = 0;
UPDATE cuentas SET saldo = saldo - 100 WHERE id = 1;
UPDATE cuentas SET saldo = saldo + 100 WHERE id = 2;
-- Pregunta:
-- 1️⃣ ¿Se han guardado los cambios? NO.
-- 2️⃣ ¿Qué falta? COMMIT para confirmar cambios.
-- 3️⃣ ¿Qué pasaría si ahora ejecutas ROLLBACK? Se deshacen los cambios.