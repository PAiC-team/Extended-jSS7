package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:54:36 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface RedirectCapability extends ISUPParameter {

    int _PARAMETER_CODE = 0x4E;

    /**
     * See Q.763 3.96 Redirect possible indicator : not used
     */
    int _RPI_NOT_USED = 0;
    /**
     * See Q.763 3.96 Redirect possible indicator : redirect possible before ACM use)
     */
    int _RPI_RPB_ACM = 1;
    /**
     * See Q.763 3.96 Redirect possible indicator : redirect possible before ANM
     */
    int _RPI_RPB_ANM = 2;
    /**
     * See Q.763 3.96 Redirect possible indicator : redirect possible at any time during the call
     */
    int _RPI_RPANTDC = 3;

    byte[] getCapabilities();

    void setCapabilities(byte[] capabilities);

    int getCapability(byte b);
}
