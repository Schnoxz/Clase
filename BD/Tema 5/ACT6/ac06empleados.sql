/* Crea una tabla dashboard_dpto que muestre para cada departamento, además de su código y nombre y presupuesto anual, cuantos empleados y su gasto en salarios.

Crea una tabla dashboard_centro que muestre para cada centro, además de su código y nombre, cuantos departamentos contiene y el presupuesto anual (entendido como la suma de los presupuestos de sus departamentos).

Lanza el script ac06empleados.sql para que ejecute ambas operaciones (cada vez que lo hagas, debe borrar las tablas y volver a crearlas)*/

-- Borrado de tablas si ya existen 
DROP TABLE IF EXISTS dashboard_dpto;
DROP TABLE IF EXISTS dashboard_centro;

-- Creo la tabla 'dashboard_dpto'
CREATE TABLE dashboard_dpto
-- Selecciono codigo, nombre y presupuesto anual de cada departamento, además del número de empleados de cada uno, también recogo de la tabla empleados, la cantidad total de salario en cada departamento
SELECT d.CodDepDep, d.NomDep, d.PreAnu, COUNT(e.NomEmp) AS numeroEmpelados, SUM(e.SalEmp) AS gastoSalarios FROM departamento d
-- LEFT JOIN para unir con la tabla empleado, sacamos los valores que sean iguales al departamento que trabajan
LEFT JOIN empleado e ON d.CodDep = e.CodDep
GROUP BY d.CodDep, d.NomDep, d.PreAnu;

-- Dashboard por centro
CREATE TABLE dashboard_centro
-- Selecciono el codigo y nombre de cada Centro, el numero de departamentos que tienen y el presupuesto anual total de ellos
SELECT c.CodCen, c.NomCen, COUNT(d.CodDep) AS Departamentos, SUM(d.PreAnu) AS PresupuestoAnual FROM centro c
-- Uno a la tabla departamento y con el CodCen sé qué departamentos se incluyen en cada centro
LEFT JOIN departamento d ON c.CodCen = d.CodCen
GROUP BY c.CodCen, c.NomCen;

-- Verifico los resultados consultando las tablas que se han creado
SELECT * FROM dashboard_dpto;
SELECT * FROM dashboard_centro;










