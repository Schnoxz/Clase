#Crea un script que use un bucle  while  para sumar los números del 1 al 10 y muestre solo el resultado final (55).

#!/usr/bin/env bash

i=1
sum=0
while [ $i -le 10 ]
do
    sum=$((sum+i))
    i=$((i+1))
done
echo $sum