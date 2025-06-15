package com.open.pix.infra.gateway;

import com.open.pix.application.gateway.CountPixKeys;
import com.open.pix.infra.persistency.PixKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CountPixKeysImpl implements CountPixKeys {

    private final PixKeyRepository repository;

    @Override
    public int countByAccountNumberAndAgencyNumber(Integer accountNumber, Integer agencyNumber) {
        return repository.countByAccountNumberAndAgencyNumber(accountNumber, agencyNumber);
    }
}
