echo -l pa -p 34 | java -cp "..\out\production\prj;..\commons-cli-1.4.jar" com.company.Main 
echo =====wrongPass=====
IF %ERRORLEVEL% EQU 2 ( echo OK )
IF %ERRORLEVEL% NEQ 2 ( echo ### CRASHED ### )