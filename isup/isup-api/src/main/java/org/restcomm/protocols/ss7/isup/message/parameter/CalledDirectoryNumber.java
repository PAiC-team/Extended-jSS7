package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:11:12:56 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface CalledDirectoryNumber extends ISUPParameter, NAINumber {
    // FIXME: whats the code ?
    int _PARAMETER_CODE = 0x7D;

    /**
     * See Q.763 Numbering plan indicator : ISDN (Telephony) numbering plan (ITU-T Recommendation E.164)
     */
    int _NPI_ISDN_NP = 1;

    /**
     * See Q.763 Internal network number indicator (INN) : reserved
     */
    int _INNI_RESERVED = 0;

    /**
     * See Q.763 Internal network number indicator (INN) : routing to internal network number not allowed
     */
    int _INNI_RTINNNA = 1;

    int getNumberingPlanIndicator();

    void setNumberingPlanIndicator(int numberingPlanIndicator);

    int getInternalNetworkNumberIndicator();

    void setInternalNetworkNumberIndicator(int internalNetworkNumberIndicator);
}
