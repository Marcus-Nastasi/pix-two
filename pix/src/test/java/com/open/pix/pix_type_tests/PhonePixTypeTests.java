package com.open.pix.pix_type_tests;

import com.open.pix.domain.types.pixTypes.PhonePixType;
import com.open.pix.domain.exceptions.PixTypeException;
import com.open.pix.domain.interfaces.PixType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PhonePixTypeTests {

    @Test
    void mustThrowOnPhoneWithMoreThan17Chars() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new PhonePixType("+12 345 67890123456789");
        });
    }

    @Test
    void mustThrowOnPhoneWithoutInitialPlus() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new PhonePixType("12 345 789012345");
        });
    }

    @Test
    void mustThrowOnPhoneWithLetters() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new PhonePixType("+12 345 67890123I");
        });
    }

    @Test
    void mustThrowOnPhoneWithLettersOnCountryCode() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new PhonePixType("+1A 345 678901236");
        });
    }

    @Test
    void mustThrowOnPhoneWithMoreThanTwoDigitsOnCountryCode() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new PhonePixType("+123 45 678901236");
        });
    }

    @Test
    void mustThrowOnPhoneWithLessThanOneDigitsOnCountryCode() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new PhonePixType("+ 45 678901236");
        });
    }

    @Test
    void mustThrowOnPhoneWithLettersOnDdd() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new PhonePixType("+23 4F 678901236");
        });
    }

    @Test
    void mustThrowOnPhoneWithMoreThanThreeDigitsOnDdd() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new PhonePixType("+13 45456 667890");
        });
    }

    @Test
    void mustThrowOnPhoneWithMoreThanNineDigitsOnNumber() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new PhonePixType("+12 454 1234567890");
        });
    }

    @Test
    void mustThrowOnPhoneWithLessThanNineDigitsOnNumber() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new PhonePixType("+12 454 12345678");
        });
    }

    @Test
    void mustPassOnValidPhone() {
        Assertions.assertDoesNotThrow(() -> {
            new PhonePixType("+55 11 912345678");
        });
    }

    @Test
    void shouldTrimWhitespace() {
        PixType pix = new PhonePixType("  +55 11 912345678  ");
        Assertions.assertEquals("+55 11 912345678", pix.value());
    }

    @Test
    void mustAcceptMaxLengthPhone() {
        // +12 (1+2) + espaço(1) + 345 (3) + espaço(1) + 123456789 (9) = 17 chars
        String phone = "+12 345 123456789";
        Assertions.assertDoesNotThrow(() -> new PhonePixType(phone));
    }
}
