package com.john.lewis.partnership.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.john.lewis.partnership.domain.*;
import com.john.lewis.partnership.service.CatalogProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CatalogProductsControllerTest {

    @Mock
    private CatalogProductsService catalogProductsService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CatalogProductsController(catalogProductsService))
                .build();
    }

    @Test
    public void shouldReturn200() throws Exception {
        Mockito.when(catalogProductsService.getProducts(any(), any(), any())).thenReturn(Optional.of(new CatalogProducts(Collections.singletonList(Product.aProduct().build()))));

        MvcResult result = mockMvc.perform(get("/product/search")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        CatalogProducts resultObject = new ObjectMapper().readValue(result.getResponse().getContentAsString(), CatalogProducts.class);

        assertThat(resultObject).isNotNull();
    }

    @Test
    public void shouldReturn404NotFound() throws Exception {
        Mockito.when(catalogProductsService.getProducts(any(), any(), any())).thenReturn(Optional.empty());

        mockMvc.perform(get("/product/search")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}