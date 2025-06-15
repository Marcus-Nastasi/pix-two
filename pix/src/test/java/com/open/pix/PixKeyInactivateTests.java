package com.open.pix;

import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.application.gateway.SavePixKeyGateway;
import com.open.pix.application.usecases.InactivatePixKeyUseCase;
import com.open.pix.domain.PixKey;
import com.open.pix.domain.enums.AccountNumber;
import com.open.pix.domain.enums.AccountType;
import com.open.pix.domain.enums.AgencyNumber;
import com.open.pix.domain.exceptions.PixKeyException;
import com.open.pix.domain.factory.PixTypeFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class PixKeyInactivateTests {

    @Spy
    @InjectMocks
    private InactivatePixKeyUseCase useCase;

    @Mock
    private SavePixKeyGateway savePixKeyGateway;

    @Mock
    private FindPixKeyGateway findPixKeyGateway;

    private final PixKey pixKey1 = PixKey.registerNew(PixTypeFactory.newPixType("cpf", "72356804072"),
            "72356804072",
            new AccountType("corrente"),
            new AgencyNumber(1234),
            new AccountNumber(1234567),
            "Mark",
            "Jhones");

    private final PixKey inactivePixKey = PixKey.registerNew(PixTypeFactory.newPixType("cpf", "72356804072"),
            "72356804072",
            new AccountType("corrente"),
            new AgencyNumber(1234),
            new AccountNumber(1234567),
            "Mark",
            "J");

    @Test
    void shouldThrowOnAlreadyInactivatedKey() {
        pixKey1.inactivate();
        pixKey1.setId(UUID.randomUUID());
        when(findPixKeyGateway.findById(any(UUID.class))).thenReturn(pixKey1);

        PixKeyException exception = assertThrows(PixKeyException.class, () -> {
            useCase.inactivate(pixKey1.getId());
        });

        assertEquals("Already inactivated", exception.getMessage());

        verify(findPixKeyGateway, times(1)).findById(any(UUID.class));
    }

    @Test
    void shouldInactivate() {
        UUID uuid = UUID.randomUUID();

        pixKey1.setId(uuid);
        inactivePixKey.setId(uuid);

        when(findPixKeyGateway.findById(uuid)).thenReturn(pixKey1);
        when(savePixKeyGateway.save(any(PixKey.class))).thenReturn(inactivePixKey);

        assertDoesNotThrow(() -> useCase.inactivate(uuid));

        verify(findPixKeyGateway, times(1)).findById(any(UUID.class));
        verify(savePixKeyGateway, times(1)).save(any(PixKey.class));
    }
}
