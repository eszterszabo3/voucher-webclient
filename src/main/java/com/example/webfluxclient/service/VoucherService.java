package com.example.webfluxclient.service;

import com.example.webfluxclient.model.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class VoucherService {

    private final WebClient webClient;

    @Autowired
    public VoucherService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Voucher> getVoucherById(int id) {

        return webClient.get()
                .uri("/vouchers")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Voucher.class)
                .filter(voucher -> voucher.getId() == id)
                .next();

    }

}
