$hola = "Hola, DAW"
$fecha = Get-Date

$anno = $fecha.Year
$mes  = $fecha.Month
$dia  = $fecha.Day
$hora = $fecha.Hour
$min  = $fecha.Minute
$seg  = $fecha.Second

$formatoFecha = "" + $anno + "-" + $mes + "-" + $dia + " " + $hora + ":" + $min + ":" + $seg 
# Me daba muchos fallos a la hora de expresarlo así que introduje un String vacio antes para que no tuviera errores de expresionnes ni nada raro al usar el +"


Write-Host $hola
Write-Host $formatoFecha