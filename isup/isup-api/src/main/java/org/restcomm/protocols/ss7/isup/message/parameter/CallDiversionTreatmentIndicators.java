package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:11:09:03 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface CallDiversionTreatmentIndicators extends ISUPParameter {
    int _PARAMETER_CODE = 0x6E;
    /**
     * See Q.763 3.72 Call to be diverted indicator : no indication
     */
    int _NO_INDICATION = 0;

    /**
     * See Q.763 3.72 Call to be diverted indicator : call diversion allowed
     */
    int _CD_ALLOWED = 1;

    /**
     * See Q.763 3.72 Call to be diverted indicator : call diversion not allowed
     */
    int _CD_NOT_ALLOWED = 2;

    byte[] getCallDivertedIndicators();

    void setCallDivertedIndicators(byte[] callDivertedIndicators);

    int getDiversionIndicator(byte b);
}
