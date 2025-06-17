package com.open.pix.application.gateway;

import com.open.pix.domain.PixKey;

import java.util.List;
import java.util.UUID;

public interface FindPixKeyGateway {

    /**
     * Should find all active pix keys.
     * @param page page number.
     * @param size quantity of objects to collect.
     * @return a {@link List} of {@link PixKey} objects.
     */
    List<PixKey> findAll(int page, int size);

    /**
     * Should find all pix keys based on agency and account.
     * @param accountNumber the account number.
     * @param agencyNumber the agency number.
     * @return a {@link List} of {@link PixKey} objects.
     */
    List<PixKey> findAllByAccountNumberAndAgencyNumber(Integer accountNumber, Integer agencyNumber);

    /**
     * Should find a pix key by id.
     * @param id the pix key id.
     * @return a {@link PixKey} object.
     */
    PixKey findById(UUID id);

    /**
     * Should find a pix key by its value.
     * @param value the pix key value.
     * @return a {@link PixKey} object.
     */
    PixKey findByPixValue(String value);
}
