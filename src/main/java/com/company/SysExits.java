package com.company;

public enum SysExits {EXIT0(0), EXIT1(1), EXIT2(2), EXIT3(3), EXIT4(4), EXIT5(5), EXIT6(6);

    private int exitCode;

    SysExits(int i) {
        exitCode = i;
    }

    public int getExitCode() {
        return exitCode;
    }
}
