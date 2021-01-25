package com.john.lewis.partnership.client.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.john.lewis.partnership.client.entity.deserializer.PriceNowDeserializer;

import java.math.BigDecimal;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceEntity {
    @JsonDeserialize(using = PriceNowDeserializer.class)
    private final BigDecimal now;
    private final BigDecimal was;
    private final BigDecimal then1;
    private final BigDecimal then2;
    private final String currency;

    @JsonCreator
    public PriceEntity(@JsonProperty("now") BigDecimal now,
                       @JsonProperty("was") BigDecimal was,
                       @JsonProperty("then1") BigDecimal then1,
                       @JsonProperty("then2") BigDecimal then2,
                       @JsonProperty("currency") String currency) {
        this.now = now;
        this.was = was;
        this.then1 = then1;
        this.then2 = then2;
        this.currency = currency;
    }

    private PriceEntity(Builder builder) {
        this.now = builder.now;
        this.was = builder.was;
        this.then1 = builder.then1;
        this.then2 = builder.then2;
        this.currency = builder.currency;
    }

    public static Builder aPriceEntity() {
        return new Builder();
    }

    public BigDecimal getNow() {
        return now;
    }

    public BigDecimal getWas() {
        return was;
    }

    public BigDecimal getThen1() {
        return then1;
    }

    public BigDecimal getThen2() {
        return then2;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceEntity that = (PriceEntity) o;
        return Objects.equals(now, that.now) &&
                Objects.equals(was, that.was) &&
                Objects.equals(then1, that.then1) &&
                Objects.equals(then2, that.then2) &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(now, was, then1, then2, currency);
    }

    public static Builder aPrice() {
        return new Builder();
    }

    public static final class Builder {
        private BigDecimal now;
        private BigDecimal was;
        private BigDecimal then1;
        private BigDecimal then2;
        private String currency;

        private Builder() {
        }

        public Builder now(BigDecimal now) {
            this.now = now;
            return this;
        }

        public Builder was(BigDecimal was) {
            this.was = was;
            return this;
        }

        public Builder then1(BigDecimal then1) {
            this.then1 = then1;
            return this;
        }

        public Builder then2(BigDecimal then2) {
            this.then2 = then2;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public PriceEntity build() {
            return new PriceEntity(this);
        }
    }
}
