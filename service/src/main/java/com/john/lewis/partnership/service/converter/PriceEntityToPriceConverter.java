package com.john.lewis.partnership.service.converter;

import com.john.lewis.partnership.client.entity.PriceEntity;
import com.john.lewis.partnership.domain.LabelType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Objects;

@Component
public class PriceEntityToPriceConverter {

    private final Map<String, String> currencySymbol;

    public PriceEntityToPriceConverter(Map<String, String> currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String convert(BigDecimal price, String currency) {
        String symbol = currencySymbol.get(currency);
        if(price.compareTo(BigDecimal.TEN) < 0) {
            return symbol+price.toString();
        }
        return symbol+price.intValue();
    }

    public String convertPriceLabel(LabelType labelType, PriceEntity priceEntity) {
        switch (labelType) {
            case SHOW_WAS_THEN_NOW:
                return getShowWasThenNow(priceEntity.getWas(), priceEntity.getNow(), priceEntity.getThen1(), priceEntity.getThen2(), priceEntity.getCurrency());
            case SHOW_PERC_DSCOUNT:
                return getShowPercentDiscount(priceEntity.getWas(), priceEntity.getNow(), priceEntity.getCurrency());
            default:
                return getShowWasNow(priceEntity.getWas(), priceEntity.getNow(), priceEntity.getCurrency());
        }
    }

    public BigDecimal convertPriceReduction(PriceEntity price) {
        return price.getWas().subtract(price.getNow());
    }

    private String getShowWasNow(BigDecimal was, BigDecimal now, String currency) {
        return String.format("Was %s, now %s", convert(was, currency), convert(now, currency));
    }

    private String getShowWasThenNow(BigDecimal was, BigDecimal now, BigDecimal then1, BigDecimal then2, String currency) {
        return String.format("Was %s%s, now %s", convert(was, currency), convertThenPrice(then2, then1, currency), convert(now, currency));
    }

    private String getShowPercentDiscount(BigDecimal was, BigDecimal now, String currency) {
        BigDecimal percent = (now.divide(was,2, RoundingMode.CEILING)).multiply(new BigDecimal(100));
        String discount = percent.intValue()+"%";
        return String.format("%s off - now %s", discount, convert(now, currency));
    }

    private String convertThenPrice(BigDecimal then2, BigDecimal then1, String currency) {
        if(Objects.nonNull(then2) && then2.compareTo(BigDecimal.ZERO) > 0) {
            return ", then "+ convert(then2, currency);
        }
        if(Objects.nonNull(then1) && then1.compareTo(BigDecimal.ZERO) > 0) {
            return ", then "+ convert(then1, currency);
        }
        return "";
    }
}
