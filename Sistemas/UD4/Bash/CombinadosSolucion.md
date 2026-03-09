
---

### Bloque 1: Fundamentos y Clásicos

**1. Saludo Personalizado**
Tu manual recomienda usar llaves `${}` al llamar a las variables como buena práctica.

```bash
#!/usr/bin/env bash
# Definimos la variable sin espacios
nombre="TuNombre"
# Usamos llaves para proteger la variable
echo "Hola, ${nombre}"

```

**2. Saludo Interactivo**
Usamos el comando `read` con la opción `-p` para mostrar el mensaje en la misma línea y ahorrar código.

```bash
#!/usr/bin/env bash
read -p "Introduce tu nombre: " nombre_usuario
echo "Hola, ${nombre_usuario}"

```

**3. Saludo con Argumentos**
Los argumentos se recogen con `$1`, `$2`, etc..

```bash
#!/usr/bin/env bash
# $1 representa el primer argumento pasado al script
echo "Hola, $1"

```

**4. Comprobación Numérica**
Tu curso especifica el uso de **dobles corchetes** `[[ ]]` para los condicionales. Para comparar números usamos `-gt` (mayor que).

```bash
#!/usr/bin/env bash
read -p "Introduce un número: " num

if [[ $num -gt 0 ]]; then
    echo "El número es positivo"
else
    echo "El número es 0 o negativo"
fi

```

**5. Conteo Básico**
Utilizamos el bucle `for` con un rango de llaves `{1..10}`, tal como se menciona en la sección de bucles.

```bash
#!/usr/bin/env bash
for i in {1..10}
do
    echo $i
done

```

---

### Bloque 2: Bucles y Condicionales

**6. Números Pares e Impares**
Usamos la estructura `if-else`  dentro del bucle.

```bash
#!/usr/bin/env bash
for i in {1..20}
do
    # Usamos doble paréntesis para operaciones aritméticas
    if (( i % 2 == 0 )); then
        echo "El número $i es par"
    else
        echo "El número $i es impar"
    fi
done

```

**7. Contador con while**
El manual indica que la estructura `while` usa `[[ ]]` y operadores como `-ge` (mayor o igual). También usamos `((contador--))` para restar.

```bash
#!/usr/bin/env bash
contador=10
while [[ $contador -ge 1 ]]
do
    echo $contador
    ((contador--))
done

```

**8. Contraseña Secreta**
Usamos el operador `!=` dentro de los dobles corchetes para comparar cadenas.

```bash
#!/usr/bin/env bash
pass=""
# Mientras la contraseña NO sea igual a "secreto"
while [[ "$pass" != "secreto" ]]
do
    read -p "Introduce la contraseña: " pass
done
echo "¡Contraseña correcta!"

```

**9. Recorrer un Array**
Aplicamos la sintaxis de arrays del curso: paréntesis para definir `("a" "b")` y `[@]` para leer todos los elementos.

```bash
#!/usr/bin/env bash
frutas=("Manzana" "Banana" "Fresa")

# Usamos comillas y [@} para iterar correctamente
for fruta in "${frutas[@]}"
do
    echo "${fruta}"
done

```

**10. Comprobación de Archivo**
Según la lista de expresiones de archivo de tu manual, usamos `-e` (existe).

```bash
#!/usr/bin/env bash
archivo=$1
if [[ -e ${archivo} ]]; then
    echo "El archivo ${archivo} existe"
else
    echo "El archivo ${archivo} no existe"
fi

```

---

### Bloque 3: Introducción a Funciones

**11. Tu Primera Función**
Tu manual usa la palabra clave `function` seguida de paréntesis y llaves.

```bash
#!/usr/bin/env bash
function hola() {
    echo "¡Hola desde mi función!"
}
# Llamamos a la función sin paréntesis
hola

```

**12. Función con Argumentos**
Dentro de una función, `$1` se refiere al argumento que le pasas a ella, no al del script general.

```bash
#!/usr/bin/env bash
function saludar() {
    echo "Bienvenido, $1"
}
saludar "Ana"

```

**13. Refactorización (Funciones)**
Combinamos función y condicional `if`.

```bash
#!/usr/bin/env bash
function comprobar_numero() {
    if [[ $1 -gt 0 ]]; then
        echo "El número es positivo"
    else
        echo "El número es 0 o negativo"
    fi
}

read -p "Dame un número: " n
comprobar_numero $n

```

**14. Menú Básico con case**
Seguimos la estructura `case variable in ... esac` detallada en el manual.

```bash
#!/usr/bin/env bash
echo "1) Mostrar la fecha actual"
echo "2) Mostrar quién está conectado"
read -p "Elige una opción: " opcion

case $opcion in
    1)
        date
        ;;
    2)
        who
        ;;
    *)
        echo "Opción no válida"
        ;;
esac

```

**15. Comprobación de Usuario (Root)**
Tu manual tiene un ejemplo específico para esto usando `$EUID` y comprobando si es 0.

```bash
#!/usr/bin/env bash
if [[ $EUID -eq 0 ]]; then
    echo "Advertencia: Estás ejecutando como root"
else
    echo "Ejecutando como usuario normal"
fi

```

---

### Bloque 4: Scripts Avanzados

**16. Script de Información del Sistema**
Creamos tres funciones sencillas y las llamamos en orden.

```bash
#!/usr/bin/env bash
function mostrar_memoria() {
    free -h
}
function mostrar_disco() {
    df -h
}
function mostrar_uptime() {
    uptime
}

mostrar_memoria
mostrar_disco
mostrar_uptime

```

**17. Menú con Funciones (Refactorización)**
Reutilizamos la lógica del `case` pero llamando a funciones en lugar de comandos directos.

```bash
#!/usr/bin/env bash
function func_fecha() {
    date
}
function func_usuario() {
    who
}

echo "1) Fecha"
echo "2) Usuario"
read -p "Elige: " op

case $op in
    1) func_fecha ;;
    2) func_usuario ;;
    *) echo "Error" ;;
esac

```

**18. Gestión de Argumentos**
Aunque el manual se centra en `$@`, usamos `$#` (número de argumentos) que es el estándar para contar. Si no es igual (`-ne`) a 1, damos error.

```bash
#!/usr/bin/env bash
if [[ $# -ne 1 ]]; then
    echo "Error: Debes proporcionar un nombre de archivo."
else
    echo "Procesando el archivo: $1"
fi

```

**19. Creación de Directorios**
Usamos `mkdir -p` para crear estructuras de carpetas completas de una vez.

```bash
#!/usr/bin/env bash
read -p "Nombre del proyecto: " proy
mkdir -p "${proy}/src"
mkdir -p "${proy}/docs"
mkdir -p "${proy}/tests"

```

**20. Script de Búsqueda (Avanzado)**
Verificamos si la variable está vacía con `-z`  y movemos los archivos.

```bash
#!/usr/bin/env bash
extension=$1

# Validamos que nos den un argumento
if [[ -z ${extension} ]]; then
    echo "Falta la extensión"
    exit 1
fi

# Comprobamos si el directorio existe (-d)
if [[ ! -d "backup" ]]; then
    mkdir backup
fi

# Movemos los archivos y ocultamos errores
mv *.${extension} backup/ 2>/dev/null
echo "Proceso terminado."

```