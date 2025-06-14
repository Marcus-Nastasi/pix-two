package com.open.pix.pix_type_tests;

import com.open.pix.domain.enums.pixTypes.RandomPixType;
import com.open.pix.domain.exceptions.PixTypeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RandomPixTypeTests {

    @Test
    void mustThrowWhenNull() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new RandomPixType(null);
        });
    }

    @Test
    void mustThrowWhenBlank() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new RandomPixType("   ");
        });
    }

    @Test
    void mustThrowWhenTooLong() {
        String tooLong = "A".repeat(37);
        PixTypeException ex = Assertions.assertThrows(PixTypeException.class,
                () -> new RandomPixType(tooLong));
        Assertions.assertTrue(ex.getMessage().contains("maxim of 36"));
    }

    @Test
    void mustThrowWhenContainsNonAlphanumeric() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new RandomPixType("abc123!@#");
        });
    }

    @Test
    void mustAcceptExactlyMaxLength() {
        String maxLen = "A1".repeat(18); // 36 chars
        Assertions.assertDoesNotThrow(() -> new RandomPixType(maxLen));
        Assertions.assertEquals(maxLen, new RandomPixType(maxLen).value());
    }

    @Test
    void mustAcceptShorterValues() {
        Assertions.assertDoesNotThrow(() -> {
            new RandomPixType("abcXYZ123");
        });
    }
}
