package com.open.pix.pix_type_tests;

import com.open.pix.domain.types.pixTypes.EmailPixType;
import com.open.pix.domain.exceptions.PixTypeException;
import com.open.pix.domain.interfaces.PixType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class EmailPixTypeTests {

    @Test
    void mustThrowOnEmailWithMoreThan77Chars() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new EmailPixType("t".repeat(77));
        });

        String longEmail = "a".repeat(70) + "@mail.com";

        PixTypeException exception = Assertions.assertThrows(PixTypeException.class, () -> {
            new EmailPixType(longEmail);
        });

        Assertions.assertEquals("E-mail excede the max length of 77", exception.getMessage());
    }

    @Test
    void mustThrowOnEmailWithoutAt() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new EmailPixType("marcus.nastasi.com");
        });

        PixTypeException exception = Assertions.assertThrows(PixTypeException.class, () -> {
            new EmailPixType("marcus.nastasi.com");
        });

        Assertions.assertEquals("E-mail must have '@'", exception.getMessage());
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

    @Test
    void mustTrimWhitespace() {
        PixType pixType = new EmailPixType("   marcus.vinicius@gmail.com   ");
        Assertions.assertEquals("marcus.vinicius@gmail.com", pixType.value());
    }

    @Test
    void mustAcceptExactly77Characters() {
        String local = "a".repeat(66);
        String email = local + "@x.com";

        PixType pixType = new EmailPixType(email);

        Assertions.assertEquals(email, pixType.value());
    }
}
