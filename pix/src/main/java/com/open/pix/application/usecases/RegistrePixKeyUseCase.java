package com.open.pix.application.usecases;

import com.open.pix.application.gateway.RegistrePixKey;
import com.open.pix.domain.PixKey;

public class RegistrePixKeyUseCase {

    private final RegistrePixKey registrePixKey;

    public RegistrePixKeyUseCase(RegistrePixKey registrePixKey) {
        this.registrePixKey = registrePixKey;
    }

    public PixKey registre(PixKey pixKey) {
        return registrePixKey.registre(pixKey);
    }
}
