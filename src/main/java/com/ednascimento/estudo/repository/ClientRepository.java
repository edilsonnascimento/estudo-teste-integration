package com.ednascimento.estudo.repository;

import com.ednascimento.estudo.dto.ClientDto;

import java.util.Optional;

public interface ClientRepository {
    Optional<ClientDto> find(Long idClient);
}
