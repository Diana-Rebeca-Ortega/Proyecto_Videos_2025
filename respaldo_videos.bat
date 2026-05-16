@echo off
:: 1. Configurar la carpeta de respaldos en el disco C
set BACKUP_DIR=C:\RespaldosProyecto

:: 2. Crear la carpeta si no existe
if not exist "%BACKUP_DIR%" mkdir "%BACKUP_DIR%"

echo Intentando realizar el respaldo automatizado en SQLEXPRESS...
echo.

:: 3. Declaramos variables dentro de SQL Server para armar la ruta exacta con año-mes-dia
sqlcmd -S .\SQLEXPRESS -E -Q "DECLARE @path VARCHAR(256); SELECT @path = 'C:\RespaldosProyecto\bd_videos_' + CONVERT(VARCHAR(10), GETDATE(), 112) + '.bak'; BACKUP DATABASE [bd_videos_proyecto_final] TO DISK = @path WITH FORMAT, INIT, NAME = 'Full Backup of bd_videos';"

echo.
echo Proceso ejecutado. Verifica si ya se creo el archivo en C:\RespaldosProyecto
pause