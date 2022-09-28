package com.example.webfluxclient.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@Configuration
public class AppConfiguration {

    @Value("${voucher.server.url}")
    private String voucherServerBaseUrl;

    @Bean
    public WebClient webClient() {
        String connectionProviderName = "myConnectionProvider";
        int maxConnections = 150;
        int acquireTimeout = 6_000;
        int idleTime = 15_000;
        int lifeTime = 60_000;
        int responseTimeout = 10_000;
        HttpClient httpClient = HttpClient.create(ConnectionProvider
                .builder(connectionProviderName)
                .maxConnections(maxConnections) // MAX_TOTAL_CONNECTIONS
                .maxIdleTime(Duration.ofMillis(idleTime)) // DEFAULT_KEEP_ALIVE_TIME_MILLIS
                .maxLifeTime(Duration.ofMillis(lifeTime)) // connection lifecycle
                .metrics(true)
                .pendingAcquireTimeout(Duration.ofMillis(acquireTimeout)).build()) // REQUEST_TIMEOUT
                .responseTimeout(Duration.ofMillis(responseTimeout)); //SOCKET_TIMEOUT
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(voucherServerBaseUrl)
                .build();
    }

}
