package com.john.lewis.partnership.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.Slf4jNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.john.lewis.partnership.TestApplication;
import com.john.lewis.partnership.client.entity.CatalogProductsEntity;
import com.john.lewis.partnership.client.entity.ProductEntity;
import com.john.lewis.partnership.domain.CatalogProducts;
import com.john.lewis.partnership.domain.Product;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.Response.Status.OK;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
@TestPropertySource(value = "/application.properties",
        properties = {
                "catalog.products.search.api.url=https://api.johnlewis.com/search/api/rest/v2/catalog/products/search/keyword",
                "catalog.products.search.api.key=AIzaSyDD_6O5gUgC4tRW5f9kxC0_76XRC8W7_mI",
                "wiremock.port=8080"
        })
@ExtendWith({SpringExtension.class})
class ApplicationTests {

    private static WireMockServer wireMockServer;

    @Value("${wiremock.port}")
    private int wiremock;

    @LocalServerPort
    protected int port;

    @BeforeEach
    void setupWiremock() {
        if (Objects.isNull(wireMockServer)) {
            wireMockServer = new WireMockServer(new WireMockConfiguration()
                    .notifier(new Slf4jNotifier(true))
                    .port(wiremock));

            wireMockServer.setGlobalFixedDelay(0);

            RestAssured.port = port;
            RestAssured.config = RestAssured.config();

            wireMockServer.start();
        }
    }

    @AfterEach
    void tearDownWireMock() {
        wireMockServer.resetAll();
    }

    @AfterAll
    static void tearDownSuite() {
        wireMockServer = null;
    }

    @Test
    @DisplayName("should return 200 with catalog products for dresses sorted by price reduction")
    void shouldReturn200WhenValidPropertyIdProvided() throws JsonProcessingException {
        CatalogProducts expectedCatalogProduct = new CatalogProducts(Collections.singletonList(Product.aProduct().build()));

        CatalogProductsEntity response = new CatalogProductsEntity(Collections.singletonList(ProductEntity.aProductEntity().build()));
        Map<String, String> params = Map.of("q", "dresses", "key", "key");
        WireMockHelper.stubGet("/",
                params,
                response,
                OK.getStatusCode(),
                wireMockServer);

        CatalogProducts result = given()
                .when().get("/product/search")
                .then()
                .statusCode(OK.getStatusCode())
                .extract().as(CatalogProducts.class);

        assertThat(result).isEqualToComparingFieldByField(expectedCatalogProduct);
    }
}
