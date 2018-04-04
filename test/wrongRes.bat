echo -login pa -pass 12 -res GG -role READ | java -cp "..\out\production\prj;..\commons-cli-1.4.jar" com.company.Main 
echo =====wrongRes=====
IF %ERRORLEVEL% EQU 4 ( echo OK )
IF %ERRORLEVEL% NEQ 4 ( echo ### CRASHED ### )
