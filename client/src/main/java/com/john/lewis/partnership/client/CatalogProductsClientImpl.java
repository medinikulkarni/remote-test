package com.john.lewis.partnership.client;

import com.john.lewis.partnership.client.entity.CatalogProductsEntity;
import com.john.lewis.partnership.domain.ClothingType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Component
public class CatalogProductsClientImpl implements CatalogProductsClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogProductsClientImpl.class);

    private static final String EXCEPTION_MESSAGE_FORMAT = "Error retrieving catalog products for q: %s, key: %s, Status Code %s, Reason %s";

    private final Client client;
    private final String apiUrl;
    private final String key;

    public CatalogProductsClientImpl(Client client,
                                     @Value("${catalog.products.search.api.url}") String apiUrl,
                                     @Value("${catalog.products.search.api.key}") String key) {
        this.client = client;
        this.apiUrl = apiUrl;
        this.key = key;

    }

    @Override
    //@Bulkhead(name = "VimeoUpload", type = Bulkhead.Type.THREADPOOL)
    //@TimeLimiter(name = "VimeoUpload")
    //@CircuitBreaker(name = "VimeoUpload")
    public Optional<CatalogProductsEntity> getCatalogProducts(ClothingType clothingType) {
        String queryParamQ = clothingType.getText();
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(apiUrl)
                .queryParam("q", queryParamQ)
                .queryParam("key", key);
        try {
            Response response = client
                    .target(uriBuilder.build().toUri())
                    .request()
                    .get();

            Response.StatusType statusInfo = response.getStatusInfo();
            if(statusInfo.getStatusCode() != Response.Status.OK.getStatusCode()) {
                LOGGER.error(String.format(EXCEPTION_MESSAGE_FORMAT, queryParamQ, key, statusInfo.getStatusCode(), statusInfo.getReasonPhrase()));
                // TODO Not throwing exception as mentioned in the test summary instead logging and returning empty object
                return Optional.empty();
            }

            return Optional.ofNullable(response.readEntity(CatalogProductsEntity.class));
        } catch (ClientErrorException e) {
            LOGGER.error(String.format(EXCEPTION_MESSAGE_FORMAT, queryParamQ, key, e.getResponse().getStatusInfo().getStatusCode(), e.getLocalizedMessage()));
            // TODO Not throwing exception as mentioned in the test summary instead logging and returning empty object
            return Optional.empty();
        }
    }
}
