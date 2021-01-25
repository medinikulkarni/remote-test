package com.john.lewis.partnership.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;

import javax.ws.rs.core.MediaType;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockHelper {

    private static final String CONTENT_TYPE = "Content-Type";

    public static void stubGet(String urlPath, Map<String, String> params, Object mappingObject, int statusCode, WireMockServer wireMockServer) throws JsonProcessingException {
        MappingBuilder urlBuilder = get(urlPathEqualTo(urlPath));
        if (params != null) {
            params.forEach((k, v) -> urlBuilder.withQueryParam(k, equalTo(v)));
        }

        wireMockServer.stubFor(urlBuilder
                .willReturn(aResponse().withStatus(statusCode)
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .withBody(new ObjectMapper().writeValueAsString(mappingObject))
                ));
    }
}
