package com.open.pix.domain.exceptions;

import java.io.Serial;

public class PixKeyException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3298732739277389L;

    public PixKeyException(String message) {
        super(message);
    }
}
