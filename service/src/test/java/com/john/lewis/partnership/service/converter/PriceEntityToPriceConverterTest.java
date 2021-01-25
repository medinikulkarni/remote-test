package com.john.lewis.partnership.service.converter;

import com.john.lewis.partnership.client.entity.PriceEntity;
import com.john.lewis.partnership.domain.LabelType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceEntityToPriceConverterTest {

    @Mock
    private Map<String, String> currencySymbol;

    private PriceEntityToPriceConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new PriceEntityToPriceConverter(currencySymbol);
    }

    @Test
    public void shouldConvertPriceEntityToNowPrice() {
        when(currencySymbol.get(any())).thenReturn("£");

        String result = underTest.convert(new BigDecimal(35), "£");

        assertThat(result).isEqualTo("£35");
    }

    @Test
    public void shouldConvertPriceReduction() {
        BigDecimal result = underTest.convertPriceReduction(PriceEntity.aPriceEntity()
                .was(BigDecimal.TEN)
                .now(BigDecimal.ZERO)
                .currency("GBP")
                .build());

        assertThat(result).isEqualTo(BigDecimal.TEN);
    }

    @Test
    public void shouldConvertPriceLabelWhenShowWasNow() {
        when(currencySymbol.get(any())).thenReturn("£");

        String result = underTest.convertPriceLabel(LabelType.SHOW_WAS_NOW,
                PriceEntity.aPriceEntity()
                        .was(new BigDecimal(100))
                        .now(BigDecimal.ONE)
                        .currency("GBP")
                        .build());

        assertThat(result).isEqualTo("Was £100, now £1");
    }

    @Test
    public void shouldConvertPriceLabelWhenShowPercentDiscount() {
        when(currencySymbol.get(any())).thenReturn("£");

        String result = underTest.convertPriceLabel(LabelType.SHOW_PERC_DSCOUNT,
                PriceEntity.aPriceEntity()
                        .was(new BigDecimal(100))
                        .now(new BigDecimal(50))
                        .currency("GBP")
                        .build());

        assertThat(result).isEqualTo("50% off - now £50");
    }

    @Test
    public void shouldConvertPriceLabelWhenShowWasNowThenAndThen2NotNull() {
        when(currencySymbol.get(any())).thenReturn("£");

        String result = underTest.convertPriceLabel(LabelType.SHOW_WAS_THEN_NOW,
                PriceEntity.aPriceEntity()
                        .was(new BigDecimal(100))
                        .now(new BigDecimal(50))
                        .then2(new BigDecimal(20))
                        .currency("GBP")
                        .build());

        assertThat(result).isEqualTo("Was £100, then £20, now £50");
    }

    @Test
    public void shouldConvertPriceLabelWhenShowWasNowThenAndThen2IsNullThen1NotNull() {
        when(currencySymbol.get(any())).thenReturn("£");

        String result = underTest.convertPriceLabel(LabelType.SHOW_WAS_THEN_NOW,
                PriceEntity.aPriceEntity()
                        .was(new BigDecimal(100))
                        .now(new BigDecimal(50))
                        .then1(new BigDecimal(20))
                        .currency("GBP")
                        .build());

        assertThat(result).isEqualTo("Was £100, then £20, now £50");
    }

    @Test
    public void shouldConvertPriceLabelWhenShowWasNowThenAndThen2andThen1BothNull() {
        when(currencySymbol.get(any())).thenReturn("£");

        String result = underTest.convertPriceLabel(LabelType.SHOW_WAS_THEN_NOW,
                PriceEntity.aPriceEntity()
                        .was(new BigDecimal(100))
                        .now(new BigDecimal(50))
                        .currency("GBP")
                        .build());

        assertThat(result).isEqualTo("Was £100, now £50");
    }
}