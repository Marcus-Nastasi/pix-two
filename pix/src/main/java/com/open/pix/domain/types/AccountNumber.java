package com.open.pix.domain.types;

import com.open.pix.domain.exceptions.AccountNumberException;

public record AccountNumber(Integer value) {

    public AccountNumber {
        if (value == null) {
            throw new AccountNumberException("Account number must not be empty");
        }
        if (value > 99999999) {
            throw new AccountNumberException("Account number must have less or equal than 8 digits");
        }
    }

    public static AccountNumber of(Integer value) {
        return new AccountNumber(value);
    }
}
