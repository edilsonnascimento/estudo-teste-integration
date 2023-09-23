package com.ednascimento.estudo.dto;

public record ClientDto(
        Long idClient,
        String fullName,
        String zipCode,
        String address) {
}