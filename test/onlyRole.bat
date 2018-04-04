echo -login pa -pass 12 -role READ | java -cp "..\out\production\prj;..\commons-cli-1.4.jar" com.company.Main 
echo =====onlyRole=====
IF %ERRORLEVEL% EQU 4 ( echo OK )
IF %ERRORLEVEL% NEQ 4 ( echo ### CRASHED ### )