DELIMITER ??
CREATE OR REPLACE PROCEDURE EjemploCursorEmpleado()
BEGIN
    DECLARE fin INT DEFAULT 0;
    DECLARE cod INT;
    DECLARE nom VARCHAR(50);
    DECLARE cur CURSOR FOR SELECT codEmp, NomEmp FROM empleado;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;
    OPEN cur;
    while fin = 0 DO
        FETCH cur INTO cod, nom;
        IF fin = 0 THEN
        INSERT INTO empleados VALUES (cod, nom);
        END IF;
    END WHILE;
    CLOSE cur;
END ??
DELIMITER ;

-- Rellenar la tercera tabla con el menor identificador de las dos primeras tablas y los datos de la primera

CREATE TABLE t1 (
  id INT UNSIGNED PRIMARY KEY,
  datos VARCHAR(16)
);

CREATE TABLE t2 (
  id INT UNSIGNED
);

CREATE TABLE t3 (
  datos VARCHAR(16),
  id INT UNSIGNED
);

INSERT INTO t1 VALUES (1, 'A');
INSERT INTO t1 VALUES (2, 'B');
INSERT INTO t1 VALUES (30, 'C');

INSERT INTO t2 VALUES (10);
INSERT INTO t2 VALUES (20);
INSERT INTO t2 VALUES (3);

-- Solucion:

DELIMITER ??
CREATE OR REPLACE PROCEDURE RellenarT3()
BEGIN
    DECLARE idMin INT;
    DECLARE dato VARCHAR(16);

    -- Obtiene el mínimo de las dos tablas con LEAST que nos devuelve el valor mas pequeño y en la subconsulta MIN y el dato de la tabla que queremos coger
    SELECT LEAST((SELECT MIN(id) FROM t1), (SELECT MIN(id) FROM t2)) INTO idMin;

    -- Realizo otra consulta que recoge los datos de la tabla 1 donde la id sea la minima y los guarda en la variable dato previamente definida
    SELECT datos INTO dato FROM t1
    WHERE id = idMin
    LIMIT 1;

    -- Paso los datos de las variables a los campos de la tabla 3 datos e id
    INSERT INTO t3 (datos, id) VALUES (dato, idMin);
END ??
DELIMITER ;

-- Cursor: 
DELIMITER ??
CREATE OR REPLACE PROCEDURE RellenarT3()
BEGIN
    DECLARE fin INT DEFAULT 0;
    DECLARE idT1 INT;
    DECLARE idT2 INT;
    DECLARE dato VARCHAR(16);
    DECLARE idMin INT;

    DECLARE cur1 CURSOR FOR SELECT MIN(id) FROM t1;
    DECLARE cur2 CURSOR FOR SELECT MIN(id) FROM t2;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;

    -- Obtener el menor id de t1
    OPEN cur1;
    FETCH cur1 INTO idT1;
    CLOSE cur1;

    -- Obtener el menor id de t2
    OPEN cur2;
    FETCH cur2 INTO idT2;
    CLOSE cur2;

    -- Determinar el menor entre ambos
    IF idT1 <= idT2 THEN
        SET idMin = idT1;
    ELSE
        SET idMin = idT2;
    END IF;

    -- Buscar los datos en t1 para ese id
    BEGIN
        DECLARE curDato CURSOR FOR SELECT datos FROM t1 WHERE id = idMin LIMIT 1;
        DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;

        SET dato = NULL;
        OPEN curDato;
        FETCH curDato INTO dato;
        CLOSE curDato;
    END;

    -- Insertar en t3
    INSERT INTO t3 (datos, id) VALUES (dato, idMin);

END ??
DELIMITER ;

CALL RellenarT3();


-- Solucion profesor:
DELIMITER ??
CREATE OR REPLACE PROCEDURE RellenarT3()
BEGIN
    -- variables
    DECLARE fin INT DEFAULT 0;
    DECLARE a VARCHAR(16);
    DECLARE b INT;        -- ← separadas
    DECLARE c INT;        -- ← separadas
    DECLARE cur1 CURSOR FOR SELECT id, datos FROM t1;
    DECLARE cur2 CURSOR FOR SELECT id FROM t2;   -- ← t2 solo tiene id

    -- handler
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;

    -- abrir
    OPEN cur1;
    OPEN cur2;

    -- bucle
    WHILE fin = 0 DO
        FETCH cur1 INTO b, a;
        FETCH cur2 INTO c;       -- ← solo una variable
        IF fin = 0 THEN
            IF b = c THEN
                INSERT INTO t3 VALUES (a, b);
            ELSE
                INSERT INTO t3 VALUES (a, c);
            END IF;
        END IF;
    END WHILE;

    -- cerrar
    CLOSE cur1;
    CLOSE cur2;

END ??
DELIMITER ;    -- ← typo corregido