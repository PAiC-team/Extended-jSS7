package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:33:28 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface NetworkRoutingNumber extends Number, ISUPParameter {
    int _PARAMETER_CODE = 0x84;

    /**
     * See Q.763 3.90 Numbering plan indicator : ISDN (Telephony) numbering plan (ITU-T Recommendation E.164)
     */
    int _NPI_ISDN_NP = 1;

    /**
     * See Q.763 3.90 Nature of address indicator : network routing number in national (significant) number format (national
     * use)
     */
    int _NAI_NRNI_NATIONAL_NF = 1;

    /**
     * See Q.763 3.90 Nature of address indicator : network routing number in network specific number format (national use)
     */
    int _NAI_NRNI_NETWORK_SNF = 2;

    int getNumberingPlanIndicator();

    void setNumberingPlanIndicator(int numberingPlanIndicator);

    int getNatureOfAddressIndicator();

    void setNatureOfAddressIndicator(int natureOfAddressIndicator);
}
