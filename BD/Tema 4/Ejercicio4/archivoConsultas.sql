# 01. El nombre y el genero de todos los videojuegos "Multiplataforma"
SELECT nombre, genero FROM videojuegos 
WHERE plataforma = "Multiplataforma";

# 02. Cantidad de videojuegos de "Acción"
SELECT COUNT(*) AS cantidadJuegosAccion FROM videojuegos 
WHERE genero = "Acción";

# 03. Nombre de las distintas plataformas que existen para los videojuegos(sin repetir).
SELECT plataforma FROM videojuegos GROUP BY plataforma;

# 04. Nombre y fecha_lanzamiento de los videojuegos que salieron a la venta entre el 5 de mayo de 2017 y el 5 de mayo de 2019
SELECT nombre, fecha_lanzamiento FROM videojuegos
WHERE fecha_lanzamiento BETWEEN "2017-05-05" AND "2019-05-05";

# 05. Nombre y email de los jugadores cuyo apellido acabe con la letra "a"
SELECT nombre, email FROM jugadores WHERE nombre LIKE "%a";

# 06. Duración (en minutos) de la partida más larga y nombre del videojuego en el que se jugó la partida
SELECT MAX(duracion_minutos) AS "Minutos Jugados", nombre FROM partidas p
JOIN videojuegos v ON p.videojuego_id = v.id;

# 07. Duración promedio de las partidas jugadas
SELECT AVG(duracion_minutos) as "Duración promedio por partida" from partidas;

# 08. Nombre del jugador, videojuego jugado y puntuación del jugador con menor puntuación registrado
SELECT j.nombre, v.nombre, jp.puntuacion FROM jugadores j
JOIN jugadores_partidas jp ON j.id = jp.jugador_id
JOIN videojuegos v ON jp.partida_id = v.id
# Uso la subconsulta para obtener el jugador con menor puntuación de otra forma
WHERE jp.puntuacion = (SELECT MIN(puntuacion) FROM jugadores_partidas);

# 09. Puntuación total obtenida en todos los videojuegos de "Playstation 4"
SELECT SUM(puntuacion) AS "Puntuacion total" FROM jugadores_partidas jp
JOIN videojuegos v ON v.id = jp.jugador_id
WHERE v.plataforma = "Playstation 4";

# 10. Nombre de plataforma y número de partidas totales jugadas en cada una de ellas
SELECT plataforma, COUNT(*) FROM videojuegos GROUP BY plataforma
# Para que quede bonito y ordenado podemos incluir un order by
ORDER BY COUNT(*) DESC;

