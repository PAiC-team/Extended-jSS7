
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.api.MAPDialog;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.oam.ActivateTraceModeRequest_Oam;
import org.restcomm.protocols.ss7.map.api.service.oam.MAPDialogOam;
import org.restcomm.protocols.ss7.map.api.service.oam.MDTConfiguration;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceDepthList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceEventList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceInterfaceList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceNETypeList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceReference;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceReference2;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceType;

/**
*
* @author sergey vetyutnev
*
*/
public class ActivateTraceModeRequestImpl_Oam extends ActivateTraceModeRequestImpl_Base implements ActivateTraceModeRequest_Oam {

    public ActivateTraceModeRequestImpl_Oam() {
        super();
    }

    public ActivateTraceModeRequestImpl_Oam(IMSI imsi, TraceReference traceReference, TraceType traceType, AddressString omcId,
            MAPExtensionContainer extensionContainer, TraceReference2 traceReference2, TraceDepthList traceDepthList, TraceNETypeList traceNeTypeList,
            TraceInterfaceList traceInterfaceList, TraceEventList traceEventList, GSNAddress traceCollectionEntity, MDTConfiguration mdtConfiguration) {

        super(imsi, traceReference, traceType, omcId, extensionContainer, traceReference2, traceDepthList, traceNeTypeList, traceInterfaceList, traceEventList,
                traceCollectionEntity, mdtConfiguration);
    }

    public MAPDialogOam getMAPDialog() {
        MAPDialog mapDialog = super.getMAPDialog();
        return (MAPDialogOam) mapDialog;
    }

}
