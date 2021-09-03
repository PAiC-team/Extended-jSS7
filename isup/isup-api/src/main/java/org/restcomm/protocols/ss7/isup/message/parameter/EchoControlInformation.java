package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:12:44:34 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface EchoControlInformation extends ISUPParameter {
    int _PARAMETER_CODE = 0x37;

    /**
     * See Q.763 3.19 Outgoing echo control device information indicator : no information
     */
    int _OUTGOING_ECHO_CDII_NOINFO = 0;

    /**
     * See Q.763 3.19 Outgoing echo control device information indicator : outgoing echo control device not included and not
     * available
     */
    int _OUTGOING_ECHO_CDII_NINA = 1;

    /**
     * See Q.763 3.19 Outgoing echo control device information indicator : outgoing echo control device included
     */
    int _OUTGOING_ECHO_CDII_INCLUDED = 2;

    /**
     * See Q.763 3.19 Outgoing echo control device information indicator : outgoing echo control device not included but
     * available
     */
    int _OUTGOING_ECHO_CDII_NIA = 3;

    /**
     * See Q.763 3.19 Incoming echo control device information indicator : no information
     */
    int _INCOMING_ECHO_CDII_NOINFO = 0;

    /**
     * See Q.763 3.19 Incoming echo control device information indicator : incoming echo control device not included and not
     * available
     */
    int _INCOMING_ECHO_CDII_NINA = 1;

    /**
     * See Q.763 3.19 Incoming echo control device information indicator : incoming echo control device included
     */
    int _INCOMING_ECHO_CDII_INCLUDED = 2;

    /**
     * See Q.763 3.19 Incoming echo control device information indicator : incoming echo control device not included but
     * available
     */
    int _INCOMING_ECHO_CDII_NIA = 3;

    /**
     * See Q.763 3.19 Incoming echo control device request indicator : no information
     */
    int _INCOMING_ECHO_CDRI_NOINFO = 0;

    /**
     * See Q.763 3.19 Incoming echo control device request indicator : incoming echo control device activation request
     */
    int _INCOMING_ECHO_CDRI_AR = 1;

    /**
     * See Q.763 3.19 Incoming echo control device request indicator : incoming echo control device deactivation request (Note
     * 2)
     */
    int _INCOMING_ECHO_CDRI_DR = 2;

    /**
     * See Q.763 3.19 Outgoing echo control device request indicator : no information
     */
    int _OUTGOING_ECHO_CDRI_NOINFO = 0;

    /**
     * See Q.763 3.19 Outgoing echo control device request indicator : outgoing echo control device activation request
     */
    int _OUTGOING_ECHO_CDRI_AR = 1;

    /**
     * See Q.763 3.19 Outgoing echo control device request indicator : outgoing echo control device deactivation request (Note
     * 1)
     */
    int _OUTGOING_ECHO_CDRI_DR = 2;

    int getOutgoingEchoControlDeviceInformationIndicator();

    void setOutgoingEchoControlDeviceInformationIndicator(int outgoingEchoControlDeviceInformationIndicator);

    int getIncomingEchoControlDeviceInformationIndicator();

    void setIncomingEchoControlDeviceInformationIndicator(int incomingEchoControlDeviceInformationIndicator);

    int getOutgoingEchoControlDeviceInformationRequestIndicator();

    void setOutgoingEchoControlDeviceInformationRequestIndicator(int outgoingEchoControlDeviceInformationRequestIndicator);

    int getIncomingEchoControlDeviceInformationRequestIndicator();

    void setIncomingEchoControlDeviceInformationRequestIndicator(int incomingEchoControlDeviceInformationRequestIndicator);

}
