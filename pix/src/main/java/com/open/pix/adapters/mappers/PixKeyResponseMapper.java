package com.open.pix.adapters.mappers;

import com.open.pix.adapters.output.PixKeyResponse;
import com.open.pix.domain.PixKey;

public class PixKeyResponseMapper {

    public static PixKeyResponse toResponse(PixKey pixKey) {
        return new PixKeyResponse(
                pixKey.getId(),
                pixKey.getPixType().type(),
                pixKey.getValue(),
                pixKey.getAccountType().type(),
                pixKey.getAgencyNumber().value(),
                pixKey.getAccountNumber().value(),
                pixKey.getFirstName(),
                pixKey.getLastName()
        );
    }
}
