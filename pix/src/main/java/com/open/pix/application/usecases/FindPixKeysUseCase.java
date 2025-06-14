package com.open.pix.application.usecases;

import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.domain.PixKey;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.UUID;

public class FindPixKeysUseCase {

    private final FindPixKeyGateway findPixKeyGateway;

    public FindPixKeysUseCase(FindPixKeyGateway findPixKeyGateway) {
        this.findPixKeyGateway = findPixKeyGateway;
    }

    public List<PixKey> findAll() {
        return findPixKeyGateway.findAll();
    }

    public PixKey findById(UUID id) throws ChangeSetPersister.NotFoundException {
        return findPixKeyGateway.findById(id);
    }

    public PixKey findByPixValue(String value) {
        return findPixKeyGateway.findByPixValue(value);
    }
}
