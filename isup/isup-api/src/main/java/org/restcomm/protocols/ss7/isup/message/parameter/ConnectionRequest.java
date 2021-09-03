package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:12:32:51 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface ConnectionRequest extends ISUPParameter {
    int _PARAMETER_CODE = 0x0D;

    int getLocalReference();

    void setLocalReference(int localReference);

    int getSignalingPointCode();

    void setSignalingPointCode(int signalingPointCode);

    int getProtocolClass();

    void setProtocolClass(int protocolClass);

    int getCredit();

    void setCredit(int credit);
}
