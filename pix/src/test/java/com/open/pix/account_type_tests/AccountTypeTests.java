package com.open.pix.account_type_tests;

import com.open.pix.domain.types.AccountType;
import com.open.pix.domain.exceptions.AccountTypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTypeTests {

    @Test
    void mustThrowOnAccountTypeNull() {
        AccountTypeException ex = assertThrows(AccountTypeException.class, () -> {
            new AccountType(null);
        });
        assertEquals("Account type must not be empty", ex.getMessage());
    }

    @Test
    void mustThrowOnAccountTypeLengthMoreThanTen() {
        AccountTypeException ex = assertThrows(AccountTypeException.class, () -> {
            new AccountType("correntecorrente"); // 16 chars
        });
        assertEquals("Account type must not exceed 10 characters", ex.getMessage());
    }

    @Test
    void mustThrowOnAccountTypeInvalidValue() {
        AccountTypeException ex = assertThrows(AccountTypeException.class, () -> {
            new AccountType("investimen");
        });
        assertTrue(ex.getMessage().startsWith("Invalid account type: "));
        assertTrue(ex.getMessage().contains("Permitted values:"));
    }

    @Test
    void mustNormalizeAndAcceptValidTypesCaseInsensitive() {
        AccountType t1 = new AccountType(" CoRrEnTe ");
        assertEquals("corrente", t1.type());

        AccountType t2 = new AccountType("POUPANÇA");
        assertEquals("poupança", t2.type());
    }
}
