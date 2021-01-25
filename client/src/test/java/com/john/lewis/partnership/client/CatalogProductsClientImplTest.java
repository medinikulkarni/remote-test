package com.john.lewis.partnership.client;

import com.john.lewis.partnership.client.entity.CatalogProductsEntity;
import com.john.lewis.partnership.client.entity.ProductEntity;
import com.john.lewis.partnership.domain.ClothingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import java.net.URI;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogProductsClientImplTest {

    @Mock
    protected Client client;
    @Mock
    private WebTarget webTarget;
    @Mock
    private Invocation.Builder invocationBuilder;
    @Mock
    protected Response response;
    @Mock
    protected Response.StatusType responseStatusType;

    private CatalogProductsClient underTest;

    @BeforeEach
    void setUp() {
        underTest = new CatalogProductsClientImpl(client, "localhost", "test");
        when(client.target(any(URI.class))).thenReturn(webTarget);
        when(webTarget.request()).thenReturn(invocationBuilder);
        when(invocationBuilder.get()).thenReturn(response);
        when(response.getStatusInfo()).thenReturn(responseStatusType);
    }

    @Test
    void shouldReturnCatalogProducts() {
        CatalogProductsEntity entity = new CatalogProductsEntity(Collections.singletonList(ProductEntity.aProductEntity().build()));

        when(responseStatusType.getStatusCode()).thenReturn(Response.Status.OK.getStatusCode());
        when(response.readEntity(CatalogProductsEntity.class)).thenReturn(entity);

        Optional<CatalogProductsEntity> result = underTest.getCatalogProducts(ClothingType.DRESSES);

        assertThat(result).isNotEmpty();
        assertThat(result.get().getProducts().size()).isEqualTo(entity.getProducts().size());
    }

    @Test
    void shouldReturnEmptyCatalogProductsWhenProductsNotFound() {
        when(responseStatusType.getStatusCode()).thenReturn(Response.Status.NOT_FOUND.getStatusCode());

        Optional<CatalogProductsEntity> result = underTest.getCatalogProducts(ClothingType.DRESSES);

        assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnEmptyCatalogProductsWhenClientReturns500() {
        when(responseStatusType.getStatusCode()).thenReturn(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());

        Optional<CatalogProductsEntity> result = underTest.getCatalogProducts(ClothingType.DRESSES);

        assertThat(result).isEmpty();
    }
}