find -name "*.java" > sources.txt
javac -d bin -cp commons-cli-1.4.jar @sources.txt
pause