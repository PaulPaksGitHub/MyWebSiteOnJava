echo  -vol 4 -login pa -role READ -ds 2101-12-12 -pass 12 -res A.B -de 1233-12-03 | java -cp "..\out\production\prj;..\commons-cli-1.4.jar" com.company.Main 
echo =====RandomPositinOfParams=====
IF %ERRORLEVEL% EQU 0 ( echo OK )
IF %ERRORLEVEL% NEQ 0 ( echo ### CRASHED ### )