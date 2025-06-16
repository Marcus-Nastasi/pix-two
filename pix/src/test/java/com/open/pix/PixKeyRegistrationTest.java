package com.open.pix;

import com.open.pix.application.exceptions.PixRegistreException;
import com.open.pix.application.gateway.CountPixKeysGateway;
import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.application.gateway.SavePixKeyGateway;
import com.open.pix.application.usecases.RegistrePixKeyUseCase;
import com.open.pix.domain.PixKey;
import com.open.pix.domain.factory.LegalTypeFactory;
import com.open.pix.domain.types.*;
import com.open.pix.domain.factory.PixTypeFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PixKeyRegistrationTest {

    @Spy
    @InjectMocks
    private RegistrePixKeyUseCase useCase;

    @Mock
    private SavePixKeyGateway savePixKeyGateway;

    @Mock
    private FindPixKeyGateway findPixKeyGateway;

    @Mock
    private CountPixKeysGateway countPixKeys;

    @Mock
    private LegalTypeFactory legalTypeFactory;

    private final PixKey pixKey1 = PixKey.registerNew(PixTypeFactory.newPixType("cpf", "72356804072"),
                                                      "72356804072",
                                                      new AccountType("corrente"),
                                                      new AgencyNumber(1234),
                                                      new AccountNumber(1234567),
                                                      "Mark",
                                                      "Jhones");

    private final PixKey pixKey1_2 = PixKey.registerNew(PixTypeFactory.newPixType("celular", "+55 13 123456789"),
            "+55 13 123456789",
            new AccountType("corrente"),
            new AgencyNumber(1234),
            new AccountNumber(1234567),
            "Mark",
            "Jhones");

    private final PixKey pixKey1_3 = PixKey.registerNew(PixTypeFactory.newPixType("email", "mark.jones@gmail.com"),
            "mark.jones@gmail.com",
            new AccountType("corrente"),
            new AgencyNumber(1234),
            new AccountNumber(1234567),
            "Mark",
            "Jhones");

    private final PixKey pixKey1_4 = PixKey.registerNew(PixTypeFactory.newPixType("aleatorio", "dcta478j196l03fmt6gh4298er7845m2"),
            "dcta478j196l03fmt6gh4298er7845m2",
            new AccountType("corrente"),
            new AgencyNumber(1234),
            new AccountNumber(1234567),
            "Mark",
            "Jhones");

    private final PixKey pixKey1_5 = PixKey.registerNew(PixTypeFactory.newPixType("aleatorio", "octa478j196l03fmt6gh4298er7845m2"),
            "octa478j196l03fmt6gh4298er7845m2",
            new AccountType("corrente"),
            new AgencyNumber(1234),
            new AccountNumber(1234567),
            "Mark",
            "Jhones");

    private final PixKey pixKey1_6 = PixKey.registerNew(PixTypeFactory.newPixType("aleatorio", "xyzs478j196l03fmt6gh4298er7845m2"),
            "xyzs478j196l03fmt6gh4298er7845m2",
            new AccountType("corrente"),
            new AgencyNumber(1234),
            new AccountNumber(1234567),
            "Mark",
            "Jhones");

    private final List<PixKey> markJhonesPixKeys = new ArrayList<>(List.of(pixKey1,
            pixKey1_2, pixKey1_3, pixKey1_4, pixKey1_5));

    private final PixKey duplicatedPixKey1 = PixKey.registerNew(PixTypeFactory.newPixType("cpf", "72356804072"),
                                                                "72356804072",
                                                                new AccountType("corrente"),
                                                                new AgencyNumber(8984),
                                                                new AccountNumber(739287),
                                                                "Bob",
                                                                "Kane");

    private final PixKey pixKey2 = PixKey.registerNew(PixTypeFactory.newPixType("cnpj", "56724791000157"),
                                                        "56724791000157",
                                                        new AccountType("poupança"),
                                                        new AgencyNumber(2345),
                                                        new AccountNumber(678901),
                                                        "Rosa",
                                                        "Linda");

    private final PixKey pixKey3 = PixKey.registerNew(PixTypeFactory.newPixType("email", "lucas.santos@example.com"),
                                                        "lucas.santos@example.com",
                                                        new AccountType("corrente"),
                                                        new AgencyNumber(1111),
                                                        new AccountNumber(1002003),
                                                        "Lucas",
                                                        "Santos");

    private final PixKey pixKey4 = PixKey.registerNew(PixTypeFactory.newPixType("celular", "+55 11 987654321"),
                                                        "+55 11 987654321",
                                                        new AccountType("poupança"),
                                                        new AgencyNumber(2222),
                                                        new AccountNumber(2003004),
                                                        "Ana",
                                                        "Costa");

    private final PixKey pixKey5 = PixKey.registerNew(PixTypeFactory.newPixType("aleatorio", "a7f3a16b9a3d4f89857a1cf8aef9c23d"),
            "a7f3a16b9a3d4f89857a1cf8aef9c23d",
            new AccountType("corrente"),
            new AgencyNumber(3333),
            new AccountNumber(3004005),
            "Pedro",
            "Moraes");

    private final PixKey pixKey6 = PixKey.registerNew(PixTypeFactory.newPixType("cpf", "56923651034"),
            "56923651034",
            new AccountType("poupança"),
            new AgencyNumber(4444),
            new AccountNumber(4005006),
            "Juliana",
            "Silva");

    private final PixKey pixKey7 = PixKey.registerNew(PixTypeFactory.newPixType("cnpj", "01244335000118"),
            "01244335000118",
            new AccountType("corrente"),
            new AgencyNumber(5555),
            new AccountNumber(5006007),
            "Empresa",
            "NovaTech");

    private final PixKey pixKey8 = PixKey.registerNew(PixTypeFactory.newPixType("email", "carla.monteiro@example.com"),
            "carla.monteiro@example.com",
            new AccountType("poupança"),
            new AgencyNumber(6666),
            new AccountNumber(6007008),
            "Carla",
            "Monteiro");

    private final PixKey pixKey9 = PixKey.registerNew(PixTypeFactory.newPixType("celular", "+55 21 998877665"),
            "+55 21 998877665",
            new AccountType("corrente"),
            new AgencyNumber(7777),
            new AccountNumber(7008009),
            "Fernando",
            "Lima");

    private final PixKey pixKey10 = PixKey.registerNew(PixTypeFactory.newPixType("aleatorio", "f9e1bdf2bd2640c39f2df84df32f5b5e"),
            "f9e1bdf2bd2640c39f2df84df32f5b5e",
            new AccountType("corrente"),
            new AgencyNumber(8888),
            new AccountNumber(8009000),
            "Beatriz",
            "Ferreira");

    private final PixKey pixKey11 = PixKey.registerNew(PixTypeFactory.newPixType("cpf", "90288348001"),
            "90288348001",
            new AccountType("poupança"),
            new AgencyNumber(9999),
            new AccountNumber(9000001),
            "Rodrigo",
            "Souza");

    private final PixKey pixKey12 = PixKey.registerNew(PixTypeFactory.newPixType("cnpj", "67435654000119"),
            "67435654000119",
            new AccountType("corrente"),
            new AgencyNumber(1212),
            new AccountNumber(12013002),
            "Global",
            "Logística");

    private final List<PixKey> pixKeyList = new ArrayList<>(List.of(pixKey1,
            pixKey2,
            pixKey3,
            pixKey4,
            pixKey5,
            pixKey6,
            pixKey7,
            pixKey8,
            pixKey9,
            pixKey10,
            pixKey11,
            pixKey12));

    @Test
    void shouldThrowOnDuplicatedPixKeyRegistre() {
        when(findPixKeyGateway.findByPixValue("72356804072")).thenReturn(pixKey1);

        assertThrows(PixRegistreException.class, () -> {
           useCase.registre(duplicatedPixKey1);
        });

        verify(findPixKeyGateway, times(1)).findByPixValue("72356804072");
    }

    @Test
    void shouldThrowOnPfTryingToRegistreMoreThanFiveKeys() {
        when(findPixKeyGateway.findByPixValue(anyString())).thenReturn(null);
        when(countPixKeys.countByAccountNumberAndAgencyNumber(
                pixKey1.getAccountNumber().value(), pixKey1.getAgencyNumber().value())).thenReturn(markJhonesPixKeys.size());
        when(findPixKeyGateway.findAllByAccountNumberAndAgencyNumber(
                pixKey1.getAccountNumber().value(), pixKey1.getAgencyNumber().value())).thenReturn(markJhonesPixKeys);
        when(legalTypeFactory.resolve(any())).thenReturn(new CpfLegalType());

        assertThrows(PixRegistreException.class, () -> {
            useCase.registre(pixKey1_6);
        });

        verify(findPixKeyGateway, times(1)).findByPixValue(anyString());
        verify(countPixKeys, times(1)).countByAccountNumberAndAgencyNumber(anyInt(), anyInt());
        verify(findPixKeyGateway, times(1)).findAllByAccountNumberAndAgencyNumber(anyInt(), anyInt());
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

        assertDoesNotThrow(() -> result);
        assertEquals(pixKey2.getId(), result.getId());
        assertEquals(LocalDateTime.class, result.getCreationDateTime().getClass());
        assertEquals(LocalDateTime.class, result.getUpdateDateTime().getClass());
        assertNull(result.getInactivationDateTime());

        verify(findPixKeyGateway, times(1)).findByPixValue(anyString());
        verify(countPixKeys, times(1)).countByAccountNumberAndAgencyNumber(anyInt(), anyInt());
        verify(findPixKeyGateway, times(1)).findAllByAccountNumberAndAgencyNumber(anyInt(), anyInt());
        verify(savePixKeyGateway, times(1)).save(any(PixKey.class));
    }
}
