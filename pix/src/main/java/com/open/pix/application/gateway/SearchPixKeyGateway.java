package com.open.pix.application.gateway;

import com.open.pix.domain.PixKey;

import java.time.LocalDateTime;
import java.util.List;

@FunctionalInterface
public interface SearchPixKeyGateway {

    List<PixKey> search(String keyType,
                        Integer agency,
                        Integer account,
                        String holderName,
                        LocalDateTime inclusionDate,
                        LocalDateTime inactivationDate);
}
