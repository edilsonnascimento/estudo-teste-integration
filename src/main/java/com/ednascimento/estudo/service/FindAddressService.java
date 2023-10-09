package com.ednascimento.estudo.service;

import com.ednascimento.estudo.entity.Address;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Service
public class FindAddressService {
    public Address find(String zipCode) {

        var uri = URI.create("https://viacep.com.br/ws/" + zipCode + "/json");
        var builder = WebClient.builder();

        return builder
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Address.class)
                .block();
    }
}
