package com.open.pix.account_type_tests;

import com.open.pix.domain.types.AccountType;
import com.open.pix.domain.exceptions.AccountTypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class AccountTypeTests {

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

    @Test
    void mustThrowOnBlankString() {
        AccountTypeException ex = assertThrows(AccountTypeException.class, () -> {
            new AccountType("   ");
        });
        assertEquals("Account type must not be empty", ex.getMessage());
    }

    @Test
    void mustAcceptExactValidLowercase() {
        AccountType t = new AccountType("corrente");
        assertEquals("corrente", t.type());
    }

    @Test
    void mustThrowOnAccountTypeNullWithOf() {
        AccountTypeException ex = assertThrows(AccountTypeException.class, () -> {
            AccountType.of(null);
        });
        assertEquals("Account type must not be empty", ex.getMessage());
    }

    @Test
    void mustThrowOnAccountTypeLengthMoreThanTenWithOf() {
        AccountTypeException ex = assertThrows(AccountTypeException.class, () -> {
            AccountType.of("correntecorrente"); // 16 chars
        });
        assertEquals("Account type must not exceed 10 characters", ex.getMessage());
    }

    @Test
    void mustThrowOnAccountTypeInvalidValueWithOf() {
        AccountTypeException ex = assertThrows(AccountTypeException.class, () -> {
            AccountType.of("investimen");
        });
        assertTrue(ex.getMessage().startsWith("Invalid account type: "));
        assertTrue(ex.getMessage().contains("Permitted values:"));
    }

    @Test
    void mustNormalizeAndAcceptValidTypesCaseInsensitiveWithOf() {
        AccountType t1 = AccountType.of(" CoRrEnTe ");
        assertEquals("corrente", t1.type());

        AccountType t2 = AccountType.of("POUPANÇA");
        assertEquals("poupança", t2.type());
    }

    @Test
    void mustThrowOnBlankStringWithOf() {
        AccountTypeException ex = assertThrows(AccountTypeException.class, () -> {
            AccountType.of("   ");
        });
        assertEquals("Account type must not be empty", ex.getMessage());
    }

    @Test
    void mustAcceptExactValidLowercaseWithOf() {
        AccountType t = AccountType.of("corrente");
        assertEquals("corrente", t.type());
    }
}
