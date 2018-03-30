java -cp "..\out\production\prj;..\commons-cli-1.4.jar" com.company.Main "-l pa -p 12 -r A.B -o READ -s 2101-12-12 -e 1233-12-03 -v 4"
echo =====DataVol=====
IF %ERRORLEVEL% EQU 0 ( echo OK )
IF %ERRORLEVEL% NEQ 0 ( echo ### CRASHED ### )