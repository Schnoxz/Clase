#!/usr/bin/env bash
#CREAR E IMPRIMIR VARIABLES
nombre="Mi variable"
Imprimir: echo "¿Cómo te llamas?"
Escribir por pantalla: read nombre
echo "¡Hola ${nombre}!"
#CREAR ARRAYS
mi_array=("valor 1" "valor 2" "valor 3" "valor 4")
echo ${mi_array[1]} Devuelve la posición 0 de los elementos (valor 1).
echo ${mi_array[-1]} Devuelve el último elemento (valor 4).
echo ${mi_array[@]} Devuelve todos los elementos (valor 1 a 4).
#REBANADO DE ARRAYS
Teniendo: array=("A" "B" "C" "D" "E")
Imprimir rango de elementos:
echo "${array[@]:1:3}"
Donde 1 es el inicio y 3 la cantidad de elementos
echo "${array[@]:3}"
Imprimirá a partir del elemento en la posición 3 (D E)
#REBANADO DE CADENAS
${cadena:inicio:longitud}
Donde:
- inicio es el índice inicial (basado en 0)
- longitud es el número máximo de caracteres a extraer
texto="ABCDE"
Extraer desde el índice 0, un máximo de 2 caracteres
echo "${texto:0:2}" # Salida: AB
Extraer desde el índice 3 hasta el final
echo "${texto:3}" # Salida: DE
Extraer 3 caracteres comenzando desde el índice 1
echo "${texto:1:3}" # Salida: BCD
Si la longitud excede los caracteres restantes, se detiene al final
echo "${texto:3:3}"
Salida: DE (solo 2 caracteres disponibles)
#EXPRESIONES DE ARCHIVO
Verdadero si el archivo existe. (sustituido por -e )
[[ -a ${archivo} ]]
Verdadero si el archivo existe y es un archivo especial de bloque.
[[ -b ${archivo} ]]
Verdadero si el archivo existe y es un archivo especial de caracteres.
[[ -c ${archivo} ]]
Verdadero si el archivo existe y es un directorio.
[[ -d ${archivo} ]]
Verdadero si el archivo existe. (Alternativa a -a)
[[ -e ${archivo} ]]
Verdadero si el archivo existe y es un archivo regular.
[[ -f ${archivo} ]]
Verdadero si el archivo existe y es un enlace simbólico.
[[ -h ${archivo} ]]
Verdadero si el archivo existe y es legible.
[[ -r ${archivo} ]]
Verdadero si el archivo existe y tiene un tamaño mayor que cero.
[[ -s ${archivo} ]]
Verdadero si el archivo existe y es escribible.
[[ -w ${archivo} ]]
Verdadero si el archivo existe y es ejecutable.
[[ -x ${archivo} ]]
Verdadero si el archivo existe y es un enlace simbólico. (Alternativa a -h)
[[ -L ${archivo} ]]
#EXPRESIONES DE CADENA
Verdadero si la variable de shell nombre_var está definida (se le ha asignado un valor).
[[ -v nombre_var ]]
(Aquí, nombre_var es el nombre de la variable. El operador -v espera un nombre de
variable, no
su valor, así que si pasas ${nombre_var} en lugar de nombre_var , la expresión devolverá
falso).
Verdadero si la longitud de la cadena es cero.
[[ -z ${cadena} ]]
Verdadero si la longitud de la cadena no es cero.
[[ -n ${cadena} ]]
Verdadero si las cadenas son iguales. ( = debe usarse con el comando test para
conformidad
POSIX).
[[ ${cadena1} == ${cadena2} ]]
Verdadero si las cadenas no son iguales.
[[ ${cadena1} != ${cadena2} ]]
Verdadero si cadena1 se ordena antes que cadena2 lexicográficamente.
[[ ${cadena1} < ${cadena2} ]]
Verdadero si cadena1 se ordena después que cadena2 lexicográficamente.
[[ ${cadena1} > ${cadena2} ]]
#OPERADORES ARITMÉTICOS
Devuelve verdadero si los números son iguales
[[ $arg1 -eq $arg2 ]]
Devuelve verdadero si los números no son iguales
[[ $arg1 -ne $arg2 ]]
Devuelve verdadero si arg1 es menor que arg2
[[ $arg1 -lt $arg2 ]]
Devuelve verdadero si arg1 es menor o igual que arg2
[[ $arg1 -le $arg2 ]]
Devuelve verdadero si arg1 es mayor que arg2
[[ $arg1 -gt $arg2 ]]
Devuelve verdadero si arg1 es mayor o igual que arg2
[[ $arg1 -ge $arg2 ]]
#SENTENCIA IF ELSE
Ejemplo de sentencia if de Bash:
read -p "¿Cuál es tu nombre? " nombre
if [[ -z $nombre ]]
then
echo "¡Por favor, ingresa tu nombre!"
else
echo "Hola $nombre"
fi
Otro ejemplo:
admin="usuario_admin"
read -p "Ingresa tu usuario: " usuario
#Comprueba si el usuario proporcionado es el admin
if [[ "$usuario" == "$admin" ]]
then
echo "¡Eres el usuario administrador!"
else
echo "Tú NO eres el usuario administrador."
fi
Otro ejemplo con elif:
read -p "Ingresa un número: " num
if [[ $num -gt 0 ]]; then
echo "El número es positivo"
elif [[ $num -lt 0 ]]; then
echo "El número es negativo"
else
echo "El número es 0"
fi