#Creo los parametros que se indican para trabajar con el script cuando se ejecute
param(
    [string]$Source,      # Carpeta a respaldar
    [string]$Destination, # Dónde guardar el zip
    [int]$Keep            # Número de copias a conservar
)

if ($Source -eq "") { $Source = Read-Host "Introduce la ruta de origen de la carpeta a guardar: " }
if ($Destination -eq "") { $Destination = Read-Host "Introduce la ruta destino del backup; " }
#Si Keep es 0, pedimos un número
if ($Keep -eq 0) { 
    $Keep = Read-Host "Número de copias que desea mantener: " 
    $Keep = $Keep - 0
}
#Si no existe ruta de origen
if ((Test-Path $Source) -eq $false) {
    Write-Host "Error: La ruta de origen no existe"
}

#Si no existe ruta de destino, se crea con New-Item
if ((Test-Path $Destination) -eq $false) {
    New-Item -Path $Destination -ItemType Directory
    Write-Host "Se ha creado una carpeta de destino correctamente"
}

 Write-Host "" 
 
#Obtenemos la fecha 
$fecha = Get-Date
#Formato "backup_2023(año)12(mes)31(dia)_23(hora)59(minuto)59(segundo).zip" usando el timestamp
$timestamp = "" + $fecha.Year + $fecha.Month + $fecha.Day + "_" + $fecha.Hour + $fecha.Minute + $fecha.Second

# Lo pegamos al nombre del archivo
$nombreZip = "backup_" + $timestamp + ".zip"

#Creo una variable para unir la ruta final de la carpeta y su nombre
$rutaFinal = $Destination + "\" + $nombreZip

 Write-Host ""

#He necesitado buscar qué era el Compress-Archive y como funciona, sé que existe la opción "-Force" pero como el nombre del archivo va a variar por el timestamp no veo necesario que se añada al script
Write-Host "--Creando backup--"
Compress-Archive -Path $Source -DestinationPath $rutaFinal
#Compruebo que se haya creado correctamente
if ((Test-Path $rutaFinal) -eq $true) {
    Write-Host "Backup creado con éxito."
} else {
    Write-Host "Falló la compresión."
}

 Write-Host ""
#Ahora nos quedaría mantener las copias más recientes y borrar las antiguas
Write-Host "---Mantener $Keep---"

#Creo un listado de ficheros del backup que hemos creado
$listaBackups = Get-ChildItem -Path $Destination -Filter "backup_*.zip"

#Cuenta cuántos hay
$total = $listaBackups.Count

#Si el total supera al número que el usuario desea mantener
if ($total -gt $Keep) {
    #Se calcula cuántos sobran
    $sobran = $total - $Keep
    Write-Host "Hay $total copias. Sobran $sobran."

    #Ordena por fecha de creación, es decir de más antiguos a más recientes, y elige los "$sobran" primeros, ya depende del valor que haya dado el usuario
    $paraBorrar = $listaBackups | Sort-Object CreationTime | Select-Object -First $sobran

    #Creo un bucle que borra los ficheros uno a uno y los va mostrando por pantalla
    foreach ($fichero in $paraBorrar) {
        Write-Host "Borrando antiguo: " $fichero.Name
        Remove-Item -Path $fichero.FullName
    }
    #Mensaje para cuando termine de eliminar ficheros
    Write-Host "Limpieza completada."

} else {
    Write-Host "No es necesario borrar nada (Total: $total)."
}