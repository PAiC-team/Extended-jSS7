
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
 TracePropagationList ::= SEQUENCE { traceReference [0] TraceReference OPTIONAL, traceType [1] TraceType OPTIONAL,
 * traceReference2 [2] TraceReference2 OPTIONAL, traceRecordingSessionReference [3] TraceRecordingSessionReference OPTIONAL,
 * rnc-TraceDepth [4] TraceDepth OPTIONAL, rnc-InterfaceList [5] RNC-InterfaceList OPTIONAL, msc-s-TraceDepth [6] TraceDepth
 * OPTIONAL, msc-s-InterfaceList [7] MSC-S-InterfaceList OPTIONAL, msc-s-EventList [8] MSC-S-EventList OPTIONAL, mgw-TraceDepth
 * [9] TraceDepth OPTIONAL, mgw-InterfaceList [10] MGW-InterfaceList OPTIONAL, mgw-EventList [11] MGW-EventList OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TracePropagationList extends Serializable {

    TraceReference getTraceReference();

    TraceType getTraceType();

    TraceReference2 getTraceReference2();

    TraceRecordingSessionReference getTraceRecordingSessionReference();

    TraceDepth getTraceDepth();

    RNCInterfaceList getRNCInterfaceList();

    TraceDepth getMscSTraceDepth();

    MSCSInterfaceList getMscSInterfaceList();

    MSCSEventList getMSCSEventList();

    TraceDepth getMgwTraceDepth();

    MGWInterfaceList getMGWInterfaceList();

    MGWEventList getMGWEventList();

}
