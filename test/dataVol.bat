echo -login pa -pass 12 -res A.B -role READ -ds 2101-12-12 -de 1233-12-03 -vol 4 | java -cp "..\out\production\prj;..\commons-cli-1.4.jar" com.company.Main 
echo =====DataVol=====
IF %ERRORLEVEL% EQU 0 ( echo OK )
IF %ERRORLEVEL% NEQ 0 ( echo ### CRASHED ### )