package com.open.pix.domain.types.accountTypes;

import com.open.pix.domain.exceptions.AccountTypeException;
import com.open.pix.domain.interfaces.AccountType;

public class SavingsAccount implements AccountType {

    private final String type;

    public SavingsAccount(String type) {
        if (type == null || type.isEmpty() || type.isBlank()) {
            throw new AccountTypeException("");
        }
        String trimmed = type.trim().toLowerCase();
        validate(trimmed);
        this.type = trimmed;
    }

    @Override
    public String value() {
        return "poupança";
    }

    @Override
    public void validate(String value) {
        if (value.length() > 10) {
            throw new AccountTypeException("Account type must not exceed 10 characters");
        }
        if (!value().equals(value)) {
            throw new AccountTypeException("Invalid account type: " + type);
        }
    }
}
