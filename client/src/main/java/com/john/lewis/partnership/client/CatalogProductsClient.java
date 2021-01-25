package com.john.lewis.partnership.client;

import com.john.lewis.partnership.client.entity.CatalogProductsEntity;
import com.john.lewis.partnership.domain.ClothingType;

import java.util.Optional;

public interface CatalogProductsClient {
    Optional<CatalogProductsEntity> getCatalogProducts(ClothingType clothingType);
}
