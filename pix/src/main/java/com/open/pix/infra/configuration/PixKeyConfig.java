package com.open.pix.infra.configuration;

import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.application.gateway.RegistrePixKey;
import com.open.pix.application.usecases.FindPixKeysUseCase;
import com.open.pix.application.usecases.RegistrePixKeyUseCase;
import com.open.pix.infra.gateway.FindPixKeyGatewayImpl;
import com.open.pix.infra.gateway.RegistrePixKeyImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PixKeyConfig {

    @Bean
    public FindPixKeyGateway getPixGateway() {
        return new FindPixKeyGatewayImpl();
    }

    @Bean
    public FindPixKeysUseCase getPixKeysUseCase(FindPixKeyGateway findPixKeyGateway) {
        return new FindPixKeysUseCase(findPixKeyGateway);
    }

    @Bean
    public RegistrePixKey registrePixKey() {
        return new RegistrePixKeyImpl();
    }

    @Bean
    public RegistrePixKeyUseCase registrePixKeyUseCase(RegistrePixKey registrePixKey) {
        return new RegistrePixKeyUseCase(registrePixKey);
    }
}
