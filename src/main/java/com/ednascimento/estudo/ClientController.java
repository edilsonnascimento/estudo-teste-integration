package com.ednascimento.estudo;

import com.ednascimento.estudo.dto.ClientInDto;
import com.ednascimento.estudo.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Validated ClientInDto clientDto) throws URISyntaxException {
        var newIdClient = clientService.create(clientDto);
        return ResponseEntity.created(new URI("/clients/" + newIdClient)).build();
    }
}