package com.open.pix.domain.enums;

public record AccountType(String type) {

    public AccountType {
        if (!type.matches("\\d{10}")) {
            throw new RuntimeException("Tipo da conta não pode ser maior que 10 dígitos");
        }
    }
}
