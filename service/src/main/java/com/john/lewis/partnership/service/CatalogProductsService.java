package com.john.lewis.partnership.service;

import com.john.lewis.partnership.domain.CatalogProducts;
import com.john.lewis.partnership.domain.ClothingType;
import com.john.lewis.partnership.domain.FilterType;
import com.john.lewis.partnership.domain.LabelType;

import java.util.Optional;

public interface CatalogProductsService {
    Optional<CatalogProducts> getProducts(LabelType labelType, ClothingType clothingType, FilterType filterType);
}
