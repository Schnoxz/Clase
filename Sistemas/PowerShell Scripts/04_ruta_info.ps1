do{
    $ruta = Read-Host "Introduce una ruta: "

    if ($ruta -eq "") {
    Write-Host "La ruta no puede estar vacía, introduce una ruta: "
    }
    } while ($ruta -eq "")

Write-Host ""

$existe = Get-Item -Path $ruta 

if ($existe -eq $null){
    Write-Host "La ruta no existe"
    } else {

    #Saco el nombre para saber que tipo de dato es
    $tipo = $existe.GetType().Name

    #Comparo el nombre por si es directorio
    if ($tipo -eq "DirectoryInfo") {
    Write-Host "Tipo: Directorio"
    $esFichero = $false

    #Cuento los elementos dentro
    $lista = Get-ChildItem -Path $ruta
    $numElementos = 0
    foreach ($elemento in $lista) { $numElementos ++ }
    Write-Host "Elementos directos : " $numElementos
    
    #Si no e comparo con el de fichero
    } elseif ($tipo -eq "FileInfo"){
    Write-Host "Tipo: Fichero"
    $esFichero = $true
    
    #Asumo que es enlace
    } else {
    Write-Host "Tipo: Enlace"
    }
    if ($esFichero = $true){
    Write-Host "Tamaño: " $existe.length "bytes"
    }
    #En Get-Help saco "Get-Acl" para ver permisos y dueño del fichero
    $permiso = Get-Acl -Path $ruta
    Write-Host "Dueño: " $permiso.Owner
    Write-Host "Permisos: " $permiso.AccessToString

    }

