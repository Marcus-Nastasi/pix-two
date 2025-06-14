package com.open.pix.application.usecases;

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
        return registrePixKey.registre(pixKey);
    }
}
