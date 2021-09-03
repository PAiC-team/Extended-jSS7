
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.isup.CauseCap;
import org.restcomm.protocols.ss7.cap.api.primitives.DateAndTime;

/**
 *
 RequestedInformationTypeList ::= SEQUENCE SIZE (1.. numOfInfoItems) OF RequestedInformationType
 *
 * RequestedInformation {PARAMETERS-BOUND : bound} ::= SEQUENCE { requestedInformationType [0] RequestedInformationType,
 * requestedInformationValue [1] RequestedInformationValue {bound}, ... }
 *
 * RequestedInformationValue {PARAMETERS-BOUND : bound} ::= CHOICE { callAttemptElapsedTimeValue [0] INTEGER (0..255),
 * callStopTimeValue [1] DateAndTime, callConnectedElapsedTimeValue [2] Integer4, releaseCauseValue [30] Cause {bound} } -- The
 * callAttemptElapsedTimeValue is specified in seconds. The unit for the -- callConnectedElapsedTimeValue is 100 milliseconds
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface RequestedInformation extends Serializable {

    RequestedInformationType getRequestedInformationType();

    Integer getCallAttemptElapsedTimeValue();

    DateAndTime getCallStopTimeValue();

    Integer getCallConnectedElapsedTimeValue();

    CauseCap getReleaseCauseValue();

}