package com.open.pix;

import com.open.pix.application.exceptions.NotFoundException;
import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.application.gateway.SavePixKeyGateway;
import com.open.pix.application.usecases.InactivatePixKeyUseCase;
import com.open.pix.domain.PixKey;
import com.open.pix.domain.types.AccountNumber;
import com.open.pix.domain.types.AccountType;
import com.open.pix.domain.types.AgencyNumber;
import com.open.pix.domain.exceptions.PixKeyException;
import com.open.pix.domain.factory.PixTypeFactory;
import com.open.pix.domain.types.pixTypes.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class PixKeyInactivateTests {

    @Spy
    @InjectMocks
    private InactivatePixKeyUseCase useCase;

    private final PixTypeFactory pixTypeFactory = new PixTypeFactory(Map.of(
            "cpf", CpfPixType::new,
            "cnpj", CnpjPixType::new,
            "email", EmailPixType::new,
            "celular", PhonePixType::new,
            "aleatorio", RandomPixType::new
    ));

    @Mock
    private SavePixKeyGateway savePixKeyGateway;

    @Mock
    private FindPixKeyGateway findPixKeyGateway;

    private final PixKey pixKey1 = PixKey.registerNew(pixTypeFactory.newPixType("cpf", "72356804072"),
            "72356804072",
            new AccountType("corrente"),
            new AgencyNumber(1234),
            new AccountNumber(1234567),
            "Mark",
            "Jhones");

    private final PixKey inactivePixKey = PixKey.registerNew(pixTypeFactory.newPixType("cpf", "72356804072"),
            "72356804072",
            new AccountType("corrente"),
            new AgencyNumber(1234),
            new AccountNumber(1234567),
            "Mark",
            "J");

    @Test
    void shouldThrowOnAlreadyInactivatedKey() {
        pixKey1.setId(UUID.randomUUID());
        pixKey1.inactivate();

        when(findPixKeyGateway.findById(any(UUID.class))).thenReturn(pixKey1);

        PixKeyException exception = assertThrows(PixKeyException.class, () -> {
            useCase.inactivate(pixKey1.getId());
        });

        assertEquals("Already inactivated", exception.getMessage());
        assertFalse(pixKey1.isActive());

        verify(findPixKeyGateway, times(1)).findById(pixKey1.getId());
        verifyNoInteractions(savePixKeyGateway);
    }

    @Test
    void shouldInactivate() {
        UUID uuid = UUID.randomUUID();

        pixKey1.setId(uuid);
        inactivePixKey.setId(uuid);

        when(findPixKeyGateway.findById(uuid)).thenReturn(pixKey1);
        when(savePixKeyGateway.save(any(PixKey.class))).thenReturn(inactivePixKey);

        PixKey result = assertDoesNotThrow(() -> useCase.inactivate(uuid));

        assertNotNull(result);
        assertEquals(uuid, result.getId());
        assertTrue(result.isActive());

        verify(findPixKeyGateway, times(1)).findById(uuid);
        verify(savePixKeyGateway, times(1)).save(any(PixKey.class));
    }

    @Test
    void shouldThrowIfPixKeyNotFound() {
        UUID randomId = UUID.randomUUID();

        when(findPixKeyGateway.findById(randomId)).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            useCase.inactivate(randomId);
        });

        assertEquals("Pix key not found", exception.getMessage());

        verify(findPixKeyGateway, times(1)).findById(randomId);
        verifyNoInteractions(savePixKeyGateway);
    }
}
