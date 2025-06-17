package com.open.pix.application.gateway;

import com.open.pix.domain.PixKey;

@FunctionalInterface
public interface SavePixKeyGateway {

    /**
     * Should save a pix key to database. If exists, save updating, else create a new registre.
     * @param pixKey the pix key object.
     * @return the {@link PixKey} saved.
     */
    PixKey save(PixKey pixKey);
}
