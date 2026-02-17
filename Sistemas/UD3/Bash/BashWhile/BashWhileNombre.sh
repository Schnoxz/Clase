#Escribe un script que pida al usuario su nombre. Usa un bucle  while  para seguir preguntando si el usuario no introduce ningún texto (cadena vacía).

#!/usr/bin/env bash
nombre=""
while [ -z "$nombre" ]; do
read -p "Introduce tu nombre: " nombre
done