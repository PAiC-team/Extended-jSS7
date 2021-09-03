package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:24:32 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface LoopPreventionIndicators extends ISUPParameter {
    int _PARAMETER_CODE = 0x44;

    boolean _TYPE_REQUEST = false;
    boolean _TYPE_RESPONSE = true;

    /**
     * See Q.763 3.67 Response indicator : insufficient information (note)
     */
    int _RI_INS_INFO = 0;

    /**
     * See Q.763 3.67 Response indicator : no loop exists
     */
    int _RI_NO_LOOP_E = 1;

    /**
     * See Q.763 3.67 Response indicator : simultaneous transfer
     */
    int _RI_SIM_TRANS = 2;

    boolean isResponse();

    void setResponse(boolean response);

    int getResponseIndicator();

    void setResponseIndicator(int responseIndicator);

}
