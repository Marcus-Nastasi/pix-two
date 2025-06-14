package com.open.pix.adapters.input;

public record PixKeyRegistreRequest(
        String pixType,
        String value,
        String accountType,
        Integer agencyNumber,
        Integer accountNumber,
        String firstName,
        String lastName
) {}
