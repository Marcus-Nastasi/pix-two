package com.open.pix.infra.gateway;

import com.open.pix.application.gateway.RegistrePixKey;
import com.open.pix.domain.PixKey;
import com.open.pix.infra.mappers.PixKeyEntityMapper;
import com.open.pix.infra.persistency.PixKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RegistrePixKeyImpl implements RegistrePixKey {

    @Autowired
    private PixKeyRepository repository;

    @Override
    public PixKey registre(PixKey pixKey) {
        return PixKeyEntityMapper.toDomain(repository.save(PixKeyEntityMapper.toEntity(pixKey)));
    }
}
