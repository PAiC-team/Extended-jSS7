
package org.restcomm.protocols.ss7.map.api.service.oam;

import org.restcomm.protocols.ss7.map.api.MAPMessage;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
<code>
MAP V1-2-3:

MAP V3:
activateTraceMode OPERATION ::= {
  --Timer m
  ARGUMENT ActivateTraceModeArg
  RESULT ActivateTraceModeRes
  -- optional
  ERRORS { systemFailure | dataMissing | unexpectedDataValue | facilityNotSupported | unidentifiedSubscriber | tracingBufferFull}
  CODE local:50
}

MAP V2:
ActivateTraceMode ::= OPERATION
  --Timer m
  ARGUMENT activateTraceModeArg ActivateTraceModeArg
  RESULT
  ERRORS { SystemFailure, DataMissing, UnexpectedDataValue, FacilityNotSupported, UnidentifiedSubscriber, TracingBufferFull}

MAP V3:
ActivateTraceModeArg ::= SEQUENCE {
  imsi                 [0] IMSI OPTIONAL,
  traceReference       [1] TraceReference,
  traceType            [2] TraceType,
  omc-Id               [3] AddressString OPTIONAL,
  extensionContainer   [4] ExtensionContainer OPTIONAL,
  ...,
  traceReference2      [5] TraceReference2 OPTIONAL,
  traceDepthList       [6] TraceDepthList OPTIONAL,
  traceNE-TypeList     [7] TraceNE-TypeList OPTIONAL,
  traceInterfaceList   [8] TraceInterfaceList OPTIONAL,
  traceEventList       [9] TraceEventList OPTIONAL,
  traceCollectionEntity [10] GSN-Address OPTIONAL,
  mdt-Configuration    [11] MDT-Configuration OPTIONAL
}

MAP V2:
ActivateTraceModeArg ::= SEQUENCE {
  imsi                 [0] IMSI OPTIONAL,
  traceReference       [1] TraceReference,
  traceType            [2] TraceType,
  omc-Id               [3] AddressString OPTIONAL,
  ...
}
</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface ActivateTraceModeRequest_Base extends MAPMessage {

    IMSI getImsi();

    TraceReference getTraceReference();

    TraceType getTraceType();

    AddressString getOmcId();

    MAPExtensionContainer getExtensionContainer();

    TraceReference2 getTraceReference2();

    TraceDepthList getTraceDepthList();

    TraceNETypeList getTraceNeTypeList();

    TraceInterfaceList getTraceInterfaceList();

    TraceEventList getTraceEventList();

    GSNAddress getTraceCollectionEntity();

    MDTConfiguration getMdtConfiguration();

}
