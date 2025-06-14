package com.open.pix.domain.enums.pixTypes;

import com.open.pix.domain.exceptions.PixTypeException;
import com.open.pix.domain.interfaces.PixType;

import java.util.regex.Pattern;

public class RandomPixType implements PixType {

    private static final Pattern ALPHANUMERIC = Pattern.compile("^[A-Za-z0-9]+$");

    private final String value;

    public RandomPixType(String value) {
        if (value == null || value.isBlank()) {
            throw new PixTypeException("Random key cannot be empty");
        }
        String trimmed = value.trim();
        validate(trimmed);
        this.value = trimmed;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public String type() {
        return "aleatorio";
    }

    @Override
    public void validate(String value) {
        if (value.length() > maxLength()) {
            throw new PixTypeException("Random key must contain the maxim of 36 alphanumeric characters");
        }
        if (!ALPHANUMERIC.matcher(value).matches()) {
            throw new PixTypeException("Random key must contain just alphanumeric characters");
        }
    }

    @Override
    public int maxLength() {
        return 36;
    }
}
