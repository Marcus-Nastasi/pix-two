package com.open.pix.domain.enums.pixTypes;

import com.open.pix.domain.exceptions.PixTypeException;
import com.open.pix.domain.interfaces.PixType;

public class RandomPixType implements PixType {

    private final String value;

    public RandomPixType(String value) {
        validate(value);
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public void validate(String value) {
        if (value.length() > maxLength()) {
            throw new PixTypeException("Random key must contain the maxim of 36 alpha-numeric characters");
        }
    }

    @Override
    public int maxLength() {
        return 36;
    }
}
