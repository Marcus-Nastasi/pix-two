package com.open.pix.pix_type_tests;

import com.open.pix.domain.types.pixTypes.*;
import com.open.pix.domain.exceptions.PixTypeException;
import com.open.pix.domain.factory.PixTypeFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public final class PixTypeFactoryTests {

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
        var pixType = pixTypeFactory.newPixType("cpf", "77525208026");
        Assertions.assertEquals(CpfPixType.class, pixType.getClass());
        Assertions.assertEquals("77525208026", pixType.value());
    }

    @Test
    void mustCreateCnpjPixType() {
        Assertions.assertEquals(CnpjPixType.class, pixTypeFactory.newPixType("cnpj", "54197972000147").getClass());
        var pixType = pixTypeFactory.newPixType("cnpj", "54197972000147");
        Assertions.assertEquals(CnpjPixType.class, pixType.getClass());
        Assertions.assertEquals("54197972000147", pixType.value());
    }

    @Test
    void mustCreateEmailPixType() {
        Assertions.assertEquals(EmailPixType.class, pixTypeFactory.newPixType("email", "email@gmail.com").getClass());
        var pixType = pixTypeFactory.newPixType("email", "email@gmail.com");
        Assertions.assertEquals(EmailPixType.class, pixType.getClass());
        Assertions.assertEquals("email@gmail.com", pixType.value());
    }

    @Test
    void mustCreatePhonePixType() {
        Assertions.assertEquals(PhonePixType.class, pixTypeFactory.newPixType("celular", "+55 11 123456789").getClass());
        var pixType = pixTypeFactory.newPixType("celular", "+55 11 123456789");
        Assertions.assertEquals(PhonePixType.class, pixType.getClass());
        Assertions.assertEquals("+55 11 123456789", pixType.value());
    }

    @Test
    void mustCreateRandomPixType() {
        Assertions.assertEquals(RandomPixType.class,
                pixTypeFactory.newPixType("aleatorio", "dcta478j196l03fmt6gh4298er7845m2").getClass());
        var pixType = pixTypeFactory.newPixType("aleatorio", "dcta478j196l03fmt6gh4298er7845m2");

        Assertions.assertEquals(RandomPixType.class, pixType.getClass());
        Assertions.assertEquals("dcta478j196l03fmt6gh4298er7845m2", pixType.value());
    }

    @Test
    void mustThrowOnUnknownPixType() {
        Assertions.assertThrows(PixTypeException.class, () -> {
            pixTypeFactory.newPixType("   ", "123456789").getClass();
        });
        PixTypeException exception = Assertions.assertThrows(PixTypeException.class, () -> {
            pixTypeFactory.newPixType("   ", "123456789");
        });
        Assertions.assertTrue(exception.getMessage().contains("Invalid type of pix key:"));
    }

    @Test
    void mustThrowOnNullPixType() {
        PixTypeException exception = Assertions.assertThrows(PixTypeException.class, () -> {
            pixTypeFactory.newPixType(null, "qualquerValor");
        });
        Assertions.assertTrue(exception.getMessage().contains("Pix type cannot be null"));
    }
}
