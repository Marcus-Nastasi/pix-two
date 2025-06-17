package com.open.pix.adapters.input;

import jakarta.validation.constraints.NotNull;

public record PixKeyRegistreRequest(
        @NotNull String pixType,
        @NotNull String value,
        @NotNull String accountType,
        @NotNull Integer agencyNumber,
        @NotNull Integer accountNumber,
        @NotNull String firstName,
        String lastName
) {}
