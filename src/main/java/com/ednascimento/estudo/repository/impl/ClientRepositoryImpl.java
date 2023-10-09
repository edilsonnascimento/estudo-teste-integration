package com.ednascimento.estudo.repository.impl;

import com.ednascimento.estudo.entity.Client;
import com.ednascimento.estudo.repository.ClientRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public ClientRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Optional<Client> find(Integer idClient) {
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
                var dto = new Client(
                        result.getInt("id_client"),
                        result.getString("full_name"),
                        result.getString("zip_code"),
                        result.getString("address"));
                return Optional.of(dto);
            }
            return Optional.empty();
        });
    }

    @Override
    public Integer create(Client client) {
        var sql =
            """
            INSERT INTO client(full_name, zip_code, address)
            VALUES(:fullName, :zipCode, :address)
            """;
        var parameters = new MapSqlParameterSource()
                .addValue("fullName", client.fullName())
                .addValue("zipCode", client.zipCode())
                .addValue("address", client.address());
        jdbcTemplate.update(sql, parameters);
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", new MapSqlParameterSource(), Integer.class);
    }

    @Override
    public Optional<List<Client>> findAll() {
        var sql = "SELECT * FROM client";
        return Optional.of(
                  jdbcTemplate.query(sql, (resultSet, rowNum) ->
                    new Client(
                               resultSet.getInt("id_client"),
                               resultSet.getString("full_name"),
                               resultSet.getString("zip_code"),
                               resultSet.getString("address"))
                ));
    }

    @Override
    public void deletedAll() {
        var sql = "TRUNCATE TABLE client";
        jdbcTemplate.update(sql, new MapSqlParameterSource());
    }
}