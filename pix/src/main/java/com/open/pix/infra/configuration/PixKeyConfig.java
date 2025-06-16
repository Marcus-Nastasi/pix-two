package com.open.pix.infra.configuration;

import com.open.pix.application.gateway.*;
import com.open.pix.application.usecases.*;
import com.open.pix.domain.factory.LegalTypeFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PixKeyConfig {

    @Bean
    public FindPixKeysUseCase getPixKeysUseCase(FindPixKeyGateway findPixKeyGateway) {
        return new FindPixKeysUseCase(findPixKeyGateway);
    }

    @Bean
    public RegistrePixKeyUseCase registrePixKeyUseCase(SavePixKeyGateway savePixKeyGateway,
                                                       FindPixKeyGateway findPixKeyGateway,
                                                       CountPixKeysGateway countPixKeysGateway,
                                                       LegalTypeFactory legalTypeFactory) {
        return new RegistrePixKeyUseCase(savePixKeyGateway, findPixKeyGateway, countPixKeysGateway, legalTypeFactory);
    }

    @Bean
    public UpdatePixKeyUseCase updatePixKeyUseCase(SavePixKeyGateway savePixKeyGateway,
                                                   FindPixKeyGateway findPixKeyGateway) {
        return new UpdatePixKeyUseCase(savePixKeyGateway, findPixKeyGateway);
    }

    @Bean
    public InactivatePixKeyUseCase inactivatePixKeyUseCase(SavePixKeyGateway savePixKeyGateway,
                                                           FindPixKeyGateway findPixKeyGateway) {
        return new InactivatePixKeyUseCase(savePixKeyGateway, findPixKeyGateway);
    }

    @Bean
    public SearchPixKeysUseCase searchPixKeysUseCase(SearchPixKeyGateway searchPixKeyGateway) {
        return new SearchPixKeysUseCase(searchPixKeyGateway);
    }
}
