package com.open.pix.infra.configuration;

import com.open.pix.application.gateway.*;
import com.open.pix.application.usecases.FindPixKeysUseCase;
import com.open.pix.application.usecases.InactivatePixKeyUseCase;
import com.open.pix.application.usecases.RegistrePixKeyUseCase;
import com.open.pix.application.usecases.UpdatePixKeyUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PixKeyConfig {

    @Bean
    public FindPixKeysUseCase getPixKeysUseCase(FindPixKeyGateway findPixKeyGateway) {
        return new FindPixKeysUseCase(findPixKeyGateway);
    }

    @Bean
    public RegistrePixKeyUseCase registrePixKeyUseCase(RegistrePixKeyGateway registrePixKeyGateway,
                                                       FindPixKeyGateway findPixKeyGateway,
                                                       CountPixKeysGateway countPixKeysGateway) {
        return new RegistrePixKeyUseCase(registrePixKeyGateway, findPixKeyGateway, countPixKeysGateway);
    }

    @Bean
    public UpdatePixKeyUseCase updatePixKeyUseCase(UpdatePixKeyGateway updatePixKeyGateway,
                                                   FindPixKeyGateway findPixKeyGateway) {
        return new UpdatePixKeyUseCase(updatePixKeyGateway, findPixKeyGateway);
    }

    @Bean
    public InactivatePixKeyUseCase inactivatePixKeyUseCase(SavePixKeyGateway savePixKeyGateway,
                                                           FindPixKeyGateway findPixKeyGateway) {
        return new InactivatePixKeyUseCase(savePixKeyGateway, findPixKeyGateway);
    }
}
