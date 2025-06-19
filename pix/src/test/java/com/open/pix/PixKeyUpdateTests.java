package com.open.pix;

import com.open.pix.adapters.input.PixKeyUpdateRequest;
import com.open.pix.adapters.mappers.PixKeyRequestMapper;
import com.open.pix.application.exceptions.NotFoundException;
import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.application.gateway.SavePixKeyGateway;
import com.open.pix.application.usecases.UpdatePixKeyUseCase;
import com.open.pix.domain.PixKey;
import com.open.pix.domain.types.AccountNumber;
import com.open.pix.domain.factory.AccountTypeFactory;
import com.open.pix.domain.types.AgencyNumber;
import com.open.pix.domain.exceptions.PixKeyException;
import com.open.pix.domain.factory.PixTypeFactory;
import com.open.pix.domain.types.accountTypes.CurrentAccount;
import com.open.pix.domain.types.accountTypes.SavingsAccount;
import com.open.pix.domain.types.pixTypes.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public final class PixKeyUpdateTests {

    @Spy
    @InjectMocks
    private UpdatePixKeyUseCase useCase;

    private final PixTypeFactory pixTypeFactory = new PixTypeFactory(Map.of(
            "cpf", CpfPixType::new,
            "cnpj", CnpjPixType::new,
            "email", EmailPixType::new,
            "celular", PhonePixType::new,
            "aleatorio", RandomPixType::new
    ));

    private final AccountTypeFactory accountTypeFactory = new AccountTypeFactory(Map.of(
            "corrente", CurrentAccount::new,
            "poupança", SavingsAccount::new
    ));

    private final PixKeyRequestMapper pixKeyRequestMapper = new PixKeyRequestMapper(pixTypeFactory, accountTypeFactory);

    @Mock
    private SavePixKeyGateway savePixKeyGateway;

    @Mock
    private FindPixKeyGateway findPixKeyGateway;

    private final UUID uuid = UUID.randomUUID();

    private final PixKey pixKey1 = PixKey.registerNew(pixTypeFactory.newPixType("cpf", "72356804072"),
            "72356804072",
            accountTypeFactory.resolve("corrente"),
            new AgencyNumber(1234),
            new AccountNumber(1234567),
            "Mark",
            "Jhones");

    private final PixKey pixKeyUpdated = PixKey.registerNew(pixTypeFactory.newPixType("cpf", "72356804072"),
            "72356804072",
            accountTypeFactory.resolve("corrente"),
            new AgencyNumber(1234),
            new AccountNumber(12345678),
            "Marcus",
            "Rol");

    private final PixKeyUpdateRequest pixKeyUpdateRequest = new PixKeyUpdateRequest(uuid,
            accountTypeFactory.resolve("corrente").value(), 1234, 12345678, "Marcus", "Rol");

    @Test
    void shouldThrowOnInactiveKey() {
        pixKey1.setId(UUID.randomUUID());
        pixKey1.inactivate();

        Mockito.when(findPixKeyGateway.findById(Mockito.any(UUID.class)))
                .thenReturn(pixKey1);

        PixKeyException exception = Assertions.assertThrows(PixKeyException.class, () -> {
            useCase.update(pixKey1);
        });

        Assertions.assertEquals("It's not allowed to update inactive keys", exception.getMessage());

        Mockito.verify(findPixKeyGateway, Mockito.times(1)).findById(Mockito.any(UUID.class));
        Mockito.verifyNoInteractions(savePixKeyGateway);
    }

    @Test
    void shouldUpdate() {
        pixKey1.setId(uuid);
        pixKeyUpdated.setId(uuid);

        Mockito.when(findPixKeyGateway.findById(uuid)).thenReturn(pixKey1);
        Mockito.when(savePixKeyGateway.save(Mockito.any(PixKey.class))).thenReturn(pixKeyUpdated);

        PixKey updatedKey = useCase.update(pixKeyRequestMapper.fromUpdate(pixKeyUpdateRequest));

        Assertions.assertNotNull(updatedKey);
        Assertions.assertEquals(uuid, updatedKey.getId());
        Assertions.assertEquals("Marcus", updatedKey.getFirstName());
        Assertions.assertEquals("Rol", updatedKey.getLastName());
        Assertions.assertEquals(1234, updatedKey.getAgencyNumber().value());
        Assertions.assertEquals(12345678, updatedKey.getAccountNumber().value());
        Assertions.assertEquals("corrente", updatedKey.getAccountType().value());
        Assertions.assertEquals("72356804072", updatedKey.getValue());
        Assertions.assertTrue(updatedKey.isActive());

        Mockito.verify(findPixKeyGateway, Mockito.times(1)).findById(uuid);
        Mockito.verify(savePixKeyGateway, Mockito.times(1)).save(Mockito.any(PixKey.class));
    }

    @Test
    void shouldThrowIfKeyNotFound() {
        Mockito.when(findPixKeyGateway.findById(uuid)).thenReturn(null);

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
            useCase.update(pixKeyRequestMapper.fromUpdate(pixKeyUpdateRequest));
        });

        Assertions.assertEquals("Pix key not found", exception.getMessage());
        Mockito.verify(findPixKeyGateway, Mockito.times(1)).findById(uuid);
        Mockito.verifyNoInteractions(savePixKeyGateway);
    }

    @Test
    void shouldNotUpdateWhenDataIsSame() {
        pixKey1.setId(uuid);

        PixKeyUpdateRequest sameDataRequest = new PixKeyUpdateRequest(uuid,
                "corrente", 1234, 1234567, "Mark", "Jhones");

        Mockito.when(findPixKeyGateway.findById(uuid)).thenReturn(pixKey1);
        Mockito.when(savePixKeyGateway.save(Mockito.any(PixKey.class))).thenAnswer(i -> i.getArgument(0));

        PixKey result = useCase.update(pixKeyRequestMapper.fromUpdate(sameDataRequest));

        Assertions.assertEquals(pixKey1.getFirstName(), result.getFirstName());
        Assertions.assertEquals(pixKey1.getAccountNumber(), result.getAccountNumber());

        Mockito.verify(findPixKeyGateway, Mockito.times(1)).findById(uuid);
        Mockito.verify(savePixKeyGateway).save(Mockito.any(PixKey.class));
    }
}
