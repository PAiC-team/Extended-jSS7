package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:12:03:25 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface CallReference extends ISUPParameter {
    int _PARAMETER_CODE = 0x01;

    int getCallIdentity();

    void setCallIdentity(int callIdentity);

    int getSignalingPointCode();

    void setSignalingPointCode(int signalingPointCode);
}
