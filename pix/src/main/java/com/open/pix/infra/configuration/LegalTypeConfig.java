package com.open.pix.infra.configuration;

import com.open.pix.domain.factory.LegalTypeFactory;
import com.open.pix.domain.interfaces.LegalType;
import com.open.pix.domain.types.legalTypes.CnpjLegalType;
import com.open.pix.domain.types.legalTypes.CpfLegalType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LegalTypeConfig {

    @Bean
    public LegalType cpfLegalType() {
        return new CpfLegalType();
    }

    @Bean
    public LegalType cnpjLegalType() {
        return new CnpjLegalType();
    }

    @Bean
    public LegalTypeFactory legalTypeFactory(List<LegalType> legalTypes) {
        return new LegalTypeFactory(legalTypes);
    }
}
