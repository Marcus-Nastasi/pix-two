package com.open.pix.domain.factory;

import com.open.pix.domain.PixKey;
import com.open.pix.domain.exceptions.PixTypeException;
import com.open.pix.domain.interfaces.LegalType;

import java.util.List;

public class LegalTypeFactory {

    private final List<LegalType> legalTypes;

    public LegalTypeFactory(List<LegalType> legalTypes) {
        this.legalTypes = legalTypes;
    }

    public LegalType resolve(List<PixKey> pixKeys) {
        if (pixKeys.isEmpty()) {
            return null;
        }
        LegalType legalType = null;
        for (LegalType type: legalTypes) {
            for (PixKey pixKey: pixKeys) {
                if (type.supports(pixKey.getPixType().type())) {
                    legalType = type.resolve();
                    break;
                }
            }
        }
        if (legalType == null) {
            throw new PixTypeException("null legal type");
        }
        return legalType;
    }
}
