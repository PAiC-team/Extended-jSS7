package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:12:39:58 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface DisplayInformation extends ISUPParameter {
    int _PARAMETER_CODE = 0x73;

    byte[] getInfo();

    void setInfo(byte[] info) throws IllegalArgumentException;
}
