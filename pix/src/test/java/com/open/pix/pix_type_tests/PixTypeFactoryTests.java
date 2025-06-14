package com.open.pix.pix_type_tests;

import com.open.pix.domain.enums.pixTypes.*;
import com.open.pix.domain.exceptions.PixTypeException;
import com.open.pix.domain.factory.PixTypeFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PixTypeFactoryTests {

    @Test
    void mustCreateCpfPixType() {
        Assertions.assertEquals(CpfPixType.class, PixTypeFactory.newPixType("cpf", "77525208026").getClass());
    }

    @Test
    void mustCreateCnpjPixType() {
        Assertions.assertEquals(CnpjPixType.class, PixTypeFactory.newPixType("cnpj", "54197972000147").getClass());
    }

    @Test
    void mustCreateEmailPixType() {
        Assertions.assertEquals(EmailPixType.class, PixTypeFactory.newPixType("email", "email@gmail.com").getClass());
    }

    @Test
    void mustCreatePhonePixType() {
        Assertions.assertEquals(PhonePixType.class, PixTypeFactory.newPixType("celular", "+55 11 123456789").getClass());
    }

    @Test
    void mustCreateRandomPixType() {
        Assertions.assertEquals(RandomPixType.class,
                PixTypeFactory.newPixType("aleatorio", "dcta478j196l03fmt6gh4298er7845m2").getClass());
    }

    @Test
    void mustThrowOnUnknownPixType() {
        Assertions.assertThrows(PixTypeException.class,
                () -> PixTypeFactory.newPixType("   ", "123456789").getClass());
    }
}
