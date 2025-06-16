package com.open.pix.account_number_tests;

import com.open.pix.domain.types.AccountNumber;
import com.open.pix.domain.exceptions.AccountNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountNumberTests {

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
}
