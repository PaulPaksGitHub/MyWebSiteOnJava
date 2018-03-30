java -cp "..\out\production\prj;..\commons-cli-1.4.jar" com.company.Main "-l kukuruzka -p 12"
echo =====wrongLogin=====
IF %ERRORLEVEL% EQU 1 ( echo OK )
IF %ERRORLEVEL% NEQ 1 ( echo ### CRASHED ### )