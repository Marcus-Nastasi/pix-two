package com.open.pix.infra.gateway;

import com.open.pix.application.exceptions.NotFoundException;
import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.domain.PixKey;
import com.open.pix.infra.entity.PixKeyEntity;
import com.open.pix.infra.mappers.PixKeyEntityMapper;
import com.open.pix.infra.persistency.PixKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindPixKeyGatewayImpl implements FindPixKeyGateway {

    private final PixKeyRepository repository;

    @Override
    public List<PixKey> findAll() {
        return repository.findAll().stream().map(PixKeyEntityMapper::toDomain).toList();
    }

    @Override
    public List<PixKey> findAllByAccountNumberAndAgencyNumber(Integer accountNumber, Integer agencyNumber) {
        return repository.findAllByAccountNumberAndAgencyNumberAndActiveTrue(accountNumber, agencyNumber).stream()
                .map(PixKeyEntityMapper::toDomain)
                .toList();
    }

    @Override
    public PixKey findById(UUID id) {
        return PixKeyEntityMapper.toDomain(repository.findById(id).orElseThrow(() -> new NotFoundException("Pix key not found")));
    }

    @Override
    public PixKey findByPixValue(String value) {
        PixKeyEntity pixKey = repository.findByValueAndActiveTrue(value);
        if (pixKey == null) {
            return null;
        }
        return PixKeyEntityMapper.toDomain(pixKey);
    }
}
