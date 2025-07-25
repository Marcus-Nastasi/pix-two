package com.open.pix.domain.types.legalTypes;

import com.open.pix.domain.interfaces.LegalType;

import java.util.List;

public final class PjLegalType implements LegalType {

    @Override
    public boolean supports(String pixType) {
        return value().equalsIgnoreCase(pixType);
    }

    @Override
    public String value() {
        return "cpf";
    }

    @Override
    public LegalType resolve() {
        return new PjLegalType();
    }

    @Override
    public int limit() {
        return 20;
    }

    @Override
    public String getLimitErrorMessage() {
        return "Limit of 20 keys reached for legal entity account," +
                " need to delete an existing key to add more.";
    }

    @Override
    public String getBlockErrorMessage() {
        return "Your account is type PJ, cannot registre a CPF as pix key";
    }

    @Override
    public List<String> blocks() {
        return List.of("cpf");
    }
}
