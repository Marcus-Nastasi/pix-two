package com.open.pix.application.gateway;

@FunctionalInterface
public interface CountPixKeysGateway {

    /**
     * Should count the number of active pix keys based on agency and account.
     * @param accountNumber the account number.
     * @param agencyNumber the agency number.
     * @return an int number of pix key found.
     */
    int countByAccountNumberAndAgencyNumber(Integer accountNumber, Integer agencyNumber);
}
