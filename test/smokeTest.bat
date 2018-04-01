echo "" | java -cp "..\out\production\prj;..\commons-cli-1.4.jar" com.company.Main
echo =====smokeTest=====
IF %ERRORLEVEL% EQU 0 ( echo OK )
IF %ERRORLEVEL% NEQ 0 ( echo ### CRASHED ### )
