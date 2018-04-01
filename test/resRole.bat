echo -l pa -p 12 -r A.B -o READ | java -cp "..\out\production\prj;..\commons-cli-1.4.jar" com.company.Main 
echo =====ResRole=====
IF %ERRORLEVEL% EQU 0 ( echo OK )
IF %ERRORLEVEL% NEQ 0 ( echo ### CRASHED ### )
