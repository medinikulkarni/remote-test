package com.john.lewis.partnership.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FilterType {
    PRICE_REDUCTION("PriceReduction");

    private String text;

    FilterType(String text) {
        this.text = text;
    }

    @JsonValue
    public String getText() {
        return text;
    }
}
