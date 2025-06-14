package com.open.pix.application.gateway;

import com.open.pix.domain.PixKey;

@FunctionalInterface
public interface RegistrePixKey {

    PixKey registre(PixKey pixKey);
}
