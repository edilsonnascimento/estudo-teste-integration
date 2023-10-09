package com.ednascimento.estudo.controller;

import com.ednascimento.estudo.entity.Address;
import com.ednascimento.estudo.entity.Client;
import com.ednascimento.estudo.service.FindAddressService;
import helper.TestIntegrationHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ClientControllerIT extends TestIntegrationHelper {

    private String URI_PATH = "/clients";
    @MockBean
    private FindAddressService findAddresService;

    @Test
    @Sql(value = "/data/client-trunc.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void GIVEN_Valid_Payload_MUST_Return_Created_Client() {

        // Given
        var address = new Address(faker.address().zipCode(),
                                  faker.address().streetAddressNumber(),
                                  faker.superhero().name(),
                                  faker.address().stateAbbr(),
                                  faker.address().cityName(),
                                  faker.address().stateAbbr());
        when(findAddresService.find(anyString())).thenReturn(address);
        var client = new Client(
                faker.number().randomDigit(),
                faker.name().fullName(),
                faker.address().zipCode(),
                faker.address().streetAddressNumber());
        // When
        webTestClient
                .post()
                .uri(URI_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(client), Client.class)
        // Then
                .exchange()
                .expectStatus().isCreated()
                .expectHeader()
                .location(URI_PATH + "/1");
    }
}