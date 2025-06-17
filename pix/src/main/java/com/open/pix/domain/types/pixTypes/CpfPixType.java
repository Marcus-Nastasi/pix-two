package com.open.pix.domain.types.pixTypes;

import com.open.pix.domain.exceptions.PixTypeException;
import com.open.pix.domain.interfaces.PixType;

import java.util.regex.Pattern;

public class CpfPixType implements PixType {

    private static final Pattern DIGITS = Pattern.compile("\\d+");

    private final String value;

    public CpfPixType(String value) {
        validate(value);
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public String type() {
        return "cpf";
    }

    @Override
    public void validate(String value) {
        if (value.length() != maxLength()) {
            throw new PixTypeException("The CPF have more digits than " + maxLength());
        }
        if (!isNumeric(value)) {
            throw new PixTypeException("The CPF must have exact " + maxLength() + " numeric numbers");
        }
        if (!isValidCpf(value)) {
            throw new PixTypeException("The CPF isn't valid");
        }
    }

    @Override
    public int maxLength() {
        return 11;
    }

    private boolean isNumeric(String s) {
        return s != null && DIGITS.matcher(s).matches();
    }

    /**
     * Validates a CPF number using the modulus 11 algorithm.
     *
     * <p>The CPF must be composed of 11 digits. This method first checks whether the input
     * is a sequence of identical digits (which is invalid), then calculates the two
     * verifying digits using the standard modulus 11 algorithm. If both calculated digits
     * match the last two digits of the input, the CPF is considered valid.</p>
     *
     * @param cpf the CPF number as a numeric string (only digits)
     * @return true if the CPF is valid, false otherwise
     */
    private static boolean isValidCpf(String cpf) {
        // reject equal digit sequence
        if (cpf.chars().distinct().count() == 1) {
            return false;
        }

        int[] digits = cpf.chars()
                .map(c -> c - '0')
                .toArray();

        // 1º verifying digit
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += digits[i] * (10 - i);
        }
        int remainder = sum % 11;
        int check1 = (remainder < 2) ? 0 : 11 - remainder;
        if (digits[9] != check1) {
            return false;
        }

        // 2º verifying digit
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += digits[i] * (11 - i);
        }
        remainder = sum % 11;
        int check2 = (remainder < 2) ? 0 : 11 - remainder;
        return digits[10] == check2;
    }
}
