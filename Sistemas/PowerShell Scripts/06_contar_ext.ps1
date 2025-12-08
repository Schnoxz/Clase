#Pido al usuario la ruta hasta que escriba algo que no sea espacios o nada
do {
    $ruta = Read-Host "Introduce la ruta del directorio"
} while ($ruta -eq "")

$extension = Read-Host "Introduce la extensión a buscar (ej: txt)"

#Compruebo que la ruta exista
 
$existe = Get-Item -Path $ruta 

if ($existe -eq $null){
    Write-Host "Error: El directorio '$ruta' no existe."
}

#Compruebo que la extensión tenga el "." y si no lo tiene se lo añado
if ($extension.StartsWith(".") -eq $false) {
    $extension = "." + $extension
}

#Declaro una variable para buscar todos esos ficheros con la extension
$busqueda = "*" + $extension

#Sacotodos los "File" de la ruta con Get-ChildItem
Write-Host "Buscando archivos $busqueda en $ruta..."
$archivos = Get-ChildItem -Path $ruta -Filter $busqueda -File 

#Cuento todos los elementos dentro del archivo teniendo en cuenta el tipo de extension anteriormente declarado
$cantidad = 0
foreach ($elemento in $archivos) {
    $cantidad = $cantidad + 1
}

Write-Host "Resultado: Se encontraron $cantidad archivos con extensión $extension"