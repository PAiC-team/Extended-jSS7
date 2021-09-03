package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:32:08 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface NetworkManagementControls extends ISUPParameter {
    int _PARAMETER_CODE = 0x5B;

    boolean isTARControlEnabled(byte b);

    byte createTAREnabledByte(boolean enabled);

    byte[] getNetworkManagementControls();

    void setNetworkManagementControls(byte[] networkManagementControls) throws IllegalArgumentException;

}
