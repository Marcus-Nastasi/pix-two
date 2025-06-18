package com.open.pix;

import com.open.pix.application.exceptions.PixRegistreException;
import com.open.pix.application.gateway.CountPixKeysGateway;
import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.application.gateway.SavePixKeyGateway;
import com.open.pix.application.usecases.RegistrePixKeyUseCase;
import com.open.pix.domain.PixKey;
import com.open.pix.domain.factory.LegalTypeFactory;
import com.open.pix.domain.factory.PixTypeFactory;
import com.open.pix.domain.types.*;

import com.open.pix.domain.types.pixTypes.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public final class PixKeyRegistrationTest {

    @Spy
    @InjectMocks
    private RegistrePixKeyUseCase useCase;

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

    @Mock
    private CountPixKeysGateway countPixKeys;

    @Mock
    private LegalTypeFactory legalTypeFactory;

    private final PixKey pixKey1 = PixKey.registerNew(pixTypeFactory.newPixType("cpf", "72356804072"),
                                                      "72356804072",
                                                      new AccountType("corrente"),
                                                      new AgencyNumber(1234),
                                                      new AccountNumber(1234567),
                                                      "Mark",
                                                      "Jhones");

    private final PixKey pixKey1_2 = PixKey.registerNew(pixTypeFactory.newPixType("celular", "+55 13 123456789"),
            "+55 13 123456789",
            new AccountType("corrente"),
            new AgencyNumber(1234),
            new AccountNumber(1234567),
            "Mark",
            "Jhones");

    private final PixKey pixKey1_3 = PixKey.registerNew(pixTypeFactory.newPixType("email", "mark.jones@gmail.com"),
            "mark.jones@gmail.com",
            new AccountType("corrente"),
            new AgencyNumber(1234),
            new AccountNumber(1234567),
            "Mark",
            "Jhones");

    private final PixKey pixKey1_4 = PixKey.registerNew(pixTypeFactory.newPixType("aleatorio", "dcta478j196l03fmt6gh4298er7845m2"),
            "dcta478j196l03fmt6gh4298er7845m2",
            new AccountType("corrente"),
            new AgencyNumber(1234),
            new AccountNumber(1234567),
            "Mark",
            "Jhones");

    private final PixKey pixKey1_5 = PixKey.registerNew(pixTypeFactory.newPixType("aleatorio", "octa478j196l03fmt6gh4298er7845m2"),
            "octa478j196l03fmt6gh4298er7845m2",
            new AccountType("corrente"),
            new AgencyNumber(1234),
            new AccountNumber(1234567),
            "Mark",
            "Jhones");

    private final PixKey pixKey1_6 = PixKey.registerNew(pixTypeFactory.newPixType("aleatorio", "xyzs478j196l03fmt6gh4298er7845m2"),
            "xyzs478j196l03fmt6gh4298er7845m2",
            new AccountType("corrente"),
            new AgencyNumber(1234),
            new AccountNumber(1234567),
            "Mark",
            "Jhones");

    private final List<PixKey> markJhonesPixKeys = new ArrayList<>(List.of(pixKey1,
            pixKey1_2, pixKey1_3, pixKey1_4, pixKey1_5));

    private final PixKey duplicatedPixKey1 = PixKey.registerNew(pixTypeFactory.newPixType("cpf", "72356804072"),
                                                                "72356804072",
                                                                new AccountType("corrente"),
                                                                new AgencyNumber(8984),
                                                                new AccountNumber(739287),
                                                                "Bob",
                                                                "Kane");

    private final PixKey pixKey2 = PixKey.registerNew(pixTypeFactory.newPixType("cnpj", "56724791000157"),
                                                        "56724791000157",
                                                        new AccountType("poupança"),
                                                        new AgencyNumber(2345),
                                                        new AccountNumber(678901),
                                                        "Rosa",
                                                        "Linda");

    private final PixKey pixKey7 = PixKey.registerNew(pixTypeFactory.newPixType("cnpj", "01244335000118"),
            "01244335000118",
            new AccountType("corrente"),
            new AgencyNumber(5555),
            new AccountNumber(5006007),
            "Empresa",
            "NovaTech");

    @Test
    void shouldThrowOnDuplicatedPixKeyRegistre() {
        when(findPixKeyGateway.findByPixValue("72356804072")).thenReturn(pixKey1);

        PixRegistreException exception = assertThrows(PixRegistreException.class, () -> {
            useCase.registre(duplicatedPixKey1);
        });

        assertEquals("The value 72356804072 is already associated", exception.getMessage());

        verify(findPixKeyGateway, times(1)).findByPixValue("72356804072");
        verifyNoInteractions(savePixKeyGateway);
    }

    @Test
    void shouldThrowOnPfTryingToRegistreMoreThanFiveKeys() {
        when(findPixKeyGateway.findByPixValue(anyString())).thenReturn(null);
        when(countPixKeys.countByAccountNumberAndAgencyNumber(
                pixKey1.getAccountNumber().value(), pixKey1.getAgencyNumber().value())).thenReturn(markJhonesPixKeys.size());
        when(findPixKeyGateway.findAllByAccountNumberAndAgencyNumber(
                pixKey1.getAccountNumber().value(), pixKey1.getAgencyNumber().value())).thenReturn(markJhonesPixKeys);
        when(legalTypeFactory.resolve(any())).thenReturn(new CpfLegalType());

        PixRegistreException exception = assertThrows(PixRegistreException.class, () -> {
            useCase.registre(pixKey1_6);
        });

        assertEquals("Limit of 5 keys reached for individual account, need to delete an existing key to add more.",
                exception.getMessage());

        verify(findPixKeyGateway, times(1)).findByPixValue(anyString());
        verify(countPixKeys, times(1)).countByAccountNumberAndAgencyNumber(anyInt(), anyInt());
        verify(findPixKeyGateway, times(1)).findAllByAccountNumberAndAgencyNumber(anyInt(), anyInt());
        verifyNoInteractions(savePixKeyGateway);
    }

    @Test
    void shouldThrowOnPjTryingToRegistreMoreThan20Keys() {
        when(findPixKeyGateway.findByPixValue(anyString())).thenReturn(null);
        when(countPixKeys.countByAccountNumberAndAgencyNumber(
                pixKey7.getAccountNumber().value(), pixKey7.getAgencyNumber().value())).thenReturn(20);
        when(findPixKeyGateway.findAllByAccountNumberAndAgencyNumber(
                pixKey7.getAccountNumber().value(), pixKey7.getAgencyNumber().value())).thenReturn(List.of(pixKey7));
        when(legalTypeFactory.resolve(any())).thenReturn(new CnpjLegalType());

        PixRegistreException exception = assertThrows(PixRegistreException.class, () -> {
            useCase.registre(pixKey7);
        });

        assertTrue(exception.getMessage().startsWith("Limit of 20 keys reached for legal entity account,"));

        verify(findPixKeyGateway, times(1)).findByPixValue(anyString());
        verify(countPixKeys, times(1)).countByAccountNumberAndAgencyNumber(anyInt(), anyInt());
        verify(findPixKeyGateway, times(1)).findAllByAccountNumberAndAgencyNumber(anyInt(), anyInt());
        verifyNoInteractions(savePixKeyGateway);
    }

    @Test
    void shouldRegistreNewPixKey() {
        when(findPixKeyGateway.findByPixValue(anyString())).thenReturn(null);
        when(countPixKeys.countByAccountNumberAndAgencyNumber(
                pixKey2.getAccountNumber().value(), pixKey2.getAgencyNumber().value())).thenReturn(1);
        when(findPixKeyGateway.findAllByAccountNumberAndAgencyNumber(
                pixKey2.getAccountNumber().value(), pixKey2.getAgencyNumber().value())).thenReturn(List.of(pixKey7));
        when(savePixKeyGateway.save(any(PixKey.class))).thenReturn(pixKey2);
        when(legalTypeFactory.resolve(any())).thenReturn(new CnpjLegalType());

        PixKey result = useCase.registre(pixKey2);

        assertNotNull(result);
        assertEquals(pixKey2.getValue(), result.getValue());
        assertEquals(pixKey2.getFirstName(), result.getFirstName());
        assertTrue(result.isActive());

        assertNotNull(result.getCreationDateTime());
        assertNotNull(result.getUpdateDateTime());
        assertNull(result.getInactivationDateTime());

        verify(findPixKeyGateway, times(1)).findByPixValue(anyString());
        verify(countPixKeys, times(1)).countByAccountNumberAndAgencyNumber(anyInt(), anyInt());
        verify(findPixKeyGateway, times(1)).findAllByAccountNumberAndAgencyNumber(anyInt(), anyInt());
        verify(savePixKeyGateway, times(1)).save(any(PixKey.class));
    }

    @Test
    void shouldAccept5thPixKeyForPf() {
        List<PixKey> keys = new ArrayList<>(markJhonesPixKeys.subList(0, 4));
        when(findPixKeyGateway.findByPixValue(anyString())).thenReturn(null);
        when(countPixKeys.countByAccountNumberAndAgencyNumber(anyInt(), anyInt())).thenReturn(4);
        when(findPixKeyGateway.findAllByAccountNumberAndAgencyNumber(anyInt(), anyInt())).thenReturn(keys);
        when(savePixKeyGateway.save(any(PixKey.class))).thenReturn(pixKey1_5);
        when(legalTypeFactory.resolve(any())).thenReturn(new CpfLegalType());

        PixKey result = useCase.registre(pixKey1_5);

        assertNotNull(result);
        assertEquals(pixKey1_5.getValue(), result.getValue());

        verify(savePixKeyGateway, times(1)).save(any(PixKey.class));
    }

}
