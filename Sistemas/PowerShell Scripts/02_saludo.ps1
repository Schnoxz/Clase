$nombre = Read-Host "Introduce tu nombre"

if ($nombre -eq  "") {
    $nombre = "desconocido"
}
Write-Host "Hola, $nombre!"