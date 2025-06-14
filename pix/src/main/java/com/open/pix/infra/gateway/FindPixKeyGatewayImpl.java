package com.open.pix.infra.gateway;

import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.domain.PixKey;
import com.open.pix.infra.mappers.PixKeyEntityMapper;
import com.open.pix.infra.persistency.PixKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class FindPixKeyGatewayImpl implements FindPixKeyGateway {

    @Autowired
    private PixKeyRepository repository;

    @Override
    public Set<PixKey> findAll() {
        return repository.findAll().stream().map(PixKeyEntityMapper::toDomain).collect(Collectors.toSet());
    }

    @Override
    public PixKey findById(UUID id) throws ChangeSetPersister.NotFoundException {
        return PixKeyEntityMapper.toDomain(repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new));
    }
}
