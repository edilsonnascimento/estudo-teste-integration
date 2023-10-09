package com.ednascimento.estudo.entity;

public record Client(
        Integer id,
        String fullName,
        String zipCode,
        String address) {
}