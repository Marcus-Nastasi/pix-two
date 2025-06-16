package com.open.pix.infra.specification;

import com.open.pix.infra.entity.PixKeyEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SearchPixKeySpecification {

    public static Specification<PixKeyEntity> hasPixType(String pixType) {
        return (root, query, cb) -> pixType == null
                ? null
                : cb.equal(root.get("pixType"), pixType);
    }

    public static Specification<PixKeyEntity> hasAgencyAndAccount(Integer agency, Integer account) {
        return (root, query, cb) -> {
            if (agency == null || account == null) return null;
            return cb.and(
                    cb.equal(root.get("agencyNumber"), agency),
                    cb.equal(root.get("accountNumber"), account)
            );
        };
    }

    public static Specification<PixKeyEntity> hasHolderName(String name) {
        return (root, query, cb) -> name == null
                ? null
                : cb.or(
                    cb.like(cb.lower(root.get("firstName")), "%" + name.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("lastName")), "%" + name.toLowerCase() + "%")
                );
    }

    public static Specification<PixKeyEntity> hasInclusionDate(LocalDateTime date) {
        return (root, query, cb) -> date == null
                ? null
                : cb.equal(root.get("creationDateTime"), date);
    }

    public static Specification<PixKeyEntity> hasInactivationDate(LocalDateTime date) {
        return (root, query, cb) -> date == null
                ? null
                : cb.equal(root.get("inactivationDateTime"), date);
    }
}
