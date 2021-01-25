package com.john.lewis.partnership.client.entity.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.john.lewis.partnership.client.entity.PriceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceNowDeserializerTest {
    private PriceNowDeserializer underTest;

    @Mock
    private JsonParser parser;
    @Mock
    private ObjectCodec objectCodec;

    @BeforeEach
    public void setUp() {
        underTest = new PriceNowDeserializer();
    }

    @Test
    public void testPriceNow() throws IOException {
        String priceNow = "12";
        when(parser.getCodec()).thenReturn(objectCodec);
        when(objectCodec.readTree(any())).thenReturn(new ObjectMapper().readTree(priceNow));
        BigDecimal result = underTest.deserialize(parser, null);

        assertThat(result).isEqualTo(new BigDecimal(priceNow));
    }

    @Test
    public void testPriceNowAsObject() throws IOException {
        String priceNowJson = "{\"from\": \"15\"}";
        when(parser.getCodec()).thenReturn(objectCodec);
        when(objectCodec.readTree(any())).thenReturn(new ObjectMapper().readTree(priceNowJson));
        BigDecimal result = underTest.deserialize(parser, null);

        assertThat(result).isEqualTo(new BigDecimal(15));
    }
}