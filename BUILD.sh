javac -sourcepath ./src -cp commons-cli-1.4.jar -d bin src/com/company/Main.java

jar -cfm my.jar bin/META-INF/MANIFEST.MF -C bin .