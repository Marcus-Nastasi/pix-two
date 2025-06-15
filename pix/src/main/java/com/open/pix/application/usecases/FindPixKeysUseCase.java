package com.open.pix.application.usecases;

import com.open.pix.application.exceptions.NotFoundException;
import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.domain.PixKey;

import java.util.List;
import java.util.UUID;

public class FindPixKeysUseCase {

    private final FindPixKeyGateway findPixKeyGateway;

    public FindPixKeysUseCase(FindPixKeyGateway findPixKeyGateway) {
        this.findPixKeyGateway = findPixKeyGateway;
    }

    public List<PixKey> findAll() {
        return findPixKeyGateway.findAll().stream()
                .filter(PixKey::isActive)
                .toList();
    }

    public PixKey findById(UUID id) {
        PixKey pixKey = findPixKeyGateway.findById(id);
        if (!pixKey.isActive()) {
            throw new NotFoundException("Pix key not found");
        }
        return pixKey;
    }
}
