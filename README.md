[![pipeline status](https://gitlab.com/kafedra/ppakseev/badges/master/pipeline.svg)](https://gitlab.com/kafedra/ppakseev/commits/master)
=============
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

Инструкция по тестированию.
-------------
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