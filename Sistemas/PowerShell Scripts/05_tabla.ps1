$n = Read-Host "Introduce un numero"
for ($i = 1; $i -le 10; $i++) {
    Write-Host "$n x $i = ($n * $i)"
}