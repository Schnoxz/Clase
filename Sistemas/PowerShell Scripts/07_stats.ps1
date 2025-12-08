do {
    $archivo = Read-Host "Introduce la ruta del fichero: "
} while ($archivo -eq "")

#Verifico que exista el fichero, con los métodos de los anteriores ejercicios no funcionaba así que busqué cómo verfificar el path de un fichero
if ((Test-Path $archivo) -eq $false) {
    Write-Host "Error: El fichero no existe."
}

$lineas = Get-Content -Path $archivo

#Declaro un par de variables para usarlas en las operaciones luego
$cantidad = 0
$suma = 0

#Para Min y Max, usos $null para saber si es el primer número que leemos
$min = $null
$max = $null

#Bucle para contar los numeros en cada linea
foreach ($numTexto in $lineas) {
    
    #Valido que no esté vacía "not empty"
    if ($numTexto -ne "") {
        
        #Casteo el texto a int
        $n = $numTexto - 0
        
        #Incremento el contador de cantidad
        $cantidad = $cantidad + 1
        
        #Acumulo el número a la variable suma
        $suma = $suma + $n
        

        #Si $max es nulo es el primero o el número actual es mayor
        if ($max -eq $null) {
            $max = $n
        } elseif ($n -gt $max) {
            $max = $n
        }
        
        #Si $min es nulo es el primero o el número actual es menor 
        if ($min -eq $null) {
            $min = $n
        } elseif ($n -lt $min) {
            $min = $n
        }
    }
}

#Si el contador no ha sumado es que está vacío o no encuentra los números debidamente
if ($cantidad -eq 0) {
    Write-Host "El fichero está vacío."
} else {
    #Calculo la media
    $media = $suma / $cantidad
    
    #Para que solo salgan 2 decimales, voy a trabajar con una cadena de texto, como solo la voy a imprimir por pantalla no necesito castearla, los calculos ya están hechos
    $mediaTexto = "$media"
    
    #String vacia para guardar el numero con solo 2 decimales
    $mediaFinal = ""
    #Contador de decimales
    $contadorDecimales = 0  
    #Levanto una bandera que confirmará cuando sea decimal
    $esDecimal = $false 
    
    #Creo un contador para encontrar el punto para distinguir enteros y decimal
    for ($i = 0; $i -lt $mediaTexto.Length; $i++){
        $letra = $mediaTexto[$i] 
        
        #Detecta si es el punto
        if ($letra -eq '.') {
            $esDecimal = $true
        }        
        #Encuentra decimal y suma 1 al contador
        if ($esDecimal -eq $true -and $letra -ne '.') {
            $contadorDecimales = $contadorDecimales + 1
        }       
        #Si solo son 2 o menos decimales se suma, ya no sumará más decimales al print de mediaFinal que aparecerá por pantalla
        if ($contadorDecimales -le 2) {
            $mediaFinal = $mediaFinal + $letra
            } else {
            break #salgo del bucle cuando acumula más de 2 decimales para que deje de contar toda la cadena
        }
    }


#Muestro las operaciones
    Write-Host 
    
    "<> Cálculos <>"

    Write-Host "Cantidad de números: $cantidad"
    Write-Host "Mínimo: $min"
    Write-Host "Máximo: $max"
    Write-Host "Media:  $mediaFinal"
}