-- Profesores que no imparten clase
SELECT PROFESOR
FROM D
WHERE PROFESOR NOT IN (SELECT PROFESOR FROM I);

-- Profesores que imparten clase en dos modulos
SELECT PROFESOR FROM i
GROUP BY PROFESOR
HAVING COUNT(MODULO) = 2;

-- Departamentos que imparten modulos en mas de un ciclo
SELECT DEPARTAMENTO FROM d  
WHERE PROFESOR IN (SELECT PROFESOR FROM i WHERE MODULO IN
(SELECT MODULO FROM E 
 GROUP BY MODULO
 HAVING COUNT(DISTINCT CICLO) > 1)
);
-- No me sale ningun departamento que cumpla el enunciado

-- Alumnos que aprueban todos los modulos a los que se presentan
SELECT ALUMNO FROM m 
GROUP BY ALUMNO
HAVING MIN(NOTA) >= 5.00;

-- Busca al profesor que imparte el mayor numero de modulos. Muestra su nombre y la cantidad de modulos que tiene a su cargo
SELECT PROFESOR, COUNT(MODULO) AS Modulos FROM i
GROUP BY PROFESOR
ORDER BY COUNT(MODULO) DESC
LIMIT 1;

-- Lista el nombre de tosos los modulos que pertenecen al ciclo 'DAW', ordenados alfabeticamente
SELECT MODULO FROM e
WHERE ciclo = 'DAW'
ORDER BY MODULO ASC;

-- Media de las notas medias de los alumnos que aprobaron por lo menos la mitad de los modulos en los que estaban matriculados
SELECT AVG(NOTA) AS Media FROM
(SELECT ALUMNO, AVG(NOTA) AS NotaMedia 
FROM SUM(ALUMNO HAVING MIN(NOTA) >= 5.00 IN MIN(MODULO) >= 3.00 -- No consigo los aprobados de al menos la mitad de los modulos y sin eso, no puedo llegar a lo demás
FROM m)
GROUP BY ALUMNO;


select avg(nota) as notaMedia from m1 
GROUP BY alumno
HAVING (SUM(nota >= 5.00) >= (COUNT(*) / 2));
) as subconsulta;