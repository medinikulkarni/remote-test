package com.john.lewis.partnership.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum LabelType {
    SHOW_WAS_NOW("ShowWasNow"),
    SHOW_WAS_THEN_NOW("ShowWasThenNow"),
    SHOW_PERC_DSCOUNT("ShowPercDscount");

    private final String text;

    LabelType(String text) {
        this.text = text;
    }

    @JsonValue
    public String getText() {
        return text;
    }
}
