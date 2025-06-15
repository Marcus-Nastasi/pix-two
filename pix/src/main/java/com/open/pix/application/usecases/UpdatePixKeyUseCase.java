package com.open.pix.application.usecases;

import com.open.pix.application.gateway.UpdatePixKeyGateway;
import com.open.pix.domain.PixKey;

public class UpdatePixKeyUseCase {

    private final UpdatePixKeyGateway updatePixKeyGateway;

    public UpdatePixKeyUseCase(UpdatePixKeyGateway updatePixKeyGateway) {
        this.updatePixKeyGateway = updatePixKeyGateway;
    }

    public PixKey update(PixKey pixKey) {
        return updatePixKeyGateway.update(pixKey);
    }
}
