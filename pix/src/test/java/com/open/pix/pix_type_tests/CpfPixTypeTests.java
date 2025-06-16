package com.open.pix.pix_type_tests;

import com.open.pix.domain.types.pixTypes.CpfPixType;
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
            new CpfPixType("1234567890G");
        });
    }

    @Test
    void mustThrowOnInvalidCpf() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new CpfPixType("11111111111");
        });
    }

    @Test
    void mustPassOnValidCpf() {
        Assertions.assertDoesNotThrow(() -> {
            new CpfPixType("60074967088");
        });
    }

    @Test
    void mustCreateValidCpfPixType() {
        PixType pixType = new CpfPixType("60074967088");
        Assertions.assertDoesNotThrow(() -> pixType);
        Assertions.assertEquals("60074967088", pixType.value());
    }
}
