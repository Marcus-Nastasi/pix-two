package com.open.pix.domain.enums;

public record AccountNumber(Integer value) {

    public AccountNumber {
        if (value > 99999999) {
            throw new RuntimeException("Número da conta deve ter exatamente 8 dígitos");
        }
    }
}
