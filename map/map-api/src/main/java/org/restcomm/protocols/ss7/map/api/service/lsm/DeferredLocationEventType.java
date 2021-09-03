package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

/**
 * DeferredLocationEventType ::= BIT STRING { msAvailable (0) , enteringIntoArea (1), leavingFromArea (2), beingInsideArea (3) }
 * (SIZE (1..16)) -- beingInsideArea is always treated as oneTimeEvent regardless of the possible value -- of occurrenceInfo
 * inside areaEventInfo. -- exception handling: -- a ProvideSubscriberLocation-Arg containing other values than listed above in
 * -- DeferredLocationEventType shall be rejected by the receiver with a return error cause of -- unexpected data value.
 *
 * @author amit bhayani
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public interface DeferredLocationEventType extends Serializable {

    boolean getMsAvailable();

    boolean getEnteringIntoArea();

    boolean getLeavingFromArea();

    boolean getBeingInsideArea();

    boolean getPeriodicLDR();
}
