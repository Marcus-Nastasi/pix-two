package com.open.pix.domain.exceptions;

import java.io.Serial;

public class AccountTypeException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 293872937L;

    public AccountTypeException(String message) {
        super(message);
    }
}
