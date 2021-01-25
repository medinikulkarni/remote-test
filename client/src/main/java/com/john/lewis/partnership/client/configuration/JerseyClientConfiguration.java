package com.john.lewis.partnership.client.configuration;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.RequestEntityProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Configuration
public class JerseyClientConfiguration {

    //TODO client config can register correlationId client filter to match requests in logs
    @Bean
    public Client client() {
        return ClientBuilder.newClient(new ClientConfig());
    }
}
