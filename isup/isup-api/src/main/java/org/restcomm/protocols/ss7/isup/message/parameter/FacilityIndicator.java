package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:12:48:26 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface FacilityIndicator extends ISUPParameter {
    int _PARAMETER_CODE = 0x18;

    /**
     * See Q.763 3.22 facility indicator user-to-user service
     */
    byte _FACILITY_INDICATOR_UTUS = 2;

    byte getFacilityIndicator();

    void setFacilityIndicator(byte facilityIndicator);
}
