package com.open.pix.infra.gateway;

import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.domain.PixKey;
import com.open.pix.infra.mappers.PixKeyEntityMapper;
import com.open.pix.infra.persistency.PixKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindPixKeyGatewayImpl implements FindPixKeyGateway {

    private final PixKeyRepository repository;

    @Override
    public List<PixKey> findAll() {
        return repository.findAll().stream().map(PixKeyEntityMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public PixKey findById(UUID id) {
        return PixKeyEntityMapper.toDomain(repository.findById(id).orElseThrow());
    }

    @Override
    public PixKey findByPixValue(String value) {
        return PixKeyEntityMapper.toDomain(repository.findByValue(value));
    }
}
