echo -login pa -pass 12 -res A.B -role READ -ds 12-2101-12 -de 1233-12-03 -vol 4 | java -cp "..\out\production\prj;..\commons-cli-1.4.jar" com.company.Main 
echo =====WrongData=====
IF %ERRORLEVEL% EQU 5 ( echo OK )
IF %ERRORLEVEL% NEQ 5 ( echo ### CRASHED ### )