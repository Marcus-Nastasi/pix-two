package com.open.pix.domain.types.pixTypes;

import com.open.pix.domain.exceptions.PixTypeException;
import com.open.pix.domain.interfaces.PixType;

import java.util.regex.Pattern;

public class PhonePixType implements PixType {

    private static final Pattern DIGITS = Pattern.compile("\\d+");

    private final String value;

    public PhonePixType(String value) {
        if (value == null || value.isBlank()) {
            throw new PixTypeException("Phone must not be empty");
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
        return "celular";
    }

    @Override
    public void validate(String value) {
        if (!value.startsWith("+")) {
            throw new PixTypeException("Phone must initialize with '+' followed by country code");
        }
        if (value.length() > maxLength()) {
            throw new PixTypeException("Phone exceed max length of " + maxLength());
        }

        // remove '+'
        String rest = value.substring(1);
        String[] parts = rest.split(" ");
        if (parts.length != 3) {
            throw new PixTypeException("Invalid format. Use: +<country-code> <DDD> <number>, ex.: +55 11 912345678");
        }

        String countryCode = parts[0];
        String ddd         = parts[1];
        String number      = parts[2];

        validateCountryCode(countryCode);
        validateDdd(ddd);
        validateNumber(number);
    }

    @Override
    public int maxLength() {
        return 17;
    }

    private void validateCountryCode(String countryCode) {
        // country: 1–2 numeric
        if (!DIGITS.matcher(countryCode).matches()) {
            throw new PixTypeException("Country code must have just numbers");
        }
        if (countryCode.isEmpty() || countryCode.length() > 2) {
            throw new PixTypeException("Country code must have between 1 and 2 digits");
        }
    }

    private void validateDdd(String ddd) {
        // DDD: 1–3 numeric
        if (!DIGITS.matcher(ddd).matches()) {
            throw new PixTypeException("DDD must have just digits");
        }
        if (ddd.isEmpty() || ddd.length() > 3) {
            throw new PixTypeException("DDD must have between 1 and 3 digits");
        }
    }

    private void validateNumber(String number) {
        // number: exact 9 digits
        if (!DIGITS.matcher(number).matches()) {
            throw new PixTypeException("Number must have just numeric digits");
        }
        if (number.length() != 9) {
            throw new PixTypeException("Number must have exact 9 digits");
        }
    }
}
