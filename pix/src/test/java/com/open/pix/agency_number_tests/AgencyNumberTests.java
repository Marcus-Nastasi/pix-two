package com.open.pix.agency_number_tests;

import com.open.pix.domain.types.AgencyNumber;
import com.open.pix.domain.exceptions.AgencyNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class AgencyNumberTests {

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

    @Test
    void mustAcceptValidFourDigitAgencyNumber() {
        AgencyNumber agency = new AgencyNumber(1234);
        assertEquals(1234, agency.value());
    }

    @Test
    void mustAcceptValidOneDigitAgencyNumber() {
        AgencyNumber agency = new AgencyNumber(7);
        assertEquals(7, agency.value());
    }

    @Test
    void mustThrowWhenAccountNumberGreaterThanFourWithOf() {
        AgencyNumberException exception = assertThrows(AgencyNumberException.class, () -> {
            AgencyNumber.of(123456789);
        });
        assertEquals("Agency number must have less or equal than 4 digits", exception.getMessage());
    }

    @Test
    void mustThrowOnAccountNumberNullWithOf() {
        AgencyNumberException exception = assertThrows(AgencyNumberException.class, () -> {
            AgencyNumber.of(null);
        });
        assertEquals("Account number must be informed", exception.getMessage());
    }

    @Test
    void mustAcceptValidFourDigitAgencyNumberWithOf() {
        AgencyNumber agency = AgencyNumber.of(1234);
        assertEquals(1234, agency.value());
    }

    @Test
    void mustAcceptValidOneDigitAgencyNumberWithOf() {
        AgencyNumber agency = AgencyNumber.of(7);
        assertEquals(7, agency.value());
    }
}
