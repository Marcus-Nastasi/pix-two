package com.open.pix.adapters.output;

import java.io.Serializable;
import java.util.UUID;

public record PixKeyResponse(
        UUID id,
        String pixType,
        String keyValue,
        String accountType,
        Integer agencyNumber,
        Integer accountNumber,
        String firstName,
        String lastName
) implements Serializable {}
