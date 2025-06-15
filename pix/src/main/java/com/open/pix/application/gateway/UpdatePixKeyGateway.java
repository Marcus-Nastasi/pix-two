package com.open.pix.application.gateway;

import com.open.pix.domain.PixKey;

@FunctionalInterface
public interface UpdatePixKeyGateway {

    PixKey update(PixKey pixKey);
}
