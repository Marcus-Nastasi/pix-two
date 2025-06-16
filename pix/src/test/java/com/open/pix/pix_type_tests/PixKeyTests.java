package com.open.pix.pix_type_tests;

import com.open.pix.domain.PixKey;
import com.open.pix.domain.types.AccountNumber;
import com.open.pix.domain.types.AccountType;
import com.open.pix.domain.types.AgencyNumber;
import com.open.pix.domain.types.pixTypes.CpfPixType;
import com.open.pix.domain.exceptions.PixKeyException;
import com.open.pix.domain.interfaces.PixType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class PixKeyTests {

    PixType pixType = new CpfPixType("43743508885");

    PixKey pixKey = PixKey.registerNew(
            pixType,
            pixType.value(),
            new AccountType("corrente"),
            new AgencyNumber(1234),
            new AccountNumber(12345678),
            "Marcus",
            "Nastasi");

    @Test
    void mustCheckCreationPixKey() {
        Assertions.assertDoesNotThrow(() -> pixKey);
        Assertions.assertEquals("43743508885", pixKey.getValue());
        Assertions.assertEquals("corrente", pixKey.getAccountType().type());
        Assertions.assertEquals(CpfPixType.class, pixKey.getPixType().getClass());
        Assertions.assertEquals(LocalDateTime.class, pixKey.getCreationDateTime().getClass());
    }

    @Test
    void mustInactivatePixKey() {
        Assertions.assertTrue(pixKey.isActive());

        pixKey.inactivate();

        Assertions.assertFalse(pixKey.isActive());
    }

    @Test
    void mustThrowOnAlreadyInactivatedPixKey() {
        pixKey.inactivate();

        Assertions.assertFalse(pixKey.isActive());
        Assertions.assertThrows(PixKeyException.class, () -> {
           pixKey.inactivate();
        });
    }
}
