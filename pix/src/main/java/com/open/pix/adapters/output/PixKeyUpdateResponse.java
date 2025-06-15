package com.open.pix.adapters.output;

import java.time.LocalDateTime;
import java.util.UUID;

public record PixKeyUpdateResponse(
        UUID id,
        String pixType,
        String keyValue,
        String accountType,
        Integer agencyNumber,
        Integer accountNumber,
        String firstName,
        String lastName,
        LocalDateTime creationDateTime
) {}
