package com.open.pix.application.usecases;

import com.open.pix.application.exceptions.NotFoundException;
import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.domain.PixKey;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FindPixKeysUseCase {

    private final FindPixKeyGateway findPixKeyGateway;

    public FindPixKeysUseCase(FindPixKeyGateway findPixKeyGateway) {
        this.findPixKeyGateway = findPixKeyGateway;
    }

    /**
     * Method to find all active pix keys.
     * @param page page's number.
     * @param size quantity of objects to get.
     * @return a {@link List} of {@link PixKey}.
     * @throws NotFoundException if the list is empty.
     */
    public List<PixKey> findAll(int page, int size) {
        List<PixKey> pixKeys = findPixKeyGateway.findAll(page, size);
        if (pixKeys.isEmpty()) {
            throw new NotFoundException("Pix keys not found");
        }
        return pixKeys;
    }

    /**
     * Method to find a pix key by id.
     * @param id the pix key id.
     * @return a {@link PixKey} object.
     * @throws NotFoundException if key is null.
     */
    public PixKey findById(UUID id) {
        PixKey pixKey = Optional.ofNullable(findPixKeyGateway.findById(id))
                .orElseThrow(() -> new NotFoundException("Pix key not found"));
        if (!pixKey.isActive()) {
            throw new NotFoundException("Pix key not found");
        }
        return pixKey;
    }
}
