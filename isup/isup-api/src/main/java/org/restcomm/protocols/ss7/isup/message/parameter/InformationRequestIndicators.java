package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:17:28 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface InformationRequestIndicators extends ISUPParameter {
    int _PARAMETER_CODE = 0x0E;

    /**
     * Flag that indicates that information is requested
     */
    boolean _INDICATOR_REQUESTED = true;

    /**
     * Flag that indicates that information is not requested
     */
    boolean _INDICATOR_NOT_REQUESTED = false;

    boolean isCallingPartAddressRequestIndicator();

    void setCallingPartAddressRequestIndicator(boolean callingPartAddressRequestIndicator);

    boolean isHoldingIndicator();

    void setHoldingIndicator(boolean holdingIndicator);

    boolean isCallingPartysCategoryRequestIndicator();

    void setCallingPartysCategoryRequestIndicator(boolean callingpartysCategoryRequestIndicator);

    boolean isChargeInformationRequestIndicator();

    void setChargeInformationRequestIndicator(boolean chargeInformationRequestIndicator);

    boolean isMaliciousCallIdentificationRequestIndicator();

    void setMaliciousCallIdentificationRequestIndicator(boolean maliciousCallIdentificationRequestIndicator);

    int getReserved();

    void setReserved(int reserved);

}
