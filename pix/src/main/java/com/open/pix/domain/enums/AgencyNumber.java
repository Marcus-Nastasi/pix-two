package com.open.pix.domain.enums;

import lombok.Getter;

@Getter
public record AgencyNumber(String value) {

    public AgencyNumber {
        if (!value.matches("\\d{4}")) {
            throw new RuntimeException("Número de agência deve ter exatamente 4 dígitos");
        }
    }
}
