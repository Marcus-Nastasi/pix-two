package com.open.pix.application.usecases;

import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.application.gateway.SavePixKeyGateway;
import com.open.pix.domain.PixKey;

import java.util.UUID;

public class InactivatePixKeyUseCase {

    private final SavePixKeyGateway savePixKeyGateway;

    private final FindPixKeyGateway findPixKeyGateway;

    public InactivatePixKeyUseCase(SavePixKeyGateway savePixKeyGateway, FindPixKeyGateway findPixKeyGateway) {
        this.savePixKeyGateway = savePixKeyGateway;
        this.findPixKeyGateway = findPixKeyGateway;
    }

    public PixKey inactivate(UUID id) {
        PixKey pixKey = findPixKeyGateway.findById(id);
        pixKey.inactivate();
        return savePixKeyGateway.save(pixKey);
    }
}
