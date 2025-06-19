package com.open.pix.application.gateway;

import com.open.pix.domain.PixKey;

import java.time.LocalDate;
import java.util.List;

@FunctionalInterface
public interface SearchPixKeyGateway {

    /**
     * Should get all pix keys based on combined filters.
     * @param keyType {@link com.open.pix.domain.interfaces.PixType}.
     * @param agency the agency number.
     * @param account the account number.
     * @param holderName the name (being first or last name).
     * @param inclusionDate the {@link LocalDate} of creation.
     * @param inactivationDate the {@link LocalDate} of inactivation.
     * @param page page's number.
     * @param size quantity of objects.
     * @return a {@link List} of {@link PixKey} objects.
     */
    List<PixKey> search(String keyType,
                        Integer agency,
                        Integer account,
                        String holderName,
                        LocalDate inclusionDate,
                        LocalDate inactivationDate,
                        int page,
                        int size);
}
