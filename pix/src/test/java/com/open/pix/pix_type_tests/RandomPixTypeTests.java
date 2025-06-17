package com.open.pix.pix_type_tests;

import com.open.pix.domain.types.pixTypes.RandomPixType;
import com.open.pix.domain.exceptions.PixTypeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RandomPixTypeTests {

    @Test
    void mustThrowWhenNull() {
        PixTypeException exception = Assertions.assertThrows(PixTypeException.class, () -> {
            new RandomPixType(null);
        });

        Assertions.assertEquals("Random key cannot be empty", exception.getMessage());
    }

    @Test
    void mustThrowWhenBlank() {
        PixTypeException exception = Assertions.assertThrows(PixTypeException.class, () -> {
            new RandomPixType("   ");
        });

        Assertions.assertEquals("Random key cannot be empty", exception.getMessage());
    }

    @Test
    void mustThrowWhenTooLong() {
        String tooLong = "A".repeat(37);
        PixTypeException ex = Assertions.assertThrows(PixTypeException.class, () -> new RandomPixType(tooLong));
        Assertions.assertTrue(ex.getMessage().contains("maxim of 36"));
    }

    @Test
    void mustThrowWhenContainsNonAlphanumeric() {
        PixTypeException exception = Assertions.assertThrows(PixTypeException.class, () -> {
            new RandomPixType("abc123!@#");
        });

        Assertions.assertEquals("Random key must contain just alphanumeric characters", exception.getMessage());
    }

    @Test
    void mustAcceptExactlyMaxLength() {
        String maxLen = "A1".repeat(18);
        Assertions.assertDoesNotThrow(() -> new RandomPixType(maxLen));
        RandomPixType pix = new RandomPixType(maxLen);
        Assertions.assertEquals(maxLen, pix.value());
    }

    @Test
    void mustAcceptShorterValues() {
        Assertions.assertDoesNotThrow(() -> {
            new RandomPixType("abcXYZ123");
        });
        RandomPixType pix = new RandomPixType("abcXYZ123");

        Assertions.assertEquals("abcXYZ123", pix.value());
    }
}
