#Para este script he necesitado saber el formato de un log, y entender en cu�ntas partes se divide una linea de datos
#Dividir� en subcadenas esa cadena de datos y trabajar� con ellas para encontrar por posici�n los datos que se me piden, me faltan muchos comandos que no s� as� que lo resuelvo usando lo que ya conozco de Java

do {
    $rutaLog = Read-Host "Introduce la ruta del fichero access.log"
} while ($rutaLog -eq "")

if ((Test-Path $rutaLog) -eq $false) {
    Write-Host "Error: El fichero no existe."
    exit
}

$lineas = Get-Content -Path $rutaLog


#Bucle para contar los datos de linea en linea y guardarlos en una variable
$datosProcesados = foreach ($linea in $lineas) {
    #Solo lee la linea si tiene al menos 10 caracteres
    if ($linea.Length -gt 10) {
        #Divido la cadena de caracteres en palabras cada vez que se encuentra un espacio en blanco
        $palabras = $linea.Split(" ")
        #Creo una cadena vacia donde a su vez creo objetos IP, URL y Estado que estar�n vac�as y luego se les asignar� unos datos
        $registro = "" | Select-Object IP, URL, Estado
        $registro.IP = $palabras[0] #Segun la posicion de un log comun la IP es lo primero que se encuentra entonces ya tengo su posici�n en la cadena
        $registro.URL = $palabras[6] #Sigo la misma norma que ya he mencionado, uso de ejemplo una cadena de un log y declaro la posici�n concreta de la "url"
        $registro.Estado = $palabras[8] #Si hay 10 secuencia de datos en la cadena pero el orden en un array y al contar dentro de una cadena empieza desde 0, el codigo HTTP se encuentra en la 8 posicion


        #Encontr� que no hace falta se�alar un array y que la misma powershell te agrupa todo en la misma variable as� que la declaro sola y se ir�n acumulando
        $registro
    }
}
#Si la cantidad de datos es 0, devuelve un mensaje por pantalla para saber que no hay datos
if ($datosProcesados -eq $null -or $datosProcesados.Count -eq 0) { 
    Write-Host "Sin datos."
} else {


#Creo un fichero temporal donde envio los datos y los consulto, porque en la terminal me salen todos juntos y no separados por "IP, URL o HTTP"
$informe = "informe_temp.txt"

"--- Total de peticiones realizadas ---"
$datosProcesados.Count >> $informe

#Voy enviando las tablas de datos al fichero  usando el redireccionamiento que a�ade datos, los ordeno por grupo y cantidad de veces que aparece, los titulos no los sobreescribo para que no se repitan
"--- TOP 5 IPs ---" >> $informe
$datosProcesados | Group-Object IP | Sort-Object Count -Descending | Select-Object -First 5 Name, Count >> $informe

"--- TOP 5 Recursos utilizados ---" >> $informe
$datosProcesados | Group-Object URL | Sort-Object Count -Descending | Select-Object -First 5 Name, Count >> $informe

"--- Resumen por C�digo HTTP ---" >> $informe
$datosProcesados | Group-Object Estado | Sort-Object Count -Descending | Select-Object Name, Count >> $informe



#Solicito los datos del archivo .txt que he creado (Se crear� en el directorio que estemos en la terminal, muy posiblemente la carpeta donde se encuentran los scripts)
Get-Content $informe
}