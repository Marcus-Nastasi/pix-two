package com.open.pix.pix_type_tests;

import com.open.pix.domain.exceptions.PixKeyException;
import com.open.pix.domain.types.pixTypes.CnpjPixType;
import com.open.pix.domain.exceptions.PixTypeException;
import com.open.pix.domain.interfaces.PixType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class CnpjPixTypeTests {

    @Test
    void mustThrowOnCnpjWithLessThan14Numbers() {
        PixKeyException ex = Assertions.assertThrows(PixKeyException.class, () -> {
            new CnpjPixType("123");
        });
        Assertions.assertEquals("The CNPJ have more digits than 14", ex.getMessage());
    }

    @Test
    void mustThrowOnCnpjWithMoreThan14Numbers() {
        PixKeyException ex = Assertions.assertThrows(PixKeyException.class, () -> {
            new CnpjPixType("12345678901234567");
        });
        Assertions.assertEquals("The CNPJ have more digits than 14", ex.getMessage());
    }

    @Test
    void mustThrowOnCnpjWithNonNumeric() {
        PixKeyException ex = Assertions.assertThrows(PixKeyException.class, () -> {
            new CnpjPixType("1234567890123G");
        });
        Assertions.assertEquals("The CNPJ must have exact 14 numeric numbers", ex.getMessage());
    }

    @Test
    void mustThrowOnInvalidCnpj() {
        PixKeyException ex = Assertions.assertThrows(PixKeyException.class, () -> {
            new CnpjPixType("43192371287391");

        });
        Assertions.assertEquals("The CNPJ isn't valid", ex.getMessage());
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
