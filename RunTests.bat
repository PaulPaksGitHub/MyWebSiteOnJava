@echo off
echo C3.6
java -cp ".\out\production\prj;.\libraries\*" com.company.Main -login 'jdoe' -pass 'sup3rpaZZ' -role 'WRITE' -res 'a.bc'
IF %ERRORLEVEL% EQU 4 ( echo OK )
IF %ERRORLEVEL% NEQ 4 ( echo ### CRASHED ### )

echo =====onlyRole=====
java -cp ".\out\production\prj;.\libraries\*" com.company.Main -login pa -pass 12 -role READ
IF %ERRORLEVEL% EQU 4 ( echo OK )
IF %ERRORLEVEL% NEQ 4 ( echo ### CRASHED ### )

echo =====DataVol=====
java -cp ".\out\production\prj;.\libraries\*" com.company.Main -login pa -pass 12 -res A.B -role READ -ds 2101-12-12 -de 1233-12-03 -vol 4
IF %ERRORLEVEL% EQU 0 ( echo OK )
IF %ERRORLEVEL% NEQ 0 ( echo ### CRASHED ### )

echo =====UnknownOption=====
java -cp ".\out\production\prj;.\libraries\*" com.company.Main -egg foo
IF %ERRORLEVEL% EQU 0 ( echo OK )
IF %ERRORLEVEL% NEQ 0 ( echo ### CRASHED ### )

echo =====smokeTest=====
java -cp ".\out\production\prj;.\libraries\*" com.company.Main 
IF %ERRORLEVEL% EQU 0 ( echo OK )
IF %ERRORLEVEL% NEQ 0 ( echo ### CRASHED ### )

echo =====LogPass=====
java -cp ".\out\production\prj;.\libraries\*" com.company.Main  -login pa -pass 12
IF %ERRORLEVEL% EQU 0 ( echo OK )
IF %ERRORLEVEL% NEQ 0 ( echo ### CRASHED ### )


echo =====wrongLogin=====
java -cp ".\out\production\prj;.\libraries\*" com.company.Main "-login kukuruzka -pass 12"
IF %ERRORLEVEL% EQU 1 ( echo OK )
IF %ERRORLEVEL% NEQ 1 ( echo ### CRASHED ### )

echo =====ResRole=====
java -cp ".\out\production\prj;.\libraries\*" com.company.Main "-login 'pa' -pass '12' -res 'A.B.C' -role 'WRITE' "
IF %ERRORLEVEL% EQU 0 ( echo OK )
IF %ERRORLEVEL% NEQ 0 ( echo ### CRASHED ### )


echo =====OnlyLogin=====
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar;.\log4j-1.2.17.jar" com.company.Main "-login pa "
IF %ERRORLEVEL% EQU 2 ( echo OK )
IF %ERRORLEVEL% NEQ 2 ( echo ### CRASHED ### )



echo =====wrongLogin=====
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar;.\log4j-1.2.17.jar" com.company.Main "-login kukuruzka -pass 12"
IF %ERRORLEVEL% EQU 1 ( echo OK )
IF %ERRORLEVEL% NEQ 1 ( echo ### CRASHED ### )

echo =====wrongPass=====
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar;.\log4j-1.2.17.jar" com.company.Main "-login pa -pass 34"
IF %ERRORLEVEL% EQU 2 ( echo OK )
IF %ERRORLEVEL% NEQ 2 ( echo ### CRASHED ### )

echo =====wrongRes=====
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar;.\log4j-1.2.17.jar" com.company.Main "-login pa -pass 12 -res GG -role READ"
IF %ERRORLEVEL% EQU 4 ( echo OK )
IF %ERRORLEVEL% NEQ 4 ( echo ### CRASHED ### )

echo =====wrongRole=====
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar;.\log4j-1.2.17.jar" com.company.Main "-login pa -pass 12 -res A.B -role SORRY"
IF %ERRORLEVEL% EQU 3 ( echo OK )
IF %ERRORLEVEL% NEQ 3 ( echo ### CRASHED ### )

echo =====WrongData=====
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar;.\log4j-1.2.17.jar" com.company.Main "-login pa -pass 12 -res A.B -role READ -ds 12-2101-12 -de 1233-12-03 -vol 4"
IF %ERRORLEVEL% EQU 5 ( echo OK )
IF %ERRORLEVEL% NEQ 5 ( echo ### CRASHED ### )

echo =====WrongVol=====
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar;.\log4j-1.2.17.jar" com.company.Main "-login pa -pass 12 -res A.B -role READ -ds 2101-12-12 -de 1233-12-03 -vol 4.7 "
IF %ERRORLEVEL% EQU 5 ( echo OK )
IF %ERRORLEVEL% NEQ 5 ( echo ### CRASHED ### )

echo =====RandomPositinOfParams=====
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar;.\log4j-1.2.17.jar" com.company.Main "-vol 4 -login pa -role READ -ds 2101-12-12 -pass 12 -res A.B -de 1233-12-03 "
IF %ERRORLEVEL% EQU 0 ( echo OK )
IF %ERRORLEVEL% NEQ 0 ( echo ### CRASHED ### )

echo C1.1
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar;.\log4j-1.2.17.jar" com.company.Main ""

echo C1.2
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar;.\log4j-1.2.17.jar" com.company.Main "-h"
IF %ERRORLEVEL% EQU 0 ( echo OK )
IF %ERRORLEVEL% NEQ 0 ( echo ### CRASHED ### )

echo C2.1
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar;.\log4j-1.2.17.jar" com.company.Main "-login 'XXX' -pass 'XXX'"
IF %ERRORLEVEL% EQU 1 ( echo OK )
IF %ERRORLEVEL% NEQ 1 ( echo ### CRASHED ### )

echo C2.2
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar;.\log4j-1.2.17.jar" com.company.Main "-login 'jdoe' -pass 'XXX'"
IF %ERRORLEVEL% EQU 2 ( echo OK )
IF %ERRORLEVEL% NEQ 2 ( echo ### CRASHED ### )

echo C2.3
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar;.\log4j-1.2.17.jar" com.company.Main "-login 'jdoe' -pass 'sup3rpaZZ'"
IF %ERRORLEVEL% EQU 0 ( echo OK )
IF %ERRORLEVEL% NEQ 0 ( echo ### CRASHED ### )

echo C3.1
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar;.\log4j-1.2.17.jar" com.company.Main "-login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'a'"
IF %ERRORLEVEL% EQU 0 ( echo OK )
IF %ERRORLEVEL% NEQ 0 ( echo ### CRASHED ### )

echo C3.2
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar;.\log4j-1.2.17.jar" com.company.Main "-login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'a.b'"
IF %ERRORLEVEL% EQU 0 ( echo OK )
IF %ERRORLEVEL% NEQ 0 ( echo ### CRASHED ### )

echo C3.3
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar;.\log4j-1.2.17.jar" com.company.Main "-login 'jdoe' -pass 'sup3rpaZZ' -role 'XXX' -res 'a.b'"
IF %ERRORLEVEL% EQU 3 ( echo OK )
IF %ERRORLEVEL% NEQ 3 ( echo ### CRASHED ### )

echo C3.4
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar;.\log4j-1.2.17.jar" com.company.Main "-login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'XXX'"
IF %ERRORLEVEL% EQU 4 ( echo OK )
IF %ERRORLEVEL% NEQ 4 ( echo ### CRASHED ### )

echo C3.5
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar;.\log4j-1.2.17.jar" com.company.Main "-login 'jdoe' -pass 'sup3rpaZZ' -role 'WRITE' -res 'a'"
IF %ERRORLEVEL% EQU 4 ( echo OK )
IF %ERRORLEVEL% NEQ 4 ( echo ### CRASHED ### )

echo C3.6
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar;.\log4j-1.2.17.jar" com.company.Main "-login 'jdoe' -pass 'sup3rpaZZ' -role 'WRITE' -res 'a.bc'"
IF %ERRORLEVEL% EQU 4 ( echo OK )
IF %ERRORLEVEL% NEQ 4 ( echo ### CRASHED ### )

echo C4.1
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar;.\log4j-1.2.17.jar" com.company.Main "-login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'a.b' -ds '2015-01-01' -de '2015-12-31' -vol '100'"
IF %ERRORLEVEL% EQU 0 ( echo OK )
IF %ERRORLEVEL% NEQ 0 ( echo ### CRASHED ### )

echo C4.2
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar" com.company.Main "-login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'a.b' -ds '01-01-2015' -de '2015-12-31' -vol '100' "
IF %ERRORLEVEL% EQU 5 ( echo OK )
IF %ERRORLEVEL% NEQ 5 ( echo ### CRASHED ### )

echo C4.3
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar" com.company.Main "-login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'a.b' -ds '2015-01-01' -de '2015-12-31' -vol 'XXX' "
IF %ERRORLEVEL% EQU 5 ( echo OK )
IF %ERRORLEVEL% NEQ 5 ( echo ### CRASHED ### )

echo C5.1
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar" com.company.Main "-login 'X' -pass 'X' -role 'READ' -res 'X' -ds '2015-01-01' -de '2015-12-31' -vol 'XXX'"
IF %ERRORLEVEL% EQU 1 ( echo OK )
IF %ERRORLEVEL% NEQ 1 ( echo ### CRASHED ### )

echo C5.2
java -cp ".\out\production\prj;.\commons-cli-1.4.jar;.\h2-1.4.197.jar" com.company.Main "-login 'X' -pass 'X' -role 'READ' -res 'X'"
IF %ERRORLEVEL% EQU 1 ( echo OK )
IF %ERRORLEVEL% NEQ 1 ( echo ### CRASHED ### )

pause