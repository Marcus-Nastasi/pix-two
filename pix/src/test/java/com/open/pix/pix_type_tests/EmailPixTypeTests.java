package com.open.pix.pix_type_tests;

import com.open.pix.domain.types.pixTypes.EmailPixType;
import com.open.pix.domain.exceptions.PixTypeException;
import com.open.pix.domain.interfaces.PixType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailPixTypeTests {

    @Test
    void mustThrowOnEmailWithMoreThan77Chars() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new EmailPixType("test test test test test test test test test " +
                    "test test test test test test test test test test " +
                    "test test test test test test test test test test test " +
                    "test test test test test test test test test test test");
        });
    }

    @Test
    void mustThrowOnEmailWithoutAt() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new EmailPixType("marcus.nastasi.com");
        });
    }

    @Test
    void mustPassOnValidEmail() {
        Assertions.assertDoesNotThrow(() -> {
            new EmailPixType("marcus.vinicius@gmail.com");
        });
    }

    @Test
    void mustCreateValidEmailPixType() {
        PixType pixType = new EmailPixType("marcus.vinicius@gmail.com");
        Assertions.assertDoesNotThrow(() -> pixType);
        Assertions.assertEquals("marcus.vinicius@gmail.com", pixType.value());
    }
}
