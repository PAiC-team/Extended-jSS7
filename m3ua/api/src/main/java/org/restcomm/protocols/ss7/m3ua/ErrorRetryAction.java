package org.restcomm.protocols.ss7.m3ua;

public interface ErrorRetryAction {
   int getRetryCount();
   String getName();
   int getErrorCode();
}
