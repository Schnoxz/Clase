#Define un array en bash de 4 frutas (ej:  frutas=("Manzana" "Banana" "Naranja" "Uva") ) y usa un bucle  for  para imprimir cada elemento del array.

#!/usr/bin/env bash

# 1. Definimos el array con 4 frutas
frutas=("Manzana" "Banana" "Naranja" "Uva")

# 2. Usamos el bucle for para recorrerlo
# OJO: "${frutas[@]}" significa "todos los elementos del array"
for fruta in "${frutas[@]}"; do
    echo "La fruta es: $fruta"
done