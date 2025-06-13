package com.open.pix.domain.enums.pixTypes;

import com.open.pix.domain.interfaces.PixType;

public class EmailPixType implements PixType {

    @Override
    public String value() {
        return null;
    }

    @Override
    public void validate(String value) {

    }

    @Override
    public int maxLength() {
        return 0;
    }
}
