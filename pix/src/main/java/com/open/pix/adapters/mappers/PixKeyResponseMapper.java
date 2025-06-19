package com.open.pix.adapters.mappers;

import com.open.pix.adapters.output.PixKeyResponse;
import com.open.pix.adapters.output.PixKeyUpdateResponse;
import com.open.pix.domain.PixKey;

public class PixKeyResponseMapper {

    public static PixKeyResponse toResponse(PixKey pixKey) {
        return new PixKeyResponse(
                pixKey.getId(),
                pixKey.getPixType().type(),
                pixKey.getValue(),
                pixKey.getAccountType().value(),
                pixKey.getAgencyNumber().value(),
                pixKey.getAccountNumber().value(),
                pixKey.getFirstName(),
                pixKey.getLastName() != null
                        ? pixKey.getLastName()
                        : "",
                pixKey.getCreationDateTime().toString(),
                pixKey.getInactivationDateTime() != null
                        ? pixKey.getInactivationDateTime().toString()
                        : ""
        );
    }

    public static PixKeyUpdateResponse toUpdateResponse(PixKey pixKey) {
        return new PixKeyUpdateResponse(
                pixKey.getId(),
                pixKey.getPixType().type(),
                pixKey.getValue(),
                pixKey.getAccountType().value(),
                pixKey.getAgencyNumber().value(),
                pixKey.getAccountNumber().value(),
                pixKey.getFirstName(),
                pixKey.getLastName(),
                pixKey.getCreationDateTime()
        );
    }
}
