
[![pipeline status](https://gitlab.com/kafedra/ppakseev/badges/master/pipeline.svg)](https://gitlab.com/kafedra/ppakseev/commits/master)
=============
Интрукци по сборке
-------------
javac -sourcepath ./src -cp commons-cli-1.4.jar -d bin src/com/company/Main.java
- Создает папу .\bin с фалами класса. 

jar -cfm my.jar bin/META-INF/MANIFEST.MF -C bin .
- собирает из папки .\bin файл с расширением jar. В .\bin\META-INF\MANIFEST.MF должны быть указаны путь к main и
    -classpath к commons-cli. Имя файла my.jar.

Инструкция по запуску.
-------------
java -jar my.jar
- Запускает собранное приложение (my.jar).

Инструкция по тестированию.
-------------
java -jar my.jar 'parametrs'
- Запускает приложение с указанными параметрами. 

echo $?
- Вернет в консоль exit-code работы программы.

Roadmaps (Наборы требований).
-------------
1. ДОБАВИТЬ ДЛЯ ПЕРВОГО НАБОРА
2. https://gitlab.com/kafedra/ppakseev/blob/master/RoadMap2.md