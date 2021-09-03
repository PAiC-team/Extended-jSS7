/**
 * Start time:10:53:42 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:10:53:42 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public interface AccessDeliveryInformation extends ISUPParameter {
    /**
     * See Q.763 3.2 Access delivery indicator: set-up message generated
     */
    int _SETUP_MESSAGE = 0;

    /**
     * See Q.763 3.2 Access delivery indicator:no set-up message generated
     */
    int _NO_SETUP_MESSAGE = 1;

    int _PARAMETER_CODE = 0x2E;

    int getAccessDeliveryIndicator();

    void setAccessDeliveryIndicator(int accessDeliveryIndicator);
}
