java -cp "..\out\production\prj;..\commons-cli-1.4.jar" com.company.Main "-l pa -p 12 -r A.B -o READ -s 12-2101-12 -e 1233-12-03 -v 4"
echo =====WrongData=====
IF %ERRORLEVEL% EQU 5 ( echo OK )
IF %ERRORLEVEL% NEQ 5 ( echo ### CRASHED ### )