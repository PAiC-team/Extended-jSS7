
package org.restcomm.protocols.ss7.sccp.parameter;

import org.restcomm.protocols.ss7.indicator.AddressIndicator;

/**
 * @author baranowb
 *
 */
public interface SccpAddress extends Parameter {

    // calling party address
    int CGA_PARAMETER_CODE = 0x4;
    // called party address
    int CDA_PARAMETER_CODE = 0x3;

    boolean isTranslated();

    void setTranslated(boolean translated);

    AddressIndicator getAddressIndicator();

    int getSignalingPointCode();

    int getSubsystemNumber();

    GlobalTitle getGlobalTitle();
}
