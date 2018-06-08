[![pipeline status](https://travis-ci.org/PaulPaksGitHub/MyWebSiteOnJava.svg?branch=master]
=============
Приложение на heroku: https://ppakseev.herokuapp.com

Интрукци по сборке
-------------
./gradlew build
или
gradle build
- Создает папку build с отчетами и собранным приложением. 

Инструкция по запуску.
-------------
./gradlew run или gradle run 
- Запускает собранное приложение .

Инструкция по деплою приложения на heroku
-------------
git push heroku master
- Деплоит приложение на heroku

Инструкция по тестированию.
-------------
gradle appRun
- Запускает локальный сервер с web проектом

./gradlew test или gradle test
- Запускает unit тесты приложения. 

gradle jacocoTestReport
- Создает отчет по покрытию тестами

gradle findbugsMain

gradle findbugsTest
- Генерирует отчет по найденным багам.

Roadmaps (Наборы требований).
-------------
2. https://gitlab.com/kafedra/ppakseev/blob/master/Roadmaps/RoadMap2.md
3. https://gitlab.com/kafedra/ppakseev/blob/master/Roadmaps/RoadMap3.md
4. https://gitlab.com/kafedra/ppakseev/blob/master/roadmaps/Roadmap4.md
5. https://gitlab.com/kafedra/ppakseev/blob/master/roadmaps/Roadmap5.md