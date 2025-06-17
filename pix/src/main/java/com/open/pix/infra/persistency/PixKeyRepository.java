package com.open.pix.infra.persistency;

import com.open.pix.infra.entity.PixKeyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PixKeyRepository extends JpaRepository<PixKeyEntity, UUID>, JpaSpecificationExecutor<PixKeyEntity> {

    Page<PixKeyEntity> findAllByActiveTrue(Pageable pageable);

    PixKeyEntity findByValueAndActiveTrue(String value);

    List<PixKeyEntity> findAllByAccountNumberAndAgencyNumberAndActiveTrue(Integer accountNumber, Integer agencyNumber);

    int countByAccountNumberAndAgencyNumberAndActiveTrue(Integer accountNumber, Integer agencyNumber);
}
