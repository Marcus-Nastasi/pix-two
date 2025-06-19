package com.open.pix.domain.factory;

import com.open.pix.domain.exceptions.AccountTypeException;
import com.open.pix.domain.interfaces.AccountType;

import java.util.Map;
import java.util.function.Function;

/**
 * Factory of {@link AccountType}. Following the OC principle.
 */
public class AccountTypeFactory {

    private final Map<String, Function<String, AccountType>> allowed;

    public AccountTypeFactory(Map<String, Function<String, AccountType>> allowed) {
        this.allowed = allowed;
    }

    /**
     * Method responsible to resolve the pix key legal type.
     * @param type a string representation of the account type.
     * @return the {@link AccountType} founded.
     * @throws AccountTypeException if the account type informed is not permitted.
     */
    public AccountType resolve(String type) {
        if (type == null) {
            throw new AccountTypeException("Account type cannot be null");
        }
        String key = type.trim().toLowerCase();
        Function<String, AccountType> constructor = allowed.get(key);
        if (constructor == null) {
            throw new AccountTypeException("Invalid account type: "
                    + key + ". Permitted values: " + allowed.keySet());
        }
        return constructor.apply(type);
    }
}
