package com.open.pix.application.usecases;

import com.open.pix.application.gateway.SearchPixKeyGateway;
import com.open.pix.domain.PixKey;

import java.time.LocalDateTime;
import java.util.List;

public class SearchPixKeysUseCase {

    private final SearchPixKeyGateway searchPixKeyGateway;

    public SearchPixKeysUseCase(SearchPixKeyGateway searchPixKeyGateway) {
        this.searchPixKeyGateway = searchPixKeyGateway;
    }

    public List<PixKey> search(String keyType,
                               Integer agency,
                               Integer account,
                               String holderName,
                               LocalDateTime inclusionDate,
                               LocalDateTime inactivationDate,
                               int page,
                               int size) {
        return searchPixKeyGateway.search(keyType, agency, account, holderName, inclusionDate, inactivationDate, page, size);
    }
}
