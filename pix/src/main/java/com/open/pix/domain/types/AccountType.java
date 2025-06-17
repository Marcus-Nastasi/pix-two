package com.open.pix.domain.types;

import com.open.pix.domain.exceptions.AccountTypeException;

import java.util.Set;

public record AccountType(String type) {

    private static final Set<String> ALLOWED = Set.copyOf(Set.of("corrente", "poupança"));

    public AccountType {
        validate(type);
        type = type.trim().toLowerCase();
    }

    private void validate(String value) {
        if (value == null || value.isEmpty() || value.isBlank()) {
            throw new AccountTypeException("Account type must not be empty");
        }
        if (value.length() > 10) {
            throw new AccountTypeException("Account type must not exceed 10 characters");
        }
        String normalized = value.trim().toLowerCase();
        if (!ALLOWED.contains(normalized)) {
            throw new AccountTypeException("Invalid account type: " + type + ". Permitted values: " + ALLOWED);
        }
    }

    public static AccountType of(String type) {
        return new AccountType(type);
    }
}
