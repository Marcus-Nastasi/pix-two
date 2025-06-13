package com.open.pix.pix_type_tests;

import com.open.pix.domain.enums.pixTypes.CpfPixType;
import com.open.pix.domain.exceptions.PixTypeException;
import com.open.pix.domain.interfaces.PixType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CpfPixTypeTests {

    @Test
    void mustThrowOnCpfWithLessThan11Numbers() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new CpfPixType("123");
        });
    }

    @Test
    void mustThrowOnCpfWithMoreThan11Numbers() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new CpfPixType("1234567890123");
        });
    }

    @Test
    void mustThrowOnCpfWithNonNumeric() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new CpfPixType("1234567890123G");
        });
    }

    @Test
    void mustThrowOnInvalidCpf() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new CpfPixType("43192371287391");
        });
    }

    @Test
    void mustPassOnValidCpf() {
        Assertions.assertDoesNotThrow(() -> {
            new CpfPixType("16758031000146");
        });
    }

    @Test
    void mustCreateValidCpfPixType() {
        PixType pixType = new CpfPixType("16758031000146");
        Assertions.assertDoesNotThrow(() -> pixType);
        Assertions.assertEquals("16758031000146", pixType.value());
    }
}
