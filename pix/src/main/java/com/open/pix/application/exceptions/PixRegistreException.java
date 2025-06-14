package com.open.pix.application.exceptions;

import java.io.Serial;

public class PixRegistreException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 9017398131L;

    public PixRegistreException(String message) {
        super(message);
    }
}
