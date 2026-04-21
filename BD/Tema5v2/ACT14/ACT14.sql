/* La empresa se está planteando ampliar la cantidad de departamentos para fomentar la conciliación familiar. De esta manera, aquellos departamentos que tengan más de 2 trabajadores con hijos se dividirán en dos, y sus empleados se repartirán entre ellos. Para ello, en una nueva tabla departamentoFamiliar almacenaremos una copia de los departamentos, junto a las nuevas versiones familiares.

Tened en consideración los siguientes puntos:

Actualizar la fila recién insertada en departamentoFamiliar reduciendo su presupuesto anual

Creación de Sub-departamento: Insertar una segunda fila en departamentoFamiliar que represente una "sección familiar" con las siguientes modificaciones:

Código: El nuevo código será el original pero sustituyendo el quinto carácter por un "2" (ej. 'ADMZS' pasa a ser 'ADMZ2').

Nombre: Se le añadirá el sufijo "** Familiar**" al nombre original (ej. 'Ventas Zona Sur' pasa a ser 'Ventas Zona Sur Familiar').

Dependencia: Este nuevo departamento tendrá como departamento superior (CodDepDep) al departamento original.

Presupuesto: Recibirá la otra mitad del presupuesto original.
*/


-- Creación de la tabla departamentoFamiliar
CREATE TABLE departamentoFamiliar LIKE departamento;

-- Procedimiento para insertar los departamentos familiares
DELIMITER ??
CREATE OR REPLACE PROCEDURE crearDepartamentosFamiliares()
BEGIN
    -- Declaro la variables que voy a usarS
    DECLARE fin INT DEFAULT 0;
    DECLARE dept ROW TYPE OF departamento;
    DECLARE Mitad DECIMAL (12,2);
    DECLARE NuevoCodDep VARCHAR(5);

    -- Declaro el cursor
    -- Selecciono los departamentos que tengan mas de un empleado con hijos y los filtro por numero de hijos mayor que 0, y solo se seleccionan los departamentos con 2 trabajadores o más con hijos
    DECLARE cur CURSOR FOR
        SELECT d.* FROM departamento d
        INNER JOIN empleado e ON d.CodDep = e.CodDep
        WHERE e.NumHi > 0
        GROUP BY d.CodDep
        HAVING COUNT(e.CodEmp) > 1;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;

    -- Abro el cursor
    OPEN cur;
    -- Comienzo el bucle para insertar los departamentos familiares
    bucle: LOOP
        -- Traigo el siguiente departamento del cursor
        FETCH cur INTO dept;
        -- Condicion para que si no hay más filas, se salga del bucle
        IF fin = 1 THEN
            LEAVE bucle;
        END IF;

        -- Mitad almacena la mitad del presupuesto del departamento original
        SET Mitad = dept.PreAnu / 2;
        -- NuevoCodDep almacena el nuevo código del departamento familiar, sustituyendo el quinto carácter por un "2"
        SET NuevoCodDep = CONCAT(LEFT(dept.CodDep, 4), '2');
        -- Inserta la fila del departamento original en departamentoFamiliar, con la mitad de presupuesto
        INSERT INTO departamentoFamiliar
        VALUES (dept.CodDep, dept.CodEmpDir, dept.CodDepDep, dept.CodCen, dept.NomDep, Mitad, dept.TiDir);

        -- Inserta el sub-departamento con el nuevo codigo y nombre y la mitad de presupuesto
        INSERT INTO departamentoFamiliar
        VALUES (NuevoCodDep, dept.CodEmpDir, dept.CodDep, dept.CodCen, CONCAT(dept.NomDep, ' Familiar'), Mitad, dept.TiDir);

    END LOOP;
    -- Cierro el cursor
    CLOSE cur;
END ??
DELIMITER ;
