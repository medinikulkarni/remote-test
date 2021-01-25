package com.john.lewis.partnership.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Product {
    private final String productId;
    private final String title;
    private final List<ColorSwatch> colorSwatches;
    private final String nowPrice;
    private final String priceLabel;
    private BigDecimal priceReduction;

    @JsonCreator
    public Product(@JsonProperty("productId") String productId,
                   @JsonProperty("title") String title,
                   @JsonProperty("colorSwatches") List<ColorSwatch> colorSwatches,
                   @JsonProperty("nowPrice") String nowPrice,
                   @JsonProperty("priceLabel") String priceLabel) {
        this.productId = productId;
        this.title = title;
        this.colorSwatches = colorSwatches;
        this.nowPrice = nowPrice;
        this.priceLabel = priceLabel;
    }

    private Product(Builder builder) {
        this.productId = builder.productId;
        this.title = builder.title;
        this.colorSwatches = builder.colorSwatches;
        this.nowPrice = builder.nowPrice;
        this.priceLabel = builder.priceLabel;
        this.priceReduction = builder.priceReduction;
    }

    public String getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public List<ColorSwatch> getColorSwatches() {
        return colorSwatches;
    }

    public String getNowPrice() {
        return nowPrice;
    }

    public String getPriceLabel() {
        return priceLabel;
    }

    public BigDecimal getPriceReduction() {
        return priceReduction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) &&
                Objects.equals(title, product.title) &&
                Objects.equals(colorSwatches, product.colorSwatches) &&
                Objects.equals(nowPrice, product.nowPrice) &&
                Objects.equals(priceLabel, product.priceLabel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, title, colorSwatches, nowPrice, priceLabel);
    }

    public static Builder aProduct() {
        return new Builder();
    }

    public static final class Builder {
        private String productId;
        private String title;
        private List<ColorSwatch> colorSwatches;
        private String nowPrice;
        private String priceLabel;
        private BigDecimal priceReduction;

        private Builder() {
        }

        public Builder productId(String productId) {
            this.productId = productId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder colorSwatches(List<ColorSwatch> colorSwatches) {
            this.colorSwatches = colorSwatches;
            return this;
        }

        public Builder nowPrice(String nowPrice) {
            this.nowPrice = nowPrice;
            return this;
        }

        public Builder priceLabel(String priceLabel) {
            this.priceLabel = priceLabel;
            return this;
        }

        public Builder priceReduction(BigDecimal priceReduction) {
            this.priceReduction = priceReduction;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
