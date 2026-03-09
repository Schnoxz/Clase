

### Tema 05: SQL avanzado

#### 1. Gestión de usuarios

##### 1.1 Creación de usuarios
La creación de usuarios en SQL se realiza con el comando `CREATE USER`. Por ejemplo:
```sql
CREATE USER 'nombre_usuario'@'localhost' IDENTIFIED BY 'contraseña_usuario';
```
Aquí, `'nombre_usuario'` es el nombre del usuario y `'contraseña_usuario'` es su contraseña. Usar `'localhost'` restringe la conexión al usuario desde la misma máquina donde reside la base de datos. Para permitir conexiones desde cualquier lugar, se puede reemplazar `'localhost'` con `'%'`.

##### 1.2 Borrado de usuarios
Para eliminar un usuario usamos `DROP USER`.
```sql
DROP USER 'nombre_usuario';
```

##### 1.3 Asignación de privilegios a usuarios
Una vez creado el usuario, es posible asignarle privilegios específicos utilizando el comando `GRANT`. Por ejemplo:
```sql
GRANT ALL PRIVILEGES ON nombre_base_datos.* TO 'nombre_usuario'@'localhost';
```
En este caso, `nombre_base_datos` es el nombre de la base de datos a la que se le están otorgando los privilegios.

Para asignar permisos SELECT e INSERT en una tabla específica:
```sql
GRANT SELECT, INSERT ON nombre_base_datos.nombre_tabla TO 'nombre_usuario'@'localhost';
```

##### 1.4 Eliminación de permisos
Para quitar permisos, puedes usar el comando `REVOKE`. Por ejemplo:
```sql
REVOKE SELECT, INSERT ON nombre_base_datos.nombre_tabla FROM 'nombre_usuario'@'localhost';
```

#### 2. Triggers

Los triggers realizan funciones transparentes para el programador y se ejecutan directamente en la base de datos. Estas acciones pueden ejecutarse de manera más eficiente directamente sobre la base de datos, pero hay que tener en cuenta el tiempo de procesamiento para no saturar el sistema.

**Sintaxis básica para crear un trigger:**
1. Especifica la operación que activa el disparador (INSERT, UPDATE o DELETE).
2. Especifica el nombre de la tabla a la que pertenece el disparador después de la palabra clave `ON`.
3. Especifica la sentencia que se ejecutará cuando se active el disparador.

**Ejemplo:**
```sql
CREATE TRIGGER ejemplo_trigger
AFTER UPDATE ON mi_tabla
FOR EACH ROW
BEGIN
    IF NEW.descripcion != OLD.descripcion THEN
        -- Acciones a realizar
    END IF;
END;
```

#### 3. Consideraciones sobre triggers

- **Recursividad:** Si un trigger modifica una tabla que a su vez activa otro trigger, podría entrar en un ciclo infinito.
- **Desempeño:** Los triggers pueden afectar negativamente el rendimiento si no se utilizan de manera responsable.

