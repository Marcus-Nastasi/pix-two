package com.open.pix.domain.types;

import com.open.pix.domain.exceptions.AgencyNumberException;

public record AgencyNumber(Integer value) {

    public AgencyNumber {
        if (value == null) {
            throw new AgencyNumberException("Agency number must be informed");
        }
        if (value > 9999) {
            throw new AgencyNumberException("Agency number must equals or have less than 4 digits");
        }
    }

    public static AgencyNumber of(Integer value) {
        return new AgencyNumber(value);
    }
}
