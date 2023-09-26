package com.ednascimento.estudo.dto;

public record ClientResponseDto(
        Long idClient,
        String fullName,
        String zipCode,
        String address) {
}