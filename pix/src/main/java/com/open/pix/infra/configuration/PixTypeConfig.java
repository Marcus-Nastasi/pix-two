package com.open.pix.infra.configuration;

import com.open.pix.domain.factory.PixTypeFactory;
import com.open.pix.domain.types.pixTypes.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class PixTypeConfig {

    @Bean
    public PixTypeFactory pixTypeFactory() {
        return new PixTypeFactory(Map.of(
            "cpf", CpfPixType::new,
            "cnpj", CnpjPixType::new,
            "email", EmailPixType::new,
            "celular", PhonePixType::new,
            "aleatorio", RandomPixType::new
        ));
    }
}
