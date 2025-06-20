package com.open.pix.infra.gateway;

import com.open.pix.application.exceptions.NotFoundException;
import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.domain.PixKey;
import com.open.pix.infra.entity.PixKeyEntity;
import com.open.pix.infra.mappers.PixKeyEntityMapper;
import com.open.pix.infra.persistency.PixKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindPixKeyGatewayImpl implements FindPixKeyGateway {

    private final PixKeyRepository repository;

    private final PixKeyEntityMapper pixKeyEntityMapper;

    @Override
    public List<PixKey> findAll(int page, int size) {
        return repository.findAllByActiveTrue(PageRequest.of(page, size, Sort.by("creationDateTime").ascending()))
                .stream()
                .map(pixKeyEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<PixKey> findAllByAccountNumberAndAgencyNumber(Integer accountNumber, Integer agencyNumber) {
        return repository.findAllByAccountNumberAndAgencyNumberAndActiveTrue(accountNumber, agencyNumber)
                .stream()
                .map(pixKeyEntityMapper::toDomain)
                .toList();
    }

    @Override
    public PixKey findById(UUID id) {
        return pixKeyEntityMapper.toDomain(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pix key not found")));
    }

    @Override
    public PixKey findByPixValue(String value) {
        PixKeyEntity pixKey = Optional.ofNullable(repository.findByValueAndActiveTrue(value))
                .orElseThrow(() -> new NotFoundException("Pix key not found"));
        return pixKeyEntityMapper.toDomain(pixKey);
    }
}
