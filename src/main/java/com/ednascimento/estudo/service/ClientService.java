package com.ednascimento.estudo.service;

import com.ednascimento.estudo.ClientController;
import com.ednascimento.estudo.dto.ClientInDto;
import com.ednascimento.estudo.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Long create(ClientInDto clientInDto) {
        return clientRepository.create(clientInDto);
    }
}