
package org.restcomm.protocols.ss7.map.service.mobility.oam;

import org.restcomm.protocols.ss7.map.api.MAPDialog;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MAPDialogMobility;
import org.restcomm.protocols.ss7.map.api.service.mobility.oam.ActivateTraceModeRequest_Mobility;
import org.restcomm.protocols.ss7.map.api.service.oam.MDTConfiguration;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceDepthList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceEventList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceInterfaceList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceNETypeList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceReference;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceReference2;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceType;
import org.restcomm.protocols.ss7.map.service.oam.ActivateTraceModeRequestImpl_Base;

/**
*
* @author sergey vetyutnev
*
*/
public class ActivateTraceModeRequestImpl_Mobility extends ActivateTraceModeRequestImpl_Base implements ActivateTraceModeRequest_Mobility {

    public ActivateTraceModeRequestImpl_Mobility() {
        super();
    }

    public ActivateTraceModeRequestImpl_Mobility(IMSI imsi, TraceReference traceReference, TraceType traceType, AddressString omcId,
            MAPExtensionContainer extensionContainer, TraceReference2 traceReference2, TraceDepthList traceDepthList, TraceNETypeList traceNeTypeList,
            TraceInterfaceList traceInterfaceList, TraceEventList traceEventList, GSNAddress traceCollectionEntity, MDTConfiguration mdtConfiguration) {

        super(imsi, traceReference, traceType, omcId, extensionContainer, traceReference2, traceDepthList, traceNeTypeList, traceInterfaceList, traceEventList,
                traceCollectionEntity, mdtConfiguration);
    }

    public MAPDialogMobility getMAPDialog() {
        MAPDialog mapDialog = super.getMAPDialog();
        return (MAPDialogMobility) mapDialog;
    }

}
