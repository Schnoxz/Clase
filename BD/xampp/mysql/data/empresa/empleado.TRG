TYPE=TRIGGERS
triggers='CREATE DEFINER=`root`@`localhost` TRIGGER triggerSalariosEmpleadoAfterInsert\n-- AFTER INSERT porque voy a insertar un registro en la tabla salarios despues de que se inserte una fila en la tabla empleado, for each row porque quiero que se ejecute para cada fila\nAFTER INSERT ON empleado FOR EACH ROW\nBEGIN\n    -- Inserto el registro en la tabla salarios\n    INSERT INTO salarios (salario, codigo_empleado)\n    -- Introduzco el salario y el codigo del empleado\n    VALUES (NEW.SalEmp, NEW.CodEmp);\nEND' 'CREATE DEFINER=`root`@`localhost` TRIGGER triggerSalariosEmpleadoAfterUpdate\n-- AFTER UPDATE porque voy a insertar un registro en la tabla salarios despues de que se actualice una fila en la tabla empleado, for each row porque quiero que se ejecute para cada fila\nAFTER UPDATE ON empleado FOR EACH ROW\nBEGIN\n    -- Solo actúa si el salario ha cambiado previamente, comparo el nuevo salario con el antiguo, si son diferentes entonces inserto el registro en la tabla salarios\n    IF NEW.salEmp <> OLD.SalEmp THEN\n        -- Inserto el registro en la tabla salarios\n        INSERT INTO salarios (salario, codigo_empleado)\n        -- Introduzco el nuevo salario y el codigo del empleado\n        VALUES (NEW.SalEmp, NEW.CodEmp);\n    END IF;\nEND'
sql_modes=1098907648 1098907648
definers='root@localhost' 'root@localhost'
client_cs_names='utf8mb4' 'utf8mb4'
connection_cl_names='utf8mb4_unicode_ci' 'utf8mb4_unicode_ci'
db_cl_names='utf8_general_ci' 'utf8_general_ci'
created=1776961673793032 1776962476920049
