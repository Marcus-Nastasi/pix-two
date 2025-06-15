package com.open.pix.application.exceptions;

import java.io.Serial;

public class PixUpdateException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 92317239173L;

    public PixUpdateException(String message) {
        super(message);
    }
}
