package com.example.first.gpio;

public enum EnPinDirection {

    out("out"),
    in("in");

    private final String dir;

    EnPinDirection(String dir) {
        this.dir = dir;
    }

    public String getDir() {
        return dir;
    }

}
