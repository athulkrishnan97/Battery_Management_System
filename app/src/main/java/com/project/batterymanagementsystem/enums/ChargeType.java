package com.project.batterymanagementsystem.enums;
//Enum class with group of constants here Fast representing fast charging, Average for average charging and Slow for slow charging

public enum ChargeType {
    FAST("Fast"),
    AVERAGE("Average"),
    SLOW("Slow");

    public String name;
    ChargeType(String s) {
        name = s;
    }
}
