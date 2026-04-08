/* En la base de datos empresa, crea:

El procedimiento ac09semanaIf que reciba como entrada un entero que represente un día de la semana y que devuelva una cadena con el nombre del día de la semana correspondiente (utilizando IF). Por ejemplo, para la entrada 1 debería devolver Lunes.

El procedimiento ac09semanaCase que reciba como entrada un entero que represente un día de la semana y que devuelva una cadena con el nombre del día de la semana correspondiente (utilizando CASE)

El procedimiento ac09semanaCasIng que reciba como entrada un entero que represente un día de la semana y una cadena con el idioma (los posibles valores son CAS o ING) y que devuelva una cadena con el nombre del día de la semana correspondiente en el idioma indicado (puedes utilizar las sentencias condicionales que consideres). Por ejemplo, para 1 y CAS, devolverá Lunes, pero si es ING devolverá Monday. */


-- Procedimiento ac09semanaIf usando IF

-- Creamos el procedimiento con parámetro dia de entrada y parámetro de salida nombreDelDia, ambos con tipo de dato declarado
DELIMITER ??
CREATE OR REPLACE PROCEDURE ac09semanaIf(IN dia INT, OUT nombreDelDia VARCHAR(40))

BEGIN
    -- Creo el bloque IF donde comprueba el parámetro de entrada y da un valor de cadena de texto a la variable nombreDelDia
    IF dia = 1 THEN SET nombreDelDia = 'Lunes';
    ELSEIF dia = 2 THEN SET nombreDelDia = 'Martes';
    ELSEIF dia = 3 THEN SET nombreDelDia = 'Miércoles';
    ELSEIF dia = 4 THEN SET nombreDelDia = 'Jueves';
    ELSEIF dia = 5 THEN SET nombreDelDia = 'Viernes';
    ELSEIF dia = 6 THEN SET nombreDelDia = 'Sábado';
    ELSEIF dia = 7 THEN SET nombreDelDia = 'Domingo';
    ELSE SET nombreDelDia = 'Error: Número de la semana inválido';
    END IF;
END ??
DELIMITER ;

-- Procedimiento ac09semanaCase con parámetro dia de entrada y parámero de salida nombreDelDia al igual que ac09semanaIf
DELIMITER ??
CREATE OR REPLACE PROCEDURE ac09semanaCase (IN dia INT, OUT nombreDelDia VARCHAR(40))
BEGIN
    -- Creo el bloque case donde comprueba el parámetro de entrada y da un valor de cadena de texto a la variable nombreDelDia, mismo funcionamiento que el IF
    CASE dia 
        WHEN 1 THEN SET nombreDelDia = 'Lunes';
        WHEN 2 THEN SET nombreDelDia = 'Martes';
        WHEN 3 THEN SET nombreDelDia = 'Miércoles';
        WHEN 4 THEN SET nombreDelDia = 'Jueves';
        WHEN 5 THEN SET nombreDelDia = 'Viernes';
        WHEN 6 THEN SET nombreDelDia = 'Sábado';
        WHEN 7 THEN SET nombreDelDia = 'Domingo';
        ELSE SET nombreDelDia = 'Error: Número de la semana inválido';
        END CASE;
END ??
DELIMITER ;

-- Procedimiento ac09semanaCasIng con parámetro dia de entrada y una cadena con el idioma, (CAS o ING) y parámetro de salida nombreDelDia según el idioma indicado (hacerlo en español e ingles)

DELIMITER ??
CREATE OR REPLACE PROCEDURE ac09semanaCasIng (IN dia INT, IN idioma VARCHAR(3), OUT nombreDelDia VARCHAR(40))

BEGIN 
    -- Creo el bloque con un case igual que el procedimiento ac09semanaCase
    CASE idioma
        WHEN 'CAS' THEN -- Validación para cuando el parámetro de idioma en la entrada sea CAS
            CASE dia -- El case que ya se ha construido en el procedimiento ac09semanaCase
                WHEN 1 THEN SET nombreDelDia = 'Lunes';
                WHEN 2 THEN SET nombreDelDia = 'Martes';
                WHEN 3 THEN SET nombreDelDia = 'Miércoles';
                WHEN 4 THEN SET nombreDelDia = 'Jueves';
                WHEN 5 THEN SET nombreDelDia = 'Viernes';
                WHEN 6 THEN SET nombreDelDia = 'Sábado';
                WHEN 7 THEN SET nombreDelDia = 'Domingo';
                ELSE SET nombreDelDia = 'Error: Número de la semana inválido';
             END CASE;
        -- Ahora se hace exactamente lo mismo pero con el idioma ING y los nombres de los días en inglés
        WHEN 'ING' THEN
            CASE dia
                WHEN 1 THEN SET nombreDelDia = 'Monday';
                WHEN 2 THEN SET nombreDelDia = 'Tuesday';
                WHEN 3 THEN SET nombreDelDia = 'Wednesday';
                WHEN 4 THEN SET nombreDelDia = 'Thursday';
                WHEN 5 THEN SET nombreDelDia = 'Friday';
                WHEN 6 THEN SET nombreDelDia = 'Saturday';
                WHEN 7 THEN SET nombreDelDia = 'Sunday';
                ELSE SET nombreDelDia = 'Error: Número de la semana inválido';
             END CASE;
        ELSE SET nombreDelDia = 'Error: Idioma inválido'; -- Validación adicional para el CASE del idioma al igual que tenemos dentro de los CASE dia
    END CASE;
END ??
DELIMITER ;

-- Debes pensar y argumentar qué sucede si cualquiera de los parámetros recibidos como entrada no contienen alguno de los valores esperados.

