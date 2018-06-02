package com.company;

public enum SysExits {
    /**
     * 0 - успех
     * 1 - неизвестный логин
     * 2 - неверный пароль
     * 3 - неизвестная роль
     * 4 - нет доступа
     * 5 - некорректная активность (невалидная дата или объем)
     * 6 - все прочие ошибки
     */
    EXIT0(0), EXIT1(1), EXIT2(2), EXIT3(3), EXIT4(4), EXIT5(5), EXIT6(6);

    private int exitCode;

    SysExits(int i) {
        exitCode = i;
    }

    public int getExitCode() {
        return exitCode;
    }
}
