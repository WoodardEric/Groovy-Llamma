package com.eric.groovyllama;

public enum RepeatMode {
    REPEAT_MODE_OFF(0),
    REPEAT_MODE_ONE(1),
    REPEAT_MODE_ALL(2);

    private final int value;
    RepeatMode(int value) {
        this.value=value;
    }

    public int getValue() {
        return value;
    }
}
