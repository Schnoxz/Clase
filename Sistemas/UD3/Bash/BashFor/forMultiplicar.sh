# Escribe un script que use un bucle  for  para mostrar la tabla de multiplicar del 3 (del 3x1 al 3x10)

#!/usr/bin/env bash

for i in {1..10}
do
    echo "3 x $i = $(($i*3))"
done