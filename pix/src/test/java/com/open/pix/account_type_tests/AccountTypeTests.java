package com.open.pix.account_type_tests;

import com.open.pix.domain.factory.AccountTypeFactory;
import com.open.pix.domain.exceptions.AccountTypeException;
import com.open.pix.domain.interfaces.AccountType;
import com.open.pix.domain.types.accountTypes.CurrentAccount;
import com.open.pix.domain.types.accountTypes.SavingsAccount;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public final class AccountTypeTests {

    private final AccountTypeFactory accountTypeFactory = new AccountTypeFactory(Map.of(
            "corrente", CurrentAccount::new,
            "poupança", SavingsAccount::new
    ));

    @Test
    void mustThrowOnAccountTypeNull() {
        AccountTypeException ex = assertThrows(AccountTypeException.class, () -> {
            accountTypeFactory.resolve(null);
        });
        assertEquals("Account type cannot be null", ex.getMessage());
    }

    @Test
    void mustThrowOnAccountTypeLengthMoreThanTen() {
        AccountTypeException ex = assertThrows(AccountTypeException.class, () -> {
            accountTypeFactory.resolve("correntecorrente"); // 16 chars
        });
        assertTrue(ex.getMessage().startsWith("Invalid account type:"));
    }

    @Test
    void mustThrowOnAccountTypeInvalidValue() {
        AccountTypeException ex = assertThrows(AccountTypeException.class, () -> {
            accountTypeFactory.resolve("investimen");
        });
        assertTrue(ex.getMessage().startsWith("Invalid account type: "));
        assertTrue(ex.getMessage().contains("Permitted values:"));
    }

    @Test
    void mustNormalizeAndAcceptValidTypesCaseInsensitive() {
        AccountType t1 = accountTypeFactory.resolve(" CoRrEnTe ");
        assertEquals("corrente", t1.value());

        AccountType t2 = accountTypeFactory.resolve("POUPANÇA");
        assertEquals("poupança", t2.value());
    }

    @Test
    void mustThrowOnBlankString() {
        AccountTypeException ex = assertThrows(AccountTypeException.class, () -> {
            accountTypeFactory.resolve("   ");
        });
        assertTrue(ex.getMessage().startsWith("Invalid account type:"));
    }

    @Test
    void mustAcceptExactValidLowercase() {
        AccountType t = accountTypeFactory.resolve("corrente");
        assertEquals("corrente", t.value());
    }

    @Test
    void mustThrowOnAccountTypeNullWithOf() {
        AccountTypeException ex = assertThrows(AccountTypeException.class, () -> {
            accountTypeFactory.resolve(null);
        });
        assertEquals("Account type cannot be null", ex.getMessage());
    }

    @Test
    void mustThrowOnAccountTypeLengthMoreThanTenWithOf() {
        AccountTypeException ex = assertThrows(AccountTypeException.class, () -> {
            accountTypeFactory.resolve("correntecorrente"); // 16 chars
        });
        assertTrue(ex.getMessage().startsWith("Invalid account type: correntecorrente."));
    }

    @Test
    void mustThrowOnAccountTypeInvalidValueWithOf() {
        AccountTypeException ex = assertThrows(AccountTypeException.class, () -> {
            accountTypeFactory.resolve("investimen");
        });
        assertTrue(ex.getMessage().startsWith("Invalid account type: "));
        assertTrue(ex.getMessage().contains("Permitted values:"));
    }

    @Test
    void mustNormalizeAndAcceptValidTypesCaseInsensitiveWithOf() {
        AccountType t1 = accountTypeFactory.resolve(" CoRrEnTe ");
        assertEquals("corrente", t1.value());

        AccountType t2 = accountTypeFactory.resolve("POUPANÇA");
        assertEquals("poupança", t2.value());
    }

    @Test
    void mustThrowOnBlankStringWithOf() {
        AccountTypeException ex = assertThrows(AccountTypeException.class, () -> {
            accountTypeFactory.resolve("   ");
        });
        assertTrue(ex.getMessage().startsWith("Invalid account type:"));
    }

    @Test
    void mustAcceptExactValidLowercaseWithOf() {
        AccountType t = accountTypeFactory.resolve("corrente");
        assertEquals("corrente", t.value());
    }
}
