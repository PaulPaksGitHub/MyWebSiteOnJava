javac -sourcepath ./src -cp "commons-cli-1.4.jar;h2-1.4.197.jar;flyway-core-5.0.7.jar" -d bin src/com/company/Main.java

jar -cfm my.jar bin/META-INF/MANIFEST.MF -C bin .