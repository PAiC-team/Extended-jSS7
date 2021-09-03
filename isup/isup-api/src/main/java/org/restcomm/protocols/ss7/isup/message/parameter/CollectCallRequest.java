package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:12:27:28 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface CollectCallRequest extends ISUPParameter {
    int _PARAMETER_CODE = 0x79;

    /**
     * See Q.763 3.81 Collect call request indicator : no indication
     */
    boolean _CCRI_NO_INDICATION = false;
    /**
     * See Q.763 3.81 Collect call request indicator : collect call requested
     */
    boolean _CCRI_CCR = true;

    boolean isCollectCallRequested();

    void setCollectCallRequested(boolean collectCallRequested);
}
