package com.open.pix.application.gateway;

import com.open.pix.domain.PixKey;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Set;
import java.util.UUID;

public interface FindPixKeyGateway {

    Set<PixKey> findAll();

    PixKey findById(UUID id) throws ChangeSetPersister.NotFoundException;
}
