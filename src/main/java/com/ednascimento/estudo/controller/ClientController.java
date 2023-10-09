package com.ednascimento.estudo.controller;

import com.ednascimento.estudo.entity.Client;
import com.ednascimento.estudo.entity.ClientInDto;
import com.ednascimento.estudo.exception.NotFoundException;
import com.ednascimento.estudo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody ClientInDto clientInDto) {
        var id = service.create(clientInDto);
        URI location
                = URI.create("/clients/" + id);
        return ResponseEntity.created(location).build();
    }

    @GetMapping()
    public List<Client> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Client find(@PathVariable(value = "id") Integer idClient) throws NotFoundException {
        return service.find(idClient);
    }

}
