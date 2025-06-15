package com.open.pix.infra.configuration;

import com.open.pix.application.gateway.CountPixKeysGateway;
import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.application.gateway.RegistrePixKey;
import com.open.pix.application.usecases.FindPixKeysUseCase;
import com.open.pix.application.usecases.RegistrePixKeyUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PixKeyConfig {

    @Bean
    public FindPixKeysUseCase getPixKeysUseCase(FindPixKeyGateway findPixKeyGateway) {
        return new FindPixKeysUseCase(findPixKeyGateway);
    }

    @Bean
    public RegistrePixKeyUseCase registrePixKeyUseCase(RegistrePixKey registrePixKey,
                                                       FindPixKeyGateway findPixKeyGateway,
                                                       CountPixKeysGateway countPixKeysGateway) {
        return new RegistrePixKeyUseCase(registrePixKey, findPixKeyGateway, countPixKeysGateway);
    }
}
