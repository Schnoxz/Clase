-- 1º--
ALTER TABLE empleado (
ADD COLUMN fecha_alta DATE NOT NULL
);

--2º--
ALTER TABLE supermercado (
MODIFY domicilio VARCHAR (100);
);

--3º--
ALTER TABLE pedido (
CONSTRAINT CHK_TotalPositivo CHECK (total >= 0)
);

--4º--
ALTER TABLE supermercado (
REMOVE COLUMN superficie;
);

--5º--
ALTER TABLE empleado ( 
UPDATE fecha_alta SET CURRENT DATE
);

--6º--
ALTER TABLE empleado (
DELETE FROM empleado WHERE nombre = 'Laura Gómez'
);