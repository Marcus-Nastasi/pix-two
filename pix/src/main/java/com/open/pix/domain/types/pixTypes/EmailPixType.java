package com.open.pix.domain.types.pixTypes;

import com.open.pix.domain.exceptions.PixTypeException;
import com.open.pix.domain.interfaces.PixType;

import java.util.regex.Pattern;

public class EmailPixType implements PixType {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private final String value;

    public EmailPixType(String value) {
        if (value == null || value.isBlank()) {
            throw new PixTypeException("E-mail cannot be null");
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
        return "email";
    }

    @Override
    public void validate(String value) {
        if (!value.contains("@")) {
            throw new PixTypeException("E-mail must have '@'");
        }
        if (value.length() > maxLength()) {
            throw new PixTypeException("E-mail excede the max length of " + maxLength());
        }
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new PixTypeException("Invalid e-mail format: " + value);
        }
    }

    @Override
    public int maxLength() {
        return 77;
    }
}
