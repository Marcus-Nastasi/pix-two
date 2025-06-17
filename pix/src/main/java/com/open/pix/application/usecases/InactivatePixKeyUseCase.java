package com.open.pix.application.usecases;

import com.open.pix.application.exceptions.NotFoundException;
import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.application.gateway.SavePixKeyGateway;
import com.open.pix.domain.PixKey;

import java.util.Optional;
import java.util.UUID;

public class InactivatePixKeyUseCase {

    private final SavePixKeyGateway savePixKeyGateway;

    private final FindPixKeyGateway findPixKeyGateway;

    public InactivatePixKeyUseCase(SavePixKeyGateway savePixKeyGateway, FindPixKeyGateway findPixKeyGateway) {
        this.savePixKeyGateway = savePixKeyGateway;
        this.findPixKeyGateway = findPixKeyGateway;
    }

    /**
     * Method to inactivate and save a pix key.
     * @param id the pix key id.
     * @return the {@link PixKey} object that was inactivated.
     */
    public PixKey inactivate(UUID id) {
        PixKey pixKey = Optional.ofNullable(findPixKeyGateway.findById(id))
                .orElseThrow(() -> new NotFoundException("Pix key not found"));
        pixKey.inactivate();
        return savePixKeyGateway.save(pixKey);
    }
}
