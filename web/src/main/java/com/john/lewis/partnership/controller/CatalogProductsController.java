package com.john.lewis.partnership.controller;

import com.john.lewis.partnership.domain.ClothingType;
import com.john.lewis.partnership.domain.FilterType;
import com.john.lewis.partnership.exception.CatalogProductSearchException;
import com.john.lewis.partnership.service.CatalogProductsService;
import com.john.lewis.partnership.domain.CatalogProducts;
import com.john.lewis.partnership.domain.LabelType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogProductsController {

    private final CatalogProductsService catalogProductsService;

    public CatalogProductsController(CatalogProductsService catalogProductsService) {
        this.catalogProductsService = catalogProductsService;
    }

    @GetMapping("/product/search")
    public CatalogProducts getProducts(@RequestParam(name = "labelType", defaultValue = "SHOW_WAS_NOW") LabelType labelType,
                                       @RequestParam(name = "type", defaultValue = "DRESSES") ClothingType clothingType,
                                       @RequestParam(name = "filter", defaultValue = "PRICE_REDUCTION") FilterType filterType) {
        return catalogProductsService.getProducts(labelType, clothingType, filterType)
                .orElseThrow(() -> new CatalogProductSearchException(String.format("Catalog products not found for Search query labelType: %s, clothingType: %s, filter: %s",
                        labelType.getText(),
                        clothingType.getText(),
                        filterType.getText())));
    }
}
