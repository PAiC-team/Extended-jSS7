
package org.restcomm.protocols.ss7.map.api.service.oam;

import org.restcomm.protocols.ss7.isup.message.parameter.CallReference;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;

/**
 *
 MAP V1: TraceSubscriberActivity ::= OPERATION--Timer s ARGUMENT traceSubscriberActivityArg TraceSubscriberActivityArg
 *
 * TraceSubscriberActivityArg ::= SEQUENCE { imsi [0] IMSI OPTIONAL, traceReference [1] TraceReference, traceType [2] TraceType,
 * omc-Id [3] AddressString OPTIONAL, callReference [4] CallReference OPTIONAL}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TraceSubscriberActivityRequest extends OamMessage {

    IMSI getImsi();

    TraceReference getTraceReference();

    TraceType getTraceType();

    AddressString getOmcId();

    CallReference getCallReference();

}
