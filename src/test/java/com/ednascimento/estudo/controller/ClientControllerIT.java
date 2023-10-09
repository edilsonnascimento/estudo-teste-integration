package com.ednascimento.estudo.controller;

import com.ednascimento.estudo.entity.Client;
import helper.TestIntegrationHelper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClientControllerIT extends TestIntegrationHelper {

    private String URI_PATH = "/clients";

    @Test
    @Sql(value = "/data/client-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void GIVEN_Valid_Payload_MUST_Return_Created_Client() throws Exception {

        // Given
        var client = new Client(
                                  faker.number().randomDigit(),
                                  faker.name().fullName(),
                                  faker.address().zipCode(),
                                  faker.address().streetAddressNumber());
        var payload =
                """
                {
                    "name": "%s",
                    "ziCode": "%s",
                    "address": "%s"
                }
                """.formatted(client.fullName(), client.zipCode(), client.address());
        // When
        mockMvc
                .perform(post(URI_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
        // Then
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", URI_PATH + "/1"));
    }

}
