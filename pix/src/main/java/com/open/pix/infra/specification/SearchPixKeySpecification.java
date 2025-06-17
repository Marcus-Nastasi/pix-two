package com.open.pix.infra.specification;

import com.open.pix.infra.entity.PixKeyEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SearchPixKeySpecification {

    /**
     * Creates a specification to filter pix keys by their pix type.
     *
     * @param pixType the pix type to match (e.g., "CPF", "CNPJ", "EMAIL")
     * @return a specification matching the given pix type, or null if pixType is null
     */
    public static Specification<PixKeyEntity> hasPixType(String pixType) {
        return (root, query, cb) -> pixType == null
                ? null
                : cb.equal(root.get("pixType"), pixType);
    }

    /**
     * Creates a specification to filter pix keys by agency and account numbers.
     *
     * @param agency the agency number to match
     * @param account the account number to match
     * @return a specification matching both agency and account, or null if either is null
     */
    public static Specification<PixKeyEntity> hasAgencyAndAccount(Integer agency, Integer account) {
        return (root, query, cb) -> {
            if (agency == null || account == null) return null;
            return cb.and(
                    cb.equal(root.get("agencyNumber"), agency),
                    cb.equal(root.get("accountNumber"), account)
            );
        };
    }

    /**
     * Creates a specification to filter pix keys by holder's first or last name.
     * The comparison is case-insensitive and uses a LIKE pattern match.
     *
     * @param name the name fragment to match in either first or last name
     * @return a specification that searches by name, or null if name is null
     */
    public static Specification<PixKeyEntity> hasHolderName(String name) {
        return (root, query, cb) -> name == null
                ? null
                : cb.or(
                    cb.like(cb.lower(root.get("firstName")), "%" + name.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("lastName")), "%" + name.toLowerCase() + "%")
                );
    }

    /**
     * Creates a specification to filter pix keys by their creation (inclusion) date.
     *
     * @param date the exact creation date to match
     * @return a specification matching the given creation date, or null if date is null
     */
    public static Specification<PixKeyEntity> hasInclusionDate(LocalDateTime date) {
        return (root, query, cb) -> date == null
                ? null
                : cb.equal(root.get("creationDateTime"), date);
    }

    /**
     * Creates a specification to filter pix keys by their inactivation date.
     *
     * @param date the exact inactivation date to match
     * @return a specification matching the given inactivation date, or null if date is null
     */
    public static Specification<PixKeyEntity> hasInactivationDate(LocalDateTime date) {
        return (root, query, cb) -> date == null
                ? null
                : cb.equal(root.get("inactivationDateTime"), date);
    }
}
