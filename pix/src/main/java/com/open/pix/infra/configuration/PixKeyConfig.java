package com.open.pix.infra.configuration;

import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.application.usecases.FindPixKeysUseCase;
import com.open.pix.infra.gateway.FindPixKeyGatewayImpl;
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
}
