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
        assertEquals("Account number must not be empty", exception.getMessage());
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

    @Test
    void mustThrowWhenAccountNumberGreaterThanEightWithOf() {
        AccountNumberException exception = assertThrows(AccountNumberException.class, () -> {
            AccountNumber.of(123456789);
        });
        assertEquals("Account number must have less or equal than 8 digits", exception.getMessage());
    }

    @Test
    void mustThrowOnAccountNumberNullWithOf() {
        AccountNumberException exception = assertThrows(AccountNumberException.class, () -> {
            AccountNumber.of(null);
        });
        assertEquals("Account number must not be empty", exception.getMessage());
    }

    @Test
    void mustAcceptValidEightDigitAccountNumberWithOf() {
        AccountNumber acc = AccountNumber.of(87654321);
        assertEquals(87654321, acc.value());
    }

    @Test
    void mustAcceptValidShortAccountNumberWithOf() {
        AccountNumber acc = AccountNumber.of(123);
        assertEquals(123, acc.value());
    }
}
