package com.example.webfluxclient.controller;

import com.example.webfluxclient.model.Voucher;
import com.example.webfluxclient.service.VoucherService;
import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class VoucherController {

    private final VoucherService voucherService;

    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping("/voucher-webclient/{id}")
    @Timed(value = "web.client.one.voucher", description = "GET WebClient - one voucher")
    public Mono<Voucher> retrieveVoucherById(@PathVariable int id){
        return voucherService.getVoucherById(id);
    }
}
