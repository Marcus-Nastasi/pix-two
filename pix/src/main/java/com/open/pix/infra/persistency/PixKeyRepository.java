package com.open.pix.infra.persistency;

import com.open.pix.infra.entity.PixKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PixKeyRepository extends JpaRepository<PixKeyEntity, UUID> {

    PixKeyEntity findByValueAndActiveTrue(String value);

    List<PixKeyEntity> findAllByAccountNumberAndAgencyNumberAndActiveTrue(Integer accountNumber, Integer agencyNumber);

    int countByAccountNumberAndAgencyNumberAndActiveTrue(Integer accountNumber, Integer agencyNumber);
}
