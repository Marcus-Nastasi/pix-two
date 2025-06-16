package com.open.pix.infra.configuration;

import com.open.pix.domain.factory.LegalTypeFactory;
import com.open.pix.domain.types.CnpjLegalType;
import com.open.pix.domain.types.CpfLegalType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LegalTypeConfig {

    @Bean
    public LegalTypeFactory legalTypeFactory() {
        return new LegalTypeFactory(List.of(new CpfLegalType(), new CnpjLegalType()));
    }
}
