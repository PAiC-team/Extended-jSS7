package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:13:40 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface HopCounter extends ISUPParameter {
    int _PARAMETER_CODE = 0x3D;

    int getHopCounter();

    void setHopCounter(int hopCounter);

}
