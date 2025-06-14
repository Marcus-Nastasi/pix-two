package com.open.pix.domain.enums;

import com.open.pix.domain.exceptions.AccountTypeException;

import java.util.Objects;
import java.util.Set;

public record AccountType(String type) {

    private static final Set<String> ALLOWED = Set.copyOf(Set.of("corrente", "poupança"));

    public AccountType {
        validate(type);
        type = type.trim().toLowerCase();
    }

    /** Factory methods to autocomplete */
    public static AccountType current() {
        return new AccountType("corrente");
    }

    public static AccountType savings() {
        return new AccountType("poupança");
    }

    private void validate(String value) {
        Objects.requireNonNull(value, "Account type must not be null");
        if (value.length() > 10) {
            throw new AccountTypeException("Account type must not exceed 10 characters");
        }
        String normalized = value.trim().toLowerCase();
        if (!ALLOWED.contains(normalized)) {
            throw new AccountTypeException("Invalid account type: " + type + ". Permitted values: " + ALLOWED);
        }
    }
}
