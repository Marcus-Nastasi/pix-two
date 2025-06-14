package com.open.pix.application.usecases;

import com.open.pix.application.exceptions.PixRegistreException;
import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.application.gateway.RegistrePixKey;
import com.open.pix.domain.PixKey;

public class RegistrePixKeyUseCase {

    private final RegistrePixKey registrePixKey;

    private final FindPixKeyGateway findPixKeyGateway;

    public RegistrePixKeyUseCase(RegistrePixKey registrePixKey, FindPixKeyGateway findPixKeyGateway) {
        this.registrePixKey = registrePixKey;
        this.findPixKeyGateway = findPixKeyGateway;
    }

    public PixKey registre(PixKey pixKey) {
        PixKey existingPixKey = findPixKeyGateway.findByPixValue(pixKey.getValue());
        if (existingPixKey != null) {
            throw new PixRegistreException("The value " + pixKey.getValue() + " is already associated");
        }
        return registrePixKey.registre(pixKey);
    }
}
