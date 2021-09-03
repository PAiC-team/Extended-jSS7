package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:01:51 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface TerminatingNetworkRoutingNumber extends Number, ISUPParameter {
    int _PARAMETER_CODE = 0;

    // FIXME: Add C defs

    /**
     * see Q.763 3.66 c4 : subscriber number (national use)
     */
    int _NAI_SN = 1;
    /**
     * see Q.763 3.66 c4 : unknown (national use)
     */
    int _NAI_UNKNOWN = 2;
    /**
     * see Q.763 3.66 c4 : national (significant) number
     */
    int _NAI_NATIONAL_SN = 3;
    /**
     * see Q.763 3.66 c4 : international number
     */
    int _NAI_IN = 4;
    /**
     * see Q.763 3.66 c4 : network specific number
     */
    int _NAI_NETWORK_SN = 5;

    /**
     * numbering plan indicator indicator value. See Q.763 - 3.9d
     */
    int _NPI_ISDN = 1;
    /**
     * numbering plan indicator indicator value. See Q.763 - 3.9d
     */
    int _NPI_DATA = 3;
    /**
     * numbering plan indicator indicator value. See Q.763 - 3.9d
     */
    int _NPI_TELEX = 4;

    int getNumberingPlanIndicator();

    void setNumberingPlanIndicator(int numberingPlanIndicator);

    int getNatureOfAddressIndicator();

    void setNatureOfAddressIndicator(int natureOfAddressIndicator);

    int getTnrnLengthIndicator();
}
