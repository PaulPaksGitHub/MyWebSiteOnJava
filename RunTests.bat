@echo off

echo =====UnknownOption=====
java -cp ".\out\production\prj;.\commons-cli-1.4.jar" com.company.Main "-egg foo"
IF %ERRORLEVEL% EQU 0 ( echo OK )
IF %ERRORLEVEL% NEQ 0 ( echo ### CRASHED ### )

echo =====smokeTest=====
java -cp ".\out\production\prj;.\commons-cli-1.4.jar" com.company.Main ""
IF %ERRORLEVEL% EQU 0 ( echo OK )
IF %ERRORLEVEL% NEQ 0 ( echo ### CRASHED ### )

echo =====LogPass=====
java -cp ".\out\production\prj;.\commons-cli-1.4.jar" com.company.Main  "-login pa -pass 12"
IF %ERRORLEVEL% EQU 0 ( echo OK )
IF %ERRORLEVEL% NEQ 0 ( echo ### CRASHED ### )

echo =====ResRole=====
java -cp ".\out\production\prj;.\commons-cli-1.4.jar" com.company.Main "-login pa -pass 12 -res A.B -role READ "
IF %ERRORLEVEL% EQU 0 ( echo OK )
IF %ERRORLEVEL% NEQ 0 ( echo ### CRASHED ### )

echo =====DataVol=====
java -cp ".\out\production\prj;.\commons-cli-1.4.jar" com.company.Main "-login pa -pass 12 -res A.B -role READ -ds 2101-12-12 -de 1233-12-03 -vol 4"
IF %ERRORLEVEL% EQU 0 ( echo OK )
IF %ERRORLEVEL% NEQ 0 ( echo ### CRASHED ### )

echo =====OnlyLogin=====
java -cp ".\out\production\prj;.\commons-cli-1.4.jar" com.company.Main "-login pa "
IF %ERRORLEVEL% EQU 2 ( echo OK )
IF %ERRORLEVEL% NEQ 2 ( echo ### CRASHED ### )

echo =====onlyRole=====
java -cp ".\out\production\prj;.\commons-cli-1.4.jar" com.company.Main "-login pa -pass 12 -role READ"
IF %ERRORLEVEL% EQU 4 ( echo OK )
IF %ERRORLEVEL% NEQ 4 ( echo ### CRASHED ### )

echo =====wrongLogin=====
java -cp ".\out\production\prj;.\commons-cli-1.4.jar" com.company.Main "-login kukuruzka -pass 12"
IF %ERRORLEVEL% EQU 1 ( echo OK )
IF %ERRORLEVEL% NEQ 1 ( echo ### CRASHED ### )

echo =====wrongPass=====
java -cp ".\out\production\prj;.\commons-cli-1.4.jar" com.company.Main "-login pa -pass 34"
IF %ERRORLEVEL% EQU 2 ( echo OK )
IF %ERRORLEVEL% NEQ 2 ( echo ### CRASHED ### )

echo =====wrongRes=====
java -cp ".\out\production\prj;.\commons-cli-1.4.jar" com.company.Main "-login pa -pass 12 -res GG -role READ"
IF %ERRORLEVEL% EQU 4 ( echo OK )
IF %ERRORLEVEL% NEQ 4 ( echo ### CRASHED ### )

echo =====wrongRole=====
java -cp ".\out\production\prj;.\commons-cli-1.4.jar" com.company.Main "-login pa -pass 12 -res A.B -role SORRY"
IF %ERRORLEVEL% EQU 3 ( echo OK )
IF %ERRORLEVEL% NEQ 3 ( echo ### CRASHED ### )

echo =====WrongData=====
java -cp ".\out\production\prj;.\commons-cli-1.4.jar" com.company.Main "-login pa -pass 12 -res A.B -role READ -ds 12-2101-12 -de 1233-12-03 -vol 4"
IF %ERRORLEVEL% EQU 5 ( echo OK )
IF %ERRORLEVEL% NEQ 5 ( echo ### CRASHED ### )

echo =====WrongVol=====
java -cp ".\out\production\prj;.\commons-cli-1.4.jar" com.company.Main "-login pa -pass 12 -res A.B -role READ -ds 2101-12-12 -de 1233-12-03 -vol 4.7 "
IF %ERRORLEVEL% EQU 5 ( echo OK )
IF %ERRORLEVEL% NEQ 5 ( echo ### CRASHED ### )

echo =====RandomPositinOfParams=====
java -cp ".\out\production\prj;.\commons-cli-1.4.jar" com.company.Main "-vol 4 -login pa -role READ -ds 2101-12-12 -pass 12 -res A.B -de 1233-12-03 "
IF %ERRORLEVEL% EQU 0 ( echo OK )
IF %ERRORLEVEL% NEQ 0 ( echo ### CRASHED ### )

pause