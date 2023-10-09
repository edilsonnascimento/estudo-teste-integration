package com.ednascimento.estudo.service;

import com.ednascimento.estudo.entity.Client;
import com.ednascimento.estudo.entity.ClientInDto;
import com.ednascimento.estudo.exception.NotFoundException;
import com.ednascimento.estudo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private FindAddressService findAddresService;

    public Integer create(ClientInDto clientInDto)  {
        var address = findAddresService.find(clientInDto.zipCode());
        var client = new Client(null, clientInDto.fullName(), clientInDto.zipCode(), address.logradouro());
        return clientRepository.create(client);
    }

    public List<Client> findAll() {
         return clientRepository.findAll().orElse(Collections.EMPTY_LIST);
    }

    public Client find(Integer idClient) throws NotFoundException {
        return clientRepository.find(idClient).orElseThrow(() -> new NotFoundException("NOT EXISTS"));
    }
}