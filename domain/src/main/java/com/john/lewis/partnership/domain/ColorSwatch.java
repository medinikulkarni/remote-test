package com.john.lewis.partnership.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ColorSwatch {
    private final String color;
    private final String rgbColor;
    private final String skuId;

    @JsonCreator
    public ColorSwatch(@JsonProperty("color") String color,
                       @JsonProperty("rgbColor") String rgbColor,
                       @JsonProperty("skuId") String skuId) {
        this.color = color;
        this.rgbColor = rgbColor;
        this.skuId = skuId;
    }

    private ColorSwatch(Builder builder) {
        this.color = builder.color;
        this.rgbColor = builder.rgbColor;
        this.skuId = builder.skuId;
    }

    public static Builder aColorSwatch() {
        return new Builder();
    }

    public String getColor() {
        return color;
    }

    public String getRgbColor() {
        return rgbColor;
    }

    public String getSkuId() {
        return skuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColorSwatch that = (ColorSwatch) o;
        return Objects.equals(color, that.color) &&
                Objects.equals(rgbColor, that.rgbColor) &&
                Objects.equals(skuId, that.skuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, rgbColor, skuId);
    }

    public static final class Builder {
        private String color;
        private String rgbColor;
        private String skuId;

        private Builder() {
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder rgbColor(String rgbColor) {
            this.rgbColor = rgbColor;
            return this;
        }

        public Builder skuid(String skuid) {
            this.skuId = skuid;
            return this;
        }

        public ColorSwatch build() {
            return new ColorSwatch(this);
        }
    }
}
