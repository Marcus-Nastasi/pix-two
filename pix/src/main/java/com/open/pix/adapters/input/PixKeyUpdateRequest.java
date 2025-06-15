package com.open.pix.adapters.input;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PixKeyUpdateRequest(
        @NotNull UUID id,
        @NotNull String accountType,
        @NotNull Integer agencyNumber,
        @NotNull Integer accountNumber,
        @NotNull String firstName,
        String lastName
) {}
