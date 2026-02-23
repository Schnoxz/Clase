-- 1. Selecciona los usuarios que tengan una suscripción VIP (Activa o caducada) y que no sean entrenadores. (He usado INNER JOIN + LEFT JOIN + WHERE + AND)

SELECT CONCAT(u.nombre, ' ', u.apellido) AS UsuariosVIP FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio 
INNER JOIN membresia m ON s.id_socio = m.id_socio
INNER JOIN plan p ON m.id_plan = p.id_plan
LEFT JOIN entrenador e ON u.id_usuario = e.id_entrenador
WHERE p.nombre LIKE '%VIP'
AND e.id_entrenador IS NULL; -- Solo muestro los usuarios que no son entrenadores, si el id_entrenador es NULL es que no es entrenador

-- 2. Dime el nombre y correo de los usuarios (Activos) que tienen una membresía mensual. (He usado INNER JOIN + WHERE + LIKE + AND)

SELECT CONCAT(u.nombre, ' ', u.apellido) AS NombreCompleto, u.email FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio 
INNER JOIN membresia m ON s.id_socio = m.id_socio
INNER JOIN plan p ON m.id_plan = p.id_plan
WHERE p.nombre LIKE '%Mensual%' -- Aparecen tambien los VIP mensuales que tienen id_plan 4
AND m.estado = 'Vigente';

-- 3. Crea una columna con las distintas clases que hay y su cupo maximo, ordena por orden alfabetico (He usado SUM + GROUP BY + ORDER BY)

SELECT c.nombre_clase AS Clases, SUM(c.cupo_maximo) AS CupoMaximo FROM clase c
GROUP BY c.nombre_clase
ORDER BY c.nombre_clase ASC;

-- 4. Selecciona el usuario que más clases practica y muestra su nombre, correo y la cantidad de clases que practica. (He usado COUNT + GROUP BY + ORDER BY)
SELECT CONCAT(u.nombre, ' ', u.apellido) AS NombreCompleto, u.email, COUNT(a.id_clase) AS Clases FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio -- Saco los socios para luego sacar sus asistencias
INNER JOIN asistencia a ON s.id_socio = a.id_socio -- Saco las asistencias del socio y saber cuantas clases tiene
INNER JOIN clase c ON a.id_clase = c.id_clase -- Saco las clases a la que asiste el socio
GROUP BY u.id_usuario 
ORDER BY COUNT(a.id_clase) DESC
LIMIT 1; -- Solo muestro 1 usuario

-- 5. Muestra los usuarios VIP y los usuarios Anual, en una misma consulta. (Aqui muestro un ejemplo de UNION con dos consultas separadas)

SELECT CONCAT(u.nombre, ' ', u.apellido) AS NombreCompleto, u.email, p.nombre AS Tipo, m.estado 
FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio -- Saco los socios para luego sacar sus membresias
INNER JOIN membresia m ON s.id_socio = m.id_socio -- Saco las membresias de los socios (¡Cambiada la dirección!)
INNER JOIN plan p ON m.id_plan = p.id_plan -- Saco el catálogo para ver el tipo de membresia (¡Nuevo JOIN!)
WHERE p.nombre LIKE '%VIP%'
UNION -- Con el UNION uno ambas consultas y muestro usuarios VIP y Anuales
SELECT CONCAT(u.nombre, ' ', u.apellido) AS NombreCompleto, u.email, p.nombre AS Tipo, m.estado 
FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio
INNER JOIN membresia m ON s.id_socio = m.id_socio
INNER JOIN plan p ON m.id_plan = p.id_plan
WHERE p.nombre LIKE '%Anual%';

-- 6. Suma los pagos de los usuarios que tengan membresia mensual y muestra la suma total y la media de esos pagos (He usado SUM + AVG + INNER JOIN + WHERE + LIKE)

SELECT SUM(p.precio_base) AS SumaTotal, AVG(p.precio_base) AS MediaPagos FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio -- Saco los socios para luego sacar sus membresias
INNER JOIN membresia m ON s.id_socio = m.id_socio -- Saco las membresias de los socios para luego sacar el plan y su precio
INNER JOIN plan p ON m.id_plan = p.id_plan -- Saco el plan para luego sacar su precio y hacer la suma y media de los pagos
WHERE p.nombre LIKE '%Mensual%'; -- Aparecen tambien los VIP mensuales que tienen id_plan 4

-- 7. Muestra el nombre de los entrenadores que imparten clases de CrossFit y el número de clases que imparten (He usado COUNT + GROUP BY)

SELECT CONCAT(u.nombre, ' ', u.apellido) AS NombreCompleto, COUNT(c.id_clase) AS ClasesCrossFit FROM usuario u
INNER JOIN entrenador e ON u.id_usuario = e.id_entrenador --
INNER JOIN clase c ON e.id_entrenador = c.id_entrenador
WHERE c.nombre_clase LIKE '%CrossFit%' 
GROUP BY e.id_entrenador;

