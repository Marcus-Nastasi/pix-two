package com.open.pix.infra.gateway;

import com.open.pix.application.gateway.SavePixKeyGateway;
import com.open.pix.domain.PixKey;
import com.open.pix.infra.mappers.PixKeyEntityMapper;
import com.open.pix.infra.persistency.PixKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SavePixKeyGatewayImpl implements SavePixKeyGateway {

    private final PixKeyRepository repository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PixKey save(PixKey pixKey) {
        return PixKeyEntityMapper.toDomain(repository.save(PixKeyEntityMapper.toEntity(pixKey)));
    }
}
