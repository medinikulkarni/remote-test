package com.john.lewis.partnership.service.converter;

import com.john.lewis.partnership.client.entity.ColorSwatchEntity;
import com.john.lewis.partnership.client.entity.ProductEntity;
import com.john.lewis.partnership.domain.ColorSwatch;
import com.john.lewis.partnership.domain.LabelType;
import com.john.lewis.partnership.domain.Product;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CatalogProductsEntityToCatalogProductsConverter {

    private final PriceEntityToPriceConverter priceEntityToPriceConverter;
    private final Map<String, String> rgbColors;

    public CatalogProductsEntityToCatalogProductsConverter(PriceEntityToPriceConverter priceEntityToPriceConverter,
                                                           Map<String, String> rgbColors) {
        this.priceEntityToPriceConverter = priceEntityToPriceConverter;
        this.rgbColors = rgbColors;
    }

    public Product convert(ProductEntity productEntity, LabelType labelType) {
        return Product.aProduct()
                        .productId(productEntity.getProductId())
                        .title(productEntity.getTitle())
                        .colorSwatches(convert(productEntity.getColorSwatches()))
                        .nowPrice(priceEntityToPriceConverter.convert(productEntity.getPrice().getNow(), productEntity.getPrice().getCurrency()))
                        .priceLabel(priceEntityToPriceConverter.convertPriceLabel(labelType, productEntity.getPrice()))
                        .priceReduction(priceEntityToPriceConverter.convertPriceReduction(productEntity.getPrice()))
                        .build();
    }

    private List<ColorSwatch> convert(List<ColorSwatchEntity> colorSwatchEntities) {
        return colorSwatchEntities
                .stream()
                .map(colorSwatchEntity -> ColorSwatch.aColorSwatch()
                        .color(colorSwatchEntity.getColor())
                        .skuid(colorSwatchEntity.getSkuid())
                        .rgbColor(convert(colorSwatchEntity.getBasicColor()))
                        .build())
                .collect(Collectors.toList());
    }

    private String convert(String basicColor) {
        String rgbColor = null;
        if(Objects.nonNull(basicColor)) {
            rgbColor = rgbColors.get(StringUtils.trimAllWhitespace(basicColor).toLowerCase());
        }
        return Objects.isNull(rgbColor) ? rgbColors.get("white") : rgbColor;
    }
}
