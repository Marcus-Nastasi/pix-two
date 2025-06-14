package com.open.pix.domain.enums;

import com.open.pix.domain.exceptions.AgencyNumberException;

public record AgencyNumber(Integer value) {

    public AgencyNumber {
        if (value == null) {
            throw new AgencyNumberException("Account number must be informed");
        }
        if (value > 9999) {
            throw new AgencyNumberException("Agency number must have less or equal than 4 digits");
        }
    }
}
