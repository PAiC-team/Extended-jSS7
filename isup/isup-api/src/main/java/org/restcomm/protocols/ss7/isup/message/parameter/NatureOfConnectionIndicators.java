package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:31:04 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface NatureOfConnectionIndicators extends ISUPParameter {
    int _PARAMETER_CODE = 0x06;

    /**
     * See Q.763 3.35 Echo control device indicator : outgoing echo control device included
     */
    boolean _ECDI_INCLUDED = true;

    /**
     * See Q.763 3.35 Echo control device indicator : outgoing echo control device not included
     */
    boolean _ECDI_NOT_INCLUDED = false;

    /**
     * See Q.763 3.35 Satellite indicator : no satellite circuit in the connection
     */
    int _SI_NO_SATELLITE = 0;

    /**
     * See Q.763 3.35 Satellite indicator : one satellite circuit in the connection
     */
    int _SI_ONE_SATELLITE = 1;

    /**
     * See Q.763 3.35 Satellite indicator : two satellite circuits in the connection
     */
    int _SI_TWO_SATELLITE = 2;

    /**
     * See Q.763 3.35 Continuity check indicator
     */
    int _CCI_NOT_REQUIRED = 0;
    /**
     * See Q.763 3.35 Continuity check indicator
     */
    int _CCI_REQUIRED_ON_THIS_CIRCUIT = 1;

    /**
     * See Q.763 3.35 Continuity check indicator
     */
    int _CCI_PERFORMED_ON_PREVIOUS_CIRCUIT = 2;

    int getSatelliteIndicator();

    void setSatelliteIndicator(int satelliteIndicator);

    int getContinuityCheckIndicator();

    void setContinuityCheckIndicator(int continuityCheckIndicator);

    boolean isEchoControlDeviceIndicator();

    void setEchoControlDeviceIndicator(boolean echoControlDeviceIndicator);
}
