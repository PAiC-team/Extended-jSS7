
package org.restcomm.protocols.ss7.map.api.service.oam;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 MAP V1-2-3:
 *
 * MAP V3: activateTraceMode OPERATION ::= { --Timer m ARGUMENT ActivateTraceModeArg RESULT ActivateTraceModeRes -- optional
 * ERRORS { systemFailure | dataMissing | unexpectedDataValue | facilityNotSupported | unidentifiedSubscriber |
 * tracingBufferFull} CODE local:50 }
 *
 * MAP V2: DeactivateTraceMode ::= OPERATION--Timer m ARGUMENT deactivateTraceModeArg DeactivateTraceModeArg RESULT ERRORS {
 * SystemFailure, DataMissing, UnexpectedDataValue, FacilityNotSupported, UnidentifiedSubscriber}
 *
 * MAP V3: DeactivateTraceModeArg ::= SEQUENCE { imsi [0] IMSI OPTIONAL, traceReference [1] TraceReference, extensionContainer
 * [2] ExtensionContainer OPTIONAL, ..., traceReference2 [3] TraceReference2 OPTIONAL }
 *
 * MAP V2: DeactivateTraceModeArg ::= SEQUENCE { imsi[0] IMSI OPTIONAL, traceReference[1] TraceReference, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface DeactivateTraceModeRequest extends OamMessage {

    IMSI getImsi();

    TraceReference getTraceReference();

    MAPExtensionContainer getExtensionContainer();

    TraceReference2 getTraceReference2();

}
