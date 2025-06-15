package com.open.pix.infra.gateway;

import com.open.pix.application.gateway.UpdatePixKeyGateway;
import com.open.pix.domain.PixKey;
import com.open.pix.infra.mappers.PixKeyEntityMapper;
import com.open.pix.infra.persistency.PixKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdatePixKeyGatewayImpl implements UpdatePixKeyGateway {

    private final PixKeyRepository repository;

    @Override
    public PixKey update(PixKey pixKey) {
        return PixKeyEntityMapper.toDomain(repository.save(PixKeyEntityMapper.toEntity(pixKey)));
    }
}
