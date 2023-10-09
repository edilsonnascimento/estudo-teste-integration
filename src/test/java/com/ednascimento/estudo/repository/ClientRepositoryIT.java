package com.ednascimento.estudo.repository;

import com.ednascimento.estudo.entity.Client;
import helper.TestIntegrationHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ClientRepositoryIT extends TestIntegrationHelper {

    @Autowired
    private ClientRepository repository;

    @Test
    @Sql(value = "/data/client-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void SHOULD_Find_Client_By_Id() {

        // Given
        var idCLient = 1;
        var expected = Optional.of(new Client(idCLient,
                                               "Edilson do Nascimento",
                                                "85000-000",
                                                "Rua B; N 1"));
        // When
        var actual = repository.find(idCLient);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void WHEN_Creating_Client_which_GIVEN_Valid_Data_Must_Persist_On_DataBase() {

        // Given
        var client = new Client(
                             faker.number().randomDigit(),
                             faker.name().fullName(),
                             faker.address().zipCode(),
                            faker.address().streetAddressNumber());
        var expected = 1L;

        // When
        var actual = repository.create(client);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}