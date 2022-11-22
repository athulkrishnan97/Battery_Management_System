package com.project.batterymanagementsystem.enums;

public enum ChargeType {
    FAST("Fast"),
    AVERAGE("Average"),
    SLOW("Slow");

    public String name;
    ChargeType(String s) {
        name = s;
    }
}
