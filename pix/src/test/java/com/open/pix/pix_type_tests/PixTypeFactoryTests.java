package com.open.pix.pix_type_tests;

import com.open.pix.domain.types.pixTypes.*;
import com.open.pix.domain.exceptions.PixTypeException;
import com.open.pix.domain.factory.PixTypeFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class PixTypeFactoryTests {

    private final PixTypeFactory pixTypeFactory = new PixTypeFactory(Map.of(
            "cpf", CpfPixType::new,
            "cnpj", CnpjPixType::new,
            "email", EmailPixType::new,
            "celular", PhonePixType::new,
            "aleatorio", RandomPixType::new
    ));

    @Test
    void mustCreateCpfPixType() {
        Assertions.assertEquals(CpfPixType.class, pixTypeFactory.newPixType("cpf", "77525208026").getClass());
    }

    @Test
    void mustCreateCnpjPixType() {
        Assertions.assertEquals(CnpjPixType.class, pixTypeFactory.newPixType("cnpj", "54197972000147").getClass());
    }

    @Test
    void mustCreateEmailPixType() {
        Assertions.assertEquals(EmailPixType.class, pixTypeFactory.newPixType("email", "email@gmail.com").getClass());
    }

    @Test
    void mustCreatePhonePixType() {
        Assertions.assertEquals(PhonePixType.class, pixTypeFactory.newPixType("celular", "+55 11 123456789").getClass());
    }

    @Test
    void mustCreateRandomPixType() {
        Assertions.assertEquals(RandomPixType.class,
                pixTypeFactory.newPixType("aleatorio", "dcta478j196l03fmt6gh4298er7845m2").getClass());
    }

    @Test
    void mustThrowOnUnknownPixType() {
        Assertions.assertThrows(PixTypeException.class,
                () -> pixTypeFactory.newPixType("   ", "123456789").getClass());
    }
}
