#Escribe un script que use un bucle  while  para imprimir los números del 1 al 5.

#!/usr/bin/env bash
i=1
while [ $i -le 5 ]
do
    echo $i
    i=$((i+1))
done