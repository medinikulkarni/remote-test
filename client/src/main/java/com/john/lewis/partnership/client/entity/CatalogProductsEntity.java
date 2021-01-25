package com.john.lewis.partnership.client.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CatalogProductsEntity {
    private final List<ProductEntity> products;

    @JsonCreator
    public CatalogProductsEntity(@JsonProperty("products") List<ProductEntity> products) {
        this.products = products;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatalogProductsEntity that = (CatalogProductsEntity) o;
        return Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }
}
