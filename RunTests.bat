@echo off
cd test
call unknownOption.bat
call smokeTest.bat
call logPass.bat
call resRole.bat
call dataVol.bat
call onlyLogin.bat
call onlyRole.bat
call wrongLogin.bat
call wrongPass.bat
call wrongRes.bat
call wrongRole.bat
call wrongData.bat
call wrongVol.bat
pause