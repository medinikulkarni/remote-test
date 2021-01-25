package com.john.lewis.partnership.service;

import com.john.lewis.partnership.client.CatalogProductsClient;
import com.john.lewis.partnership.client.entity.CatalogProductsEntity;
import com.john.lewis.partnership.client.entity.ProductEntity;
import com.john.lewis.partnership.domain.*;
import com.john.lewis.partnership.service.converter.CatalogProductsEntityToCatalogProductsConverter;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CatalogProductsServiceImpl implements CatalogProductsService {

    private final CatalogProductsClient catalogProductsClient;
    private final CatalogProductsEntityToCatalogProductsConverter catalogProductsEntityToCatalogProductsConverter;

    public CatalogProductsServiceImpl(CatalogProductsClient catalogProductsClient,
                                      CatalogProductsEntityToCatalogProductsConverter catalogProductsEntityToCatalogProductsConverter) {
        this.catalogProductsClient = catalogProductsClient;
        this.catalogProductsEntityToCatalogProductsConverter = catalogProductsEntityToCatalogProductsConverter;
    }

    @Override
    public Optional<CatalogProducts> getProducts(LabelType labelType, ClothingType clothingType, FilterType filterType) {
        Optional<CatalogProductsEntity> catalogProducts = catalogProductsClient.getCatalogProducts(clothingType);
        if(catalogProducts.isEmpty()){
            return Optional.empty();
        }
        List<Product> productList = catalogProducts.get().getProducts()
                .stream()
                .filter(productEntity -> applyFilter(productEntity, filterType))
                .map(productEntity -> catalogProductsEntityToCatalogProductsConverter.convert(productEntity, labelType))
                .sorted(Comparator.comparing(Product::getPriceReduction).reversed())
                .collect(Collectors.toList());

        return Optional.of(new CatalogProducts(productList));
    }

    private boolean applyFilter(ProductEntity productEntity, FilterType filterType) {
        switch(filterType) {
            case PRICE_REDUCTION:
                return Objects.nonNull(productEntity.getPrice().getWas())
                        && Objects.nonNull(productEntity.getPrice().getNow())
                        && productEntity.getPrice().getWas().compareTo(productEntity.getPrice().getNow()) > 0;
        }
        return false;
    }

}
