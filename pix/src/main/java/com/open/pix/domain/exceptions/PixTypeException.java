package com.open.pix.domain.exceptions;

import java.io.Serial;

public class PixTypeException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 48927492343982L;

    public PixTypeException(String message) {
        super(message);
    }
}
