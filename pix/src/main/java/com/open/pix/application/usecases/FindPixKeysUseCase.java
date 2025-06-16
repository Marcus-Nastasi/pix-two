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

    public List<PixKey> findAll(int page, int size) {
        List<PixKey> pixKeys = findPixKeyGateway.findAll(page, size).stream()
                .filter(PixKey::isActive)
                .toList();
        if (pixKeys.isEmpty()) {
            throw new NotFoundException("Pix keys not found");
        }
        return pixKeys;
    }

    public PixKey findById(UUID id) {
        PixKey pixKey = findPixKeyGateway.findById(id);
        if (!pixKey.isActive()) {
            throw new NotFoundException("Pix key not found");
        }
        return pixKey;
    }
}
