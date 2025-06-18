package com.open.pix.account_number_tests;

import com.open.pix.domain.types.AccountNumber;
import com.open.pix.domain.exceptions.AccountNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class AccountNumberTests {

    @Test
    void mustThrowWhenAccountNumberGreaterThanEight() {
        AccountNumberException exception = assertThrows(AccountNumberException.class, () -> {
            new AccountNumber(123456789);
        });
        assertEquals("Account number must have less or equal than 8 digits", exception.getMessage());
    }

    @Test
    void mustThrowOnAccountNumberNull() {
        AccountNumberException exception = assertThrows(AccountNumberException.class, () -> {
            new AccountNumber(null);
        });
        assertEquals("Account number must be informed", exception.getMessage());
    }

    @Test
    void mustAcceptValidEightDigitAccountNumber() {
        AccountNumber acc = new AccountNumber(87654321);
        assertEquals(87654321, acc.value());
    }

    @Test
    void mustAcceptValidShortAccountNumber() {
        AccountNumber acc = new AccountNumber(123);
        assertEquals(123, acc.value());
    }
}
