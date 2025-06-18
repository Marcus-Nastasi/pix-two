package com.open.pix.infra.configuration;

import com.open.pix.domain.factory.LegalTypeFactory;
import com.open.pix.domain.interfaces.LegalType;
import com.open.pix.domain.types.legalTypes.PjLegalType;
import com.open.pix.domain.types.legalTypes.PfLegalType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LegalTypeConfig {

    @Bean
    public LegalType cpfLegalType() {
        return new PfLegalType();
    }

    @Bean
    public LegalType cnpjLegalType() {
        return new PjLegalType();
    }

    @Bean
    public LegalTypeFactory legalTypeFactory(List<LegalType> legalTypes) {
        return new LegalTypeFactory(legalTypes);
    }
}
