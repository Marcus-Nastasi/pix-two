package com.open.pix.infra.configuration;

import com.open.pix.domain.factory.AccountTypeFactory;
import com.open.pix.domain.types.accountTypes.CurrentAccount;
import com.open.pix.domain.types.accountTypes.SavingsAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class AccountTypeConfig {

    @Bean
    public AccountTypeFactory accountTypeFactory() {
        return new AccountTypeFactory(Map.of(
            "corrente", CurrentAccount::new,
            "poupança", SavingsAccount::new
        ));
    }
}
