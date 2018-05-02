[![pipeline status](https://gitlab.com/kafedra/ppakseev/badges/master/pipeline.svg)](https://gitlab.com/kafedra/ppakseev/commits/master)
=============
Интрукци по сборке
-------------
javac -sourcepath ./src -implicit:class -classpath "./libraries/*" -d bin src/com/company/Main.java
- Создает папу .\bin с фалами класса. 

jar -cvfm app.jar bin/META-INF/MANIFEST.MF -C bin .
- собирает из папки .\bin файл с расширением jar. 

Инструкция по запуску.
-------------
java -cp "./libraries/*:app.jar" com.company.Main
- Запускает собранное приложение .

Инструкция по тестированию.
-------------
java -cp "./libraries/*:app.jar" com.company.Main 'parametrs'
- Запускает приложение с указанными параметрами. 

echo $?
- Вернет в консоль exit-code работы программы.

Roadmaps (Наборы требований).
-------------
2. https://gitlab.com/kafedra/ppakseev/blob/master/Roadmaps/RoadMap2.md
3. https://gitlab.com/kafedra/ppakseev/blob/master/Roadmaps/RoadMap3.md