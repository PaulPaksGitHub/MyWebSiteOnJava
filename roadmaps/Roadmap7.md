План 7
==================

Исследовательские задачи
--------------------

1. 
    1. Вопросы: Как сериализовать Gson?
    2. Методы исследования: изучение примеров
    3. Результаты: В программе возможно сериализовать gson объект
    
2. 
    1. Вопросы: Как передать Gson через Provider?
    2. Методы исследования: Изучение документации и примеров
    3. Результаты: Gson передается в сервлеты через провайдер

Вопросы по ТЗ
--------------------

1. Для какого ресурса возвращать право пользователя?
2. Что представляет из себя список действий?

Этапы работы
--------------------
1. Написать Gson сериализатор
2. Написать провайдер для передачи сериализатора
3. Инъектить Gson провайдер в сервлеты 
4. Сервлет Юзер
    1. Get возвращает список всех пользователей 
    2. get&userid= возвращает пользователя с данным userid
    3. НЕ СЕРИАЛИЗОВАТЬ ПАРОЛЬ И СОЛЬ
5. Servlet autority
    1. get возвращает возможные права доступа
    2. get?userid= Возвращает право пользователя с данным userid
    3. get?userid= Возвращает все возможные права пользователя с данным userid
    4. НЕ СЕРИАЛИЗОВАТЬ USER
6. Servlet activity
    1. get возвращать список действий
    2. get?userid= возвращает действие с данным userid
    3. get?authorityId= возвращает действия с указанной ролью
    4. НЕ СЕРИАЛИЗОВАТЬ AUTORITY
7. Коннект в dao прокинуть при помощи DependencyInjection
    