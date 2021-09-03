package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:34:38 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface NetworkSpecificFacility extends ISUPParameter {
    int _PARAMETER_CODE = 0x2F;

    /**
     * See Q.763 Type of network identification : national network identification
     */
    int _TNI_NNI = 0x02;

    /**
     * See Q.763 Type of network identification : reserved for international network identification
     */
    int _TNI_RESERVED_INI = 0x03;

    boolean isIncludeNetworkIdentification();

    int getLengthOfNetworkIdentification();

    int getTypeOfNetworkIdentification();

    void setTypeOfNetworkIdentification(byte typeOfNetworkIdentification);

    int getNetworkIdentificationPlan();

    void setNetworkIdentificationPlan(byte networkdIdentificationPlan);

    byte[] getNetworkIdentification();

    void setNetworkIdentification(byte[] networkdIdentification);

    byte[] getNetworkSpecificaFacilityIndicator();

    void setNetworkSpecificaFacilityIndicator(byte[] networkSpecificaFacilityIndicator);

}
