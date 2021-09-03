package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:14:24:19 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface UserToUserInformation extends ISUPParameter {
    // FIXME: fill this!
    int _PARAMETER_CODE = 0x20;

    byte[] getInformation();

    void setInformation(byte[] b);
}
