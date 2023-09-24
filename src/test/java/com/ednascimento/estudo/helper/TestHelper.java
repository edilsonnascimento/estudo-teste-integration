package com.ednascimento.estudo.helper;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("all")
public abstract class TestHelper {
    protected static Faker faker = new Faker();
}
