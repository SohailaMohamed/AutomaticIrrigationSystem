package com.automaicirrigationsystem.model;

import java.util.HashMap;
import java.util.Map;

public enum Crop {
    WHEAT(10.0),
    RICE(20.0),
    CORN(30.0),
    COTTON(40.0),
    JUTE(50.0),
    SUGARCANE(60.0),
    COCONUT(70.0),
    WATERMELON(80.0),
    APPLE(90.0),
    ORANGE(100.0),
    PAPAYA(110.0),
    GRAPES(120.0),
    MANGO(130.0),
    BANANA(140.0),
    STRAWBERRY(150.0),
    POMEGRANATE(160.0),
    PINEAPPLE(170.0),
    TOMATO(180.0),
    ONION(190.0),
    CARROT(200.0),
    BEETROOT(210.0),
    CABBAGE(220.0),
    POTATO(230.0),
    CUCUMBER(240.0),
    BROCCOLI(250.0),
    CAULIFLOWER(260.0),
    CELERY(270.0),
    BEANS(280.0),
    GARLIC(290.0),
    BLUEBERRY(300.0),
    CHERRY(310.0);

    private Double value;

    Crop(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    private static final Map<String, Crop> valuesMap = new HashMap<String, Crop>();
    static {
        for (Crop val : values()) {
            valuesMap.put(val.name(), val);
        }
    }
    public static Crop getValue(Double value) {
        return valuesMap.get(value);
    }
    public static boolean isValid(Double value) {
        return valuesMap.containsKey(value);
    }
}
