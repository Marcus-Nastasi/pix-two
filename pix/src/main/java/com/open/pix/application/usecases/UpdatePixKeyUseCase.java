package com.open.pix.application.usecases;

import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.application.gateway.SavePixKeyGateway;
import com.open.pix.domain.PixKey;

import java.util.UUID;

public class UpdatePixKeyUseCase {

    private final SavePixKeyGateway savePixKeyGateway;

    private final FindPixKeyGateway findPixKeyGateway;

    public UpdatePixKeyUseCase(SavePixKeyGateway savePixKeyGateway,
                               FindPixKeyGateway findPixKeyGateway) {
        this.savePixKeyGateway = savePixKeyGateway;
        this.findPixKeyGateway = findPixKeyGateway;
    }

    private PixKey findById(UUID id) {
        return findPixKeyGateway.findById(id);
    }

    public PixKey update(PixKey pixKey) {
        PixKey existingPixKey = findById(pixKey.getId());
        return savePixKeyGateway.save(
                existingPixKey.update(pixKey.getAccountType(),
                                    pixKey.getAgencyNumber(),
                                    pixKey.getAccountNumber(),
                                    pixKey.getFirstName(),
                                    pixKey.getLastName())
        );
    }
}
