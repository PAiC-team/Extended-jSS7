
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OfferedCamel4Functionalities;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SupportedCamelPhases;

/**
 *
 InitiateCallAttemptRes {PARAMETERS-BOUND : bound} ::= SEQUENCE {
   supportedCamelPhases          [0] SupportedCamelPhases OPTIONAL,
   offeredCamel4Functionalities  [1] OfferedCamel4Functionalities OPTIONAL,
   extensions                    [2] Extensions {bound} OPTIONAL,
   ...
}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface InitiateCallAttemptResponse extends CircuitSwitchedCallMessage {

    SupportedCamelPhases getSupportedCamelPhases();

    OfferedCamel4Functionalities getOfferedCamel4Functionalities();

    CAPExtensions getExtensions();

    boolean getReleaseCallArgExtensionAllowed();

}
