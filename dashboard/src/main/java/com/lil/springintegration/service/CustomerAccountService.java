package com.lil.springintegration.service;

import com.lil.springintegration.domain.AppSupportStatus;

public class CustomerAccountService {

    private static double mockPersistedCredit = 0.0;

    public static double getAccountCredit() { return mockPersistedCredit; }

    public static boolean isAccountCredit() { return mockPersistedCredit > 0; }

    public void creditCustomerAccount(Object payload) throws IllegalArgumentException {
        if (payload instanceof AppSupportStatus) {
            AppSupportStatus customerStatus = (AppSupportStatus) payload;
            if (customerStatus.getAccountCreditEarned() > 0) {
                // Simulates a back-end account credit
                mockPersistedCredit += customerStatus.getAccountCreditEarned();
            }
        } else {
            throw new IllegalArgumentException("Unknown data type has been received: " + payload.getClass());
        }
    }
}
