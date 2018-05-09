Этап 4 
=======
Исследовательские задачи
------
1. 
    1. Вопросы: Какова структура проекта Maven?
    2. Методы исследования: прочесть документацию, посмотреть примеры.
    3. Результаты: Проект соответствует структуре Maven
2. 
    1. Вопросы: Как собрать проект при момощи Maven?
    2. Методы исследования: прочитать документацию.
    3. Результаты: Проект собирается при помощи Maven.
3.  
    1. Вопросы: Как тестировать приложение пользуясь JUnit4? 
    2. Методы исследдования; чтение статей, изучение примеров
    3. Результаты: Проект тестируется при помощи JUnit4.
4. 
    1. Вопросы: Как генерировать отчет при помощи Maven? 
    2. Методы исследования: Чтение документации, просмотр примеров.
    3. Результаты: По проекту генерируются такие отчеты как: 
        - Покрытие тестами Coberture
        - Соответствие codestyle 
        - Статический анализ кода PMD
        - Статический анализ кода FindBugs

План работы
------
1. Переделать структуру проекта в соответствии с принципами Maven
2. Сборка при помощи Maven
    1. Библиотеки подставляются из repo1
    2. Зависимости упаковать в исполняемый файл
3. Протестировать приложение через JUnit4
4. Генерация отчетов
    1. Отчет по покрытию тестами Coberture
    2. Отчет на соответствие codestyle 
    3. Отчет по статическому анализу кода PMD
    4. Отчет по статическому анализу кода FindBugs
5. Обновить докуметацию.