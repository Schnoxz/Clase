$a = Read-Host "Introduce el número A"
$b = Read-Host "Introduce el número B"

# Al intentar sumar A+B se convertía en AB y no un numero C, por ejemplo 1+1=11 --> != 2 supongo que así funciona como si concatenara texto y no un int ya que nunca se declara que sea un numero
# Voy a castearlos restando 0 a la cadena y asi lo convierto a numero, ya vimos este truquito con Java 

$a = $a - 0
$b = $b - 0

$suma = $a + $b
$resta = $a - $b 
$multiplicacion = $a * $b 

Write-Host "Suma: " $suma
Write-Host "Resta: " $resta
Write-Host "Multiplicación: " $multiplicacion

if ($b -eq 0){
    Write-Host "No se puede dividir por 0"
    } else {
    $division = $a / $b
    Write-Host "División: " $division
    }
    
