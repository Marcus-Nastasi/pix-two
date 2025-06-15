package com.open.pix.application.gateway;

import com.open.pix.domain.PixKey;

@FunctionalInterface
public interface SavePixKeyGateway {

    PixKey save(PixKey pixKey);
}
