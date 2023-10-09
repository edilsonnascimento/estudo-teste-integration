package com.ednascimento.estudo.repository;

import com.ednascimento.estudo.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    Optional<Client> find(Integer idClient);
    Integer create(Client client);
    Optional<List<Client>> findAll();
}
