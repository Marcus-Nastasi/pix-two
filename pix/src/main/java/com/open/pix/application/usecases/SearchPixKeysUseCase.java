package com.open.pix.application.usecases;

import com.open.pix.application.exceptions.NotFoundException;
import com.open.pix.application.gateway.SearchPixKeyGateway;
import com.open.pix.domain.PixKey;

import java.time.LocalDateTime;
import java.util.List;

public class SearchPixKeysUseCase {

    private final SearchPixKeyGateway searchPixKeyGateway;

    public SearchPixKeysUseCase(SearchPixKeyGateway searchPixKeyGateway) {
        this.searchPixKeyGateway = searchPixKeyGateway;
    }

    /**
     * Method gets all pix keys based on combined filters.
     * @param keyType {@link com.open.pix.domain.interfaces.PixType}.
     * @param agency the agency number.
     * @param account the account number.
     * @param holderName the name (being first or last name).
     * @param inclusionDate the {@link LocalDateTime} of creation.
     * @param inactivationDate the {@link LocalDateTime} of inactivation.
     * @param page page's number.
     * @param size quantity of objects.
     * @return a {@link List} of {@link PixKey} objects.
     */
    public List<PixKey> search(String keyType,
                               Integer agency,
                               Integer account,
                               String holderName,
                               LocalDateTime inclusionDate,
                               LocalDateTime inactivationDate,
                               int page,
                               int size) {
        List<PixKey> pixKeys = searchPixKeyGateway.search(keyType,
                agency, account, holderName, inclusionDate, inactivationDate, page, size);
        if (pixKeys.isEmpty()) {
            throw new NotFoundException("Pix keys not found for this filters");
        }
        return pixKeys;
    }
}