-- 8. Muestra el equipamiento de cada clase (He usado INNER JOIN + ORDER BY)

SELECT c.nombre_clase AS Clase, eq.nombre_equipo AS Equipamiento FROM clase c
INNER JOIN clase_equipamiento ce ON c.id_clase = ce.id_clase -- Saco el equipamiento de cada clase
INNER JOIN equipamiento eq ON ce.id_equipamiento = eq.id_equipamiento -- Saco el nombre del equpamiento
ORDER BY c.nombre_clase ASC; 


-- 9. Muestra el nombre de los usuarios que han asistido a clases de Yoga y el nombre del entrenador que imparte esa clase (He usado INNER JOIN + WHERE + LIKE)

SELECT CONCAT(u.nombre, ' ', u.apellido) AS Entrenador FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio -- Saco los socios para luego sacar sus asistencias
INNER JOIN asistencia a ON s.id_socio = a.id_socio -- Saco las asistencias del socio para luego sacar las clases a las que asiste
INNER JOIN clase c ON a.id_clase = c.id_clase -- Saco las clases a las que asiste el socio para luego sacar el entrenador de esa clase
INNER JOIN entrenador e ON c.id_entrenador = e.id_entrenador -- Saco el entrenador de la clase para mostrar su nombre
WHERE c.nombre_clase LIKE '%Yoga%'; 

-- 10. Muestra los usuarios que hayan pagado solo 1 membresía y el tipo de membresía que han pagado (He usado COUNT + GROUP BY + HAVING)

SELECT CONCAT(u.nombre, ' ', u.apellido) AS Usuario, pl.nombre AS TipoMembresia, COUNT(pa.id_pago) AS TotalPagos FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio -- Saco los socios para luego sacar sus membresias
INNER JOIN membresia m ON s.id_socio = m.id_socio -- Saco las membresias de los socios
INNER JOIN plan pl ON m.id_plan = pl.id_plan -- Saco el plan para mostrar el tipo
INNER JOIN pago pa ON m.id_membresia = pa.id_membresia -- Saco la cantidad de pagos 
GROUP BY u.id_usuario, pl.nombre -- Muestra los usuarios y el tipo de membresia que han pagado
HAVING COUNT(pa.id_pago) = 1; -- Solo muestra usuarios con un pago


-- Crear vistas

-- 1. Vista con los datos personales de todos los usuarios VIP (Se usa CREATE VIEW, y si quiero borrarla con un DROP VIEW)
CREATE VIEW DatosVIP AS SELECT CONCAT(u.nombre, ' ', u.apellido) AS NombreCompleto, u.email, m.tipo, m.estado FROM usuario u
INNER JOIN socio s ON u.id_usuario = s.id_socio
INNER JOIN membresia m ON s.id_membresia = m.id_membresia
WHERE m.tipo LIKE '%VIP' -- De momento no hay usuarios con VIP que no sea Anual pero si en un futuro los hay uso el LIKE igualmente, no concreto con un '='
AND m.estado = 'Vigente';

-- Eliminación de datos fitlrando valores o con subconsulta

-- 1. Elimina un socio que tenga una membresia caducada (Se usa DELETE FROM)
DELETE FROM socio WHERE id_socio = (SELECT id_socio FROM membresia WHERE estado = 'Caducada' LIMIT 1);
-- Como elijo borrar solo a uno le meto un LIMIT 1, de forma aleatoria. 

-- 1. Cambia el entrenador de la clase de yoga con el entrenador de la clase de Boxeo (Como no sé el nombre completo de la clase uso LIKE y tampoco sé que entrenador es cual hago una subconsulta)
-- Puede que no cambie ningun dato en la base de datos actual porque he tenido que retocar campos e igual no existe entrenadores para intercambiar con la clase de yoga o boxeo

--  Con el UPDATE eligo la tabla y SET el campo que quiero actualizar, meto la subconsulta y hago lo mismo por el entrenador a intercambiar con otra subconsulta
UPDATE clase SET id_entrenador = (SELECT id_entrenador FROM clase WHERE nombre_clase LIKE '%Boxeo')
WHERE id_entrenador = (SELECT id_entrenador FROM clase WHERE nombre_clase LIKE '%Yoga');


-- Inserción de datons filtrando valores o con una subconsulta

-- 1. Inserta una nueva clase a cualquier entrenador

INSERT INTO clase (id_entrenador, nombre_clase, fecha, hora, dia_semana, cupo_maximo) VALUES
((SELECT id_entrenador FROM entrenador WHERE especialidad LIKE '%Cardio%'), -- Con la subconsulta elijo un entrenador que sea de cardio
 'Cardio para principiantes', '2026-03-01', '10:00:00', 'Lunes', 20); -- Le asigno una nueva clase con su nombre, fecha, hora, dia de la semana y cupo maximo