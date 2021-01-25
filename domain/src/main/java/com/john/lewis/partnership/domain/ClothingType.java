package com.john.lewis.partnership.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ClothingType {
    DRESSES("dresses");

    private String text;

    ClothingType(String text) {
        this.text = text;
    }

    @JsonValue
    public String getText() {
        return text;
    }
}
