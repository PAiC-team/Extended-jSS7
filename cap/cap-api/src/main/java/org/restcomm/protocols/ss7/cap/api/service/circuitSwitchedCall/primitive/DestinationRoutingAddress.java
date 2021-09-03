
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.cap.api.isup.CalledPartyNumberCap;

/**
 *
 DestinationRoutingAddress {PARAMETERS-BOUND : bound} ::= SEQUENCE SIZE(1) OF CalledPartyNumber {bound} -- Indicates the
 * Called Party Number.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface DestinationRoutingAddress extends Serializable {

    ArrayList<CalledPartyNumberCap> getCalledPartyNumber();

}