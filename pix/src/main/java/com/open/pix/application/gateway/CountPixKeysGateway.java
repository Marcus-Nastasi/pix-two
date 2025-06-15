package com.open.pix.application.gateway;

@FunctionalInterface
public interface CountPixKeysGateway {

    int countByAccountNumberAndAgencyNumber(Integer accountNumber, Integer agencyNumber);
}
