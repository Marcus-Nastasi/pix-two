package com.open.pix.domain.enums;

import lombok.Getter;

@Getter
public record AccountNumber(String value) {

    public AccountNumber {
        if (!value.matches("\\d{8}")) {
            throw new RuntimeException("Número da conta deve ter exatamente 8 dígitos");
        }
    }

}
