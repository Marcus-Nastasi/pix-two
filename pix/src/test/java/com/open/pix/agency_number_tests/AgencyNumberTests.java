package com.open.pix.agency_number_tests;

import com.open.pix.domain.types.AgencyNumber;
import com.open.pix.domain.exceptions.AgencyNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AgencyNumberTests {

    @Test
    void mustThrowWhenAccountNumberGreaterThanFour() {
        AgencyNumberException exception = assertThrows(AgencyNumberException.class, () -> {
            new AgencyNumber(123456789);
        });
        assertEquals("Agency number must have less or equal than 4 digits", exception.getMessage());
    }

    @Test
    void mustThrowOnAccountNumberNull() {
        AgencyNumberException exception = assertThrows(AgencyNumberException.class, () -> {
            new AgencyNumber(null);
        });
        assertEquals("Account number must be informed", exception.getMessage());
    }
}
