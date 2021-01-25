package com.john.lewis.partnership.service;

import com.john.lewis.partnership.client.CatalogProductsClient;
import com.john.lewis.partnership.client.entity.CatalogProductsEntity;
import com.john.lewis.partnership.client.entity.PriceEntity;
import com.john.lewis.partnership.client.entity.ProductEntity;
import com.john.lewis.partnership.domain.*;
import com.john.lewis.partnership.service.converter.CatalogProductsEntityToCatalogProductsConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogProductsServiceImplTest {

    @Mock
    private CatalogProductsClient catalogProductsClient;
    @Mock
    private CatalogProductsEntityToCatalogProductsConverter catalogProductsEntityToCatalogProductsConverter;

    private CatalogProductsService underTest;

    @BeforeEach
    public void setUp() {
        underTest = new CatalogProductsServiceImpl(catalogProductsClient, catalogProductsEntityToCatalogProductsConverter);
    }

    @Test
    void shouldReturnEmptyWhenSearchResultsNotFound() {
        when(catalogProductsClient.getCatalogProducts(any())).thenReturn(Optional.empty());

        Optional<CatalogProducts> result = underTest.getProducts(LabelType.SHOW_WAS_NOW, ClothingType.DRESSES, FilterType.PRICE_REDUCTION);

        assertThat(result).isEqualTo(Optional.empty());
    }

    @Test
    void shouldReturnProductListWhenFilterTypePriceReductionAndWasPriceNull() {
        CatalogProductsEntity entities = new CatalogProductsEntity(Collections.singletonList(ProductEntity.aProductEntity()
                .priceEntity(PriceEntity.aPriceEntity().now(BigDecimal.TEN).build())
                .build()));
        when(catalogProductsClient.getCatalogProducts(any())).thenReturn(Optional.of(entities));

        Optional<CatalogProducts> result = underTest.getProducts(LabelType.SHOW_WAS_NOW, ClothingType.DRESSES, FilterType.PRICE_REDUCTION);

        assertThat(result).isEqualTo(Optional.of(new CatalogProducts(Collections.emptyList())));
    }

    @Test
    void shouldReturnProductListWhenFilterTypePriceReductionAndNowPriceNull() {
        CatalogProductsEntity entities = new CatalogProductsEntity(Collections.singletonList(ProductEntity.aProductEntity()
                .priceEntity(PriceEntity.aPriceEntity().was(BigDecimal.TEN).build())
                .build()));
        when(catalogProductsClient.getCatalogProducts(any())).thenReturn(Optional.of(entities));

        Optional<CatalogProducts> result = underTest.getProducts(LabelType.SHOW_WAS_NOW, ClothingType.DRESSES, FilterType.PRICE_REDUCTION);

        assertThat(result).isEqualTo(Optional.of(new CatalogProducts(Collections.emptyList())));
    }

    @Test
    void shouldReturnProductListWhenFilterTypePriceReductionAndNowWasPriceNull() {
        CatalogProductsEntity entities = new CatalogProductsEntity(Collections.singletonList(ProductEntity.aProductEntity()
                .priceEntity(PriceEntity.aPriceEntity().build())
                .build()));
        when(catalogProductsClient.getCatalogProducts(any())).thenReturn(Optional.of(entities));

        Optional<CatalogProducts> result = underTest.getProducts(LabelType.SHOW_WAS_NOW, ClothingType.DRESSES, FilterType.PRICE_REDUCTION);

        assertThat(result).isEqualTo(Optional.of(new CatalogProducts(Collections.emptyList())));
    }

    @Test
    void shouldReturnProductListWhenFilterTypePriceReductionAndSortedDesc() {
        CatalogProductsEntity entities = new CatalogProductsEntity(Arrays.asList(ProductEntity.aProductEntity()
                .productId("123")
                .priceEntity(PriceEntity.aPriceEntity().now(BigDecimal.TEN).was(new BigDecimal(100)).build())
                .build(),
                ProductEntity.aProductEntity()
                        .productId("456")
                        .priceEntity(PriceEntity.aPriceEntity().now(BigDecimal.TEN).was(new BigDecimal(200)).build())
                        .build(),
                ProductEntity.aProductEntity()
                        .productId("789")
                        .priceEntity(PriceEntity.aPriceEntity().now(BigDecimal.TEN).was(new BigDecimal(300)).build())
                        .build()));
        Product product1 = Product.aProduct()
                .productId("789")
                .nowPrice("£290")
                .priceReduction(new BigDecimal(290))
                .build();
        Product product2 = Product.aProduct()
                .productId("456")
                .nowPrice("£190")
                .priceReduction(new BigDecimal(190))
                .build();
        Product product3 = Product.aProduct()
                .productId("123")
                .nowPrice("£90")
                .priceReduction(new BigDecimal(90))
                .build();

        when(catalogProductsClient.getCatalogProducts(any())).thenReturn(Optional.of(entities));
        when(catalogProductsEntityToCatalogProductsConverter.convert(any(), any())).thenReturn(product3).thenReturn(product2).thenReturn(product1);

        Optional<CatalogProducts> result = underTest.getProducts(LabelType.SHOW_WAS_NOW, ClothingType.DRESSES, FilterType.PRICE_REDUCTION);

        assertThat(result).isEqualTo(Optional.of(new CatalogProducts(Arrays.asList(product1, product2, product3))));
    }
}