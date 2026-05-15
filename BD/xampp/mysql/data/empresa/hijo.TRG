TYPE=TRIGGERS
triggers='CREATE DEFINER=`root`@`localhost` TRIGGER triggerHolaHijo\n-- AFTER INSERT porque quiero modificar el valor del email despues de que se inserte la fila en la tabla alumnado, for each row porque quiero que se ejecute para cada fila\nAFTER INSERT ON hijo FOR EACH ROW\nBEGIN\n    -- Actualizo de la tabla empleado el campo NumHi donde CodEmp sea igual al CodEmp del hijo insertado, le sumo 1 al valor original\n    UPDATE empleado\n    SET NumHi = NumHi + 1\n    WHERE CodEmp = NEW.CodEmp;\nEND' 'CREATE DEFINER=`root`@`localhost` TRIGGER triggerAdiosHijo\n-- AFTER DELETE porque quiero modificar el valor del email despues de que se elimine la fila en la tabla alumnado, for each row porque quiero que se ejecute para cada fila\nAFTER DELETE ON hijo FOR EACH ROW\nBEGIN\n    -- Igual que antes pero le resto 1 y CodEmp es igual al CodEmp del hijo eliminado\n    UPDATE empleado\n    SET NumHi = NumHi - 1\n    WHERE CodEmp = OLD.CodEmp;\nEND'
sql_modes=1098907648 1098907648
definers='root@localhost' 'root@localhost'
client_cs_names='utf8mb4' 'utf8mb4'
connection_cl_names='utf8mb4_unicode_ci' 'utf8mb4_unicode_ci'
db_cl_names='utf8_general_ci' 'utf8_general_ci'
created=1776959501033022 1776959668621239
