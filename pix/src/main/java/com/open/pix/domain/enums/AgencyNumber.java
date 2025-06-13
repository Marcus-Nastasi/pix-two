package com.open.pix.domain.enums;

public record AgencyNumber(Integer value) {

    public AgencyNumber {
        if (value > 9999) {
            throw new RuntimeException("Número de agência deve ter exatamente 4 dígitos");
        }
    }
}
