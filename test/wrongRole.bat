echo -l pa -p 12 -r A.B -o SORRY | java -cp "..\out\production\prj;..\commons-cli-1.4.jar" com.company.Main 
echo =====wrongRole=====
IF %ERRORLEVEL% EQU 3 ( echo OK )
IF %ERRORLEVEL% NEQ 3 ( echo ### CRASHED ### )
