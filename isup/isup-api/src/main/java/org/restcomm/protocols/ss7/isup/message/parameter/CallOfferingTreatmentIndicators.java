package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:11:59:38 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface CallOfferingTreatmentIndicators extends ISUPParameter {

    int _PARAMETER_CODE = 0x70;

    /**
     * See Q.763 3.74 Call to be offered indicator : no indication
     */
    int _CTBOI_NO_INDICATION = 0;

    /**
     * See Q.763 3.74 Call to be offered indicator : call offering not allowed
     */
    int _CTBOI_CONA = 1;

    /**
     * See Q.763 3.74 Call to be offered indicator : call offering allowed
     */
    int _CTBOI_COA = 2;

    byte[] getCallOfferingTreatmentIndicators();

    void setCallOfferingTreatmentIndicators(byte[] callOfferingTreatmentIndicators);

    int getCallOfferingIndicator(byte b);

}
