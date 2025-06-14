package com.open.pix.domain.exceptions;

import java.io.Serial;

public class AgencyNumberException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 798237120L;

    public AgencyNumberException(String message) {
        super(message);
    }
}
