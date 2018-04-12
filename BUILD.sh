javac -d bin -cp commons-cli-1.4.jar find -name "*.java"
jar cvf project.jar -C bin .