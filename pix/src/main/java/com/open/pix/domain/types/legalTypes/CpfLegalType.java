package com.open.pix.domain.types.legalTypes;

import com.open.pix.domain.interfaces.LegalType;

import java.util.List;

public final class CpfLegalType implements LegalType {

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
        return new CpfLegalType();
    }

    @Override
    public int limit() {
        return 5;
    }

    @Override
    public String getLimitErrorMessage() {
        return "Limit of 5 keys reached for individual account, " +
                "need to delete an existing key to add more.";
    }

    @Override
    public String getBlockErrorMessage() {
        return "Your account is type PF, cannot registre a CNPJ as pix key";
    }

    @Override
    public List<String> blocks() {
        return List.of("cnpj");
    }
}