Sobre pensar, ya he implementado validaciones dentro de los CASE y el IF previamente a leer esta sentencia, por lo que si alguno de los parámetros no contienen alguno de los valores esperados, se mostrara el mensaje de error correspondiente, he comprobado que funcionen introduciendo varios calls, algunos con parámetros de entrada incorrectos y otros con parámetros de entrada correctos.

-- Calls de los procedimientos para probar
CALL ac09semanaIf(1, @dia);
SELECT @dia;

Resultado: @dia = 'Lunes'

CALL ac09semanaIf(11, @dia);
SELECT @dia;

Resultado: @dia = 'Error: Número de la semana inválido'

CALL ac09semanaCase(0, @dia);
SELECT @dia;

Resultado: @dia = 'Error: Número de la semana inválido' -- Muestra correctamente el mensaje

CALL ac09semanaCasIng(1, 'CAS', @dia);
SELECT @dia;

Resultado: @dia = 'Lunes' -- Muestra correctamente el idioma y el dia

CALL ac09semanaCasIng(1, 'MAL', @dia);
SELECT @dia;
        
Resultado: @dia = 'Error: Idioma inválido' -- Muestra correctamente el mensaje de error del idioma incorrecto


/* A continuación, sobre la tabla habilidad, crea:

El procedimiento ac09insertaHabilidad 
El procedimiento ac09upsertHabilidad 
El procedimiento ac09upsertHabilidadPlus */


-- Procedimiento ac09insertaHabilidad con IF que recibe como parámetro de entrada un código de habilidad y su descripción, y que solo la inserte si el código de la habilidad tiene un tamaño de 5 caracteres

DELIMITER ??
CREATE OR REPLACE PROCEDURE ac09insertaHabilidad (IN codigoHabilidad VARCHAR(5), IN descripcionHabilidad VARCHAR(40))
BEGIN
    -- Tenemos una condición que hay que cumplir siempre para insertar la habilidad, que el codigo no tenga más de 5 caracteres, uso IF para ello
    IF LENGTH(codigoHabilidad) = 5 THEN
        -- Si se pasa la condición, se procede a insertar los datos, en este caso van a la tabla habiliadd a los campos CodHab y DesHab con los valores del parámetro de entrada del procedimiento
        INSERT INTO habilidad (CodHab, DesHab) VALUES (codigoHabilidad, descripcionHabilidad);
    END IF;
END ??
DELIMITER ;

CALL ac09insertaHabilidad('31284', 'Cazador pokemon');


-- Procedimiento ac09upsertHabilidad con IF que recibe como entrada un código de habilidad y su descripción, y que sólo la inserte si el código de la habilidad tiene un tamaño de 5 caracteres. Si el código ya existe, debe modificar la habilidad con la nueva descripción, y si no, la insertará.

    -- La condición de este procedimiento es la misma que la anterior pero si ya existe se modifica con una nueva descripción, si no hay se inserta. (Importante lo de si no, "la insertará")

DELIMITER ??
CREATE OR REPLACE PROCEDURE ac09upsertHabilidad (IN codigoHabilidad VARCHAR(5), IN descripcionHabilidad VARCHAR(40))
BEGIN 
    IF LENGTH(codigoHabilidad) = 5 THEN
    -- Nos valida con un if la existencia de la habilidad, si es asi, selecciona todos los campos de la tabla que tenga similitud con el codigo de entrada
        IF EXISTS (SELECT * FROM habilidad WHERE CodHab = codigoHabilidad) THEN
        -- Modifica la descripción de la habilidad con la nueva descripción
            UPDATE habilidad SET DesHab = descripcionHabilidad WHERE CodHab = codigoHabilidad;
            -- Si no, se inserta como hace el procedimiento anterior ac09insertaHabilidad
        ELSE
            INSERT INTO habilidad (CodHab, DesHab) VALUES (codigoHabilidad, descripcionHabilidad);
        END IF;
    END IF;
END ??
DELIMITER ;

CALL ac09upsertHabilidad('31284', 'Cazador Digimon');

-- El procedimiento ac09upsertHabilidadPlus que además de todo lo anterior, informe al usuario de la operación realizada. En el caso de que los datos de entrada sean incorrectos o incompletos, deberá también informar de ello.

-- La unica forma de poder informar es tener un parámetro de salida, donde pasar una cadena de texto a la variable que se va a mostrar, así que le meto un SET
DELIMITER ??
CREATE OR REPLACE PROCEDURE ac09upsertHabilidadPlus (IN codigoHabilidad VARCHAR(5), IN descripcionHabilidad VARCHAR(40), OUT error VARCHAR(40))
BEGIN
    -- Mismo código que el procedimiento ac09upsertHabilidad que primero comprueba si existe alguno, y si no lo inserta
    IF LENGTH(codigoHabilidad) = 5 THEN
        IF EXISTS (SELECT * FROM habilidad WHERE CodHab = codigoHabilidad) THEN
            UPDATE habilidad SET DesHab = descripcionHabilidad WHERE CodHab = codigoHabilidad; 
            Set error = 'Se ha modificado correctamente';
        ELSE
            Set error = 'Error: Campo codigo incorrecto';
        END IF;
    END IF;
    IF LENGTH(codigoHabilidad) = 0 OR LENGTH(codigoHabilidad) > 5 THEN
        Set error = 'Error: Campo codigo incorrecto';
        ELSE 
            INSERT INTO habilidad (CodHab, DesHab) VALUES (codigoHabilidad, descripcionHabilidad);
            SET error = 'Se ha insertado correctamente';
    END IF;
END ??
DELIMITER ;

CALL ac09upsertHabilidadPlus('31284', 'Cazador Digimon' @error);
SELECT @error;
