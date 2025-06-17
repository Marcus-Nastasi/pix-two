package com.open.pix.infra.gateway;

import com.open.pix.application.gateway.SearchPixKeyGateway;
import com.open.pix.domain.PixKey;
import com.open.pix.infra.entity.PixKeyEntity;
import com.open.pix.infra.mappers.PixKeyEntityMapper;
import com.open.pix.infra.persistency.PixKeyRepository;
import com.open.pix.infra.specification.SearchPixKeySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SearchPixKeyGatewayImpl implements SearchPixKeyGateway {

    private final PixKeyRepository repository;

    private final PixKeyEntityMapper pixKeyEntityMapper;

    @Override
    public List<PixKey> search(String keyType,
                               Integer agency,
                               Integer account,
                               String holderName,
                               LocalDateTime inclusionDate,
                               LocalDateTime inactivationDate,
                               int page,
                               int size) {
        Specification<PixKeyEntity> spec = Specification.where(SearchPixKeySpecification.hasPixType(keyType))
                .and(SearchPixKeySpecification.hasAgencyAndAccount(agency, account))
                .and(SearchPixKeySpecification.hasHolderName(holderName))
                .and(SearchPixKeySpecification.hasInclusionDate(inclusionDate))
                .and(SearchPixKeySpecification.hasInactivationDate(inactivationDate));

        return repository.findAll(spec, PageRequest.of(page, size, Sort.by("creationDateTime").ascending()))
                .stream()
                .map(pixKeyEntityMapper::toDomain)
                .toList();
    }
}
