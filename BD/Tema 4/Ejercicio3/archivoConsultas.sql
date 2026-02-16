USE ayuntamiento;

#01. El nombre y el email de todos los usuarios del departamento de "Recursos humanos".
SELECT nombre, email FROM usuario WHERE departamento = 'Recursos Humanos';

#02. Lista de todos los tickets que no tienen asignado un técnico y su prioridad es "Alta".
SELECT * FROM ticket WHERE email_tecnico IS NULL AND prioridad = 'Alta';

#03. Lista de tickets que incluyen en su descripción la palabra "servi".
SELECT * FROM ticket WHERE descripcion LIKE '%servi%';

#04. Número total de tickets resueltos.
SELECT COUNT(*) FROM ticket WHERE estado = 'Resuelto';

#05. Numero de usuarios que tiene cada departamento.
SELECT departamento, COUNT(*) FROM usuario GROUP BY departamento;

#06. El nombre y el email de todos los técnicos y administradores cuyo apellido acabe en "ez", ordenados de manera ascendente.
SELECT nombre, email FROM tecnico WHERE nombre LIKE '%ez' ORDER BY nombre ASC;
# Hago dos consultas separadas
SELECT nombre, email FROM administrador WHERE nombre LIKE '%ez' ORDER BY nombre ASC;

#07. Lista de tickets "Pendiente" ordenados por fecha de creación descendente.
SELECT * FROM ticket WHERE estado = 'Pendiente' ORDER BY fecha_creacion DESC;

#08. Número de tickets registrados por cada departamento.
SELECT u.departamento, COUNT(t.idTicket) FROM ticket t 
JOIN usuario u ON t.email_usuario = u.email 
GROUP BY u.departamento;

#09. Lista de tickets resueltos en los últimos 5 días.
SELECT * FROM ticket WHERE estado = 'Resuelto' AND fecha_creacion >= DATE_SUB(NOW(), INTERVAL 5 DAY);

#10. Mostrar la cantidad de tickets "En progreso", "Resuelto" y "Pendiente".
SELECT estado, COUNT(*) FROM ticket GROUP BY estado;

#11. Lista de tickets "Pendiente" con el nombre del usuario que lo ha creado.
SELECT t.idTicket, t.descripcion, u.nombre FROM ticket t 
JOIN usuario u ON t.email_usuario = u.email WHERE t.estado = 'Pendiente';

#12. Lista de tickets "En progueso" con el nombre del usuario que lo ha creado y el nombre del técnico que lo tiene asignado.
SELECT t.idTicket, t.descripcion, u.nombre AS usuario, tec.nombre AS tecnico FROM ticket t 
JOIN usuario u ON t.email_usuario = u.email 
JOIN tecnico tec ON t.email_tecnico = tec.email 
WHERE t.estado = 'En progreso';

#13. Lista de tickets "Resuelto" de las categorías que empiezan por "S" o por "A".
SELECT * FROM ticket WHERE estado = 'Resuelto' 
AND (categoria LIKE 'S%' OR categoria LIKE 'A%');

#14. Nombre de los 3 técnicos que más tickets han resuelto.
SELECT tec.nombre, COUNT(t.idTicket) AS total FROM ticket t 
JOIN tecnico tec ON t.email_tecnico = tec.email WHERE t.estado = 'Resuelto' 
GROUP BY tec.nombre ORDER BY total DESC 
LIMIT 3;

#15. Nombre e email (para despedir) de los técnicos que no han resuelto ningún ticket.
SELECT tec.nombre, tec.email FROM tecnico tec 
LEFT JOIN ticket t ON tec.email = t.email_tecnico AND t.estado = 'Resuelto' 
WHERE t.idTicket IS NULL;

#16. Día del mes de febrero que más tickets se crearon.
SELECT DAY(fecha_creacion) AS dia, COUNT(*) AS total FROM ticket 
WHERE MONTH(fecha_creacion) = 2 
GROUP BY dia ORDER BY total DESC 
LIMIT 1;

#17. Nombre e email y número de tickets creados de los 5 usuarios que más tickets han creado, ordenados por número de tickets creados de manera descendente.
SELECT u.nombre, u.email, COUNT(t.idTicket) AS total FROM usuario u 
JOIN ticket t ON u.email = t.email_usuario 
GROUP BY u.email, u.nombre ORDER BY total DESC 
LIMIT 5;

#18. Mostrar la descripción, fecha y estado de todos los tickets del técnico que haya resuelto menos tickets (al menos uno).
SELECT descripcion, fecha_creacion, estado FROM ticket 
WHERE email_tecnico = ( SELECT email_tecnico FROM ticket WHERE estado = 'Resuelto' 
GROUP BY email_tecnico 
ORDER BY COUNT(*) ASC 
LIMIT 1);

#19. Muestra el ultimo ticket registrado.
SELECT * FROM ticket ORDER BY idTicket DESC LIMIT 1;

#20. Muestra todos los estados por los que ha pasado el ticket con idTicket 5, incluyendo en cada uno de ellos su descripción, nombre del técnico asignado, nombre del usuario que lo creó y la fecha.
SELECT h.estado, h.fecha, t.descripcion, tec.nombre AS tecnico, u.nombre AS usuario FROM historial_ticket h 
JOIN ticket t ON h.idTicket = t.idTicket 
JOIN usuario u ON t.email_usuario = u.email LEFT JOIN tecnico tec ON t.email_tecnico = tec.email 
WHERE h.idTicket = 5;