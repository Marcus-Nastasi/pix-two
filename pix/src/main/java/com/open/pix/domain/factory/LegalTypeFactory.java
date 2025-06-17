package com.open.pix.domain.factory;

import com.open.pix.domain.PixKey;
import com.open.pix.domain.interfaces.LegalType;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Factory of {@link LegalType}. Following the OC principle.
 */
public class LegalTypeFactory {

    private final List<LegalType> legalTypes;

    public LegalTypeFactory(List<LegalType> legalTypes) {
        this.legalTypes = legalTypes;
    }

    /**
     * Method responsible to resolve the pix key legal type.
     * @param pixKeys a {@link List} of {@link PixKey} to verify.
     * @return the {@link LegalType} founded or null.
     */
    public LegalType resolve(List<PixKey> pixKeys) {
        if (pixKeys.isEmpty()) {
            return null;
        }
        Set<String> pixTypes = pixKeys.stream()
                .map(p -> p.getPixType().type())
                .collect(Collectors.toSet());
        LegalType legalType = null;
        for (LegalType type: legalTypes) {
            if (pixTypes.contains(type.value())) {
                legalType = type.resolve();
                break;
            }
        }
        return legalType;
    }
}
