package com.open.pix.domain.types.pixTypes;

import com.open.pix.domain.exceptions.PixTypeException;
import com.open.pix.domain.interfaces.PixType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class CnpjPixType implements PixType {

    private static final Pattern DIGITS = Pattern.compile("\\d+");

    private final String value;

    public CnpjPixType(String value) {
        validate(value);
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public String type() {
        return "cnpj";
    }

    @Override
    public void validate(String value) {
        if (value.length() != maxLength()) {
            throw new PixTypeException("The CNPJ have more digits than " + maxLength());
        }
        if (!isNumeric(value)) {
            throw new PixTypeException("The CNPJ must have exact " + maxLength() + " numeric numbers");
        }
        if (!isValidCnpj(value)) {
            throw new PixTypeException("The CNPJ isn't valid");
        }
    }

    @Override
    public int maxLength() {
        return 14;
    }

    private boolean isNumeric(String s) {
        return s != null && DIGITS.matcher(s).matches();
    }

    /**
     * Validates a CNPJ number based on check digit calculation.
     *
     * <p>The CNPJ must be 14 digits long and cannot be composed of identical digits.
     * The validation follows the official modulus 11 algorithm to calculate the check digits
     * and compares them with the provided ones.</p>
     *
     * @param cnpj the CNPJ number as a numeric string (only digits)
     * @return true if the CNPJ is valid, false otherwise
     */
    private static boolean isValidCnpj(String cnpj) {
        if (cnpj.length() != 14 || cnpj.chars().distinct().count() == 1) {
            return false;
        }
        List<Integer> digits = cnpj.chars()
                .map(c -> c - '0')
                .boxed()
                .collect(Collectors.toList());

        List<Integer> base = new ArrayList<>(digits.subList(0, 12));
        int dv1 = mod11(base, new int[]{5,4,3,2,9,8,7,6,5,4,3,2});
        base.add(dv1);
        int dv2 = mod11(base, new int[]{6,5,4,3,2,9,8,7,6,5,4,3,2});
        base.add(dv2);

        return base.stream()
                .map(String::valueOf)
                .collect(Collectors.joining())
                .equals(cnpj);
    }

    /**
     * Applies the modulus 11 algorithm to a list of digits using a given set of weights.
     *
     * <p>Each digit is multiplied by its corresponding weight, the results are summed,
     * and the remainder of the division by 11 is used to determine the check digit
     * according to the standard rule.</p>
     *
     * @param digits the list of numeric digits to validate
     * @param weights the weight factors to apply in the calculation, matching the digit positions
     * @return the calculated check digit
     */
    private static int mod11(List<Integer> digits, int[] weights) {
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += digits.get(i) * weights[i];
        }
        int rest = sum % 11;
        return (rest < 2) ? 0 : 11 - rest;
    }
}
