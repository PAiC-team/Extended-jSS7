package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
*
<code>
DpSpecificCriteria {PARAMETERS-BOUND : bound} ::= CHOICE {
  numberOfDigits     [0] NumberOfDigits,
  applicationTimer   [1] ApplicationTimer,
  midCallControlInfo [2] MidCallControlInfo {bound}
}
-- The SCF may specify the number of digits to be collected by the SSF
-- for the EventReportBCSMEvent event.
-- When all digits are collected, the SSF reports the event to the SCF.
-- The SCF may set a timer in the SSF for the No Answer event. If the user does not answer the call
-- within the allotted time, the SSF reports the event to the SCF

NumberOfDigits ::= INTEGER (1..255)
-- Indicates the number of digits to be collected

ApplicationTimer ::=INTEGER (0..2047)
-- Used by the SCF to set a timer in the SSF. The timer is in seconds.
</code>
*
*
* @author sergey vetyutnev
*
*/
public interface DpSpecificCriteria extends Serializable {

    Integer getNumberOfDigits();

    Integer getApplicationTimer();

    MidCallControlInfo getMidCallControlInfo();

}
