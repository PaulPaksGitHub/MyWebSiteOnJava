echo -login pa -pass 12 -res A.B -role SORRY | java -cp "..\out\production\prj;..\commons-cli-1.4.jar" com.company.Main 
echo =====wrongRole=====
IF %ERRORLEVEL% EQU 3 ( echo OK )
IF %ERRORLEVEL% NEQ 3 ( echo ### CRASHED ### )
