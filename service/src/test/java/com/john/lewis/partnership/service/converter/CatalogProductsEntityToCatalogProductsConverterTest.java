package com.john.lewis.partnership.service.converter;

import com.john.lewis.partnership.client.entity.ColorSwatchEntity;
import com.john.lewis.partnership.client.entity.PriceEntity;
import com.john.lewis.partnership.client.entity.ProductEntity;
import com.john.lewis.partnership.domain.LabelType;
import com.john.lewis.partnership.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogProductsEntityToCatalogProductsConverterTest {

    @Mock
    private PriceEntityToPriceConverter priceEntityToPriceConverter;
    @Mock
    private Map<String, String> rgbColors;

    private CatalogProductsEntityToCatalogProductsConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new CatalogProductsEntityToCatalogProductsConverter(priceEntityToPriceConverter, rgbColors);
    }

    @Test
    public void shouldConvertProductEntityToProduct() {
        String productId = "123";
        String title = "test";
        PriceEntity priceEntity = PriceEntity.aPriceEntity()
                .currency("GBP")
                .now(new BigDecimal(65))
                .was(new BigDecimal(165))
                .build();
        ColorSwatchEntity colorSwatchEntity = new ColorSwatchEntity("", "pink", "12345");
        ProductEntity input = ProductEntity.aProductEntity()
                .productId(productId)
                .title(title)
                .priceEntity(priceEntity)
                .colorSwatches(Collections.singletonList(colorSwatchEntity))
                .build();

        when(priceEntityToPriceConverter.convert(any(), anyString())).thenReturn("£65");
        when(priceEntityToPriceConverter.convertPriceLabel(any(), any())).thenReturn("Was £165, now £65");
        when(priceEntityToPriceConverter.convertPriceReduction(any())).thenReturn(BigDecimal.TEN);
        when(rgbColors.get(any())).thenReturn("0FFFFF");

        Product result = underTest.convert(input, LabelType.SHOW_WAS_NOW);

        assertThat(result.getProductId()).isEqualTo(productId);
        assertThat(result.getTitle()).isEqualTo(title);
        assertThat(result.getNowPrice()).isEqualTo("£65");
        assertThat(result.getPriceLabel()).isEqualTo("Was £165, now £65");
        assertThat(result.getPriceReduction()).isEqualTo(BigDecimal.TEN);
        assertThat(result.getColorSwatches().size()).isEqualTo(1);
        assertThat(result.getColorSwatches().get(0).getColor()).isEqualTo("");
        assertThat(result.getColorSwatches().get(0).getRgbColor()).isEqualTo("0FFFFF");
        assertThat(result.getColorSwatches().get(0).getSkuId()).isEqualTo("12345");
    }

    @Test
    public void shouldConvertColorSwatchEntityToColorSwatchWithNullBasicColor() {
        String productId = "123";
        String title = "test";
        PriceEntity priceEntity = PriceEntity.aPriceEntity()
                .currency("GBP")
                .now(new BigDecimal(65))
                .was(new BigDecimal(165))
                .build();
        ColorSwatchEntity colorSwatchEntity = new ColorSwatchEntity("", "", "12345");
        ProductEntity input = ProductEntity.aProductEntity()
                .productId(productId)
                .title(title)
                .priceEntity(priceEntity)
                .colorSwatches(Collections.singletonList(colorSwatchEntity))
                .build();

        when(priceEntityToPriceConverter.convert(any(), anyString())).thenReturn("£65");
        when(priceEntityToPriceConverter.convertPriceLabel(any(), any())).thenReturn("Was £165, now £65");
        when(priceEntityToPriceConverter.convertPriceReduction(any())).thenReturn(BigDecimal.TEN);
        when(rgbColors.get(any())).thenReturn("000000");

        Product result = underTest.convert(input, LabelType.SHOW_WAS_NOW);

        assertThat(result.getColorSwatches().size()).isEqualTo(1);
        assertThat(result.getColorSwatches().get(0).getRgbColor()).isEqualTo("000000");
    }

    @Test
    public void shouldConvertColorSwatchEntityToColorSwatchWithNullRgbColor() {
        String productId = "123";
        String title = "test";
        PriceEntity priceEntity = PriceEntity.aPriceEntity()
                .currency("GBP")
                .now(new BigDecimal(65))
                .was(new BigDecimal(165))
                .build();
        ColorSwatchEntity colorSwatchEntity = new ColorSwatchEntity("", "nocolor", "12345");
        ProductEntity input = ProductEntity.aProductEntity()
                .productId(productId)
                .title(title)
                .priceEntity(priceEntity)
                .colorSwatches(Collections.singletonList(colorSwatchEntity))
                .build();

        when(priceEntityToPriceConverter.convert(any(), anyString())).thenReturn("£65");
        when(priceEntityToPriceConverter.convertPriceLabel(any(), any())).thenReturn("Was £165, now £65");
        when(priceEntityToPriceConverter.convertPriceReduction(any())).thenReturn(BigDecimal.TEN);
        when(rgbColors.get(any())).thenReturn(null).thenReturn("000000");

        Product result = underTest.convert(input, LabelType.SHOW_WAS_NOW);

        assertThat(result.getColorSwatches().size()).isEqualTo(1);
        assertThat(result.getColorSwatches().get(0).getRgbColor()).isEqualTo("000000");
    }
}