package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:11:54:44 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface CallHistoryInformation extends ISUPParameter {
    int _PARAMETER_CODE = 0x2D;

    int getCallHistory();

    void setCallHistory(int callHistory);

}
