package com.open.pix.pix_type_tests;

import com.open.pix.domain.enums.pixTypes.CnpjPixType;
import com.open.pix.domain.exceptions.PixTypeException;
import com.open.pix.domain.interfaces.PixType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CnpjPixTypeTests {

    @Test
    void mustThrowOnCnpjWithLessThan14Numbers() {
        Assertions.assertThrows(PixTypeException.class, () -> {
           new CnpjPixType("123");
        });
    }

    @Test
    void mustThrowOnCnpjWithNonNumeric() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new CnpjPixType("1234567890123G");
        });
    }

    @Test
    void mustThrowOnInvalidCnpj() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            new CnpjPixType("43192371287391");
        });
    }

    @Test
    void mustPassOnValidCnpj() {
        Assertions.assertDoesNotThrow(() -> {
            new CnpjPixType("16758031000146");
        });
    }

    @Test
    void mustCreateValidCnpjPixType() {
        PixType pixType = new CnpjPixType("16758031000146");
        Assertions.assertDoesNotThrow(() -> pixType);
        Assertions.assertEquals("16758031000146", pixType.value());
    }
}
