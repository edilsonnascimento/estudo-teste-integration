package com.ednascimento.estudo.repository.impl;

import com.ednascimento.estudo.dto.ClientInDto;
import com.ednascimento.estudo.dto.ClientResponseDto;
import com.ednascimento.estudo.repository.ClientRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;
    public ClientRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Optional<ClientResponseDto> find(Long idClient) {
        var sql =
            """
            SELECT *
            FROM client
            WHERE id_client = :idClient
            """;
        var parameter = new MapSqlParameterSource()
                .addValue("idClient", idClient);
        return jdbcTemplate.query(sql, parameter, result -> {
            if (result.next()) {
                var dto = new ClientResponseDto(
                        result.getLong("id_client"),
                        result.getString("full_name"),
                        result.getString("zip_code"),
                        result.getString("address"));
                return Optional.of(dto);
            }
            return Optional.empty();
        });
    }

    @Override
    public Long create(ClientInDto dto) {
        var sql =
            """
            INSERT INTO client(full_name, zip_code, address)
            VALUES(:fullName, :zipCode, :address)
            """;
        var parameters = new MapSqlParameterSource()
                .addValue("fullName", dto.fullName())
                .addValue("zipCode", dto.zipCode())
                .addValue("address", dto.address());
        return Long.valueOf(jdbcTemplate.update(sql, parameters));
    }
}
