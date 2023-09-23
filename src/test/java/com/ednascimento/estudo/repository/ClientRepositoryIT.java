package com.ednascimento.estudo.repository;

import com.ednascimento.estudo.dto.ClientDto;
import com.ednascimento.estudo.helper.IntegrationHelper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ClientRepositoryIT extends IntegrationHelper {

    @Autowired
    private ClientRepository repository;

    @Test
    @Sql("/data/client-insert.sql")
    void SHOULD_Find_Client_By_Id() {

        // Given
        var idCLient = 1L;
        var expected = Optional.of(new ClientDto(idCLient, "Edilson do Nascimento", "85000-000", "Rua B; N 1"));

        // When
        var actual = repository.find(idCLient);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
