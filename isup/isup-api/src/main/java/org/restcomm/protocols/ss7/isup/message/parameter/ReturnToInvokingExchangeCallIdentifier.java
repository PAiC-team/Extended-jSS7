package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:14:02:34 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface ReturnToInvokingExchangeCallIdentifier extends Information {
    int getCallIdentity();

    void setCallIdentity(int callIdentity);

    int getSignalingPointCode();

    void setSignalingPointCode(int signalingPointCode);
}
