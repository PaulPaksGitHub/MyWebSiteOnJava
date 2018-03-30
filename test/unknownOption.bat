java -cp "..\out\production\prj;..\commons-cli-1.4.jar" com.company.Main "--egg hello"
echo =====UnknownOption=====
IF %ERRORLEVEL% EQU 6 ( echo OK )
IF %ERRORLEVEL% NEQ 6 ( echo ### CRASHED ### )