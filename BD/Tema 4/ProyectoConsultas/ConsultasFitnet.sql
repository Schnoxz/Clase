-- Selecciona los usuarios que tengan una suscripción VIP (Activa o caducada) y que no sean entrenadores. Usando INNER JOIN + LEFT JOIN + WHERE 
SELECT CONCAT(u.nombre, ' ', u.apellido) AS UsuariosVIP FROM usuario u 
INNER JOIN socio s ON u.id_usuario = s.id_socio
INNER JOIN membresia m ON s.id_membresia = m.id_membresia
LEFT JOIN entrenador e ON u.id_usuario = e.id_entrenador
WHERE m.tipo = 'Anual VIP' 
AND e.id_entrenador IS NULL;


-- Dime el nombre y correo de los usuarios (Activos) que tienen una membresía anual que siga activa actualmente. INNER JOIN + GROUP BY + HAVING + COUNT 

SELECT u.nombre, u.email, m.tipo, m.estado FROM usuario u  
INNER JOIN socio s ON u.id_usuario = s.id_socio
INNER JOIN membresia m ON s.id_membresia = m.id_membresia
WHERE m.tipo LIKE 'Anual%' AND m.estado = 'Vigente';

-- ¿Cuántos tipos de clases hay y cuál es el cupo máximo total de todas ellas (por grupo)? Dime también el equipamiento necesario para cada clase. Usando COUNT + SUM + GROUP BY

SELECT c.nombre_clase, COUNT(*) AS TotalClases, SUM(c.cupo_maximo) AS CupoTotal, GROUP_CONCAT(DISTINCT e.especialidad) AS EquipamientoNecesario
FROM clase c
INNER JOIN entrenador e ON c.id_entrenador = e.id_entrenador
GROUP BY c.nombre_clase;

-- Selecciona el usuario que más clases practica y muestra su nombre, correo y las clases que practica. Usa una subselect para el distincto de clases y CONCAT para el nombre completo.





