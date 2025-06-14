package com.open.pix.application.gateway;

import com.open.pix.domain.PixKey;

import java.util.List;
import java.util.UUID;

public interface FindPixKeyGateway {

    List<PixKey> findAll();

    PixKey findById(UUID id);

    PixKey findByPixValue(String value);
}
