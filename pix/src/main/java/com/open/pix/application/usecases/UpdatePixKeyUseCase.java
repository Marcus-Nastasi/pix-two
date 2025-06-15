package com.open.pix.application.usecases;

import com.open.pix.application.exceptions.NotFoundException;
import com.open.pix.application.exceptions.PixUpdateException;
import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.application.gateway.UpdatePixKeyGateway;
import com.open.pix.domain.PixKey;

import java.util.Optional;
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
        return Optional.ofNullable(findPixKeyGateway.findById(id))
                .orElseThrow(() -> new NotFoundException("Pix key not found for id: " + id));
    }

    public PixKey update(PixKey pixKey) {
        PixKey existingPixKey = findById(pixKey.getId());
        return updatePixKeyGateway.update(
                existingPixKey.update(pixKey.getAccountType(),
                                    pixKey.getAgencyNumber(),
                                    pixKey.getAccountNumber(),
                                    pixKey.getFirstName(),
                                    pixKey.getLastName(),
                                    existingPixKey.isActive())
        );
    }
}
