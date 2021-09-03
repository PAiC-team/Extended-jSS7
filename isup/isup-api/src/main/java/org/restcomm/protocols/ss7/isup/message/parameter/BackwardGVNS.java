package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:11:04:41 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface BackwardGVNS extends ISUPParameter {
    int _PARAMETER_CODE = 0x4D;

    byte[] getBackwardGVNS();

    void setBackwardGVNS(byte[] backwardGVNS);
}
