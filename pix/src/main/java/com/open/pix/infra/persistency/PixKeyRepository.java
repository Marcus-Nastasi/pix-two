package com.open.pix.infra.persistency;

import com.open.pix.infra.entity.PixKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PixKeyRepository extends JpaRepository<PixKeyEntity, UUID> {

    PixKeyEntity findByValue(String value);
}
