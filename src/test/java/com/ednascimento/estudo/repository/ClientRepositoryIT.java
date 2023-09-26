package com.ednascimento.estudo.repository;

import com.ednascimento.estudo.dto.ClientInDto;
import com.ednascimento.estudo.dto.ClientResponseDto;
import com.ednascimento.estudo.helper.IntegrationHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ClientRepositoryIT extends IntegrationHelper {

    @Autowired
    private ClientRepository repository;

    @Test
    @Sql(value = "/data/client-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/data/client-trunc.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void SHOULD_Find_Client_By_Id() {

        // Given
        var idCLient = 1L;
        var expected = Optional.of(new ClientResponseDto(idCLient, "Edilson do Nascimento", "85000-000", "Rua B; N 1"));

        // When
        var actual = repository.find(idCLient);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void WHEN_Creating_Client_which_GIVEN_Valid_Data_Must_Persist_On_DataBase() {

        // Given
        var dto = new ClientInDto(faker.name().fullName(),
                                faker.address().zipCode(),
                                faker.address().streetAddressNumber());
        var expected = 1L;

        // When
        var actual = repository.create(dto);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}