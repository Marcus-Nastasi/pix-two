package com.open.pix.application.usecases;

import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.application.gateway.UpdatePixKeyGateway;
import com.open.pix.domain.PixKey;

import java.util.UUID;

public class UpdatePixKeyUseCase {

    private final UpdatePixKeyGateway updatePixKeyGateway;

    private final FindPixKeyGateway findPixKeyGateway;

    public UpdatePixKeyUseCase(UpdatePixKeyGateway updatePixKeyGateway,
                               FindPixKeyGateway findPixKeyGateway) {
        this.updatePixKeyGateway = updatePixKeyGateway;
        this.findPixKeyGateway = findPixKeyGateway;
    }

    private PixKey findById(UUID id) {
        return findPixKeyGateway.findById(id);
    }

    public PixKey update(PixKey pixKey) {
        PixKey existingPixKey = findById(pixKey.getId());
        return updatePixKeyGateway.update(
                existingPixKey.update(pixKey.getAccountType(),
                                    pixKey.getAgencyNumber(),
                                    pixKey.getAccountNumber(),
                                    pixKey.getFirstName(),
                                    pixKey.getLastName())
        );
    }
}
