package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:12:25:27 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface ClosedUserGroupInterlockCode extends ISUPParameter {
    int _PARAMETER_CODE = 0x1A;

    byte[] getNiDigits();

    void setNiDigits(byte[] niDigits);

    int getBinaryCode();

    void setBinaryCode(int binaryCode);
}
