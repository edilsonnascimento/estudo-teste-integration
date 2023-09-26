package com.ednascimento.estudo.repository;

import com.ednascimento.estudo.dto.ClientInDto;
import com.ednascimento.estudo.dto.ClientResponseDto;

import java.util.Optional;

public interface ClientRepository {
    Optional<ClientResponseDto> find(Long idClient);
    Long create(ClientInDto dto);
}
