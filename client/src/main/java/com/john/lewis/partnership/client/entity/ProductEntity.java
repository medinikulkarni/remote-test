package com.john.lewis.partnership.client.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductEntity {
    private final String productId;
    private final String title;
    private final List<ColorSwatchEntity> colorSwatches;
    private final PriceEntity price;

    @JsonCreator
    public ProductEntity(@JsonProperty("productId") String productId,
                         @JsonProperty("title") String title,
                         @JsonProperty("colorSwatches") List<ColorSwatchEntity> colorSwatches,
                         @JsonProperty("price") PriceEntity price) {
        this.productId = productId;
        this.title = title;
        this.colorSwatches = colorSwatches;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public List<ColorSwatchEntity> getColorSwatches() {
        return colorSwatches;
    }

    public PriceEntity getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(colorSwatches, that.colorSwatches) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, title, colorSwatches, price);
    }

    private ProductEntity(Builder builder) {
        this.productId = builder.productId;
        this.title = builder.title;
        this.colorSwatches = builder.colorSwatches;
        this.price = builder.price;
    }

    public static Builder aProductEntity() {
        return new Builder();
    }

    public static final class Builder {
        private String productId;
        private String title;
        private List<ColorSwatchEntity> colorSwatches;
        private PriceEntity price;

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

        public Builder colorSwatches(List<ColorSwatchEntity> colorSwatches) {
            this.colorSwatches = colorSwatches;
            return this;
        }

        public Builder priceEntity(PriceEntity priceEntity) {
            this.price = priceEntity;
            return this;
        }

        public ProductEntity build() {
            return new ProductEntity(this);
        }
    }
}
