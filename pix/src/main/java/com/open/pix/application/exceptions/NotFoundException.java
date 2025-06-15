package com.open.pix.application.exceptions;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6438624821L;

    public NotFoundException(String message) {
        super(message);
    }
}
