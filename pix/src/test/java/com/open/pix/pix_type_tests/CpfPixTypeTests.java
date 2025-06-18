package com.open.pix.pix_type_tests;

import com.open.pix.domain.types.pixTypes.CpfPixType;
import com.open.pix.domain.exceptions.PixTypeException;
import com.open.pix.domain.interfaces.PixType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class CpfPixTypeTests {

    @Test
    void mustThrowOnCpfWithLessThan11Numbers() {
        PixTypeException exception = Assertions.assertThrows(PixTypeException.class, () -> {
            new CpfPixType("123");
        });

        Assertions.assertEquals("The CPF have more digits than 11", exception.getMessage());
    }

    @Test
    void mustThrowOnCpfWithMoreThan11Numbers() {
        PixTypeException exception = Assertions.assertThrows(PixTypeException.class, () -> {
            new CpfPixType("1234567890123");
        });
        Assertions.assertEquals("The CPF have more digits than 11", exception.getMessage());
    }

    @Test
    void mustThrowOnCpfWithNonNumeric() {
        PixTypeException exception = Assertions.assertThrows(PixTypeException.class, () -> {
            new CpfPixType("1234567890G");
        });
        Assertions.assertEquals("The CPF must have exact 11 numeric numbers", exception.getMessage());
    }

    @Test
    void mustThrowOnInvalidCpf() {
        PixTypeException exception = Assertions.assertThrows(PixTypeException.class, () -> {
            new CpfPixType("11111111111");
        });
        Assertions.assertEquals("The CPF isn't valid", exception.getMessage());
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

    @Test
    void mustTrimCpf() {
        PixType pixType = new CpfPixType("  60074967088  ");
        Assertions.assertEquals("60074967088", pixType.value());
    }

    @Test
    void mustRejectCpfWithDotOrDash() {
        PixTypeException exception = Assertions.assertThrows(PixTypeException.class, () -> {
            new CpfPixType("600.749.670-88");
        });
        Assertions.assertEquals("The CPF have more digits than 11", exception.getMessage());
    }
}
