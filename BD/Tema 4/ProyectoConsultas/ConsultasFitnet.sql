-- 1. Selecciona los usuarios que tengan una suscripción VIP (Activa o caducada) y que no sean entrenadores. (He usado INNER JOIN + LEFT JOIN + WHERE + AND)

SELECT CONCAT(u.nombre, ' ', u.apellido) AS UsuariosVIP FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio
INNER JOIN membresia m ON s.id_membresia = m.id_membresia
LEFT JOIN entrenador e ON u.id_usuario = e.id_entrenador
WHERE m.tipo = 'Anual VIP'
AND e.id_entrenador IS NULL;

-- 2. Dime el nombre y correo de los usuarios (Activos) que tienen una membresía anual que siga activa actualmente. (He usado INNER JOIN + WHERE + LIKE + AND)

SELECT u.nombre, u.email, m.tipo, m.estado FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio
INNER JOIN membresia m ON s.id_membresia = m.id_membresia
WHERE m.tipo LIKE 'Anual%' AND m.estado = 'Vigente';

-- 3. Crea una columna con las distintas clases que hay y su cupo maximo, ordena por orden alfabetico (He usado SUM + GROUP BY + ORDER BY)

SELECT c.nombre_clase AS Clases, SUM(c.cupo_maximo) AS CupoMaximo FROM clase c
GROUP BY c.nombre_clase
ORDER BY c.nombre_clase ASC;

-- 4. Selecciona el usuario que más clases practica y muestra su nombre, correo y la cantidad de clases que practica. (He usado COUNT + GROUP BY + ORDER BY)

SELECT CONCAT(u.nombre, ' ', u.apellido) AS NombreCompleto, u.email, COUNT(a.id_clase) AS Clases FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio
INNER JOIN asistencia a ON s.id_socio = a.id_socio
INNER JOIN clase c ON a.id_clase = c.id_clase
GROUP BY u.id_usuario
ORDER BY COUNT(a.id_clase) DESC;


-- 5. Muestra los usuarios VIP y los usuarios Anual, en una misma consulta. (Aqui muestro un ejemplo de UNION con dos consultas separadas)

SELECT CONCAT(u.nombre, ' ', u.apellido) AS NombreCompleto, u.email, m.tipo, m.estado FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio
INNER JOIN membresia m ON s.id_membresia = m.id_membresia
WHERE m.tipo LIKE '%VIP'
UNION
SELECT CONCAT(u.nombre, ' ', u.apellido) AS NombreCompleto, u.email, m.tipo, m.estado FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio
INNER JOIN membresia m ON s.id_membresia = m.id_membresia
WHERE m.tipo LIKE '%Anual';

-- 6. Suma los pagos de los usuarios que tengan membresia anual y haz una media de cuanto pagan en total al mes

SELECT SUM(p.cantidad) AS TotalPagos, AVG(p.cantidad) AS MediaPagos FROM pago p
INNER JOIN socio s ON p.id_socio = s.id_socio
INNER JOIN membresia m ON s.id_membresia = m.id_membresia
WHERE m.tipo LIKE '%Anual';





-- Crear vistas

-- 1. Vista con los datos personales de todos los usuarios VIP (Se usa CREATE VIEW, y si quiero borrarla con un DROP VIEW)
CREATE VIEW DatosVIP AS SELECT CONCAT(u.nombre, ' ', u.apellido) AS NombreCompleto, u.email, m.tipo, m.estado FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio
INNER JOIN membresia m ON s.id_membresia = m.id_membresia
WHERE m.tipo LIKE '%VIP' -- De momento no hay usuarios con VIP que no sea Anual pero si en un futuro los hay uso el LIKE igualmente, no concreto con un '='
AND m.estado = 'Vigente';

-- Eliminación de datos fitlrando valores o con subconsulta

-- 1. Eliminar todos los datos de los socios que no tengan una membresía activa (vigente) (Se usa DELETE FROM)
-- Desde la tabla socio, obtengo aquellos socios que no tengan una membresia activa con una subconsulta
DELETE FROM socio WHERE id_socio NOT IN (SELECT id_socio FROM socio INNER JOIN membresia ON socio.id_membresia = membresia.id_membresia WHERE membresia.estado = 'Vigente');


-- Crear actualizacion filtrando valores o con una subconsulta

-- 1. Cambia el entrenador de la clase de yoga con el entrenador de la clase de Boxeo (Como no sé el nombre completo de la clase uso LIKE y tampoco sé que entrenador es cual hago una subconsulta)
UPDATE clase SET id_entrenador = (SELECT id_entrenador FROM clase WHERE nombre_clase LIKE '%Boxeo')
WHERE id_entrenador = (SELECT id_entrenador FROM clase WHERE nombre_clase LIKE '%Yoga');

-- Inserción de datons filtrando valores o con una subconsulta

-- 1. Inserta a un socio una membresia mensual (Se usa INSERT INTO y VALUES)

INSERT INTO socio (id_socio, id_membresia) SELECT u.id_usuario, m.id_membresia FROM usuario u
JOIN membresia m ON m.tipo = 'Mensual VIP'
WHERE u.id_usuario = 16;
