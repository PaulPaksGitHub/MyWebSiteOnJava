javac -sourcepath ./src -implicit:class -classpath "./libraries/*" -d bin src/com/company/Main.java

jar -cvfm app.jar bin/META-INF/MANIFEST.MF -C bin .

read -p "Please press enter to continue..." nothing


