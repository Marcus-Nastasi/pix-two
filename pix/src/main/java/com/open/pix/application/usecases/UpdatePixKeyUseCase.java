package com.open.pix.application.usecases;

import com.open.pix.application.exceptions.NotFoundException;
import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.application.gateway.SavePixKeyGateway;
import com.open.pix.domain.PixKey;

import java.util.Optional;

public class UpdatePixKeyUseCase {

    private final SavePixKeyGateway savePixKeyGateway;

    private final FindPixKeyGateway findPixKeyGateway;

    public UpdatePixKeyUseCase(SavePixKeyGateway savePixKeyGateway,
                               FindPixKeyGateway findPixKeyGateway) {
        this.savePixKeyGateway = savePixKeyGateway;
        this.findPixKeyGateway = findPixKeyGateway;
    }

    /**
     * Method updates the pix key object.
     * @param pixKey the pix key to be updated.
     * @return the {@link PixKey} object updated.
     */
    public PixKey update(PixKey pixKey) {
        PixKey existingPixKey = Optional.ofNullable(findPixKeyGateway.findById(pixKey.getId()))
                .orElseThrow(() -> new NotFoundException("Pix key not found"));
        return savePixKeyGateway.save(existingPixKey.update(pixKey.getAccountType(),
                                                            pixKey.getAgencyNumber(),
                                                            pixKey.getAccountNumber(),
                                                            pixKey.getFirstName(),
                                                            pixKey.getLastName())
        );
    }
}
