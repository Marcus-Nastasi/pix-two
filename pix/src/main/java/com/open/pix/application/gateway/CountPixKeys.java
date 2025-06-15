package com.open.pix.application.gateway;

@FunctionalInterface
public interface CountPixKeys {

    int countByAccountNumberAndAgencyNumber(Integer accountNumber, Integer agencyNumber);
}
