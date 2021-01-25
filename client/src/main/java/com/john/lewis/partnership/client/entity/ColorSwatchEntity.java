package com.john.lewis.partnership.client.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ColorSwatchEntity {
    private String color;
    private String basicColor;
    private String skuId;

    @JsonCreator
    public ColorSwatchEntity(@JsonProperty("color") String color,
                             @JsonProperty("basicColor") String basicColor,
                             @JsonProperty("skuId") String skuId) {
        this.color = color;
        this.basicColor = basicColor;
        this.skuId = skuId;
    }

    public String getColor() {
        return color;
    }

    public String getBasicColor() {
        return basicColor;
    }

    public String getSkuid() {
        return skuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColorSwatchEntity that = (ColorSwatchEntity) o;
        return Objects.equals(color, that.color) &&
                Objects.equals(basicColor, that.basicColor) &&
                Objects.equals(skuId, that.skuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, basicColor, skuId);
    }
}
