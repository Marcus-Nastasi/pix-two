package com.open.pix;

import com.open.pix.adapters.input.PixKeyUpdateRequest;
import com.open.pix.adapters.mappers.PixKeyRequestMapper;
import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.application.gateway.UpdatePixKeyGateway;
import com.open.pix.application.usecases.UpdatePixKeyUseCase;
import com.open.pix.domain.PixKey;
import com.open.pix.domain.enums.AccountNumber;
import com.open.pix.domain.enums.AccountType;
import com.open.pix.domain.enums.AgencyNumber;
import com.open.pix.domain.exceptions.PixKeyException;
import com.open.pix.domain.factory.PixTypeFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class PixKeyUpdateTests {

    @Spy
    @InjectMocks
    private UpdatePixKeyUseCase useCase;

    @Mock
    private UpdatePixKeyGateway updatePixKeyGateway;

    @Mock
    private FindPixKeyGateway findPixKeyGateway;

    private final UUID uuid = UUID.randomUUID();

    private final PixKey pixKey1 = PixKey.registerNew(PixTypeFactory.newPixType("cpf", "72356804072"),
            "72356804072",
            new AccountType("corrente"),
            new AgencyNumber(1234),
            new AccountNumber(1234567),
            "Mark",
            "Jhones");

    private final PixKey pixKeyUpdated = PixKey.registerNew(PixTypeFactory.newPixType("cpf", "72356804072"),
            "72356804072",
            new AccountType("corrente"),
            new AgencyNumber(1234),
            new AccountNumber(12345678),
            "Marcus",
            "Rol");

    private final PixKeyUpdateRequest pixKeyUpdateRequest = new PixKeyUpdateRequest(uuid,
            new AccountType("corrente").type(), 1234, 12345678, "Marcus", "Rol");

    @Test
    void shouldThrowOnInactiveKey() {
        pixKey1.setId(UUID.randomUUID());
        pixKey1.inactivate();

        Mockito.when(findPixKeyGateway.findById(Mockito.any(UUID.class))).thenReturn(pixKey1);

        Assertions.assertThrows(PixKeyException.class, () -> {
            useCase.update(pixKey1);
        });
    }

    @Test
    void shouldUpdate() {
        pixKey1.setId(uuid);
        pixKeyUpdated.setId(uuid);

        Mockito.when(findPixKeyGateway.findById(uuid)).thenReturn(pixKey1);
        Mockito.when(updatePixKeyGateway.update(Mockito.any(PixKey.class))).thenReturn(pixKeyUpdated);

        PixKey pixKey = useCase.update(PixKeyRequestMapper.fromUpdate(pixKeyUpdateRequest));

        Assertions.assertDoesNotThrow(() -> pixKey);
        Assertions.assertEquals(uuid, pixKey.getId());
        Assertions.assertEquals(pixKeyUpdateRequest.firstName(), pixKey.getFirstName());

        Mockito.verify(findPixKeyGateway, Mockito.times(1)).findById(Mockito.any(UUID.class));
        Mockito.verify(updatePixKeyGateway, Mockito.times(1)).update(Mockito.any(PixKey.class));
    }
}
