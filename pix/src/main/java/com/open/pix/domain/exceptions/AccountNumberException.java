package com.open.pix.domain.exceptions;

import java.io.Serial;

public class AccountNumberException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 789389820L;

    public AccountNumberException(String message) {
        super(message);
    }
}
