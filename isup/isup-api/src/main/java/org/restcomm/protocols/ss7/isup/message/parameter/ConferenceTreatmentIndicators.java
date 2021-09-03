package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:12:28:40 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface ConferenceTreatmentIndicators extends ISUPParameter {
    int _PARAMETER_CODE = 0x72;

    /**
     * See Q.763 3.76 Conference acceptance indicator (Note) : no indication
     */
    int _CAI_NO_INDICATION = 0;

    /**
     * See Q.763 3.76 Conference acceptance indicator (Note) : accept conference request
     */
    int _CAI_ACR = 1;

    /**
     * See Q.763 3.76 Conference acceptance indicator (Note) : reject conference request
     */
    int _CAI_RCR = 2;

    byte[] getConferenceAcceptance();

    void setConferenceAcceptance(byte[] conferenceAcceptance);

    int getConferenceTreatmentIndicator(byte b);
}
