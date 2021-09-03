package org.restcomm.protocols.ss7.m3ua.impl;

import org.restcomm.protocols.ss7.m3ua.ErrorRetryAction;

public class ErrorRetryActionImpl implements ErrorRetryAction {
    private final int retryCount ;
    private final String name;
    private final int errorCode;


    public ErrorRetryActionImpl(int errorCode, String name,  int retryCount) {
        this.retryCount = retryCount;
        this.name = name;
        this.errorCode = errorCode;
    }

    @Override
    public int getRetryCount() {
        return this.retryCount;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }
}
